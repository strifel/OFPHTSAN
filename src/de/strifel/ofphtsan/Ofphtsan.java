package de.strifel.ofphtsan;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Ofphtsan extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onLayInBed(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            int sleeping = 1;
            World world = event.getBed().getWorld();
            for (Player player : world.getPlayers()) {
                if (player.isSleeping()) {
                    sleeping++;
                }
            }
            if (world.getPlayers().size() / 2 <= sleeping) {
                for (Player player : world.getPlayers()) {
                    player.sendMessage(ChatColor.YELLOW + "Sleeping...");
                }
                world.setTime(0);
            } else {
                for (Player player : world.getPlayers()) {
                    player.sendMessage(ChatColor.YELLOW + event.getPlayer().getName() + " is sleeping.");
                }
            }
        }
    }
}
