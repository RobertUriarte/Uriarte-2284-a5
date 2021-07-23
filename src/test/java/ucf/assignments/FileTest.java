package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTest {
    Inventory_Controller inventory_controller = new Inventory_Controller();
    ObservableList<Inventory_Item> item_listTest = FXCollections.observableArrayList();

    @BeforeEach
    void setUp() {
        //item_listTest.add(new Inventory_Item("$50.00", "1234567890", "Tv"));
        //item_listTest.add(new Inventory_Item("$20.00", "0123456789", "Car));
        //item_listTest.add(new Inventory_Item("$10.00", "0987654321", "Watch"));
    }

    @Test
    void loadTsvButtonClickedTest() {
        File file = (new File("Tsv Sample Items.txt"));
        ParseTsv parseTsv = new ParseTsv();
        parseTsv.read_tsv_file(item_listTest,file);

        assertEquals("Tv",item_listTest.get(0).getName());
        assertEquals("Car",item_listTest.get(1).getName());
        assertEquals("Watch",item_listTest.get(2).getName());
        assertEquals(3,item_listTest.size());
    }

    @Test
    void loadHtmlButtonClickedTest() {
    }

    @Test
    void loadJsonButtonClickedTest() {
    }
}
