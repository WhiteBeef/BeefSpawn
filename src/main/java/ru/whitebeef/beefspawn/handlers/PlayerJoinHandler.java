package ru.whitebeef.beefspawn.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.whitebeef.beeflibrary.utils.ScheduleUtils;
import ru.whitebeef.beefspawn.BeefSpawn;
import ru.whitebeef.beefspawn.managers.PlayerTeleportManager;

public class PlayerJoinHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPlayedBefore()) {
            return;
        }

        ScheduleUtils.runTaskLater(BeefSpawn.getInstance(), () ->
                PlayerTeleportManager.getInstance().teleportPlayer(event.getPlayer(), BeefSpawn.getInstance().getSpawnLocation(), true), 1L);
    }

}
