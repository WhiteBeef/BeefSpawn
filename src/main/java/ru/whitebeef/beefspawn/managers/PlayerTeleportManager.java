package ru.whitebeef.beefspawn.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import ru.whitebeef.beeflibrary.chat.MessageSender;
import ru.whitebeef.beefspawn.BeefSpawn;

import java.util.HashMap;
import java.util.Map;

public class PlayerTeleportManager {

    private static PlayerTeleportManager instance;

    public static PlayerTeleportManager getInstance() {
        return instance;
    }

    private Map<Player, BukkitTask> tasks = new HashMap<>();

    public PlayerTeleportManager() {
        instance = this;
    }

    public void teleportPlayer(Player player, Location location, boolean instantTP) {
        if (player.hasPermission("beefspawn.instanttp") || instantTP) {
            player.teleport(location);
            MessageSender.sendMessageType(player, BeefSpawn.getInstance(), "successful_tp");
        } else {
            MessageSender.sendMessageType(player, BeefSpawn.getInstance(), "will_tp");

            tasks.put(player, Bukkit.getScheduler().runTaskLater(BeefSpawn.getInstance(), () -> {
                player.teleport(location);
                tasks.remove(player);
                MessageSender.sendMessageType(player, BeefSpawn.getInstance(), "successful_tp");
            }, 100));
        }
    }

    public void cancelTeleport(Player player) {
        if (!tasks.containsKey(player)) {
            return;
        }

        MessageSender.sendMessageType(player, BeefSpawn.getInstance(), "cancel_tp");
        tasks.get(player).cancel();
        tasks.remove(player);
    }

}
