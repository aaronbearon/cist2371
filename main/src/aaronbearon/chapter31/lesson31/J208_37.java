package aaronbearon.chapter31.lesson31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Aaron Blum, CIST 2372 Java 2, Lab 8 Part 7
 * Description: Add three new menu options: Save, Save As, and Undo.
 */
public class J208_37 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemNew = new MenuItem("New",
                new ImageView("https://liveexample.pearsoncmg.com/html/image/new.gif"));
        MenuItem menuItemOpen = new MenuItem("Open",
                new ImageView("https://liveexample.pearsoncmg.com/html/image/open.gif"));
        MenuItem menuItemPrint = new MenuItem("Print",
                new ImageView("https://liveexample.pearsoncmg.com/html/image/print.gif"));
        //* Added 3 new menu items for save, save as and undo.
        MenuItem menuItemSave = new MenuItem("Save",
                new ImageView("https://liveexample.pearsoncmg.com/html/image/save.gif"));
        MenuItem menuItemSaveAs = new MenuItem("Save As",
                new ImageView("https://img.icons8.com/material-outlined/16/000000/save-as.png"));
        MenuItem menuItemUndo = new MenuItem("Undo",
                new ImageView("https://liveexample.pearsoncmg.com/html/image/left.gif"));
        MenuItem menuItemExit = new MenuItem("Exit");
        contextMenu.getItems().addAll(menuItemNew, menuItemOpen,
                menuItemPrint, menuItemSave, menuItemSaveAs, menuItemUndo, menuItemExit);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setTitle("ContextMenuDemo"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window

        pane.setOnMousePressed(
                e -> contextMenu.show(pane, e.getScreenX(), e.getScreenY()));

        menuItemNew.setOnAction(e -> System.out.println("New"));
        menuItemOpen.setOnAction(e -> System.out.println("Open"));
        menuItemPrint.setOnAction(e -> System.out.println("Print"));
        //* Added the three new handlers down here.
        menuItemSave.setOnAction(e -> System.out.println("Save"));
        menuItemSaveAs.setOnAction(e -> System.out.println("Save As"));
        menuItemUndo.setOnAction(e -> System.out.println("Undo"));

        menuItemExit.setOnAction(e -> System.exit(0));
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
