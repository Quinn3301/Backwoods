/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.thebackwoods.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.thebackwoods.potion.HeavyLungsPotionMobEffect;
import net.mcreator.thebackwoods.TheBackwoodsMod;

public class TheBackwoodsModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, TheBackwoodsMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> HEAVY_LUNGS_POTION = REGISTRY.register("heavy_lungs_potion", () -> new HeavyLungsPotionMobEffect());
}