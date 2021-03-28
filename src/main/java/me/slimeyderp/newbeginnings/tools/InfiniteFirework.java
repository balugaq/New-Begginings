package me.slimeyderp.newbeginnings.tools;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.armor_weapons.NonDisenchantableSlimefunItem;
import me.slimeyderp.newbeginnings.materials.ExtraItemStack;

public class InfiniteFirework extends NonDisenchantableSlimefunItem {

    public InfiniteFirework(Category category, SlimefunItemStack item, RecipeType recipeType,
                            ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemRightClick(PlayerRightClickEvent e) {
        Bukkit.getScheduler().runTaskLater(NewBeginnings.getInstance(),
            () -> e.getPlayer().getInventory().setItemInMainHand(ExtraItemStack.INFINITE_FIREWORK_STACK), 2);
    }
}
