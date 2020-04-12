package me.slimeyderp.newbeginnings.mobs;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;

import java.util.concurrent.ThreadLocalRandom;

public class Nightmare {

    // I mean, it was obvious what was going on. It's just a tough af wither, with added health.

    public static void createNightmare(Location l) {

        Wither nightmare = (Wither) l.getWorld().spawnEntity(l, EntityType.WITHER);
        Attributable nightmareAtributtable = nightmare;
        nightmareAtributtable.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
        nightmareAtributtable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(25);
        nightmareAtributtable.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(5);
        nightmare.setHealth(2048);
        nightmare.setCustomName(ChatColor.DARK_GRAY + "The Nightmare");
        nightmare.setCustomNameVisible(true);
    }
    public static void secondPhaseCreate(Location l) {
        Wither secondPhaseNightmare = (Wither) l.getWorld().spawnEntity(l, EntityType.WITHER);
        Attributable nightmareAtributtable = secondPhaseNightmare;
        nightmareAtributtable.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
        nightmareAtributtable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
        nightmareAtributtable.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(8);
        nightmareAtributtable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1.2);
        secondPhaseNightmare.setHealth(1028);
        secondPhaseNightmare.setCustomName(ChatColor.BLACK + "The True Nightmare");
        secondPhaseNightmare.setCustomNameVisible(true);
    }

    public static void spawnNightmareFollowers(Location l, float power, String name) {
        EntityType entityType = EntityType.PANDA; // For the lols, and so that the IDE doesn't scream.
        switch(ThreadLocalRandom.current().nextInt(5)) {
            case 0:
            case 1:
                entityType = EntityType.PHANTOM;
                break;
            case 2:
                entityType = EntityType.SKELETON;
                break;
            case 3:
                entityType = EntityType.WITHER_SKELETON;
                break;
            case 4:
                entityType = EntityType.WITCH;
                break;
        }
        LivingEntity nightmareFollowers = (LivingEntity) l.getWorld().spawnEntity
            (l.clone().add(ThreadLocalRandom.current().nextInt(11) - 5,
            ThreadLocalRandom.current().nextInt(11) - 5,
            ThreadLocalRandom.current().nextInt(11) - 5), entityType);
        Attributable nightmareFollowersAttributable = nightmareFollowers;
        nightmareFollowersAttributable.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(
            (nightmareFollowers.getHealth() * power) + nightmareFollowers.getHealth());
        nightmareFollowersAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(
            (nightmareFollowers.getHealth() * power) + nightmareFollowers.getHealth());
        nightmareFollowersAttributable.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(
            (nightmareFollowers.getHealth() * power) + nightmareFollowers.getHealth());
        nightmareFollowers.setHealth((nightmareFollowers.getHealth() * power) + nightmareFollowers.getHealth());
        nightmareFollowers.setCustomName(ChatColor.DARK_GRAY + name);
        nightmareFollowers.setCustomNameVisible(true);
    }
}