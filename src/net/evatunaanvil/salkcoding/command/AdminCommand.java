package net.evatunaanvil.salkcoding.command;

import net.evatunaanvil.salkcoding.Limit;
import net.evatunaanvil.salkcoding.Probability;
import net.evatunaanvil.salkcoding.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;

import java.io.IOException;

public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!sender.isOp()) {
            sender.sendMessage(Constants.Error_Format + "You don't have permission to use this command");
            return true;
        }
        if (strings.length == 0) {
            try {
                Probability.load();
                Limit.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sender.sendMessage(Constants.Info_Format + "Configs are reloaded");
        } else if (strings.length == 3) {
            if (strings[0].equalsIgnoreCase("set")) {
                try {
                    Enchantment enchantment = Enchantment.getByName(strings[1]);
                    float chance = Float.parseFloat(strings[2]);
                    float p = Probability.getProbability(enchantment);
                    Probability.setProbability(enchantment, chance);
                    sender.sendMessage(Constants.Info_Format + p + " -> " + Probability.getProbability(enchantment));
                } catch (NumberFormatException e) {
                    sendList(sender);
                } catch (IllegalArgumentException e) {
                    sender.sendMessage(Constants.Error_Format + strings[1] + " is not enchantment name");
                    sendList(sender);
                }
            }
        } else {
            sendList(sender);
        }
        return true;
    }

    private void sendList(CommandSender sender) {

    }

}
