package net.evatunaanvil.salkcoding;

import net.evatunaanvil.salkcoding.file.JsonLoad;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Limit {

    //SUBTRACT ENCHANTMENT LEVEL
    private static QuickHash<Enchantment> set = new QuickHash<>(
            Enchantment.LOOT_BONUS_BLOCKS,
            Enchantment.MENDING,
            Enchantment.LUCK
    );

    private static HashMap<Enchantment, Integer> limitMap = new HashMap<>();

    public static Integer getLimit(Enchantment enchantment) {
        return limitMap.get(enchantment);
    }

    public static void load() throws IOException {
        limitMap = JsonLoad.loadLimitJson();
    }

    private static boolean isDisable(Enchantment enchantment) {
        return set.contains(enchantment);
    }


    public static void reduceDisableEnchantment(Player player, ItemStack item) {
        Map<Enchantment, Integer> map = item.getEnchantments();
        for (Map.Entry<Enchantment, Integer> e : map.entrySet()) {
            if (isDisable(e.getKey())) {
                if (e.getValue() - 1 <= 0)
                    item.removeEnchantment(e.getKey());
                else
                    item.addEnchantment(e.getKey(), e.getValue() - 1);
                player.sendMessage(Constants.Info_Format + "아이템의 특정 인첸트 레벨이 " + (e.getValue() - 1) + "로 감소되었습니다.");
            }
        }
    }

}
