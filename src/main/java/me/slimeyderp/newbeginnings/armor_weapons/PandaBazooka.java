package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.slimeyderp.newbeginnings.NewBeginnings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class PandaBazooka extends SlimefunItem {

    private static HashMap<UUID, Integer> pandaShootCooldown = new HashMap<>();

    private double t;

    private Location loc;

    private Vector direction;

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
            flyingCrapPanda.setCustomName(ChatColor.WHITE + "Panda Bullet!");
            flyingCrapPanda.setCustomNameVisible(true);
            loc = e.getPlayer().getEyeLocation();
            t = 0;
            direction = loc.getDirection().normalize();
            long secondsPassed = (System.currentTimeMillis() + 3000);
            pandaShootCooldown.put(e.getPlayer().getUniqueId(),
                Bukkit.getScheduler().runTaskTimer(NewBeginnings.getInstance(),
                    () -> FlyingCrappingPandaCollisionCheck(flyingCrapPanda, e.getPlayer(),
                        secondsPassed), 0, 0).getTaskId());
        }
        e.cancel();
    }

    private void FlyingCrappingPandaCollisionCheck(Panda p, Player player, long time) {
        t = t + 2.5;
        Location newLoc = loc.clone().add(direction.getX() * t,
            direction.getY() * t, direction.getZ() * t);
        p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, newLoc , 0 ,0 ,0 ,0 , 1);
        newLoc.setY(newLoc.getY() + 0.75);
        p.teleport(newLoc);
        if (((p.getLocation().getBlock().getType() != Material.AIR &&
            p.getLocation().getBlock().getType() != Material.CAVE_AIR &&
            p.getLocation().getBlock().getType() != Material.VOID_AIR &&
            p.getLocation().getBlock().getType() != Material.GRASS &&
            p.getLocation().getBlock().getType() != Material.TALL_GRASS) ||
            PandaNearThing(p, player)) ||
        System.currentTimeMillis() > time) {
            p.getWorld().createExplosion(p.getLocation(), 10, false, false);
            p.remove();
            int taskID = pandaShootCooldown.get(player.getUniqueId());
            pandaShootCooldown.remove(player.getUniqueId());
            Bukkit.getScheduler().cancelTask(taskID);
        }
    }

    private boolean PandaNearThing(Panda pand, Player p) {
        if (pand.getNearbyEntities(1,1,1).isEmpty()) { return false; }
        else if (pand.getNearbyEntities(1,1,1).contains(p))
        { return false; } else { return true; }
    }
}
