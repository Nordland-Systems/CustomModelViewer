package de.stevenpaw.cmv;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    //== BESCHREIBUNG =============
    // Creates an Item for use
    // anywhere in the plugin
    // Created for the NLP-Plugin
    //-----------------------------


    //== VARIABLEN ================
    private ItemStack item;
    private ItemMeta itemMeta;
    public static NamespacedKey IS_INVISIBLE_KEY;
    //-----------------------------


    //== BUILD A ITEM =============
    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    }

    public ItemBuilder setDamage(int damage) {
        ((Damageable) itemMeta).setDamage(damage);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setCustomModelData(int amount) {
        itemMeta.setCustomModelData(amount);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setLoreFromArray(List<String> lore) {
        itemMeta.setLore(lore);
        return this;
    }

    public ItemBuilder setInvisible(){
        itemMeta.getPersistentDataContainer().set(IS_INVISIBLE_KEY, PersistentDataType.BYTE, (byte) 1);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(itemMeta);
        return item;
    }
    //-----------------------------

}
