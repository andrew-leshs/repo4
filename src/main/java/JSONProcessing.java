import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class JSONProcessing {

    private Map<String, Integer> listOfCategories = new HashMap<>();
    private Map<String, String> tsv = new HashMap<>();

    public void parsing(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String s;
            List<String> list;
            while ((s = br.readLine()) != null) {
                list = List.of(s.split("\t"));
                tsv.put(list.get(0), list.get(1));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String processing(String jsonObject) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ClientJSON clientJSON = gson.fromJson(jsonObject, ClientJSON.class);


        boolean bool = false;
        for (var entry : tsv.entrySet()) {
            if (clientJSON.getTitle().equals(entry.getKey())) {
                listOfCategories.merge(entry.getValue(), clientJSON.getSum(), Integer::sum);
                bool = true;
            }
        }
        if (!bool) {
            listOfCategories.merge("другое", clientJSON.getSum(), Integer::sum);
        }

        Map.Entry<String, Integer> listMaxEntry = null;
        for (var entry : listOfCategories.entrySet()) {
            if (listMaxEntry == null) {
                listMaxEntry = entry;
            } else {
                if (entry.getValue() > listMaxEntry.getValue()) {
                    listMaxEntry = entry;
                }
            }
        }
        GsonBuilder gsonBuilder1 = new GsonBuilder();
        Gson gson1 = gsonBuilder1.create();

        ProcessedJSON json = new ProcessedJSON();
        json.setCategory(listMaxEntry.getKey());
        json.setSum(listMaxEntry.getValue());
        Category category = new Category();
        category.setJsonProcessingJSON(json);


        return gson1.toJson(category, Category.class);
    }
}
