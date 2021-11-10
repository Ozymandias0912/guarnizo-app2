/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Juan Guarnizo
 */
package baseline;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryManagementApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryManagementApplication.class.getResource("Inventory-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Inventory v1.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

/*    Pseudocode

     I will use the InventoryManagementApplication Class, the InventoryController Class, and the Item Class

     InventoryManagementApplication Class{

         public void start(Stage stage) throws IOException{}

         public static void main(String[] args) {launch();}
     }

     InventoryController Class{

         TextField search

         TextField serialNumberInput

         TextField nameInput

         TextField valueInput

         Button AddButton

         Button EditButton

         Button RemoveButton

         Button RemoveAllButton

         TableColumn Serial_Number

         TableColumn Name

         TableColumn Value

         Table Inventory

         ObservableList<Item> list

         FilteredList filteredItems

         SortedList sortedItems

         FileChooser file_chooser

         initialize()
         //initializes the table and the lists.

         addItem()
         //add an item to the inventory table using the Item class constructor

         removeItem()
         //delete the selected item in the inventory

         removeAll()
         //delete every item in the inventory

         editItem()
         //edit the selected item in the inventory

         save()
         //save the inventory to a file

         load()
         //load a file to display it in the app

         filter()
         //filter the data in the inventory using the search TextField

         sort()
         //sorts the inventory ascending or descending

}
     Item Class{

     private name

     private serialNumber

     private value

     constructor:
     Item()

     setters & getters:

     setName()
     getName()

     setSerialNumber()
     getSerialNumber()

     setValue()
     getValue()


     //I will also create a folder just inside the src folder called files
     //this files folder will be the default path to save files from the app





*/
