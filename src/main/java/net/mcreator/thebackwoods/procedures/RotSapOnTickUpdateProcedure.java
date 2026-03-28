package net.mcreator.thebackwoods.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class RotSapOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() < (1) / ((float) 50)) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.ASH, x, y, z, 25, 0.5, 0.5, 0.5, 1);
		}
	}
}