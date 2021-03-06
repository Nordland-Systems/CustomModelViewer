package de.stevenpaw.custommodelviewer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CMDcmv implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("custommodelviewer.*") || p.hasPermission("custommodelviewer.show")) {
                if (args.length == 0) {
                    String materialName = p.getInventory().getItemInMainHand().getType().toString().toUpperCase();
                    if(Material.getMaterial(materialName) != null && materialName != "AIR"){
                        Viewer viewer = new Viewer();
                        viewer.openCMV(p, materialName, 0);
                    } else {
                        p.sendMessage("§cUse §6'/cmv <item> [<page>]' §cor have Item in Hand");
                    }
                } else if (args.length == 1) {
                    String materialName = args[0].toUpperCase();
                    if (Material.getMaterial(materialName) == null) {
                        p.sendMessage("§cThe material §6" + materialName + "§c doesn't exist!");
                    } else {
                        Viewer viewer = new Viewer();
                        viewer.openCMV(p, materialName, 0);
                    }
                } else if (args.length == 2) {
                    if(Integer.parseInt(args[1]) > 0) {
                        String materialName = args[0].toUpperCase();
                        Integer page = Integer.parseInt(args[1]) -1;
                        if (Material.getMaterial(materialName) == null) {
                            p.sendMessage("§cThe material §6" + materialName + "§c doesn't exist!");
                        } else {
                            Viewer viewer = new Viewer();
                            viewer.openCMV(p, materialName, page);
                        }
                    } else {
                        p.sendMessage("§eInput page between 1 and 999 only!");
                    }
                } else {
                    p.sendMessage("§eToo many arguments! §cUse §6/cmv <item> [<page>]");
                }
            }
        }

        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender con = Bukkit.getServer().getConsoleSender();
            if (args.length == 0) {
                con.sendMessage("§cUse §6/cmv <item> [<page>]");
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
                for (Material m : Material.values()) {
                    String s = m.toString().toLowerCase();
                    commands.add(s);
                }
                StringUtil.copyPartialMatches(args[0], commands, completions);
                Collections.sort(completions);
            }

            if (args.length == 2) {
                //<TYP>
                commands.add("[<page>]");
                commands.add("1");
                commands.add("2");
                commands.add("3");
                commands.add("4");
                commands.add("5");
                commands.add("6");
                commands.add("7");
                commands.add("8");
                commands.add("9");
                StringUtil.copyPartialMatches(args[1], commands, completions);
            }

            return completions;
        }
        return null;
    }
}
