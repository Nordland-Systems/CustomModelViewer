package de.stevenpaw.custommodelviewer;

import org.bukkit.ChatColor;
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

        Player p = (Player) e.getWhoClicked();
        String InvName = ChatColor.stripColor(e.getView().getTitle());

        if (InvName.startsWith("Custom Model Viewer")) {
            if (e.getClickedInventory().getType() != InventoryType.PLAYER) {
                if (e.getCurrentItem() != null) {
                    if (e.getSlot() > 26 && e.getSlot() < 36) {
                        viewer.openCMV(p, "" + e.getClickedInventory().getItem(0).getType(), e.getSlot() - 27);
                        e.setCancelled(true);
                    } else if (e.getSlot() < 27) {
                        p.getInventory().addItem(e.getCurrentItem());
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
