package net.mcreator.thebackwoods.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.thebackwoods.entity.SplinterEntity;

import javax.annotation.Nullable;

import java.util.Comparator;

@EventBusSubscriber
public class SplinterOnEntityTickUpdateProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		double dot = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(96 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (entityiterator instanceof SplinterEntity) {
					if (!((findEntityInWorldRange(world, Player.class, x, y, z, 32)) == null)) {
						dot = (findEntityInWorldRange(world, Player.class, x, y, z, 32)).getLookAngle().x * ((findEntityInWorldRange(world, Player.class, x, y, z, 32)).getX() - entityiterator.getX())
								+ (findEntityInWorldRange(world, Player.class, x, y, z, 32)).getLookAngle().z * ((findEntityInWorldRange(world, Player.class, x, y, z, 32)).getZ() - entityiterator.getZ());
						if (dot < 0) {
							if (entityiterator instanceof Mob _entity)
								_entity.getNavigation().stop();
							entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
						} else {
							entityiterator.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((findEntityInWorldRange(world, Player.class, x, y, z, 32)).getX()), ((findEntityInWorldRange(world, Player.class, x, y, z, 32)).getY()),
									((findEntityInWorldRange(world, Player.class, x, y, z, 32)).getZ())));
							if (entityiterator.level()
									.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(5)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator))
									.getType() == HitResult.Type.BLOCK) {
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineProgress, (int) ((entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineProgress) : 0) + 1));
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineX, entityiterator.level().clip(
											new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(5)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator))
											.getBlockPos().getX());
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineY, entityiterator.level().clip(
											new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(5)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator))
											.getBlockPos().getY());
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineZ, entityiterator.level().clip(
											new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(5)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator))
											.getBlockPos().getZ());
								if ((entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineProgress) : 0) > Math.random() * 60 + 40) {
									if (!((entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0) == (findEntityInWorldRange(world, Player.class, x, y, z, 32)).getY() - 1)) {
										world.destroyBlock(BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
												entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
												entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0), false);
										if (world instanceof Level _level)
											_level.updateNeighborsAt(
													BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
															entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
															entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0),
													_level.getBlockState(BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
															entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
															entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0)).getBlock());
										if (entityiterator instanceof Mob _entity)
											_entity.getNavigation().stop();
										if (entityiterator instanceof SplinterEntity _datEntSetI)
											_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineProgress, 0);
									}
								}
							} else {
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineProgress, 0);
								if ((entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineProgress) : 0) == 0) {
									if ((findEntityInWorldRange(world, Player.class, x, y, z, 32)).getY() > entityiterator.getY()) {
										if (!(world.getBlockFloorHeight(BlockPos.containing(entityiterator.getX(), entityiterator.getY() - 1, entityiterator.getZ())) > 0)) {
											world.setBlock(BlockPos.containing(entityiterator.getX(), entityiterator.getY() - 1, entityiterator.getZ()), Blocks.OAK_PLANKS.defaultBlockState(), 3);
										} else {
											if (!(world.getBlockFloorHeight(BlockPos.containing(
													entityiterator.level()
															.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(1)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
																	entityiterator))
															.getBlockPos().getX(),
													entityiterator.getY(), entityiterator.level().clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(1)),
															ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator)).getBlockPos().getZ())) > 0)) {
												world.setBlock(BlockPos.containing(
														entityiterator.level()
																.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(1)), ClipContext.Block.COLLIDER,
																		ClipContext.Fluid.NONE, entityiterator))
																.getBlockPos().getX(),
														entityiterator.getY(), entityiterator.level().clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(1)),
																ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator)).getBlockPos().getZ()),
														Blocks.OAK_PLANKS.defaultBlockState(), 3);
											}
										}
									}
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