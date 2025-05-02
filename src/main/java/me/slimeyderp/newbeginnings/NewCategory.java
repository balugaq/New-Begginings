package me.slimeyderp.newbeginnings;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public final class NewCategory {

    public static final ItemGroup CUSTOM_CATEGORY = new ItemGroup(
        new NamespacedKey(NewBeginnings.getInstance(), "new_beginnings"),
        new CustomItemStack(Material.END_CRYSTAL, "New Beginnings")
    );

    private NewCategory() {}
}
