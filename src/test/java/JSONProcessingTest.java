import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.TestCase;
public class JSONProcessingTest extends TestCase {

    public void testProcessing() {
        JSONProcessing jsonProcessing = new JSONProcessing();
        ClientJSON root = new ClientJSON();
        root.setTitle("мыло");
        root.setSum(300);
        root.setDate("2022.02.08");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        assertEquals(jsonProcessing.processing(gson.toJson(root)), "{\"maxCategory\":{\"category\":\"быт\",\"sum\":300}}\n");
    }
}