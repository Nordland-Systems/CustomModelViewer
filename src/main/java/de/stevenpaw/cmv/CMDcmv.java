package de.stevenpaw.cmv;

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
                        p.sendMessage("§cUse §6'/cmv <item/frame/glowframe> [<page>]' §cor have Item in Hand");
                    }
                } else if (args.length == 1) {
                    String materialName = args[0].toUpperCase();
                    if(materialName.equalsIgnoreCase("frame")){
                        GetItemFrame(p,false);
                        return true;
                    } else if(materialName.equalsIgnoreCase("glowframe")){
                        GetItemFrame(p, true);
                        return true;
                    } else if (Material.getMaterial(materialName) == null) {
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
            } else {
                p.sendMessage("§eYou don't have the permissions to use the Custom Model Viewer");
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
                commands.add("frame");
                commands.add("glowframe");
                for (Material m : Material.values()) {
                    String s = m.toString().toLowerCase();
                    commands.add(s);
                }
                //Collections.sort(commands);
                StringUtil.copyPartialMatches(args[0], commands, completions);
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

    private void GetItemFrame(Player p, boolean glowing){
        if(p.hasPermission("custommodelviewer.*") || p.hasPermission("custommodelviewer.itemframe")) {
            if(glowing){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + p.getName() + " glow_item_frame{EntityTag:{Invisible:1b}}");
            } else {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + p.getName() + " item_frame{EntityTag:{Invisible:1b}}");
            }
        } else {
            p.sendMessage("§cYou don't have the permission to get invisible Itemframes");
        }
    }
}
