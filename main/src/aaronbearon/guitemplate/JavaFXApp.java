package aaronbearon.guitemplate;

import aaronbearon.chapter31.samples.AddNewRowDemo;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();

        GridPane newPane = new GridPane();
        newPane.setAlignment(Pos.CENTER); // Set center alignment
        newPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        newPane.setHgap(5.5);
        newPane.setVgap(5.5); // Set vGap to 5.5px

        // Place nodes in the newPane
        newPane.add(new Label("First Name:"), 0, 0);
        newPane.add(new TextField(), 1, 0);
        newPane.add(new Label("MI:"), 0, 1);
        newPane.add(new TextField(), 1, 1);
        newPane.add(new Label("Last Name:"), 0, 2);
        newPane.add(new TextField(), 1, 2);
        Button btAdd = new Button("Add Name");
        newPane.add(btAdd, 1, 3);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        // Radio Button
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlue = new RadioButton("Blue");
        newPane.getChildren().addAll(rbRed, rbGreen, rbBlue);

        ToggleGroup group = new ToggleGroup();
        rbRed.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        rbBlue.setToggleGroup(group);

        rbRed.setOnAction(e -> {
            if (rbRed.isSelected()) {
                // text.setFill(Color.RED);
            }
        });

        rbGreen.setOnAction(e -> {
            if (rbGreen.isSelected()) {
                // text.setFill(Color.GREEN);
            }
        });

        rbBlue.setOnAction(e -> {
            if (rbBlue.isSelected()) {
                // text.setFill(Color.BLUE);
            }
        });

        // Check box
        Font fontBoldItalic = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.ITALIC, 20);
        Font fontBold = Font.font("Times New Roman",
                FontWeight.BOLD, FontPosture.REGULAR, 20);
        Font fontItalic = Font.font("Times New Roman",
                FontWeight.NORMAL, FontPosture.ITALIC, 20);
        Font fontNormal = Font.font("Times New Roman",
                FontWeight.NORMAL, FontPosture.REGULAR, 20);

        // text.setFont(fontNormal);

        VBox paneForCheckBoxes = new VBox(20);
        paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));
        paneForCheckBoxes.setStyle("-fx-border-color: green");
        CheckBox chkBold = new CheckBox("Bold");
        CheckBox chkItalic = new CheckBox("Italic");
        paneForCheckBoxes.getChildren().addAll(chkBold, chkItalic);
        // newPane.setRight(paneForCheckBoxes);

        EventHandler<ActionEvent> handler = e -> {
            if (chkBold.isSelected() && chkItalic.isSelected()) {
                // text.setFont(fontBoldItalic); // Both check boxes checked
            } else if (chkBold.isSelected()) {
                // text.setFont(fontBold); // The Bold check box checked
            } else if (chkItalic.isSelected()) {
                // text.setFont(fontItalic); // The Italic check box checked
            } else {
                // text.setFont(fontNormal); // Both check boxes unchecked
            }
        };

        chkBold.setOnAction(handler);
        chkItalic.setOnAction(handler);

        //1. UI components -----------------------------
        // Label lbl1 = new Label("Enter a number:");
        TextField txtIn = new TextField();
        // Label lbl2 = new Label("Enter a number:");
        TextField txtIn2 = new TextField();
        // Label lbl3 = new Label("Result:");
        TextField txtOut = new TextField();
        // txtOut.setEditable(false);
        Button btnStart = new Button("Calculate");

//        TabPane tabPane = new TabPane();
//        Tab tab1 = new Tab("Line");
//        StackPane pane1 = new StackPane();
//        pane1.getChildren().add(new Line(10, 10, 80, 80));
//        tab1.setContent(pane1);
//        Tab tab2 = new Tab("Rectangle");
//        tab2.setContent(new Rectangle(10, 10, 200, 200));
//        Tab tab3 = new Tab("Circle");
//        tab3.setContent(new Circle(50, 50, 20));
//        Tab tab4 = new Tab("Ellipse");
//        tab4.setContent(new Ellipse(10, 10, 100, 80));
//        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

