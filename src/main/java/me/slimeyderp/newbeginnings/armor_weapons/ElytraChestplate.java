package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.materials.ExtraItemStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ElytraChestplate extends NonDisenchantableSlimefunItem {

    private final ItemStack replace;

    public ElytraChestplate(Category category, SlimefunItemStack item, RecipeType recipeType,
                            ItemStack[] recipe, boolean hidden, ItemStack replace) {
        super(category, item, recipeType, recipe);
        this.replace = replace;
        this.hidden = hidden;
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemRightClick(PlayerRightClickEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (player.isSneaking()) {
            Bukkit.getScheduler().runTaskLater(NewBeginnings.getInstance(),
                () -> handleShiftClick(player, item), 2);
        }
    }

    private void handleShiftClick(Player p, ItemStack i) {
        // If the player equipped the chestplate:

        if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            p.getInventory().setChestplate(null);
        }

        if (replace.getType() == Material.ELYTRA) { p.sendMessage(ChatColor.YELLOW + "Elytra Mode Activated"); } else {
            p.sendMessage(ChatColor.YELLOW + "Chestplate Mode Activated");
        }
        p.getInventory().setItemInMainHand(replace);

    }
}
