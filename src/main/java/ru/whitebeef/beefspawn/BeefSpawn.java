package ru.whitebeef.beefspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.whitebeef.beeflibrary.BeefLibrary;
import ru.whitebeef.beeflibrary.commands.AbstractCommand;
import ru.whitebeef.beeflibrary.commands.SimpleCommand;
import ru.whitebeef.beefspawn.handlers.PlayerJoinHandler;
import ru.whitebeef.beefspawn.handlers.PlayerMoveHandler;
import ru.whitebeef.beefspawn.handlers.PlayerRespawnHandler;
import ru.whitebeef.beefspawn.managers.PlayerTeleportManager;

public final class BeefSpawn extends JavaPlugin {

    private Location spawnLocation;

    private static BeefSpawn instance;

    public static BeefSpawn getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        BeefLibrary.loadConfig(this);

        ConfigurationSection section = getConfig().getConfigurationSection("spawn_location");
        spawnLocation = new Location(Bukkit.getWorld(section.getString("world")), section.getDouble("x"), section.getDouble("y"), section.getDouble("z"),
                (float) section.getDouble("yaw"), (float) section.getDouble("pitch"));

        BeefLibrary.registerListeners(this, new PlayerMoveHandler(), new PlayerRespawnHandler(), new PlayerJoinHandler());

        new PlayerTeleportManager();

        AbstractCommand.builder("spawn", SimpleCommand.class)
                .setOnCommand((sender, args) -> PlayerTeleportManager.getInstance().teleportPlayer((Player) sender, getSpawnLocation(), false))
                .setOnlyForPlayers(true)
                .setPermission("beefspawn.spawn")
                .build().register(this);
    }

    @Override
    public void onDisable() {

    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }
}