//        VBox vBox = new VBox(10);
//        RadioButton rbUS = new RadioButton("US");
//        RadioButton rbUK = new RadioButton("UK");
//        RadioButton rbCA = new RadioButton("CA");
//        vBox.getChildren().addAll(rbUS, rbUK, rbCA);
//
//        SplitPane content = new SplitPane();
//        content.setOrientation(Orientation.VERTICAL);
//        ImageView imageView = new ImageView(usImage);
//        StackPane imagePane = new StackPane();
//        imagePane.getChildren().add(imageView);
//        TextArea taDescription = new TextArea();
//        taDescription.setText(usDescription);
//        content.getItems().addAll(
//                imagePane, new ScrollPane(taDescription));

//        MenuBar menuBar = new MenuBar();
//
//        Menu menuOperation = new Menu("Operation");
//        Menu menuExit = new Menu("Exit");
//        menuBar.getMenus().addAll(menuOperation, menuExit);
//
//        MenuItem menuItemAdd = new MenuItem("Add");
//        MenuItem menuItemSubtract = new MenuItem("Subtract");
//        MenuItem menuItemMultiply = new MenuItem("Multiply");
//        MenuItem menuItemDivide = new MenuItem("Divide");
//        menuOperation.getItems().addAll(menuItemAdd, menuItemSubtract,
//                menuItemMultiply, menuItemDivide);
//
//        MenuItem menuItemClose = new MenuItem("Close");
//        menuExit.getItems().add(menuItemClose);

//        ContextMenu contextMenu = new ContextMenu();
//        MenuItem menuItemNew = new MenuItem("New",
//                new ImageView("https://liveexample.pearsoncmg.com/html/image/new.gif"));
//
//        pane.setOnMousePressed(
//                e -> contextMenu.show(pane, e.getScreenX(), e.getScreenY()));
//
//        menuItemNew.setOnAction(e -> System.out.println("New"));

        //2. Container --------------------------------
        VBox vbox = new VBox(10, txtIn, txtIn2, txtOut, btnStart);

        //3. Scene ------------------------------------
        Scene scene = new Scene(vbox, 300, 200);

        //4. Stage (Window) ----------------------------
        stage.setTitle("Multiply by 10");
        stage.setScene(scene);
        stage.show();

        //5. Event handler -----------------------------
        btnStart.setOnAction(e -> {
            int num = Integer.parseInt(txtIn.getText());
            int num2 = Integer.parseInt(txtIn2.getText());
            int result = num * num2;
            txtOut.setText(String.valueOf(result));
        });
    }

//    TableView<AddNewRowDemo.Country> tableView = new TableView<>();
//    ObservableList<AddNewRowDemo.Country> data =
//            FXCollections.observableArrayList(
//                    new AddNewRowDemo.Country("USA", "Washington DC", 280, true),
//                    new AddNewRowDemo.Country("Canada", "Ottawa", 32, true),
//                    new AddNewRowDemo.Country("United Kingdom", "London", 60, true),
//                    new AddNewRowDemo.Country("Germany", "Berlin", 83, true),
//                    new AddNewRowDemo.Country("France", "Paris", 60, true));
//        tableView.setItems(data);
//
//    TableColumn countryColumn = new TableColumn("Country");
//        countryColumn.setMinWidth(100);
//        countryColumn.setCellValueFactory(
//                new PropertyValueFactory<AddNewRowDemo.Country, String>("country"));

//    public static class Country {
//        private final SimpleStringProperty country;
//        private final SimpleStringProperty capital;
//        private final SimpleDoubleProperty population;
//        private final SimpleBooleanProperty democratic;
//
//        private Country(String country, String capital,
//                        double population, boolean democratic) {
//            this.country = new SimpleStringProperty(country);
//            this.capital = new SimpleStringProperty(capital);
//            this.population = new SimpleDoubleProperty(population);
//            this.democratic = new SimpleBooleanProperty(democratic);
//        }
//
//        public String getCountry() {
//            return country.get();
//        }
//
//        public void setCountry(String country) {
//            this.country.set(country);
//        }
//
//        public String getCapital() {
//            return capital.get();
//        }
//
//        public void setCapital(String capital) {
//            this.capital.set(capital);
//        }
//
//        public double getPopulation() {
//            return population.get();
//        }
//
//        public void setPopulation(double population) {
//            this.population.set(population);
//        }
//
//        public boolean isDemocratic() {
//            return democratic.get();
//        }
//
//        public void setDemocratic(boolean democratic) {
//            this.democratic.set(democratic);
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
