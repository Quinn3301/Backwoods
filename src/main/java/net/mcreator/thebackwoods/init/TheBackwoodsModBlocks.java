/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.thebackwoods.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

import net.mcreator.thebackwoods.block.BackwoodsPortalBlock;
import net.mcreator.thebackwoods.TheBackwoodsMod;

public class TheBackwoodsModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(TheBackwoodsMod.MODID);
	public static final DeferredBlock<Block> BACKWOODS_PORTAL;
	static {
		BACKWOODS_PORTAL = REGISTRY.register("backwoods_portal", BackwoodsPortalBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}