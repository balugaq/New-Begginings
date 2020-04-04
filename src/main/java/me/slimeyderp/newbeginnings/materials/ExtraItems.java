package me.slimeyderp.newbeginnings.materials;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.slimeyderp.newbeginnings.NewCategory;
import me.slimeyderp.newbeginnings.armor_weapons.MythrilBlade;
import me.slimeyderp.newbeginnings.armor_weapons.MythrilChestplate;
import me.slimeyderp.newbeginnings.armor_weapons.NonDisenchantableSlimefunItem;
import me.slimeyderp.newbeginnings.electricity.EnderPearlGenerator;

public class ExtraItems {

    //This is the area where items are made, ready to be registered.

    public static final SlimefunItem MYTHRIL_ORE = new SlimefunItem(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.MYTHRIL_ORE_STACK,
        RecipeType.GEO_MINER,
        ExtraRecipes.EMPTY_RECIPE
    );
    public static final SlimefunItem MYTHRIL = new SlimefunItem(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.MYTHRIL_STACK,
        RecipeType.SMELTERY,
        ExtraRecipes.MYTHRIL_RECIPE
    );
    public static final NonDisenchantableSlimefunItem MYTHRIL_HELMET = new NonDisenchantableSlimefunItem(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.MYTHRIL_HELMET_STACK,
        RecipeType.ARMOR_FORGE,
        ExtraRecipes.MYTHRIL_HELMET_RECIPE
    );
    public static final MythrilChestplate MYTHRIL_CHESTPLATE = new MythrilChestplate(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.MYTHRIL_CHESTPLATE_STACK,
        RecipeType.ARMOR_FORGE,
        ExtraRecipes.MYTHRIL_CHESTPLATE_RECIPE,
        false
    );
    public static final MythrilChestplate MYTHRIL_CHESTPLATE_ELYTRA = new MythrilChestplate(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.MYTHRIL_CHESTPLATE_ELYTRA_STACK,
        RecipeType.NULL,
        ExtraRecipes.EMPTY_RECIPE,
        true
    );
    public static final NonDisenchantableSlimefunItem MYTHRIL_LEGGINGS = new NonDisenchantableSlimefunItem(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.MYTHRIL_LEGGINGS_STACK,
        RecipeType.ARMOR_FORGE,
        ExtraRecipes.MYTHRIL_LEGGINGS_RECIPE
    );
    public static final NonDisenchantableSlimefunItem MYTHRIL_BOOTS = new NonDisenchantableSlimefunItem(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.MYTHRIL_BOOTS_STACK,
        RecipeType.ARMOR_FORGE,
        ExtraRecipes.MYTHRIL_BOOTS_RECIPE
    );
    public static final EnderPearlGenerator ENDER_PEARL_GENERATOR = new EnderPearlGenerator(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.ENDER_PEARL_GENERATOR,
        RecipeType.ENHANCED_CRAFTING_TABLE,
        ExtraRecipes.EMPTY_RECIPE
    );
    public static final MythrilBlade MYTHRIL_BLADE = new MythrilBlade(
        NewCategory.CUSTOM_CATEGORY,
        ExtraItemStack.MYTHRIL_BLADE_STACK,
        RecipeType.ENHANCED_CRAFTING_TABLE,
        ExtraRecipes.MYTHRIL_BLADE_RECIPE
    );


}
