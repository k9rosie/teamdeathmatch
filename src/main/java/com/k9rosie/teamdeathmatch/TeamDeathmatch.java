package com.k9rosie.teamdeathmatch;

import com.k9rosie.novswar.NovsWar;
import com.k9rosie.novswar.gamemode.Gamemode;
import com.k9rosie.novswar.player.NovsPlayer;
import com.k9rosie.novswar.team.NovsTeam;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamDeathmatch implements Gamemode {
    private NovsWar novsWar;
    private TeamDeathmatchPlugin plugin;
    private Config config;

    public TeamDeathmatch(TeamDeathmatchPlugin plugin) {
        this.plugin = plugin;
        config = new Config(plugin, "config.yml");
    }

    public void init() {
        config.saveDefaultConfig();
        config.reloadConfig();
        config.reloadData();
    }

    public int getGameTime() {
        int currentPlayers = Bukkit.getServer().getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        int initialTime = config.getGameTime();

        return currentPlayers * (initialTime/maxPlayers);
    }

    public int getDeathTime(NovsPlayer player) {
        return config.getRespawnTime();
    }

    public int getRounds() {
        return config.getRounds();
    }

    public HashMap<NovsTeam, Integer> getInitialScores() {
        int currentPlayers = Bukkit.getServer().getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        int initialScore = config.getStartingTickets();
        int score = currentPlayers * (initialScore/maxPlayers);

        HashMap<NovsTeam, Integer> scores = new HashMap<>();

        for (NovsTeam team : novsWar.getGameHandler().getGame().getEnabledTeams()) {
            scores.put(team, score);
        }

        return scores;
    }

    public String getGamemodeName() {
        return "tdm";
    }

    public NovsTeam[] getWinningTeams() {
        ArrayList<NovsTeam> winningTeams = new ArrayList<>();
        NovsTeam highestScore = novsWar.getGameHandler().getGame().getEnabledTeams().get(0);
        for (NovsTeam team : novsWar.getGameHandler().getGame().getEnabledTeams()) {
            if (team.getTeamState().getScore().value() > highestScore.getTeamState().getScore().value()) {
                highestScore = team;
            }
        }
        winningTeams.add(highestScore);

        return (NovsTeam[]) winningTeams.toArray();
    }

    public NovsWar getNovsWar() {
        return novsWar;
    }

    public void setNovsWar(NovsWar novsWar) {
        this.novsWar = novsWar;
    }
}
