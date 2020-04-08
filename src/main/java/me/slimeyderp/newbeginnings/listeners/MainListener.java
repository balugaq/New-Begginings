package me.slimeyderp.newbeginnings.listeners;

import io.github.thebusybiscuit.slimefun4.api.events.GEOResourceGenerationEvent;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.materials.ExtraItemStack;
import me.slimeyderp.newbeginnings.mobs.Nightmare;
import me.slimeyderp.newbeginnings.resources.MythrilResource;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


import java.util.Collection;
import java.util.HashMap;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MainListener implements Listener {

    public static HashSet<UUID> disableDamageForPlayer = new HashSet<>();
    public static HashMap<UUID,Integer> playerBladeCooldown = new HashMap<>();
    public static Player dashDamageDisable = null;
    private static HashMap<UUID, Integer> playerBossParticle = new HashMap<>();
    private static HashMap<UUID, Integer> playerBossSpawn = new HashMap<>();
    private static HashSet<Location> locationHashSet = new HashSet<>();
    private int h = 0;

    public MainListener(NewBeginnings plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onResourceGeneration(GEOResourceGenerationEvent e) {
        if (e.getWorld() == Bukkit.getServer().getWorld("world_the_end") &&
            e.getResource() instanceof MythrilResource) {

            e.setValue(ThreadLocalRandom.current().nextInt(4));
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player &&
            SlimefunManager.isItemSimilar(((Player) e.getDamager()).getInventory().getItemInMainHand(),
            ExtraItemStack.MYTHRIL_BLADE_STACK, true) &&
            e.getEntity().getCustomName() != null &&
            e.getEntity().getCustomName().equals(ChatColor.DARK_GRAY + "The Nightmare"))
        { e.setDamage(e.getDamage()*3); }

        if (e.getEntity() instanceof Player &&
            disableDamageForPlayer.contains(((Player) e.getEntity()).getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getCustomName() != null &&
            e.getEntity().getCustomName().equals(ChatColor.DARK_GRAY + "The Nightmare")) {
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
    public void onBlockRightClick(PlayerRightClickEvent e) {
        if (e.getClickedBlock().isPresent() && e.getClickedBlock().get().getType() == Material.BEACON &&
        SlimefunManager.isItemSimilar(e.getPlayer().getInventory().getItemInMainHand(),
            ExtraItemStack.MYTHRIL_STACK, true)) {
            Location ritualBeaconLocation = e.getClickedBlock().get().getLocation();
            if (checkIfRitualExists(ritualBeaconLocation)) {
                if (locationHashSet.contains(ritualBeaconLocation)) {
                    e.getPlayer().sendMessage(ChatColor.RED + "There is already a ritual" +
                        " being performed here.");
                } else if (playerBossParticle.containsKey(e.getPlayer().getUniqueId())) {
                    e.getPlayer().sendMessage(ChatColor.RED + "Please wait until" +
                        " the current ritual finishes.");
                } else {
                    locationHashSet.add(ritualBeaconLocation);
                    if (e.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
                        e.getPlayer().getInventory().getItemInMainHand().setAmount
                            (e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                    } else { e.getPlayer().getInventory().setItemInMainHand(null); }
                    playerBossParticle.put(e.getPlayer().getUniqueId(),
                        Bukkit.getScheduler().runTaskTimer(NewBeginnings.getInstance(),
                            () -> createParticles(ritualBeaconLocation,
                                e.getPlayer().getUniqueId()), 20, 20).getTaskId());

                    playerBossSpawn.put(e.getPlayer().getUniqueId(), Bukkit.getScheduler()
                        .runTaskLater(NewBeginnings.getInstance(),
                            () -> spawnNightmare(ritualBeaconLocation,
                                e.getPlayer().getUniqueId()), 120).getTaskId());
                }
                e.cancel();
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) { playerBladeCooldown.put(e.getPlayer().getUniqueId(), 20); }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        playerBladeCooldown.remove(e.getPlayer().getUniqueId());
    }

    private boolean checkIfRitualExists(Location l) {
        if (checkIfBlockIsType(l.clone(), Material.BEACON) &&
            checkIfBlockIsType(l.clone().add(5,0,0), Material.REDSTONE_BLOCK) &&
            checkIfBlockIsType(l.clone().add(-5,0,0), Material.REDSTONE_BLOCK) &&
            checkIfBlockIsType(l.clone().add(0,0,5), Material.REDSTONE_BLOCK) &&
            checkIfBlockIsType(l.clone().add(0,0,-5), Material.REDSTONE_BLOCK) &&
            checkIfBlockIsType(l.clone().add(3,0,3), Material.QUARTZ_PILLAR) &&
            checkIfBlockIsType(l.clone().add(-3,0,3), Material.QUARTZ_PILLAR) &&
            checkIfBlockIsType(l.clone().add(3,0,-3), Material.QUARTZ_PILLAR) &&
            checkIfBlockIsType(l.clone().add(-3,0,-3), Material.QUARTZ_PILLAR) &&
            checkIfBlockIsType(l.clone().add(3,1,3), Material.DIAMOND_BLOCK) &&
            checkIfBlockIsType(l.clone().add(-3,1,-3), Material.DIAMOND_BLOCK) &&
            checkIfBlockIsType(l.clone().add(3,1,-3), Material.DIAMOND_BLOCK) &&
            checkIfBlockIsType(l.clone().add(-3,1,3), Material.DIAMOND_BLOCK)
        ) { return true; } else { return false; }
    }

    private boolean checkIfBlockIsType(Location l, Material m) {
        return l.getBlock().getType() == (m);
    }

    private void createParticles(Location l, UUID u) {
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(1,1,1), 1,
            new Particle.DustOptions(Color.RED, 5));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(-1,1,-1), 1,
            new Particle.DustOptions(Color.RED, 5));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(1,1,-1), 1,
            new Particle.DustOptions(Color.RED, 5));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(-1,1,1), 1,
            new Particle.DustOptions(Color.RED, 5));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(2,1,2), 1,
            new Particle.DustOptions(Color.RED, 5));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(-2,1,-2), 1,
            new Particle.DustOptions(Color.RED, 5));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(2,1,-2), 1,
            new Particle.DustOptions(Color.RED, 5));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(-2,1,2), 1,
            new Particle.DustOptions(Color.RED, 5));
        l.getWorld().playSound(l, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

        if (!checkIfRitualExists(l)) {
            Bukkit.getScheduler().cancelTask(playerBossSpawn.get(u));
            ritualDisruption(l,u);
        }
        h++;
        if (h > 5) {
            Bukkit.getScheduler().cancelTask(this.playerBossParticle.get(u));
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
        locationHashSet.remove(l);
        Bukkit.getScheduler().cancelTask(playerBossParticle.remove(u));
        Bukkit.getScheduler().cancelTask(playerBossSpawn.remove(u));
    }

    private void ritualDisruption(Location l, UUID u) {
        l.getWorld().createExplosion(l, 10);
        Collection<Entity> nearbyEntities = l.getWorld().getNearbyEntities(l, 15, 15, 15);
        for (Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity) {
                LivingEntity currentAlive = (LivingEntity) entity;
                currentAlive.setHealth(0);
            }
        }
        Bukkit.getScheduler().cancelTask(playerBossParticle.remove(u));
        Bukkit.getScheduler().cancelTask(playerBossSpawn.remove(u));
    }
}
