package me.slimeyderp.newbeginnings;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public final class NewCategory {
    public static final Category CUSTOM_CATEGORY = new Category(
        new NamespacedKey(NewBeginnings.getInstance(), "new_beginnings"),
        new CustomItem(Material.END_CRYSTAL, "New Beginnings")
    );

    private NewCategory() {}
}
