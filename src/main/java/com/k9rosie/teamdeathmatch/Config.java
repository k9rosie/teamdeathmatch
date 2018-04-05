package com.k9rosie.teamdeathmatch;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Config {
    private File file;
    private String fileName;
    private FileConfiguration config;
    private TeamDeathmatchPlugin plugin;

    private int startingTickets;
    private boolean ticketsScalesWithPlayerCount;
    private int gameTime;
    private boolean timeScalesWithPlayerCount;
    private int ticketsLostPerDeath;
    private int respawnTime;
    private int rounds;

    public Config(TeamDeathmatchPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);

        try {
            Reader defaultConfigStream = new InputStreamReader(plugin.getResource(fileName), "UTF8");

            if (defaultConfigStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
                config.setDefaults(defaultConfig);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
            reloadData();
        }

        return config;
    }

    public void saveConfig() {
        if (config == null || file == null) {
            return;
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        if (file == null) {
            file = new File(plugin.getDataFolder(), fileName);
        }

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
    }

    public void reloadData() {
        startingTickets = config.getInt("starting-tickets");
        ticketsScalesWithPlayerCount = config.getBoolean("tickets-scale-with-player-count");
        gameTime = config.getInt("game-time");
        timeScalesWithPlayerCount = config.getBoolean("time-scales-with-player-count");
        ticketsLostPerDeath = config.getInt("tickets-lost-per-death");
        respawnTime = config.getInt("respawn-time");
        rounds = config.getInt("rounds");
    }

    public int getStartingTickets() {
        return startingTickets;
    }

    public boolean isTicketsScalesWithPlayerCount() {
        return ticketsScalesWithPlayerCount;
    }

    public int getGameTime() {
        return gameTime;
    }

    public boolean isTimeScalesWithPlayerCount() {
        return timeScalesWithPlayerCount;
    }

    public int getTicketsLostPerDeath() {
        return ticketsLostPerDeath;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public int getRounds() {
        return rounds;
    }
}
