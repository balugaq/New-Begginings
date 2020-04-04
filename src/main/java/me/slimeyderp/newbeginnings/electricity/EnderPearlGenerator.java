package me.slimeyderp.newbeginnings.electricity;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EnderPearlGenerator extends AGenerator {

    public EnderPearlGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public int getEnergyProduction() {
        return 15;
    }

    public int getCapacity() {
        return 128;
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(new MachineFuel(4, new ItemStack(Material.ENDER_PEARL)));
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.ENDER_EYE);
    }

    @Override
    public String getInventoryTitle() {
        return "&cEnder Pearl Generator";
    }

}
