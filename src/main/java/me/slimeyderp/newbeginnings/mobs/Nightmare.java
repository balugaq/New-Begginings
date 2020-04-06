package me.slimeyderp.newbeginnings.mobs;


import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import me.slimeyderp.newbeginnings.LoadMusic;
import me.slimeyderp.newbeginnings.NewBeginnings;
import org.bukkit.Location;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;

public class Nightmare {

    // I mean, it was obvious what was going on. It's just a tough af wither, with added health.

    public static void createNightmare(Location l) {
        Damageable nightmare = (Damageable) l.getWorld().spawnEntity(l, EntityType.WITHER);
        Attributable nightmare_Atributtable = (Attributable) nightmare;
        nightmare_Atributtable.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10000);
        nightmare_Atributtable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(15);
        nightmare_Atributtable.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(6);
        nightmare.setHealth(10000);
        nightmare.setCustomName("§8The Nightmare");
        nightmare.setCustomNameVisible(true);
        LoadMusic.psp.setTargetLocation(l);
        LoadMusic.psp.setDistance(64);
        LoadMusic.psp.setRepeatMode(RepeatMode.ALL);
        LoadMusic.psp.setPlaying(true);
    }
}