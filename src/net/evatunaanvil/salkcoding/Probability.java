package net.evatunaanvil.salkcoding;

import net.evatunaanvil.salkcoding.file.JsonLoad;
import net.evatunaanvil.salkcoding.file.JsonSave;
import org.bukkit.enchantments.Enchantment;

import java.io.IOException;
import java.util.HashMap;

public class Probability {

    private static HashMap<Enchantment, Float> probabilityMap = new HashMap<>();
    private static float zeroPlusAlpha = 0f;

    public static Float getProbability(Enchantment enchantment) {
        return probabilityMap.get(enchantment);
    }

    public static void load() throws IOException {
        probabilityMap = JsonLoad.loadProbabilityJson();
        zeroPlusAlpha = JsonLoad.loadZeroPlusAlpha();
    }

    public static void save() throws IOException {
        JsonSave.saveProbability(probabilityMap);
    }

    public static float getZeroPlusAlpha() {
        return zeroPlusAlpha;
    }

    public static void setProbability(Enchantment e, float chance) {
        probabilityMap.replace(e, chance);
    }

}
