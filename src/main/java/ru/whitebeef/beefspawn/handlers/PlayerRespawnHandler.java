package ru.whitebeef.beefspawn.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import ru.whitebeef.beeflibrary.utils.ScheduleUtils;
import ru.whitebeef.beefspawn.BeefSpawn;
import ru.whitebeef.beefspawn.managers.PlayerTeleportManager;

public class PlayerRespawnHandler implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        ScheduleUtils.runTaskLater(BeefSpawn.getInstance(), () ->
                PlayerTeleportManager.getInstance().teleportPlayer(event.getPlayer(), BeefSpawn.getInstance().getSpawnLocation(), true), 1L);
    }
}
