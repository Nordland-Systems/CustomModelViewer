package de.stevenpaw.custommodelviewer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class Viewer {

    public void openCMV(Player _player, String _material, Integer _page) {

        Player p = _player;
        String m = _material;
        Inventory inv;
        int maxpos = 0;
        int invSize = 0;

        int page = _page;
        int customID = 0;

        inv = Bukkit.createInventory((InventoryHolder)null, 9*4, "Custom Model Viewer (Page " + (page+1) + ")");

        p.sendMessage("Showing page: " + page);

        for(int i = page * 27; i < page * 27 + 27; i++) {
            customID = page * 27 + i;
            inv.setItem(i - (page * 27), new ItemBuilder(Material.getMaterial(m)).setCustomModelData(customID).setDisplayName(m).setLore("("+customID+")").build());
        }

        for(int i = 0; i < 9; i++) {
            String mat = "PAPER";
            if(i == page) {
                mat = "NETHER_STAR";
            }
            inv.setItem(i+27, new ItemBuilder(Material.getMaterial(mat)).setDisplayName("Site " + (i+1)).setLore("Switch Page").setAmount(i+1).build());
        }
        p.openInventory(inv);
    }
}
