package net.mcreator.thebackwoods.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.thebackwoods.procedures.SinkingAshEntityCollidesInTheBlockProcedure;

import com.mojang.serialization.MapCodec;

public class SinkingAshBlock extends FallingBlock {
	public static final MapCodec<SinkingAshBlock> CODEC = simpleCodec(properties -> new SinkingAshBlock());

	public MapCodec<SinkingAshBlock> codec() {
		return CODEC;
	}

	public SinkingAshBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).sound(SoundType.EMPTY).strength(0.5f).noCollission().friction(0.67f).ignitedByLava());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		SinkingAshEntityCollidesInTheBlockProcedure.execute(entity);
	}
}