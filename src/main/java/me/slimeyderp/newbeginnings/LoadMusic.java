package me.slimeyderp.newbeginnings;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;

public class LoadMusic {
    private static Song s = NBSDecoder.parse(NewBeginnings.getInstance().getResource("LeBadApple.nbs"));
    public static PositionSongPlayer psp = new PositionSongPlayer(s);

}
