package me.slimeyderp.newbeginnings.materials;

import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;

public class ExtraItemStack {

    //This area has all the Itemstacks of the items

    public static final SlimefunItemStack MYTHRIL_ORE_STACK = new SlimefunItemStack(
        "MYTHRIL_ORE",
        "6c12ff7dcad1a235a289bbb75541dc99fe3e5f4392fa16ee6c0a80fd19226a1",
        "&1Mythril Ore",
        "", "&bIt's been said it's the", "&bstrongest of all metals."
    );
    public static final SlimefunItemStack MYTHRIL_STACK = new SlimefunItemStack(
        "MYTHRIL",
        Material.DIAMOND,
        "&1Mythril",
        "", "&bIt's been said it's the", "&bstrongest of all metals."
    );
    public static final SlimefunItemStack MYTHRIL_HELMET_STACK = new SlimefunItemStack(
        "MYTHRIL_HELMET",
        Material.DIAMOND_HELMET,
        "&1Mythril Helmet",
        "", "&bStrong enough to resist", "&bnearly any damage."
    );
    public static final SlimefunItemStack MYTHRIL_CHESTPLATE_STACK = new SlimefunItemStack(
        "MYTHRIL_CHESTPLATE",
        Material.DIAMOND_CHESTPLATE,
        "&1Mythril Chestplate (Chestplate Mode)",
        "", "&bStrong enough to resist", "&bnearly any damage.",
        "&aSHIFT + RIGHT CLICK in your Main Hand", "&ato change into Elytra Mode"
    );
    public static final SlimefunItemStack MYTHRIL_CHESTPLATE_ELYTRA_STACK = new SlimefunItemStack(
        "MYTHRIL_CHESTPLATE_ELYTRA",
        Material.ELYTRA,
        "&1Mythril Chestplate (Elytra Mode)",
        "", "&bStrong enough to resist", "&bnearly any damage.",
        "&aSHIFT + RIGHT CLICK Main Hand", "&ato change into Chestplate Mode"
    );
    public static final SlimefunItemStack MYTHRIL_LEGGINGS_STACK = new SlimefunItemStack(
        "MYTHRIL_LEGGINGS",
        Material.DIAMOND_LEGGINGS,
        "&1Mythril Leggings",
        "", "&bStrong enough to resist", "&bnearly any damage."
    );
    public static final SlimefunItemStack MYTHRIL_BOOTS_STACK = new SlimefunItemStack(
        "MYTHRIL_BOOTS",
        Material.DIAMOND_BOOTS,
        "&1Mythril Boots",
        "", "&bStrong enough to resist", "&bnearly any damage."
    );
    public static final SlimefunItemStack MYTHRIL_BLADE_STACK = new SlimefunItemStack(
        "MYTHRIL_BLADE",
        Material.DIAMOND_SWORD,
        "&1Mythril Sword",
        "", "&bSharper than a thousand", "&bhellish knives",
        "&aRIGHT CLICK in your Main Hand", "&a to do an AOE Attack", "&a(10 Seconds Cooldown)"
    );
    public static final SlimefunItemStack MYTHRIL_PICKAXE_STACK = new SlimefunItemStack(
        "MYTHRIL_PICKAXE",
        Material.DIAMOND_PICKAXE,
        "&1Mythril Pickaxe",
        "", "&bStone's nightmare, this", "&btool will demolish anything!"
    );
    public static final SlimefunItemStack INFINITE_FIREWORK_STACK = new SlimefunItemStack(
        "INFINITE_FIREWORK",
        Material.FIREWORK_ROCKET,
        "&1Infinite Rocket",
        "", "&bThis special rocket seems to", "&bnever run out. Use it with an Elytra!"
    );
    public static final SlimefunItemStack ENDER_PEARL_GENERATOR_1_STACK = new SlimefunItemStack(
        "ENDER_PEARL_GENERATOR_1",
        "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730",
        "&1Ender Pearl Generator",
        "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.GENERATOR),
        LoreBuilder.powerBuffer(128), LoreBuilder.powerPerSecond(30)
    );
    public static final SlimefunItemStack ENDER_PEARL_GENERATOR_2_STACK = new SlimefunItemStack(
        "ENDER_PEARL_GENERATOR_2",
        "9343ce58da54c79924a2c9331cfc417fe8ccbbea9be45a7ac85860a6c730",
        "&1Ender Pearl Generator &7(&eII&7)",
        "", LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR),
        LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(64)
    );
    public static final SlimefunItemStack NIGHTMARE_SHARD_STACK = new SlimefunItemStack(
        "NIGHTMARE_SHARD",
        Material.QUARTZ,
        "&8Nightmare Shard",
        "", "&bA white-pale material that", "&bembodies a hollow substance."
    );
    public static final SlimefunItemStack RADIANT_SHARD_STACK = new SlimefunItemStack(
        "RADIANT_SHARD",
        Material.QUARTZ,
        "&6Radiant Shard",
        "", "&bA white-pale material that", "&bembodies a radiant substance."
    );
    public static final SlimefunItemStack RADIANT_HELMET_STACK = new SlimefunItemStack(
        "RADIANT_HELMET",
        Material.GOLDEN_HELMET,
        "&6Radiant Helmet",
        "", "&bShines with the", "&bglare of a thousand suns.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to convert your enemies into", "&ahollow husks of themselves",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack RADIANT_CHESTPLATE_STACK = new SlimefunItemStack(
        "RADIANT_CHESTPLATE",
        Material.GOLDEN_CHESTPLATE,
        "&6Radiant Chestplate ( Chestplate Mode )",
        "", "&bShines with the", "&bglare of a thousand suns.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to convert your enemies into", "&ahollow husks of themselves",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack RADIANT_CHESTPLATE_ELYTRA_STACK = new SlimefunItemStack(
        "RADIANT_CHESTPLATE_ELYTRA",
        Material.ELYTRA,
        "&6Radiant Chestplate ( Elytra Mode )",
        "", "&bShines with the", "&bglare of a thousand suns.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to convert your enemies into", "&ahollow husks of themselves",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack RADIANT_LEGGINGS_STACK = new SlimefunItemStack(
        "RADIANT_LEGGINGS",
        Material.GOLDEN_LEGGINGS,
        "&6Radiant Leggings",
        "", "&bShines with the", "&bglare of a thousand suns.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to convert your enemies into", "&ahollow husks of themselves",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack RADIANT_BOOTS_STACK = new SlimefunItemStack(
        "RADIANT_BOOTS",
        Material.GOLDEN_BOOTS,
        "&6Radiant Boots",
        "", "&bShines with the", "&bglare of a thousand suns.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to convert your enemies into", "&ahollow husks of themselves",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack NIGHTMARE_HELMET_STACK = new SlimefunItemStack(
        "NIGHTMARE_HELMET",
        Material.LEATHER_HELMET,
        "&8Nightmare Helmet",
        "", "&bEngulfs all light", "&bnear it's sight.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to pull your enemies", "&awith the power of your void",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack NIGHTMARE_CHESTPLATE_STACK = new SlimefunItemStack(
        "NIGHTMARE_CHESTPLATE",
        Material.LEATHER_CHESTPLATE,
        "&6Nightmare Chestplate ( Chestplate Mode )",
        "", "&bEngulfs all light", "&bnear it's sight.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to pull your enemies", "&awith the power of your void",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack NIGHTMARE_CHESTPLATE_ELYTRA_STACK = new SlimefunItemStack(
        "NIGHTMARE_CHESTPLATE_ELYTRA",
        Material.ELYTRA,
        "&6Nightmare Chestplate ( Elytra Mode )",
        "", "&bEngulfs all light", "&bnear it's sight.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to pull your enemies", "&awith the power of your void",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack NIGHTMARE_LEGGINGS_STACK = new SlimefunItemStack(
        "NIGHTMARE_LEGGINGS",
        Material.LEATHER_LEGGINGS,
        "&6Nightmare Leggings",
        "", "&bEngulfs all light", "&bnear it's sight.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to pull your enemies", "&awith the power of your void",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack NIGHTMARE_BOOTS_STACK = new SlimefunItemStack(
        "NIGHTMARE_BOOTS",
        Material.LEATHER_BOOTS,
        "&6Nightmare Boots",
        "", "&bEngulfs all light", "&bnear it's sight.",
        "&aFull armor Bonus: SHIFT-TWICE", "&a to pull your enemies", "&awith the power of your void",
        "&a(35 Seconds Cooldown)", "&a( If their power is too strong, it will", "&anot have an effect )"
    );
    public static final SlimefunItemStack NIGHTMARE_SUMMON_STACK = new SlimefunItemStack(
        "NIGHTMARE_SUMMON",
        Material.END_CRYSTAL,
        "&6The Nightmare",
        "", "&cWARNING: Creating this item will", "&cspawn a nightmare with power beyond",
        "&climits. Make this on an open area", "&cand we wish you luck to manage surviving."
    );
    public static final SlimefunItemStack DREAM_NAIL_STACK = new SlimefunItemStack(
        "DREAM_NAIL",
        Material.IRON_SWORD,
        "&6Dream Nail",
        "", "&bBreach between the dreams", "&band the reality with this weapon.",
        "&aRIGHT CLICK in your Main Hand", "&a to do Shadow Dash", "&a(5 Seconds Cooldown)"
    );
    public static final SlimefunItemStack PANDA_BAZOOKA_STACK = new SlimefunItemStack(
        "PANDA_BAZOOKA",
        Material.DIAMOND_HOE,
        "&8Panda Bazooka",
        "", "&bSay hello to my little friend!"
    );
}
