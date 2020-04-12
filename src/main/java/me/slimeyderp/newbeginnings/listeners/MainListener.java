package me.slimeyderp.newbeginnings.listeners;

import io.github.thebusybiscuit.slimefun4.api.events.AncientAltarCraftEvent;
import io.github.thebusybiscuit.slimefun4.api.events.GEOResourceGenerationEvent;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.materials.ExtraItemStack;
import me.slimeyderp.newbeginnings.mobs.Nightmare;
import me.slimeyderp.newbeginnings.resources.MythrilResource;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;


import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MainListener implements Listener {

    public static HashSet<UUID> disableDamageForPlayer = new HashSet<>();
    private static HashMap <UUID, Zombie> playerMobList = new HashMap<>();
    private HashSet <UUID> playerList = new HashSet<>();
    private static HashMap <UUID, Long> playerSneakTime = new HashMap<>();
    private static HashMap <UUID, Long> playerSuperCooldown = new HashMap<>();

    public MainListener(NewBeginnings plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onResourceGeneration(GEOResourceGenerationEvent e) {
        if (e.getWorld() == Bukkit.getServer().getWorld("world_the_end") &&
            e.getResource() instanceof MythrilResource) {
            if (ThreadLocalRandom.current().nextInt(11) == 7) {
                e.setValue(ThreadLocalRandom.current().nextInt(4));
            }
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player &&
            disableDamageForPlayer.contains(((Player) e.getEntity()).getUniqueId())) {
            e.setCancelled(true);
        }

        if (e.getEntity().getCustomName() != null &&
        e.getEntity().getCustomName() == ChatColor.DARK_GRAY + "The Nightmare") {

            Wither nightmare = (Wither) e.getEntity();

            if (ThreadLocalRandom.current().nextInt(21) == 14) {
                for (byte h = 0; h < ThreadLocalRandom.current().nextInt(5) + 2; h++){
                    Nightmare.spawnNightmareFollowers(nightmare.getLocation(), 0.25F, "Nightmare Followers");
                }
            }
           if (nightmare.getHealth() < 1028 ) {
                Nightmare.secondPhaseCreate(e.getEntity().getLocation());
                nightmare.getWorld().playSound(nightmare.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
                nightmare.remove();
            }
        }

        if (e.getEntity().getCustomName() != null &&
            e.getEntity().getCustomName() == ChatColor.BLACK + "The True Nightmare" &&
            ThreadLocalRandom.current().nextInt(21) == 14) {
            Wither trueNightmare = (Wither) e.getEntity();
            for (byte h = 0; h < ThreadLocalRandom.current().nextInt(5) + 2; h++){
                Nightmare.spawnNightmareFollowers(trueNightmare.getLocation(), 0.45F, "Fiendish Followers");
            }
        }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getCustomName() != null &&
            e.getEntity().getCustomName().equals(ChatColor.BLACK + "The True Nightmare")) {
            e.getDrops().clear();
            e.getDrops().add(new CustomItem((ExtraItemStack.NIGHTMARE_SHARD_STACK),
                ThreadLocalRandom.current().nextInt(7) + 4 ));
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "A nightmare has been defeated!");
        }
        if (e.getEntity().getCustomName() != null &&
        e.getEntity().getCustomName().equals((ChatColor.WHITE) + "Panda Bullet!")) {
            e.getDrops().clear();
        }
    }


    @EventHandler
    public void onProyectileHitEvent(ProjectileHitEvent e) {
        if (e.getEntity().getType().equals(EntityType.WITHER_SKULL) &&
            ( (Wither) e.getEntity().getShooter()).getCustomName() == ChatColor.DARK_GRAY + "The Nightmare") {
            if (e.getHitEntity() != null) {
                for (Entity entity : e.getHitEntity().getWorld().getNearbyEntities(e.getHitEntity().getLocation(),
                    3,3,3, entity -> entity instanceof LivingEntity)) {
                    ((LivingEntity ) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,
                        30,1));
                    ((LivingEntity ) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
                        30,2));
                }
            } else {
                for (Entity entity : e.getHitBlock().getWorld().getNearbyEntities(e.getHitBlock().getLocation(),
                    3,3,3, entity -> entity instanceof LivingEntity)) {
                    ((LivingEntity ) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,
                        30,1));
                    ((LivingEntity ) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
                        30,2));
                }
            }
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent e) {
        if ((e.getEntity().getCustomName() != null &&
            (e.getEntity().getCustomName() == ChatColor.DARK_GRAY + "The Nightmare" ||
                e.getEntity().getCustomName() == ChatColor.BLACK + "The True Nightmare") &&
            e.getTarget().getCustomName() != null &&
            (e.getTarget().getCustomName() == ChatColor.DARK_GRAY + "Nightmare Followers" ||
                e.getTarget().getCustomName() == ChatColor.DARK_GRAY + "Fiendish Followers"))
            ||
            (e.getEntity().getCustomName() != null &&
                (e.getEntity().getCustomName() == ChatColor.DARK_GRAY + "Nightmare Followers" ||
                    e.getEntity().getCustomName() == ChatColor.DARK_GRAY + "Fiendish Followers") &&
                e.getTarget().getCustomName() != null &&
                (e.getTarget().getCustomName() == ChatColor.DARK_GRAY + "The Nightmare" ||
                    e.getTarget().getCustomName() == ChatColor.BLACK + "The True Nightmare"))) {
            e.setCancelled(true); // Basically, makes the boss not target it's minions and viceversa.
        }
    }

    @EventHandler
    public void onAncientAltarCraft(AncientAltarCraftEvent e) {
        if (SlimefunUtils.isItemSimilar(e.getItem(), ExtraItemStack.NIGHTMARE_SUMMON_STACK, true)) {
            spawnNightmare(e.getAltarBlock().getLocation().clone().add(0,2,0), e.getPlayer().getUniqueId());
            e.setCancelled(true);
        }
    }

    private void spawnNightmare(Location l, UUID u) {
        l.getWorld().createExplosion(l, 10);
        l.getWorld().playSound(l,
            Sound.ENTITY_WITHER_AMBIENT, 1, 1);
        Nightmare.createNightmare(l.clone().add(0,2,0));
        Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY +
            "A nightmare has spawned in this world");
        Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY +
            "This beast is imbued with incredible power");
        Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY +
            "Use Mythril Equippement to defeat him!");
    }
}
