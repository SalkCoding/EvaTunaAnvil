package net.evatunaanvil.salkcoding.event;

import net.evatunaanvil.salkcoding.Limit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class Fishing implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH || event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            Limit.reduceDisableEnchantment(player, item);
        }
    }

}
