package de.lumoxd.simplesudo.listener;

import de.lumoxd.simplesudo.SimpleSudo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.logging.Level;

public class sudo_listener implements Listener {
    @EventHandler
    public void onAsyncChatEvent(AsyncPlayerChatEvent event) {
        if(!event.getMessage().startsWith("+sudo")) {
            return;
        }
        if(!event.getPlayer().hasPermission("simplesudo.sudo")) {
            return;
        }
        event.setCancelled(true);
        Player sender = event.getPlayer();
        String cmd;
        String[] args = removeFirstElement(event.getMessage().split(" "));
        if(args.length >= 2) {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(SimpleSudo.prefix+"Der Spieler ist leider nicht da.");
                return;
            } else {
                cmd = "";
                for (int i = 1; i < args.length; i++) {
                    cmd = cmd + args[i] + " ";
                }

                if(cmd.startsWith("chat:")) {
                    cmd = cmd.replaceFirst("chat:", "");

                    String finalCmd = cmd;
                    Bukkit.getScheduler().runTask(SimpleSudo.instance, () -> {
                       try {
                           Bukkit.getPlayer(args[0]).chat(finalCmd);
                       } catch (Throwable throwable) {}
                    });

                    return;
                }

                String finalCmd1 = cmd;
                Bukkit.getScheduler().runTask(SimpleSudo.instance, () -> {
                    try {
                        Bukkit.getPlayer(args[0]).performCommand(finalCmd1);
                    } catch (Throwable throwable) {}
                });
                return;
            }
        } else {
            sender.sendMessage(SimpleSudo.prefix+"Syntax: +sudo <NAME> <COMMAND>  oder +sudo <NAME> chat:<NACHRICHT>");
        }
    }

    static String[] removeFirstElement(String[] arr) {
        if(arr.length == 0) { return arr; }
        String newArr[] = new String[arr.length - 1];
        for (int i = 1; i < arr.length; i++) { newArr[i-1] = arr[i]; }
        return newArr;
    }
}
