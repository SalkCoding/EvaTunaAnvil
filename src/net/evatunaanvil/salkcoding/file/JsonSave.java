package net.evatunaanvil.salkcoding.file;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.evatunaanvil.salkcoding.Probability;
import net.evatunaanvil.salkcoding.main.Main;
import org.bukkit.enchantments.Enchantment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonSave {

    public static void saveProbability(HashMap<Enchantment, Float> map) throws IOException {
        File file = new File(Main.getInstance().getDataFolder(), "Exp.json");
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        for (Map.Entry<Enchantment, Float> enchantment : map.entrySet())
            json.addProperty(enchantment.getKey().toString(), enchantment.getValue());
        json.addProperty("ZeroPlusAlpha", Probability.getZeroPlusAlpha());
        FileWriter writer = new FileWriter(file);
        writer.write(gson.toJson(json));
        writer.flush();
        writer.close();
    }

}
