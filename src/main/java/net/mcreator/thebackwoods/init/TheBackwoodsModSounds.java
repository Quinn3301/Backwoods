/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.thebackwoods.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.thebackwoods.TheBackwoodsMod;

public class TheBackwoodsModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, TheBackwoodsMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_IDLE1 = REGISTRY.register("splinter_idle1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_idle1")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_IDLE2 = REGISTRY.register("splinter_idle2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_idle2")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_IDLE3 = REGISTRY.register("splinter_idle3", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_idle3")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_IDLE4 = REGISTRY.register("splinter_idle4", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_idle4")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_IDLE5 = REGISTRY.register("splinter_idle5", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_idle5")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_IDLE6 = REGISTRY.register("splinter_idle6", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_idle6")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_STEP1 = REGISTRY.register("splinter_step1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_step1")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_STEP2 = REGISTRY.register("splinter_step2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_step2")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_STEP3 = REGISTRY.register("splinter_step3", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_step3")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_UNFREEZE1 = REGISTRY.register("splinter_unfreeze1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_unfreeze1")));
	public static final DeferredHolder<SoundEvent, SoundEvent> SPLINTER_ATTACK1 = REGISTRY.register("splinter_attack1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("the_backwoods", "splinter_attack1")));
}