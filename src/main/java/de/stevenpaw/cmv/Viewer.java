package de.stevenpaw.cmv;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class Viewer {

    public void openCMV(Player _player, String _material, Integer _page) {

        Player p = _player;
        String m = _material;
        Inventory inv;
        int maxpos = 0;
        int invSize = 0;

        int page = _page;

        inv = Bukkit.createInventory((InventoryHolder)null, 9*5, "§8Custom Model Viewer §7(Page " + (page+1) + ")");

        for(int i = page * 27; i < page * 27 + 27; i++) {
            inv.setItem(i - (page * 27), new ItemBuilder(Material.getMaterial(m)).setCustomModelData(i).setDisplayName(m).setLore("("+i+")").build());
        }

        for(int i = 0; i < 9; i++) {
            String mat = "PAPER";
            if(i == page) {
                mat = "NETHER_STAR";
            }
            inv.setItem(i+27, new ItemBuilder(Material.getMaterial(mat)).setDisplayName("Site " + (i+1)).setLore("Switch Page").setAmount(i+1).build());
        }

        inv.setItem(36, new ItemBuilder(Material.getMaterial(m)).setDisplayName("§lSELECTED ITEM").setLore("§f"+m).build());

        int modeSlot = 40;
        switch (Settings.GetCurrentMode()){
            case "INVMODE":
                inv.setItem(modeSlot, new ItemBuilder(Material.WHITE_CONCRETE).setDisplayName("§lMODE").setLore("§fInventory","§7Items land in Inventory","§a§oClick to change").build());
                break;
            case "HATMODE":
                inv.setItem(modeSlot, new ItemBuilder(Material.GREEN_CONCRETE).setDisplayName("§lMODE").setLore("§fHat","§7Items land on Head","§a§oClick to change").build());
                break;
            case "HANDMODE":
                inv.setItem(modeSlot, new ItemBuilder(Material.BLUE_CONCRETE).setDisplayName("§lMODE").setLore("§fHand","§7Items land in main Hand","§a§oClick to change").build());
                break;
            case "SECONDHANDMODE":
                inv.setItem(modeSlot, new ItemBuilder(Material.CYAN_CONCRETE).setDisplayName("§lMODE").setLore("§fOffhand","§7Items land in offhand","§a§oClick to change").build());
                break;
            case "DROPMODE":
                inv.setItem(modeSlot, new ItemBuilder(Material.RED_CONCRETE).setDisplayName("§lMODE").setLore("§fDrop","§7Drops the item","§a§oClick to change").build());
                break;
        }

        inv.setItem(44, new ItemBuilder(Material.BARRIER).setDisplayName("§4CLOSE").build());

        p.openInventory(inv);
    }
}
