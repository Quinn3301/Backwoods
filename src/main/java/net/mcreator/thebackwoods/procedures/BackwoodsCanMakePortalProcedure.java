package net.mcreator.thebackwoods.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.thebackwoods.init.TheBackwoodsModItems;

public class BackwoodsCanMakePortalProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TheBackwoodsModItems.BACKWOODS.get()) {
			return true;
		}
		return false;
	}
}