package net.evatunaanvil.salkcoding.event;

import net.evatunaanvil.salkcoding.Limit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerBow implements Listener {

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            ItemStack bow = event.getBow();
            Limit.reduceDisableEnchantment((Player) event.getEntity(), bow);
        }
    }

}
