package me.slimeyderp.newbeginnings.armor_weapons;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.listeners.MainListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;


public class DreamNail extends SlimefunItem {

    public static HashSet<UUID> playerUsing = new HashSet<>();
    public static HashMap<UUID, Integer> cooldownPlayer = new HashMap<>();
    byte cooldownTimer;

    public DreamNail(Category category, SlimefunItemStack item, RecipeType recipeType,
                        ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemRightClick(PlayerRightClickEvent e) {
        if (playerUsing.contains(e.getPlayer().getUniqueId())) {
            e.getPlayer().sendMessage(ChatColor.RED + "You need to wait " + cooldownTimer +
                " seconds before using this ability again");
        } else {
            playerUsing.add(e.getPlayer().getUniqueId());
            for (Entity entity : e.getPlayer().getNearbyEntities(5,5,5)) {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).addPotionEffect
                        (new PotionEffect(PotionEffectType.BLINDNESS,40,1));
                    ((LivingEntity) entity).addPotionEffect
                        (new PotionEffect(PotionEffectType.WITHER,60,5));
                    if (entity instanceof Player) {
                        entity.sendMessage(ChatColor.DARK_GRAY + "The Void passes through you");
                    }
                }
            }
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(10));
            MainListener.disableDamageForPlayer.add(e.getPlayer().getUniqueId());
            cooldownTimer = 5;
            Bukkit.getScheduler().runTaskLater(NewBeginnings.getInstance(),
                () -> FinishDash(e.getPlayer()), 5);
            cooldownPlayer.put(e.getPlayer().getUniqueId(),
                Bukkit.getScheduler().runTaskTimer(NewBeginnings.getInstance(),
                () -> Cooldown(e.getPlayer()), 20, 20).getTaskId());

        }
    }

    private void FinishDash(Player p) {
        MainListener.disableDamageForPlayer.remove(p.getUniqueId());
        p.setVelocity(p.getLocation().getDirection().multiply(1));
    }

    private void Cooldown(Player p) {
        cooldownTimer--;
        if (cooldownTimer < 1) {
            playerUsing.remove(p.getUniqueId());
            Bukkit.getScheduler().cancelTask(cooldownPlayer.get(p.getUniqueId()));
        }
    }
}
