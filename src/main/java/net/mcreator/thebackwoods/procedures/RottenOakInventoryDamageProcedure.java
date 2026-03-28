package net.mcreator.thebackwoods.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

import net.mcreator.thebackwoods.init.TheBackwoodsModBlocks;

import javax.annotation.Nullable;

@EventBusSubscriber
public class RottenOakInventoryDamageProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world.getLevelData().getGameTime() % 45 == 0) {
			if (entity instanceof Player) {
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					if (hasEntityInInventory(entity, new ItemStack(TheBackwoodsModBlocks.ROTTEN_OAK_PLANKS.get())) || hasEntityInInventory(entity, new ItemStack(TheBackwoodsModBlocks.ROTTEN_OAK_LOG.get()))
							|| hasEntityInInventory(entity, new ItemStack(TheBackwoodsModBlocks.ROTTEN_OAK_WOOD.get())) || hasEntityInInventory(entity, new ItemStack(TheBackwoodsModBlocks.ROTTEN_OAK_STAIR.get()))
							|| hasEntityInInventory(entity, new ItemStack(TheBackwoodsModBlocks.ROTTEN_OAK_SLAB.get())) || hasEntityInInventory(entity, new ItemStack(TheBackwoodsModBlocks.ROTTEN_OAK_TRAPDOOR.get()))
							|| hasEntityInInventory(entity, new ItemStack(TheBackwoodsModBlocks.ROTTEN_OAK_FENCE.get()))) {
						if (Math.random() < (1) / ((float) 780)) {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, 0, false, false));
						}
						entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.SWEET_BERRY_BUSH)), (float) 0.1);
					}
					if (hasEntityInInventory(entity, new ItemStack(TheBackwoodsModBlocks.PLAQUE.get()))) {
						if (Math.random() < (1) / ((float) 500)) {
							if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0, false, false));
						}
						entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.WITHER)), (float) 0.3);
					}
				}
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}