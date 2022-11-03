import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.TestCase;

import java.io.File;

public class JSONProcessingTest extends TestCase {

    public void testProcessing() {
        JSONProcessing jsonProcessing = new JSONProcessing();
        ClientJSON root = new ClientJSON();
        root.setTitle("тапки");
        root.setSum(700);
        root.setDate("2022.02.08");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        jsonProcessing.parsing(new File("categories.tsv"));
        assertEquals("{\"maxCategory\":{\"category\":\"одежда\",\"sum\":700}}", jsonProcessing.processing(gson.toJson(root)));
    }
}