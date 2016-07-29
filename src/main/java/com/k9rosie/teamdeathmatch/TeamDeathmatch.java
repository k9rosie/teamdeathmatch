package com.k9rosie.teamdeathmatch;

import com.k9rosie.novswar.NovsWar;
import com.k9rosie.novswar.gamemode.Gamemode;

public class TeamDeathmatch implements Gamemode {
    private TeamDeathmatchPlugin plugin;
    private NovsWar novswar;

    private int gameTime;
    private int deathTime;
    private int maxScore;
    private String gamemodeName;

    public TeamDeathmatch(TeamDeathmatchPlugin plugin) {
        this.plugin = plugin;
        gameTime = 120;
        deathTime = 5;
        maxScore = 3;
        gamemodeName = "tdm";
    }

    public void initialize() {

    }

    public int getGameTime() {
        return gameTime;
    }

    public int getDeathTime() {
        return deathTime;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getGamemodeName() {
        return gamemodeName;
    }

    public void setNovswar(NovsWar novswar) {
        this.novswar = novswar;
    }

    public NovsWar getNovsWar() {
        return novswar;
    }

}
