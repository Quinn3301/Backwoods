package net.mcreator.thebackwoods.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class WealdFireFadeProcedure {

    // ---------- SETTINGS ----------
    private static final int CHECK_INTERVAL_TICKS = 12;
    private static final double DISAPPEAR_CHANCE = 0.60;
    private static final double WATCH_DOT_THRESHOLD = 0.70;
    private static final int MAX_TRACKED_FIRES = 256;
    private static final String NBT_LIST_KEY = "weald_tracked_fires";

    private static final ResourceKey<Level> THE_WEALD = ResourceKey.create(
        Registries.DIMENSION,
        ResourceLocation.parse("the_backwoods:the_petrified_weald")
    );
    // ------------------------------

    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        Entity e = event.getEntity();
        if (!(e instanceof Player player))
            return;

        Level level = player.level();
        if (level.isClientSide())
            return;

        if (!level.dimension().equals(THE_WEALD))
            return;

        BlockState placed = event.getPlacedBlock();
        if (!isFire(placed))
            return;

        addTrackedFire(player, event.getPos());
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player == null)
            return;

        Level level = player.level();
        if (level.isClientSide())
            return;

        if (!level.dimension().equals(THE_WEALD))
            return;

        if (player.tickCount % CHECK_INTERVAL_TICKS != 0)
            return;

        List<BlockPos> tracked = getTrackedFires(player);
        if (tracked.isEmpty())
            return;

        List<BlockPos> survivors = new ArrayList<>(tracked.size());

        for (BlockPos pos : tracked) {
            BlockState state = level.getBlockState(pos);

            // Stop tracking if the fire is gone (extinguished by player/rain)
            if (!isFire(state))
                continue;

            boolean watched = isPlayerWatchingPos(level, player, pos);

            // If not being watched, roll for disappearance
            if (!watched && Math.random() < DISAPPEAR_CHANCE) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                continue;
            }

            survivors.add(pos);
        }

        saveTrackedFires(player, survivors);
    }

    private static boolean isFire(BlockState state) {
        return state.getBlock() instanceof BaseFireBlock 
                || state.is(Blocks.FIRE) 
                || state.is(Blocks.SOUL_FIRE);
    }

    private static boolean isPlayerWatchingPos(LevelAccessor world, Player player, BlockPos pos) {
        Vec3 eye = player.getEyePosition();
        Vec3 target = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

        Vec3 toTarget = target.subtract(eye).normalize();
        double dot = player.getLookAngle().normalize().dot(toTarget);
        
        // If player isn't even facing the general direction
        if (dot <= WATCH_DOT_THRESHOLD)
            return false;

        // Line of sight check (is there a block in the way?)
        HitResult hit = world.clip(new ClipContext(
                eye,
                target,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        if (hit.getType() == HitResult.Type.MISS)
            return true;

        if (hit instanceof BlockHitResult bhr) {
            return bhr.getBlockPos().equals(pos);
        }

        return false;
    }

    private static void addTrackedFire(Player player, BlockPos pos) {
        List<BlockPos> tracked = getTrackedFires(player);
        for (BlockPos p : tracked) {
            if (p.equals(pos)) return;
        }

        tracked.add(pos);

        if (tracked.size() > MAX_TRACKED_FIRES) {
            tracked = tracked.subList(tracked.size() - MAX_TRACKED_FIRES, tracked.size());
        }

        saveTrackedFires(player, tracked);
    }

    private static List<BlockPos> getTrackedFires(Player player) {
        List<BlockPos> out = new ArrayList<>();
        CompoundTag data = player.getPersistentData();

        if (!data.contains(NBT_LIST_KEY, Tag.TAG_LIST))
            return out;

        ListTag list = data.getList(NBT_LIST_KEY, Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            String s = list.getString(i);
            BlockPos p = parsePos(s);
            if (p != null) out.add(p);
        }
        return out;
    }

    private static void saveTrackedFires(Player player, List<BlockPos> tracked) {
        ListTag list = new ListTag();
        for (BlockPos p : tracked) {
            list.add(StringTag.valueOf(encodePos(p)));
        }
        player.getPersistentData().put(NBT_LIST_KEY, list);
    }

    private static String encodePos(BlockPos pos) {
        return pos.getX() + "," + pos.getY() + "," + pos.getZ();
    }

    private static BlockPos parsePos(String s) {
        try {
            String[] parts = s.split(",");
            return new BlockPos(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        } catch (Exception ignored) {
            return null;
        }
    }
}