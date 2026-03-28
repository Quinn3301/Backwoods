package net.mcreator.thebackwoods.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

import net.mcreator.thebackwoods.init.TheBackwoodsModBlocks;
import net.mcreator.thebackwoods.entity.SplinterEntity;
import net.mcreator.thebackwoods.entity.LogSplinterEntity;
import net.mcreator.thebackwoods.entity.HollowEntity;
import net.mcreator.thebackwoods.entity.AshWeaverEntity;

import javax.annotation.Nullable;

import java.util.Comparator;

@EventBusSubscriber
public class AshWeaverOnEntityTickUpdateProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double offsetX = 0;
		double offsetZ = 0;
		Entity foundPlayer = null;
		if (entity instanceof AshWeaverEntity) {
			foundPlayer = findEntityInWorldRange(world, Player.class, x, y, z, 32);
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(32 / 2d), e -> true).isEmpty()) {
				if (!(foundPlayer == null)) {
					if (entity instanceof LivingEntity _entity ? _entity.hasLineOfSight(foundPlayer) : false) {
						if ((entity.position()).distanceTo((foundPlayer.position())) > 3) {
							if (entity instanceof Mob _entity)
								_entity.getNavigation().moveTo((foundPlayer.getX()), Math.floor(foundPlayer.getY()), (foundPlayer.getZ()), 1.45);
						} else {
							if (entity instanceof Mob _entity)
								_entity.getNavigation().stop();
						}
					}
				}
			}
			if ((entity instanceof AshWeaverEntity _datEntI ? _datEntI.getEntityData().get(AshWeaverEntity.DATA_roseCooldown) : 0) > 0) {
				if (entity instanceof AshWeaverEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AshWeaverEntity.DATA_roseCooldown, (int) ((entity instanceof AshWeaverEntity _datEntI ? _datEntI.getEntityData().get(AshWeaverEntity.DATA_roseCooldown) : 0) - 1));
			}
			if (!((findEntityInWorldRange(world, SplinterEntity.class, x, y, z, 32)) == null) == false && !((findEntityInWorldRange(world, HollowEntity.class, x, y, z, 32)) == null) == false
					&& !((findEntityInWorldRange(world, LogSplinterEntity.class, x, y, z, 32)) == null) == false) {
				if (entity instanceof AshWeaverEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AshWeaverEntity.DATA_roseCount, 0);
			}
			if ((entity instanceof AshWeaverEntity _datEntI ? _datEntI.getEntityData().get(AshWeaverEntity.DATA_roseCooldown) : 0) == 0) {
				if (!(foundPlayer == null)) {
					if (!((findEntityInWorldRange(world, SplinterEntity.class, x, y, z, 32)) == null) || !((findEntityInWorldRange(world, LogSplinterEntity.class, x, y, z, 32)) == null)
							|| !((findEntityInWorldRange(world, HollowEntity.class, x, y, z, 32)) == null)) {
						if ((entity instanceof AshWeaverEntity _datEntI ? _datEntI.getEntityData().get(AshWeaverEntity.DATA_roseCount) : 0) < 3) {
							if (Math.random() < (1) / ((float) 100)) {
								offsetX = Mth.nextInt(RandomSource.create(), -3, 3);
								offsetZ = Mth.nextInt(RandomSource.create(), -3, 3);
								if ((world.getBlockState(BlockPos.containing(foundPlayer.getX() + offsetX, foundPlayer.getY(), foundPlayer.getZ() + offsetZ))).getBlock() == Blocks.AIR
										&& world.getBlockFloorHeight(BlockPos.containing(foundPlayer.getX() + offsetX, foundPlayer.getY() - 1, foundPlayer.getZ() + offsetZ)) > 0) {
									world.setBlock(BlockPos.containing(foundPlayer.getX() + offsetX, foundPlayer.getY(), foundPlayer.getZ() + offsetZ), TheBackwoodsModBlocks.ASH_ROSE.get().defaultBlockState(), 3);
									if (entity instanceof AshWeaverEntity _datEntSetI)
										_datEntSetI.getEntityData().set(AshWeaverEntity.DATA_roseCooldown, 100);
									if (entity instanceof AshWeaverEntity _datEntSetI)
										_datEntSetI.getEntityData().set(AshWeaverEntity.DATA_roseCount, (int) ((entity instanceof AshWeaverEntity _datEntI ? _datEntI.getEntityData().get(AshWeaverEntity.DATA_roseCount) : 0) + 1));
								}
							}
						}
					}
				}
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}