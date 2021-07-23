package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Inventory_ControllerTest {

    Inventory_Controller inventory_controller = new Inventory_Controller();
    ObservableList<Inventory_Item> item_list = FXCollections.observableArrayList();

    @BeforeEach
    void setUp() {
            item_list.add(new Inventory_Item("100", "ABCDE12345", "Tv"));
            item_list.add(new Inventory_Item("75", "12345ABCDE", "Watch"));
            item_list.add(new Inventory_Item("50", "A1B2C3D4E5", "Car"));
            item_list.add(new Inventory_Item("25", "0987654321", "Tv"));
    }


    @Test
    public void filterDataTest(){
        FilteredList<Inventory_Item> list = new FilteredList<>(item_list, b->true);

        SortedList<Inventory_Item> actual = inventory_controller.filterData(list,"Tv");
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.size(),2);
        Assertions.assertEquals(actual.get(0).getValue(), "100");

        SortedList<Inventory_Item> actual2 = inventory_controller.filterData(list,"100");
        Assertions.assertNotNull(actual2);
        Assertions.assertEquals(actual2.size(),1);
        Assertions.assertEquals(actual2.get(0).getSerial(),"ABCDE12345");
    }


    @Test
    void addItemButtonClickedTest() {

    }

    @Test
    void is_duplicateTest() {
    }

    @Test
    void valid_serialTest() {
    }

    @Test
    void valid_nameTest() {
    }

    @Test
    void format_moneyTest() {
    }

    @Test
    void removeItemButtonClickedTest() {
    }

    @Test
    void clearAllButtonClickedTest() {
    }

    @Test
    void saveExcelButtonClickedTest() {
    }

    @Test
    void loadTsvButtonClickedTest() {
    }

    @Test
    void loadHtmlButtonClickedTest() {
    }

    @Test
    void loadJsonButtonClickedTest() {
    }

    @Test
    void changeValueCellTest() {
    }

    @Test
    void changeSerialNumberCellTest() {
    }

    @Test
    void changeNameCellTest() {
    }

    @Test
    void saveAsTsvButtonClickedTest() {
    }

    @Test
    void saveAsHtmlButtonClickedTest() {
    }

    @Test
    void saveAsJsonButtonClickedTest() {
    }



}