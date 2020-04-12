package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.tasks.PandaBazookaTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class PandaBazooka extends NonDisenchantableSlimefunItem {

    public static HashMap<UUID, Integer> pandaShootCooldown = new HashMap<>();

    public PandaBazooka(Category category, SlimefunItemStack item, RecipeType recipeType,
                        ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
    }

    public void onItemRightClick(PlayerRightClickEvent e) {
        if (pandaShootCooldown.containsKey(e.getPlayer().getUniqueId())) {
            e.getPlayer().sendMessage(ChatColor.RED + "Please wait a bit before firing again.");
        } else {
            e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
                Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);
            Panda flyingCrapPanda = (Panda) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation()
                    .add(e.getPlayer().getEyeLocation().getDirection().normalize())
                , EntityType.PANDA);
            flyingCrapPanda.setCustomName(ChatColor.WHITE + "PANDAAAAAAA!!!!!!");
            flyingCrapPanda.setCustomNameVisible(true);
            Vector direction = e.getPlayer().getEyeLocation().getDirection().normalize();
            long secondsPassed = (System.currentTimeMillis() + 3000);
            pandaShootCooldown.put(e.getPlayer().getUniqueId(),
                Bukkit.getScheduler().scheduleSyncRepeatingTask(NewBeginnings.getInstance(), new
                    PandaBazookaTask(flyingCrapPanda, e.getPlayer(), secondsPassed, e.getPlayer().getEyeLocation()
                    , direction), 0, 0));
        }
        e.cancel();
    }


}
