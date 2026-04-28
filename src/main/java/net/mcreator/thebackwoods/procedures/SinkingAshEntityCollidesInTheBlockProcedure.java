package net.mcreator.thebackwoods.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class SinkingAshEntityCollidesInTheBlockProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 0.35), (entity.getDeltaMovement().y() * 0.85), (entity.getDeltaMovement().z() * 0.35)));
			entity.fallDistance = 0;
		}
	}
}