package com.k9rosie.teamdeathmatch;

import com.k9rosie.novswar.gamemode.Gamemode;
import com.k9rosie.teamdeathmatch.listener.ServerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamDeathmatchPlugin extends JavaPlugin {

    private TeamDeathmatch tdm;

    public void onEnable() {
        tdm = new TeamDeathmatch(this);
        getServer().getPluginManager().registerEvents(new ServerListener(this), this);
    }

    public TeamDeathmatch getGamemode() {
        return tdm;
    }
	
}