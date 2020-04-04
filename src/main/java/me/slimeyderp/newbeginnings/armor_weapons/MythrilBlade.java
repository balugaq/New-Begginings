package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.listeners.MainListener;
import me.slimeyderp.newbeginnings.materials.ExtraItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.List;

public class MythrilBlade extends NonDisenchantableSlimefunItem {

    long time;
    int loop;
    List<Entity> entities;
    LivingEntity livingEntity;

    public MythrilBlade(Category category, SlimefunItemStack item, RecipeType recipeType,
                        ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
    }

    // Really confusing code, even for myself, so I'll sumarize: This makes an armor stand
    // which simulates a sword being thrown. While at it, the inventory of the player is soft-locked
    // to prevent issues with the sword returning.

    // I know the code is a mess, but it's really the only way I could program this. Sorry ;-;

    // plz dont kill me walshy

    private void onItemRightClick(PlayerRightClickEvent e) {
        Player p = e.getPlayer();
        Vector dir = p.getEyeLocation().getDirection();
        time = System.currentTimeMillis();
        p.setItemInHand(new ItemStack(Material.AIR));

        ArmorStand as = p.getWorld().spawn(p.getLocation().add(dir), ArmorStand.class);

        as.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_SWORD));
        as.setVisible(false);
        as.setCanPickupItems(false);
        as.setGravity(false);

        loop = Bukkit.getScheduler().runTaskTimer(NewBeginnings.getInstance(),
            () -> excecute(as,dir,p), 1 ,1).getTaskId();

    }

    private void excecute(ArmorStand as, Vector dir, Player p) {
        as.teleport(as.getLocation().add(dir));
        if (as.getLocation().add(dir).getBlock().getType() != Material.AIR) {
            entities = as.getNearbyEntities(2,2,2);
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity) {
                    livingEntity = (LivingEntity) entity;
                    if (livingEntity.getHealth() > 7) {
                        livingEntity.setHealth(livingEntity.getHealth() - 7);
                    } else {
                        livingEntity.setHealth(0);
                    }
                }
            }
            as.remove();
            p.setItemInHand(ExtraItemStack.MYTHRIL_BLADE_STACK);
            Bukkit.getScheduler().cancelTask(loop);
        } else if ((time + 1500) >= System.currentTimeMillis()) {
            as.remove();
            p.setItemInHand(ExtraItemStack.MYTHRIL_BLADE_STACK);
            Bukkit.getScheduler().cancelTask(loop);
        }
    }
}
