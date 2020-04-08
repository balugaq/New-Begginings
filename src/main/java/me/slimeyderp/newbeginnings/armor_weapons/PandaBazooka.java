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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Panda;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PandaBazooka extends SlimefunItem {

    private static HashMap<UUID, Integer> pandaShootCooldown = new HashMap<>();

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
            Location playerLookingLocation = e.getPlayer().getEyeLocation().toVector().add(
                e.getPlayer().getLocation().getDirection().normalize()
            ).toLocation(e.getPlayer().getWorld());
            Panda flyingCrapPanda = (Panda) e.getPlayer().getWorld().spawnEntity(playerLookingLocation,
                EntityType.PANDA);
            flyingCrapPanda.setAI(false);
            flyingCrapPanda.setVelocity(e.getPlayer().getEyeLocation()
                .getDirection().setY(0).normalize().multiply(0.5));
            long secondsPassed = (System.currentTimeMillis() + 3000);
            pandaShootCooldown.put(e.getPlayer().getUniqueId(),
                Bukkit.getScheduler().runTaskTimer(NewBeginnings.getInstance(),
                    () -> FlyingCrappingPandaCollisionCheck(flyingCrapPanda, e.getPlayer().getUniqueId(),
                        secondsPassed), 10, 3).getTaskId());
        }
        e.cancel();
    }

    private void FlyingCrappingPandaCollisionCheck(Panda p, UUID u, long time) {
        p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 1);
        if (((p.getLocation().getBlock().getType() != Material.AIR &&
            p.getLocation().getBlock().getType() != Material.CAVE_AIR &&
            p.getLocation().getBlock().getType() != Material.VOID_AIR &&
            p.getLocation().getBlock().getType() != Material.GRASS &&
            p.getLocation().getBlock().getType() != Material.TALL_GRASS) ||
            !p.getNearbyEntities(1,1,1).isEmpty()) ||
        System.currentTimeMillis() > time) {
            p.getWorld().createExplosion(p.getLocation(), 10, false, false);
            p.remove();
            int taskID = pandaShootCooldown.get(u);
            pandaShootCooldown.remove(u);
            Bukkit.getScheduler().cancelTask(taskID);
        }
    }
}
