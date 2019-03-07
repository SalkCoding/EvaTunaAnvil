package net.evatunaanvil.salkcoding.file;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.evatunaanvil.salkcoding.main.Main;
import org.bukkit.enchantments.Enchantment;

import java.io.*;
import java.util.HashMap;

public class JsonLoad {

    public static HashMap<Enchantment, Integer> loadLimitJson() throws IOException {
        File limit = new File(Main.getInstance().getDataFolder(), "Limit.json");
        makeSample(limit);
        return getLimitFromJson(limit);
    }

    public static HashMap<Enchantment, Float> loadProbabilityJson() throws IOException {
        File exp = new File(Main.getInstance().getDataFolder(), "Exp.json");
        makeSample(exp);
        return getProbabilityFromJson(exp);
    }

    private static void makeSample(File file) throws IOException {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
            if (file.getName().equals("Exp.json")) {
                json.addProperty("ZeroPlusAlpha", 5);
            }
            for (Enchantment enchantment : Enchantment.values()) json.addProperty(enchantment.toString(), enchantment.getMaxLevel());
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(json));
            writer.flush();
            writer.close();
        }
    }

    private static HashMap<Enchantment, Integer> getLimitFromJson(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String string;
        while ((string = reader.readLine()) != null) builder.append(string);
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(builder.toString()).getAsJsonObject();
        HashMap<Enchantment, Integer> map = new HashMap<>();
        for (Enchantment enchantment : Enchantment.values())
            map.put(enchantment, json.get(enchantment.toString()).getAsInt());
        return map;
    }

    private static HashMap<Enchantment, Float> getProbabilityFromJson(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String string;
        while ((string = reader.readLine()) != null) builder.append(string);
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(builder.toString()).getAsJsonObject();
        HashMap<Enchantment, Float> map = new HashMap<>();
        for (Enchantment enchantment : Enchantment.values())
            map.put(enchantment, json.get(enchantment.toString()).getAsFloat());
        return map;
    }

    public static float loadZeroPlusAlpha() throws IOException {
        File exp = new File(Main.getInstance().getDataFolder(), "Exp.json");
        BufferedReader reader = new BufferedReader(new FileReader(exp));
        StringBuilder builder = new StringBuilder();
        String string;
        while ((string = reader.readLine()) != null) builder.append(string);
        JsonObject json = new JsonParser().parse(builder.toString()).getAsJsonObject();
        return json.get("ZeroPlusAlpha").getAsFloat();
    }
}
