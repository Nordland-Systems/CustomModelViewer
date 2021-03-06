package de.stevenpaw.custommodelviewer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InvListener implements Listener {
    @EventHandler
    public void onINVclick(InventoryClickEvent e) {

        Viewer viewer = new Viewer();

        Player p = (Player) e.getWhoClicked();
        String InvName = ChatColor.stripColor(e.getView().getTitle());

        if (InvName.startsWith("Custom Model Viewer")) {
            if (e.getClickedInventory().getType() != InventoryType.PLAYER) {
                if (e.getCurrentItem() != null) {
                    if (e.getSlot() > 26 && e.getSlot() < 36) {
                        viewer.openCMV(p, "" + e.getClickedInventory().getItem(0).getType(), e.getSlot() - 27);
                        e.setCancelled(true);
                    } else if (e.getSlot() < 27) {
                        switch (Settings.GetCurrentMode()) {
                            case "INVMODE":
                                p.getInventory().addItem(e.getCurrentItem());
                                break;
                            case "HATMODE":
                                p.getInventory().setHelmet(e.getCurrentItem());
                                break;
                            case "HANDMODE":
                                p.getInventory().setItemInMainHand(e.getCurrentItem());
                                break;
                            case "SECONDHANDMODE":
                                p.getInventory().setItemInOffHand(e.getCurrentItem());
                                break;
                            case "DROPMODE":
                                float scaler = 4.0f;
                                p.getWorld().dropItem(p.getLocation().add(p.getEyeLocation().getDirection().multiply(scaler)), e.getCurrentItem());
                                break;
                        }
                        e.setCancelled(true);

                    } else if (e.getSlot() == 40) {

                        Settings.SwitchMode();
                        switch (Settings.GetCurrentMode()) {
                            case "INVMODE":
                                p.getOpenInventory().setItem(40, new ItemBuilder(Material.WHITE_CONCRETE).setDisplayName("§lMODE").setLore("§fInventory", "§7Items land in Inventory","§o§aClick to change").build());
                                break;
                            case "HATMODE":
                                p.getOpenInventory().setItem(40, new ItemBuilder(Material.GREEN_CONCRETE).setDisplayName("§lMODE").setLore("§fHat", "§7Items land on Head","§o§aClick to change").build());
                                break;
                            case "HANDMODE":
                                p.getOpenInventory().setItem(40, new ItemBuilder(Material.BLUE_CONCRETE).setDisplayName("§lMODE").setLore("§fHand", "§7Items land in main Hand","§o§aClick to change").build());
                                break;
                            case "SECONDHANDMODE":
                                p.getOpenInventory().setItem(40, new ItemBuilder(Material.CYAN_CONCRETE).setDisplayName("§lMODE").setLore("§fOffhand", "§7Items land in offhand","§o§aClick to change").build());
                                break;
                            case "DROPMODE":
                                p.getOpenInventory().setItem(40, new ItemBuilder(Material.RED_CONCRETE).setDisplayName("§lMODE").setLore("§fDrop", "§7Drops the item","§o§aClick to change").build());
                                break;
                        }
                        e.setCancelled(true);
                    } else if (e.getSlot() == 44) {
                        p.closeInventory();
                        e.setCancelled(true);
                    } else if (e.getSlot() == 36) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
