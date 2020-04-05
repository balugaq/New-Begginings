package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import me.slimeyderp.newbeginnings.NewBeginnings;
import org.bukkit.Bukkit;
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

    private boolean cooldown = false;

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
        if (cooldown) {
            e.getPlayer().sendMessage("Can't use this ability yet!");
            e.cancel();
        } else {
            cooldown = true;
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
            Bukkit.getScheduler().runTaskLater(NewBeginnings.getInstance(),
                () -> cooldown = false, 400);
        }
    }

    private void spawnParticles(Player p){
        p.getWorld().spawnParticle(Particle.BLOCK_CRACK,
            p.getLocation().add(5,0,0), 2);
        p.getWorld().spawnParticle(Particle.BLOCK_CRACK,
            p.getLocation().add(-5,0,0), 2);
        p.getWorld().spawnParticle(Particle.BLOCK_CRACK,
            p.getLocation().add(0,0,5), 2);
        p.getWorld().spawnParticle(Particle.BLOCK_CRACK,
            p.getLocation().add(0,0,-5), 2);
        p.getWorld().spawnParticle(Particle.BLOCK_CRACK,
            p.getLocation().add(4,0,4), 2);
        p.getWorld().spawnParticle(Particle.BLOCK_CRACK,
            p.getLocation().add(-4,0,-4), 2);
        p.getWorld().spawnParticle(Particle.BLOCK_CRACK,
            p.getLocation().add(4,0,-4), 2);
        p.getWorld().spawnParticle(Particle.BLOCK_CRACK,
            p.getLocation().add(-4,0,4), 2);
    }
}
