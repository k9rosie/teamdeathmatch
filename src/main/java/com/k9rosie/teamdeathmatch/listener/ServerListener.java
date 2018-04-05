package com.k9rosie.teamdeathmatch.listener;

import com.k9rosie.novswar.event.NovsWarInitializationEvent;
import com.k9rosie.novswar.event.NovsWarPlayerDeathEvent;
import com.k9rosie.novswar.event.NovsWarPlayerKillEvent;
import com.k9rosie.novswar.event.NovsWarScoreModifyEvent;
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
        tdm.setNovsWar(event.getNovsWar());
        event.getNovsWar().getGamemodes().put(tdm.getGamemodeName(), tdm);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNovsWarScoreModify(NovsWarScoreModifyEvent event) {
        if (event.getNovsScore().value() <= 0) {
            tdm.getNovsWar().getGameHandler().getGame().endGame();
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNovsWarPlayerKill(NovsWarPlayerKillEvent event) {
        event.getVictimTeam().getTeamState().getScore().decrementScore();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNovsWarPlayerDeath(NovsWarPlayerDeathEvent event) {
        if (event.isSuicide()) {
            event.getVictimTeam().getTeamState().getScore().decrementScore();
        }
    }
}
