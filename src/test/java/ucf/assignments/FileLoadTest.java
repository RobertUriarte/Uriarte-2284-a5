/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Robert Uriarte
 */

package ucf.assignments;

//Set up imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLoadTest {
    //Initialize list
    ObservableList<Inventory_Item> item_listTest = FXCollections.observableArrayList();

    @Test
    void loadTsvButtonClickedTest() {
        //Initialize file
        //Parse newly saved file
        //Assert if item values are correct

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
        //Initialize file
        //Parse newly saved file
        //Assert if item values are correct

        File file = (new File("Html Sample Items.html"));
        ParseHtml parseHtml = new ParseHtml();
        parseHtml.read_html(item_listTest,file);

        assertEquals("Tv",item_listTest.get(0).getName());
        assertEquals("Car",item_listTest.get(1).getName());
        assertEquals("Watch",item_listTest.get(2).getName());
        assertEquals(3,item_listTest.size());
    }

    @Test
    void loadJsonButtonClickedTest() {
        //Initialize file
        //Parse newly saved file
        //Assert if item values are correct

        File file = (new File("Json Sample Items.json"));
        ParseJson parseJson = new ParseJson();
        parseJson.read_json(item_listTest,file);

        assertEquals("Tv",item_listTest.get(0).getName());
        assertEquals("Car",item_listTest.get(1).getName());
        assertEquals("Watch",item_listTest.get(2).getName());
        assertEquals(3,item_listTest.size());
    }
}
