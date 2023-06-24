package de.stevenpaw.cmv;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InvListener implements Listener {

    @EventHandler
    public void onINVclick(InventoryClickEvent e) {

        Viewer viewer = new Viewer();

        int itemsPerPage = Settings.itemsperpage;
        int invSize = itemsPerPage + 2*9;
        int modeSlot = invSize - 5;

        Player p = (Player) e.getWhoClicked();
        String InvName = ChatColor.stripColor(e.getView().getTitle());

        //Check if correct menu:
        if (!InvName.startsWith("Custom Model Viewer")
                || e.getClickedInventory() == null
                || e.getClickedInventory().getType() == InventoryType.PLAYER
                || e.getCurrentItem() == null) {
            return;
        }

        if (e.getSlot() >= itemsPerPage && e.getSlot() < itemsPerPage+9) {
            //Page-Buttons
            viewer.openCMV(p, "" + e.getClickedInventory().getItem(0).getType(), e.getSlot() - itemsPerPage);
            e.setCancelled(true);
        } else if (e.getSlot() < itemsPerPage) {
            //Get-Item
            switch (Settings.GetCurrentMode()) {
                case "INVMODE":
                    p.getInventory().addItem(GetItem(e.getCurrentItem()));
                    break;
                case "HATMODE":
                    p.getInventory().setHelmet(GetItem(e.getCurrentItem()));
                    break;
                case "HANDMODE":
                    p.getInventory().setItemInMainHand(GetItem(e.getCurrentItem()));
                    break;
                case "SECONDHANDMODE":
                    p.getInventory().setItemInOffHand(GetItem(e.getCurrentItem()));
                    break;
                case "DROPMODE":
                    float scaler = 4.0f;
                    p.getWorld().dropItem(p.getLocation().add(p.getEyeLocation().getDirection().multiply(scaler)), GetItem(e.getCurrentItem()));
                    break;
            }
            e.setCancelled(true);
        } else if (e.getSlot() == invSize - 6){
            //Name-Button
            Settings.SwitchNoName();
            if(!Settings.NONAME){
                p.getOpenInventory().setItem(invSize - 6, new ItemBuilder(Material.NAME_TAG).setDisplayName("§lNAME")
                        .setLore("§fMinecraft", "§7Items get standard names", "§o§aClick to change").setCustomModelData(1).build());
            } else {
                p.getOpenInventory().setItem(invSize - 6, new ItemBuilder(Material.NAME_TAG).setDisplayName("§lNAME")
                        .setLore("§fCMV", "§7Items get custom names", "§o§aClick to change").setCustomModelData(2).build());
            }
            e.setCancelled(true);
        } else if (e.getSlot() == modeSlot) {
            //Mode-Button
            Settings.SwitchMode();
            switch (Settings.GetCurrentMode()) {
                case "INVMODE":
                    p.getOpenInventory().setItem(modeSlot, new ItemBuilder(Material.WHITE_CONCRETE).setDisplayName("§lMODE").setLore("§fInventory", "§7Items land in Inventory", "§o§aClick to change").build());
                    break;
                case "HATMODE":
                    p.getOpenInventory().setItem(modeSlot, new ItemBuilder(Material.GREEN_CONCRETE).setDisplayName("§lMODE").setLore("§fHat", "§7Items land on Head", "§o§aClick to change").build());
                    break;
                case "HANDMODE":
                    p.getOpenInventory().setItem(modeSlot, new ItemBuilder(Material.BLUE_CONCRETE).setDisplayName("§lMODE").setLore("§fHand", "§7Items land in main Hand", "§o§aClick to change").build());
                    break;
                case "SECONDHANDMODE":
                    p.getOpenInventory().setItem(modeSlot, new ItemBuilder(Material.CYAN_CONCRETE).setDisplayName("§lMODE").setLore("§fOffhand", "§7Items land in offhand", "§o§aClick to change").build());
                    break;
                case "DROPMODE":
                    p.getOpenInventory().setItem(modeSlot, new ItemBuilder(Material.RED_CONCRETE).setDisplayName("§lMODE").setLore("§fDrop", "§7Drops the item", "§o§aClick to change").build());
                    break;
            }
            e.setCancelled(true);
        } else if (e.getSlot() == invSize - 2){
            if(p.hasPermission("custommodelviewer.*") || p.hasPermission("custommodelviewer.itemframe")) {
                //p.getInventory().addItem(new ItemBuilder(Material.ITEM_FRAME).setInvisible().build());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + p.getName() + " item_frame{EntityTag:{Invisible:1b}}");
            } else {
                p.sendMessage("§cYou don't have the permission to get invisible Itemframes");
            }
            e.setCancelled(true);
        } else if (e.getSlot() == invSize - 1){
            if(p.hasPermission("custommodelviewer.*") || p.hasPermission("custommodelviewer.itemframe")) {
                //p.getInventory().addItem(new ItemBuilder(Material.GLOW_ITEM_FRAME).setInvisible().build());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + p.getName() + " glow_item_frame{EntityTag:{Invisible:1b}}");
            } else {
                p.sendMessage("§cYou don't have the permission to get invisible Itemframes");
            }
            e.setCancelled(true);
        } else if (e.getSlot() == invSize - 9) {
            e.setCancelled(true);
        }
    }

    public ItemStack GetItem(ItemStack item)
    {
        if(!Settings.NONAME) {
            return new ItemBuilder(item.getType()).setCustomModelData(item.getItemMeta().getCustomModelData()).build();
        } else {
            return item;
        }
    }
}
