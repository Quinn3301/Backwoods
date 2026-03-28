package net.mcreator.thebackwoods.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.thebackwoods.init.TheBackwoodsModBlocks;
import net.mcreator.thebackwoods.entity.SplinterEntity;

import javax.annotation.Nullable;

import java.util.Comparator;

@EventBusSubscriber
public class SplinterOnEntityTickUpdateProcedure {
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
		Entity foundPlayer = null;
		boolean isWatched = false;
		boolean found = false;
		double dot = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(56 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				if (entityiterator instanceof SplinterEntity) {
					isWatched = false;
					foundPlayer = findEntityInWorldRange(world, Player.class, x, y, z, 56);
					if (!(foundPlayer == null)) {
						dot = foundPlayer.getLookAngle().x * (foundPlayer.getX() - entityiterator.getX()) + foundPlayer.getLookAngle().z * (foundPlayer.getZ() - entityiterator.getZ());
						if (dot < 0) {
							isWatched = true;
						} else {
							isWatched = false;
						}
						if (isWatched == true) {
							if (entityiterator instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
								_livingEntity9.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
							if ((entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_frozenByRose) : 0) == 1) {
								if (entityiterator instanceof LivingEntity _livingEntity11 && _livingEntity11.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
									_livingEntity11.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
								entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
							}
						} else if (isWatched == false && (entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_frozenByRose) : 0) == 0) {
							entityiterator.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((foundPlayer.getX()), (foundPlayer.getY()), (foundPlayer.getZ())));
							if (entityiterator instanceof LivingEntity _livingEntity18 && _livingEntity18.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
								_livingEntity18.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35);
							if (entityiterator.level()
									.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(2)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator))
									.getType() == HitResult.Type.BLOCK) {
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineProgress, (int) ((entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineProgress) : 0) + 1));
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineX, entityiterator.level().clip(
											new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(2)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator))
											.getBlockPos().getX());
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineY, entityiterator.level().clip(
											new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(2)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator))
											.getBlockPos().getY());
								if (entityiterator instanceof SplinterEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SplinterEntity.DATA_mineZ, entityiterator.level().clip(
											new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(2)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entityiterator))
											.getBlockPos().getZ());
								if ((entityiterator instanceof SplinterEntity _datEntI
										? _datEntI.getEntityData().get(SplinterEntity.DATA_mineProgress)
										: 0) > world
												.getBlockState(BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
														entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
														entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0))
												.getDestroySpeed(world,
														BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
																entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
																entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0))
												* 40 + 40) {
									if (world
											.getBlockState(BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
													entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
													entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0))
											.getDestroySpeed(world,
													BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
															entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
															entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0)) >= 0
											&& !((entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0) == foundPlayer.getY() - 2)
											&& world.getBlockState(BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
													entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
													entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0))
													.getDestroySpeed(world,
															BlockPos.containing(entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineX) : 0,
																	entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineY) : 0,
																	entityiterator instanceof SplinterEntity _datEntI ? _datEntI.getEntityData().get(SplinterEntity.DATA_mineZ) : 0)) < 50) {
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
								if (foundPlayer.getY() - entityiterator.getY() >= 1.4 && (entityiterator.position()).distanceTo((foundPlayer.position())) < 12) {
									if (world instanceof ServerLevel _level)
										_level.getServer().getCommands().performPrefixedCommand(
												new CommandSourceStack(CommandSource.NULL, new Vec3((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
														.withSuppressedOutput(),
												"fill ~ ~-1 ~ ~ ~-1 ~ minecraft:oak_planks replace minecraft:air");
									if (world instanceof ServerLevel _level)
										_level.getServer().getCommands().performPrefixedCommand(
												new CommandSourceStack(CommandSource.NULL, new Vec3((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
														.withSuppressedOutput(),
												"fill ~ ~ ~ ~ ~1 ~ minecraft:air replace minecraft:oak_planks");
									world.destroyBlock(BlockPos.containing(entityiterator.getX(), entityiterator.getY() + 2, entityiterator.getZ()), false);
									world.destroyBlock(BlockPos.containing(entityiterator.getX(), entityiterator.getY() + 3, entityiterator.getZ()), false);
									if (entityiterator.onGround()) {
										entityiterator.setDeltaMovement(new Vec3((entityiterator.getDeltaMovement().x()), 0.4, (entityiterator.getDeltaMovement().z())));
									}
									entityiterator.fallDistance = 0;
								} else {
									if (((foundPlayer.getY() - entityiterator.getY())) > ((-0.5)) && ((foundPlayer.getY() - entityiterator.getY())) < (2.5)) {
										if (!(world.getBlockFloorHeight(BlockPos.containing(entityiterator.getX() + entityiterator.getLookAngle().x * 1, entityiterator.getY() - 1, entityiterator.getZ() + entityiterator.getLookAngle().z * 1)) > 0)) {
											world.setBlock(BlockPos.containing(entityiterator.getX() + entityiterator.getLookAngle().x * 1, entityiterator.getY() - 1, entityiterator.getZ() + entityiterator.getLookAngle().z * 1),
													Blocks.OAK_PLANKS.defaultBlockState(), 3);
										}
									}
								}
							}
							found = false;
							if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TheBackwoodsModBlocks.ASH_ROSE.get().asItem()
									|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TheBackwoodsModBlocks.ASH_ROSE.get().asItem()) {
								found = true;
								entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
								if (entityiterator instanceof LivingEntity _livingEntity100 && _livingEntity100.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
									_livingEntity100.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
								if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TheBackwoodsModBlocks.ASH_ROSE.get().asItem()) {
									{
										final String _tagName = "wilt_timer";
										final double _tagValue = ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("wilt_timer")
												+ 1);
										CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY), tag -> tag.putDouble(_tagName, _tagValue));
									}
									if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("wilt_timer") > 450) {
										if (world instanceof Level _level) {
											if (!_level.isClientSide()) {
												_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.NEUTRAL, 1, 1);
											} else {
												_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.NEUTRAL, 1, 1, false);
											}
										}
										(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
										{
											final String _tagName = "wilt_timer";
											final double _tagValue = 0;
											CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY), tag -> tag.putDouble(_tagName, _tagValue));
										}
									}
								}
								if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TheBackwoodsModBlocks.ASH_ROSE.get().asItem()) {
									{
										final String _tagName = "wilt_timer";
										final double _tagValue = ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("wilt_timer") + 1);
										CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY), tag -> tag.putDouble(_tagName, _tagValue));
									}
									if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("wilt_timer") > 450) {
										if (world instanceof Level _level) {
											if (!_level.isClientSide()) {
												_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.NEUTRAL, 1, 1);
											} else {
												_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.fire.extinguish")), SoundSource.NEUTRAL, 1, 1, false);
											}
										}
										(entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
										{
											final String _tagName = "wilt_timer";
											final double _tagValue = 0;
											CustomData.update(DataComponents.CUSTOM_DATA, (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY), tag -> tag.putDouble(_tagName, _tagValue));
										}
									}
								}
							}
							if (Math.random() < 0.05) {
								sx = -6;
								for (int index0 = 0; index0 < 12; index0++) {
									sy = -3;
									for (int index1 = 0; index1 < 6; index1++) {
										sz = -6;
										for (int index2 = 0; index2 < 12; index2++) {
											if (found == false) {
												if ((world.getBlockState(BlockPos.containing(entityiterator.getX() + sx, entityiterator.getY() + sy, entityiterator.getZ() + sz))).getBlock() == TheBackwoodsModBlocks.ASH_ROSE.get()) {
													found = true;
												}
											}
											sz = sz + 1;
										}
										sy = sy + 1;
									}
									sx = sx + 1;
								}
								if (found == true) {
									if (entityiterator instanceof LivingEntity _livingEntity132 && _livingEntity132.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
										_livingEntity132.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
									entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
								}
							}
						} else if (found == true) {
							if (entityiterator instanceof LivingEntity _livingEntity134 && _livingEntity134.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
								_livingEntity134.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
							entityiterator.setDeltaMovement(new Vec3(0, 0, 0));
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