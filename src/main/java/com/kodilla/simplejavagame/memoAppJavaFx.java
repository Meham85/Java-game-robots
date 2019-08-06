package com.kodilla.simplejavagame;



import java.util.*;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;

import javafx.beans.property.BooleanProperty;

import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.Pane;
        import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
        import javafx.scene.text.Text;


import javafx.util.Duration;

public class memoAppJavaFx extends Application {

    private static final int Liczba_Par = 8;
    private static final int Ilosc_Par_W_Rzedzie = 4;

    private Dachowka selected = null;
    private int clickCount = 2;
    private int clickNumber = 0;
    public int score;


    TextField pole1 = new TextField("Punkty gracza " + score);

    private Parent createContent() {
        Pane root = new Pane(); /* JavaFX 9. Tworzenie graficznych interfejsów użytkownika str314 */
        root.setPrefSize(400, 400);

        ToggleButton timmerButton = new ToggleButton("Start gry");
        BooleanProperty sel = timmerButton.selectedProperty(); /*Pobieramy właściwość selected. */
        sel.addListener((observable, oldValue, newValue) -> {
            System.out.println("Aktualny stan to: " + newValue);
            timmerButton.setText(newValue ? "Gra w toku" : "Wznów grę");
        });
        timmerButton.relocate(20, 350);
        root.getChildren().add(timmerButton); // Dodajemy przełącznik do rozkładu.


        pole1.relocate(180, 350);
        root.getChildren().add(pole1);
        pole1.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {

                pole1.setText(String.valueOf(score));
                System.out.println(score);
            }
        });






        char c = 'A'; /*deklaracja znaku pczatkowego kolekcji puzzli */
        List<Dachowka> dachowki = new ArrayList<>(); /* tworze liste z puzzlami */
        for (int i = 0; i < Liczba_Par; i++) {
            dachowki.add(new Dachowka(String.valueOf(c)));
            dachowki.add(new Dachowka(String.valueOf(c)));
            c++;
        }

        Collections.shuffle(dachowki); /*tasowanie puzzli w tablicy */

        for (int i = 0; i < dachowki.size(); i++) {
            Dachowka dachowka = dachowki.get(i);
            dachowka.setTranslateX(50 * (i % Ilosc_Par_W_Rzedzie)); /*przesunięcie w oparciu o ilość par */
            dachowka.setTranslateY(50 * (i / Ilosc_Par_W_Rzedzie)); /*przesunięcie w oparciu o ilość par */
            root.getChildren().add(dachowka);
        }

        return root;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Memory Simple Game");
        primaryStage.setOpacity(0.9); /* ustawia przeźroczystość sceny */
        primaryStage.show();
    }

    private class Dachowka extends StackPane { /* JavaFX 9. Tworzenie graficznych interfejsów użytkownika str316 */
        private Text text = new Text();

        public Dachowka(String value) {
            Circle border = new Circle(25, null);
            border.setFill(Color.WHITESMOKE);
            border.setStroke(Color.BLACK);

            text.setText(value);
            text.setFont(Font.font(30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(this::handleMouseClick);
            close();
        }

        public void handleMouseClick(MouseEvent event) {

            pole1.setText("test");
            if (isOpen() || clickCount == 0)
                return;

            clickCount--;

            if (selected == null) {
                selected = this;

                open(() -> {});
            }
            else {
                open(() -> {
                    if (!hasSameValue(selected)) {
                        selected.close();

                        this.close();
                    }

                    selected = null;
                    clickCount = 2;
                });
            }
        }

        public boolean isOpen() {
            return text.getOpacity() == 1; /*pobieramy nieprzezroczystosc po kliknieciu - czyli zapalamy */
        }

        public void open(Runnable action) {
            FadeTransition animacja = new FadeTransition(Duration.seconds(0.5), text);
            animacja.setToValue(1); /* wartosc 1 do zapalenia pełnego  */
            animacja.setOnFinished(e -> action.run());
            animacja.play();
        }

        public void close() {  /* animacja kliku - zanikanie  puzzla */
            FadeTransition animacja = new FadeTransition(Duration.seconds(0.5), text);
            animacja.setToValue(0); /* wartosc 0 do wygaszenia */
            animacja.play();
        }

        public boolean hasSameValue(Dachowka other) { /*sprawdzenie czy wartosci puzza sa takie same */
            return text.getText().equals(other.text.getText());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}