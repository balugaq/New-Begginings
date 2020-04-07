package me.slimeyderp.newbeginnings;

import me.slimeyderp.newbeginnings.listeners.MainListener;
import me.slimeyderp.newbeginnings.materials.ExtraItemStack;
import me.slimeyderp.newbeginnings.materials.ExtraItems;
import me.slimeyderp.newbeginnings.resources.MythrilResource;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;


public class NewBeginnings extends JavaPlugin implements SlimefunAddon {

    private static NewBeginnings instance;


    @Override
    public void onEnable() {

        instance = this;

        //Registering and giving atributtes to the items.

        registerItems();
        enchantItems();

        // Calling the listener to kick into action.
        new MainListener(this);

        //Reporting everything went well. Hooray! :D
        getLogger().info("New-Beginnings has loaded Successfully!");
        getLogger().info("Enjoy the plugin!");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/SlimeyDerp/New-Begginings/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static NewBeginnings getInstance() {
        return instance;
    }

    // Registers all resources into SF Guide and the Geo-Miner.
    private void registerItems() {
        new MythrilResource().register();
        ExtraItems.MYTHRIL_ORE.register(this);
        ExtraItems.MYTHRIL.register(this);
        ExtraItems.MYTHRIL_HELMET.register(this);
        ExtraItems.MYTHRIL_CHESTPLATE.register(this);
        ExtraItems.MYTHRIL_CHESTPLATE_ELYTRA.register(this);
        ExtraItems.MYTHRIL_LEGGINGS.register(this);
        ExtraItems.MYTHRIL_BOOTS.register(this);
        ExtraItems.MYTHRIL_BLADE.register(this);
        ExtraItems.MYTHRIL_PICKAXE.register(this);
        ExtraItems.SHULKER_SHELL.register(this);
        ExtraItems.INFINITE_FIREWORK.register(this);
        ExtraItems.ENDER_PEARL_GENERATOR_1.register(this);
        ExtraItems.ENDER_PEARL_GENERATOR_2.register(this);
    }

    //Enchants the items with their respective enchantments before registering.
    private void enchantItems() {

        ItemMeta mythrilHelmet = ExtraItemStack.MYTHRIL_HELMET_STACK.getItemMeta();
        mythrilHelmet.addEnchant(Enchantment.DURABILITY,10,true);
        mythrilHelmet.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        mythrilHelmet.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 5, true);
        mythrilHelmet.addEnchant(Enchantment.OXYGEN, 5, true);
        mythrilHelmet.addEnchant(Enchantment.WATER_WORKER, 1, true);
        ExtraItemStack.MYTHRIL_HELMET_STACK.setItemMeta(mythrilHelmet);

        ItemMeta mythrilChestplate = ExtraItemStack.MYTHRIL_CHESTPLATE_STACK.getItemMeta();
        mythrilChestplate.addEnchant(Enchantment.DURABILITY,10,true);
        mythrilChestplate.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        mythrilChestplate.addEnchant(Enchantment.PROTECTION_FIRE, 5, true);
        ExtraItemStack.MYTHRIL_CHESTPLATE_STACK.setItemMeta(mythrilChestplate);

        ItemMeta mythrilChestplateElytra = ExtraItemStack.MYTHRIL_CHESTPLATE_ELYTRA_STACK.getItemMeta();
        mythrilChestplateElytra.addEnchant(Enchantment.DURABILITY,10,true);
        mythrilChestplateElytra.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        ExtraItemStack.MYTHRIL_CHESTPLATE_ELYTRA_STACK.setItemMeta(mythrilChestplateElytra);

        ItemMeta mythrilLeggings = ExtraItemStack.MYTHRIL_LEGGINGS_STACK.getItemMeta();
        mythrilLeggings.addEnchant(Enchantment.DURABILITY,10,true);
        mythrilLeggings.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        mythrilLeggings.addEnchant(Enchantment.PROTECTION_PROJECTILE, 5, true);
        ExtraItemStack.MYTHRIL_LEGGINGS_STACK.setItemMeta(mythrilLeggings);

        ItemMeta mythrilBoots = ExtraItemStack.MYTHRIL_BOOTS_STACK.getItemMeta();
        mythrilBoots.addEnchant(Enchantment.DURABILITY,10,true);
        mythrilBoots.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        mythrilBoots.addEnchant(Enchantment.PROTECTION_FALL, 5, true);
        mythrilBoots.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
        ExtraItemStack.MYTHRIL_BOOTS_STACK.setItemMeta(mythrilBoots);

        ItemMeta mythrilBlade = ExtraItemStack.MYTHRIL_BLADE_STACK.getItemMeta();
        mythrilBlade.addEnchant(Enchantment.DURABILITY,10,true);
        mythrilBlade.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
        mythrilBlade.addEnchant(Enchantment.DAMAGE_UNDEAD, 5, true);
        mythrilBlade.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 3, true);
        ExtraItemStack.MYTHRIL_BLADE_STACK.setItemMeta(mythrilBlade);

        ItemMeta mythrilPickaxe = ExtraItemStack.MYTHRIL_PICKAXE_STACK.getItemMeta();
        mythrilPickaxe.addEnchant(Enchantment.DURABILITY,10,true);
        mythrilPickaxe.addEnchant(Enchantment.DIG_SPEED, 10, true);
        mythrilPickaxe.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 5, true);
        ExtraItemStack.MYTHRIL_PICKAXE_STACK.setItemMeta(mythrilPickaxe);

        ItemMeta infiniteFirework = ExtraItemStack.INFINITE_FIREWORK_STACK.getItemMeta();
        infiniteFirework.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        ExtraItemStack.INFINITE_FIREWORK_STACK.setItemMeta(infiniteFirework);
    }
}
