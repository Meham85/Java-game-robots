package com.kodilla.simplejavagame;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;


public class Robots extends Application {


    private Image imageback = new Image("board.JPG");
    private Image stamp1 = new Image("stamp1.jpg", 100, 100, false, false);
    private Image stamp4 = new Image("stamp4.jpg", 100, 100, false, false);




    public static void main(String[] args) {
        launch(args);

    }





    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(450, 450, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        ColumnConstraints column0 = new ColumnConstraints(90);

        ColumnConstraints column1 = new ColumnConstraints(115);

        ColumnConstraints column2 = new ColumnConstraints(115);

        ColumnConstraints column3 = new ColumnConstraints(115);

        RowConstraints row1 = new RowConstraints(115);

        RowConstraints row2 = new RowConstraints(115);

        RowConstraints row3 = new RowConstraints(115);

        RowConstraints row4 = new RowConstraints(115);

        grid.getColumnConstraints().addAll(column0, column1, column2, column3);
        grid.getRowConstraints().addAll(row1, row2, row3, row4);

        grid.setHgap(1);
        grid.setVgap(1);
        grid.setBackground(background);
        grid.setGridLinesVisible(true);



        exePrint wywolywaczGrida = new exePrint(0,0);
        int rzadGrida = wywolywaczGrida.bioreGridRow();
        int kolumnaGrida = wywolywaczGrida.bioreGridCol();





        ImageView img = new ImageView(stamp1);

        img.setRotate(180);

        GridPane.setRowIndex(img, 0); // Nadajemy dziecku rząd.
        GridPane.setColumnIndex(img, 1);

        grid.getChildren().add(img);

        ImageView img3 = new ImageView(stamp1);
        img3.setRotate(180);
        EventHandler<MouseEvent> mouseHandler2 = exePrint::print2;
        img3.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler2);

        GridPane.setRowIndex(img3, 0); // Nadajemy dziecku rząd.
        GridPane.setColumnIndex(img3, 3);
        grid.getChildren().add(img3);

        ImageView img2 = new ImageView(stamp4);
        img2.setRotate(180);
        GridPane.setRowIndex(img2, 1); // Nadajemy dziecku rząd.
        GridPane.setColumnIndex(img2, 2);
        grid.getChildren().add(img2);

        ImageView img4 = new ImageView(stamp1);
        GridPane.setRowIndex(img4, 3); // Nadajemy dziecku rząd.
        GridPane.setColumnIndex(img4, 1);
        grid.getChildren().add(img4);

        ImageView img5 = new ImageView(stamp4);

        EventHandler<KeyEvent> keyHandler1 = e -> {
            KeyCode type = e.getCode();


            GridPane.setRowIndex(img, (rzadGrida +2)); // Nadajemy dziecku rząd.
            GridPane.setColumnIndex(img, (kolumnaGrida +2));

            System.out.println("Wcisnąłeś klawisz: " + type);

        };
        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler1);

        GridPane.setRowIndex(img5, 3); // Nadajemy dziecku rząd.
        GridPane.setColumnIndex(img5, 3);
        grid.getChildren().add(img5);

        Scene scene = new Scene(grid, 500, 800, Color.BLACK);
        EventHandler<MouseEvent> mouseHandler = exePrint::print2;
        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseHandler);
        listenKeyboard(scene);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Robots Java Game");
        primaryStage.setOpacity(0.9); /* ustawia przeźroczystość sceny */
        primaryStage.show();
        System.out.println(primaryStage.hasProperties());


    }

    private void listenKeyboard(Scene scene) {
       scene.setOnKeyPressed(s->{
           System.out.println(s.getCode());
           if(s.getCode()==KeyCode.NUMPAD8) {
               System.out.println("wcisnieto 8 na klaw numerycznej");

           }

       });
    }

}
