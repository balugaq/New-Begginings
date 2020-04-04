package me.slimeyderp.newbeginnings.listeners;

import io.github.thebusybiscuit.slimefun4.api.events.GEOResourceGenerationEvent;
import me.slimeyderp.newbeginnings.NewBeginnings;
import me.slimeyderp.newbeginnings.resources.MythrilResource;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class MainListener implements Listener {

    public MainListener(NewBeginnings plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // This EventHandler makes sure the world the resource is in, is actually the End,
    // to make sure it doesn't run into compatibility issues with other
    // worlds with "THE_END" as Environment.

    @EventHandler
    public void onResourceGeneration(GEOResourceGenerationEvent e){
        if (e.getWorld() == Bukkit.getServer().getWorld("world_the_end") &&
        e.getResource() instanceof MythrilResource) {

            e.setValue(ThreadLocalRandom.current().nextInt(12) + 3);
        }
    }
}
