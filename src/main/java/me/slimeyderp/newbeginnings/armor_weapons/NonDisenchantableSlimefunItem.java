package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

public class NonDisenchantableSlimefunItem extends SlimefunItem {

    public NonDisenchantableSlimefunItem(ItemGroup category, SlimefunItemStack item, RecipeType recipeType,
                                         ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public boolean isDisenchantable() {
        return false;
    }
}
