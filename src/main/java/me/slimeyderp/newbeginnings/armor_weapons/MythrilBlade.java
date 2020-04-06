package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import me.slimeyderp.newbeginnings.NewBeginnings;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.util.List;


public class MythrilBlade extends NonDisenchantableSlimefunItem {

    private int cooldown = 20;
    private int task;

    public MythrilBlade(Category category, SlimefunItemStack item, RecipeType recipeType,
                        ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemRightClick(PlayerRightClickEvent e) {
        if (cooldown < 20) {
            e.getPlayer().sendMessage("Can't use this ability yet! You need to wait " +
                cooldown + " seconds.");
            e.cancel();
        } else {
            cooldown = 0;
            List<Entity> entities = e.getPlayer().getNearbyEntities(5, 5, 5);
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (livingEntity.getHealth() > 10) {
                        livingEntity.setHealth(livingEntity.getHealth() - 10);
                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2));
                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1));
                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 2));
                    } else {
                        livingEntity.setHealth(0);
                    }
                }
            }
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_GRAVEL_BREAK, 1, 1);
            spawnParticles(e.getPlayer());
            task = Bukkit.getScheduler().runTaskTimer(NewBeginnings.getInstance(),
                this::timerCheck, 20, 20).getTaskId();
        }
    }

    private void spawnParticles(Player p) {
        p.getWorld().spawnParticle(Particle.REDSTONE,
            p.getLocation().clone().add(5, 0, 0), 1,
            new Particle.DustOptions(Color.GREEN, 2));
        p.getWorld().spawnParticle(Particle.REDSTONE,
            p.getLocation().clone().add(-5, 0, 0), 1,
            new Particle.DustOptions(Color.GREEN, 2));
        p.getWorld().spawnParticle(Particle.REDSTONE,
            p.getLocation().clone().add(0, 0, 5), 1,
            new Particle.DustOptions(Color.GREEN, 2));
        p.getWorld().spawnParticle(Particle.REDSTONE,
            p.getLocation().clone().add(0, 0, -5), 1,
            new Particle.DustOptions(Color.GREEN, 2));
        p.getWorld().spawnParticle(Particle.REDSTONE,
            p.getLocation().clone().add(4, 0, 4), 1,
            new Particle.DustOptions(Color.GREEN, 2));
        p.getWorld().spawnParticle(Particle.REDSTONE,
            p.getLocation().clone().add(-4, 0, -4), 1,
            new Particle.DustOptions(Color.GREEN, 2));
        p.getWorld().spawnParticle(Particle.REDSTONE,
            p.getLocation().clone().add(4, 0, -4), 1,
            new Particle.DustOptions(Color.GREEN, 2));
        p.getWorld().spawnParticle(Particle.REDSTONE,
            p.getLocation().clone().add(-4, 0, 4), 1,
            new Particle.DustOptions(Color.GREEN, 2));

    }

    private void timerCheck() {
        cooldown++;
        if (cooldown > 19) {
            Bukkit.getScheduler().cancelTask(task);
        }
    }
}
