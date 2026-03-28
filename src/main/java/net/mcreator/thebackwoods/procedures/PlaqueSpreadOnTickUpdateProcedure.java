package net.mcreator.thebackwoods.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.thebackwoods.init.TheBackwoodsModBlocks;

public class PlaqueSpreadOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < (1) / ((float) 3)) {
			if (world.getBlockFloorHeight(BlockPos.containing(x + 1, y, z)) > 0) {
				world.setBlock(BlockPos.containing(x + 1, y, z), TheBackwoodsModBlocks.PLAQUE.get().defaultBlockState(), 3);
			}
			if (world.getBlockFloorHeight(BlockPos.containing(x - 1, y, z)) > 0) {
				world.setBlock(BlockPos.containing(x - 1, y, z), TheBackwoodsModBlocks.PLAQUE.get().defaultBlockState(), 3);
			}
			if (world.getBlockFloorHeight(BlockPos.containing(x, y + 1, z)) > 0) {
				world.setBlock(BlockPos.containing(x, y + 1, z), TheBackwoodsModBlocks.PLAQUE.get().defaultBlockState(), 3);
			}
			if (world.getBlockFloorHeight(BlockPos.containing(x, y - 1, z)) > 0) {
				world.setBlock(BlockPos.containing(x, y - 1, z), TheBackwoodsModBlocks.PLAQUE.get().defaultBlockState(), 3);
			}
			if (world.getBlockFloorHeight(BlockPos.containing(x, y, z + 1)) > 0) {
				world.setBlock(BlockPos.containing(x, y, z + 1), TheBackwoodsModBlocks.PLAQUE.get().defaultBlockState(), 3);
			}
			if (world.getBlockFloorHeight(BlockPos.containing(x, y, z - 1)) > 0) {
				world.setBlock(BlockPos.containing(x, y, z - 1), TheBackwoodsModBlocks.PLAQUE.get().defaultBlockState(), 3);
			}
		}
	}
}