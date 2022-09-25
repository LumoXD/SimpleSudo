package de.lumoxd.simplesudo.commands;

import de.lumoxd.simplesudo.SimpleSudo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

public class sudo_cmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd;
        if(args.length >= 2) {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(SimpleSudo.prefix+"Der Spieler ist leider nicht da.");
                return true;
            } else {
                cmd = "";
                for (int i = 1; i < args.length; i++) {
                    cmd = cmd + args[i] + " ";
                }

                if(cmd.startsWith("chat:")) {
                    cmd = cmd.replaceFirst("chat:", "");
                    Bukkit.getPlayer(args[0]).chat(cmd);
                    SimpleSudo.instance.getLogger().log(Level.INFO,
                            "chat from="+sender.getName()
                                    +" target="+Bukkit.getPlayer(args[0]).getName()
                    +" msg="+cmd);
                    return true;
                }

                SimpleSudo.instance.getLogger().log(Level.INFO,
                        "command from="+sender.getName()
                                +" target="+Bukkit.getPlayer(args[0]).getName()
                                +" msg="+cmd);


                Bukkit.getPlayer(args[0]).performCommand(cmd);
                return true;
            }
        } else {
            sender.sendMessage(SimpleSudo.prefix+"Syntax: /sudo <NAME> <COMMAND>  oder /sudo <NAME> chat:<NACHRICHT>");
        }
        return true;
    }
}
