package net.mcreator.thebackwoods.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.thebackwoods.init.TheBackwoodsModItems;

public class GiveTestKitProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(Items.NETHERITE_AXE).copy();
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(Blocks.OAK_PLANKS).copy();
			_setstack.setCount(128);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(Items.GOLDEN_CARROT).copy();
			_setstack.setCount(64);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(TheBackwoodsModItems.BACKWOODS.get()).copy();
			_setstack.setCount(2);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = new ItemStack(Items.TORCH).copy();
			_setstack.setCount(128);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
	}
}