package de.stevenpaw.custommodelviewer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CMDcmv implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("custommodelviewer.*") || p.hasPermission("custommodelviewer.show")) {
                if (args.length == 0) {
                    p.sendMessage("§cUse §6/cmv <item>");
                } else if (args.length == 1) {
                    String materialName = args[0].toUpperCase();
                    if (Material.getMaterial(materialName) == null) {
                        p.sendMessage("§cThe material §6" + materialName + "§c doesn't exist!");
                    } else {
                        Viewer viewer = new Viewer();
                        viewer.openCMV(p, materialName, 0);
                    }
                } else if (args.length == 2) {
                    String materialName = args[0].toUpperCase();
                    Integer page = Integer.parseInt(args[1]);
                    if (Material.getMaterial(materialName) == null) {
                        p.sendMessage("§cThe material §6" + materialName + "§c doesn't exist!");
                    } else {
                        Viewer viewer = new Viewer();
                        viewer.openCMV(p, materialName, page);
                    }
                } else {
                    p.sendMessage("§eToo many arguments! §cUse §6/cmv <item>");
                }
            }
        }

        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender con = Bukkit.getServer().getConsoleSender();
            if (args.length == 0) {
                con.sendMessage("§cUse §6/cmv <command>");
            } else if (args.length >= 1) {

            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 1) {
                //<TYP>
                commands.add("<item>");
                StringUtil.copyPartialMatches(args[0], commands, completions);
            }

            if (args.length == 2) {
                //<TYP>
                commands.add("[<page>]");
                StringUtil.copyPartialMatches(args[1], commands, completions);
            }

            return completions;
        }
        return null;
    }
}
