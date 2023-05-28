package ru.whitebeef.beefspawn.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.whitebeef.beefspawn.managers.PlayerTeleportManager;

public class PlayerMoveHandler implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        if (event.getFrom().distance(event.getTo()) <= 0.001d) {
            return;
        }
        PlayerTeleportManager.getInstance().cancelTeleport(event.getPlayer());

    }

}
