package me.slimeyderp.newbeginnings.listeners;

import io.github.thebusybiscuit.slimefun4.api.events.GEOResourceGenerationEvent;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.slimeyderp.newbeginnings.LoadMusic;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.materials.ExtraItemStack;
import me.slimeyderp.newbeginnings.mobs.Nightmare;
import me.slimeyderp.newbeginnings.resources.MythrilResource;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;


import java.util.concurrent.ThreadLocalRandom;

public class MainListener implements Listener {

    int h = 0;
    int particle;

    public MainListener(NewBeginnings plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // This EventHandler makes sure the world the resource is in, is actually the End,
    // to make sure it doesn't run into compatibility issues with other
    // worlds with "THE_END" as Environment.

    @EventHandler
    public void onResourceGeneration(GEOResourceGenerationEvent e) {
        if (e.getWorld() == Bukkit.getServer().getWorld("world_the_end") &&
            e.getResource() instanceof MythrilResource) {

            e.setValue(ThreadLocalRandom.current().nextInt(12) + 3);
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player &&
            SlimefunManager.isItemSimilar(((Player) e.getDamager()).getInventory().getItemInMainHand(),
            ExtraItemStack.MYTHRIL_BLADE_STACK, true) &&
            e.getEntity().getCustomName() == "§8The Nightmare")
        { e.setDamage(e.getDamage()*20); }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getCustomName() == "§8The Nightmare") {
            e.getDrops().clear();
            e.getDrops().add(new CustomItem((ExtraItemStack.NIGHTMARE_SHARD_STACK),
                ThreadLocalRandom.current().nextInt(7) + 4 ));
            LoadMusic.psp.setPlaying(false);
            Bukkit.getServer().broadcastMessage("§8A nightmare has been defeated!");
        }
    }

    @EventHandler
    public void onBlockRightClick(PlayerRightClickEvent e) {
        if (e.getClickedBlock().isPresent() && e.getClickedBlock().get().getType() == Material.BEACON &&
        SlimefunManager.isItemSimilar(e.getPlayer().getInventory().getItemInMainHand(),
            ExtraItemStack.MYTHRIL_STACK, true)) {
            Location ritualBeaconLocation = e.getClickedBlock().get().getLocation();
            if (checkIfBlockIsType(ritualBeaconLocation.clone().add(5,0,0), Material.REDSTONE) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(-5,0,0), Material.REDSTONE) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(0,0,5), Material.REDSTONE) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(0,0,-5), Material.REDSTONE) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(3,0,3), Material.QUARTZ_PILLAR) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(-3,0,3), Material.QUARTZ_PILLAR) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(3,0,-3), Material.QUARTZ_PILLAR) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(-3,0,-3), Material.QUARTZ_PILLAR) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(3,1,3), Material.DIAMOND_BLOCK) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(-3,1,-3), Material.DIAMOND_BLOCK) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(3,1,-3), Material.DIAMOND_BLOCK) &&
                checkIfBlockIsType(ritualBeaconLocation.clone().add(-3,1,3), Material.DIAMOND_BLOCK)
            ) {
                particle = Bukkit.getScheduler().runTaskTimer(NewBeginnings.getInstance(),
                    () -> createParticles(ritualBeaconLocation), 20,20).getTaskId();
                ritualBeaconLocation.getWorld().createExplosion(ritualBeaconLocation, 2);
                ritualBeaconLocation.getWorld().playSound(ritualBeaconLocation, Sound.ENTITY_WITHER_AMBIENT
                , 1, 1);
                Nightmare.createNightmare(ritualBeaconLocation.clone().add(0,2,0));
                Bukkit.getServer().broadcastMessage("§8A nightmare has spawned in this world");
                Bukkit.getServer().broadcastMessage("§8This beast is imbued with incredible power");
                Bukkit.getServer().broadcastMessage("§8Use Mythril Equippement to defeat him!");
            }
        }
    }

    private boolean checkIfBlockIsType(Location l, Material m) {
        return l.getBlock().getType() == (m);
    }

    private void createParticles(Location l) {
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(1,0,1), 1,
            new Particle.DustOptions(Color.RED, 2));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(-1,0,-1), 1,
            new Particle.DustOptions(Color.RED, 2));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(1,0,-1), 1,
            new Particle.DustOptions(Color.RED, 2));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(-1,0,1), 1,
            new Particle.DustOptions(Color.RED, 2));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(2,0,2), 1,
            new Particle.DustOptions(Color.RED, 2));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(-2,0,-2), 1,
            new Particle.DustOptions(Color.RED, 2));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(2,0,-2), 1,
            new Particle.DustOptions(Color.RED, 2));
        l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(-2,0,2), 1,
            new Particle.DustOptions(Color.RED, 2));
        l.getWorld().playSound(l, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        h++;
        if (h > 5) {
            Bukkit.getScheduler().cancelTask(particle);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        LoadMusic.psp.addPlayer(e.getPlayer().getUniqueId());
    }
}
