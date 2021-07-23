package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSaveTest {
    ObservableList<Inventory_Item> item_listTest = FXCollections.observableArrayList();
    ObservableList<Inventory_Item> item_listTest2 = FXCollections.observableArrayList();


    @BeforeEach
    public void SetUp(){
        item_listTest.add(new Inventory_Item("$50.00", "1234567890", "Tv"));
        item_listTest.add(new Inventory_Item("$20.00", "0123456789", "Car"));
        item_listTest.add(new Inventory_Item("$10.00", "0987654321", "Watch"));
    }

    @Test
    void saveTsvButtonClickedTest() {
        TsvExport tsvExport = new TsvExport();
        File file = (new File("TsvTestFile.txt"));

        tsvExport.write_tsv(item_listTest,file);
        ParseTsv parseTsv = new ParseTsv();
        parseTsv.read_tsv_file(item_listTest2,file);

        assertEquals("Tv",item_listTest2.get(0).getName());
        assertEquals("Car",item_listTest2.get(1).getName());
        assertEquals("Watch",item_listTest2.get(2).getName());
        assertEquals(3,item_listTest2.size());
    }

    @Test
    void saveHtmlButtonClickedTest() {
        HtmlExport htmlExport = new HtmlExport();
        File file = (new File("HtmlTestFile.html"));

        htmlExport.write_html(item_listTest,file);
        ParseHtml parseHtml = new ParseHtml();
        parseHtml.read_html(item_listTest2,file);

        assertEquals("Tv",item_listTest2.get(0).getName());
        assertEquals("Car",item_listTest2.get(1).getName());
        assertEquals("Watch",item_listTest2.get(2).getName());
        assertEquals(3,item_listTest2.size());
    }

    @Test
    void saveJsonButtonClickedTest() {
        JsonExport jsonExport = new JsonExport();
        File file = (new File("JsonTestFile.json"));

        jsonExport.write_json(item_listTest,file);
        ParseJson parseJson = new ParseJson();
        parseJson.read_json(item_listTest2,file);

        assertEquals("Tv",item_listTest2.get(0).getName());
        assertEquals("Car",item_listTest2.get(1).getName());
        assertEquals("Watch",item_listTest2.get(2).getName());
        assertEquals(3,item_listTest2.size());
    }
}
