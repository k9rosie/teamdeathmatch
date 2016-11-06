package com.k9rosie.teamdeathmatch.listener;

import com.k9rosie.novswar.NovsWar;
import com.k9rosie.novswar.event.NovsWarInitializationEvent;
import com.k9rosie.novswar.event.NovsWarPlayerDeathEvent;
import com.k9rosie.novswar.event.NovsWarPlayerKillEvent;
import com.k9rosie.novswar.gamemode.Gamemode;
import com.k9rosie.teamdeathmatch.TeamDeathmatch;
import com.k9rosie.teamdeathmatch.TeamDeathmatchPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ServerListener implements Listener {
    private TeamDeathmatchPlugin plugin;
    private TeamDeathmatch tdm;

    public ServerListener(TeamDeathmatchPlugin plugin) {
        this.plugin = plugin;
        tdm = plugin.getGamemode();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onNovsWarInitialize(NovsWarInitializationEvent event) {
        plugin.getLogger().info("hooking gamemode into novswar");
        tdm.setNovswar(event.getNovsWar());
        event.getNovsWar().getGamemodes().put(tdm.getGamemodeName(), tdm);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNovsWarPlayerKill(NovsWarPlayerKillEvent event) {
        switch (tdm.getScoreType()) {
            case ASCENDING:
                event.getAttackerTeam().getNovsScore().incrementScore();
            case DESCENDING:
                event.getVictimTeam().getNovsScore().decrementScore();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNovsWarPlayerDeath(NovsWarPlayerDeathEvent event) {
        if (event.isSuicide()) {
            if (tdm.getScoreType() == Gamemode.ScoreType.DESCENDING) {
                event.getVictimTeam().getNovsScore().decrementScore();
            }
        }
    }
}
