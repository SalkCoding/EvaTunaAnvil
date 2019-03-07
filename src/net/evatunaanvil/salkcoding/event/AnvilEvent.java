package net.evatunaanvil.salkcoding.event;

import net.evatunaanvil.salkcoding.Limit;
import net.evatunaanvil.salkcoding.Probability;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public class AnvilEvent implements Listener {

    @EventHandler
    public void onAnvil(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        ItemStack original = inventory.getItem(0);
        ItemStack material = inventory.getItem(1);
        ItemStack result = event.getResult();

        if (original == null || material == null || result == null) return;
        if (material.getType() != Material.ENCHANTED_BOOK) return;

        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) material.getItemMeta();

        Map<Enchantment, Integer> originalEnchantmentMap = original.getEnchantments();
        Map<Enchantment, Integer> materialEnchantmentMap = meta.getStoredEnchants();

        int exp = 0;
        for (Map.Entry<Enchantment, Integer> m : materialEnchantmentMap.entrySet()) {//material과 일치
            for (Map.Entry<Enchantment, Integer> o : originalEnchantmentMap.entrySet()) {
                if (o.getKey() == m.getKey()) {
                    if (result.getEnchantmentLevel(o.getKey()) <= Limit.getLimit(o.getKey()) && o.getKey().getMaxLevel() > o.getValue()) {
                        result.addUnsafeEnchantment(o.getKey(), o.getValue() + 1);
                    } else
                        result.addUnsafeEnchantment(o.getKey(), o.getValue());
                    exp += Probability.getZeroPlusAlpha() + (inventory.getRepairCost() * Math.pow(Probability.getProbability(o.getKey()), o.getValue()));
                    break;
                }
            }
        }
        for (Map.Entry<Enchantment, Integer> m : materialEnchantmentMap.entrySet()) {
            if (!originalEnchantmentMap.containsKey(m.getKey()) && m.getKey().canEnchantItem(result)) {
                result.addEnchantment(m.getKey(), 1);
                exp += Probability.getZeroPlusAlpha();
            }
        }
        //inventory.setMaximumRepairCost(exp + 40);
        if (exp < 60000)
            inventory.setRepairCost(exp);
        else
            inventory.setRepairCost(60000);
    }

}