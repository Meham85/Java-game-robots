package com.kodilla.simplejavagame;



import java.util.*;
import java.util.concurrent.Callable;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;

import javafx.beans.property.BooleanProperty;

import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.Pane;
        import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
        import javafx.scene.text.Text;


import javafx.util.Duration;

public class memoAppJavaFx extends Application {

    private static final int Liczba_Par = 15;
    private static final int Ilosc_Par_W_Rzedzie = 5;

    private Dachowka selected = null;
    private int clickCount = 2;
    private int clickNumber = 0;
    public int score;
    public int scoreTemp;
    public boolean gameActive = false;
    private static final Integer STARTTIME = 0;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private double startTime;
    private  double stopTempTime;
    private double stopTime;
    private double timeResult;
    private double timeResultP1;
    private double timeResultP2;
    private double timeTemp;
    public int scoreP1;
    public int scoreP2;
    private int activePlayer;
    private String playerNumber;


    private TextField pole1 = new TextField("Punkty gracza 1 = " + String.valueOf(score));
    private TextField pole2 = new TextField("Punkty gracza 2 = " + String.valueOf(score));
    private TextField pole3 = new TextField("Czas gracza 1 - " + String.valueOf(timeResult));
    private TextField pole4 = new TextField("Czas gracza 2 - " + String.valueOf(timeResult));
    private TextField timeResultText = new TextField("Twój czas to " + String.valueOf(timeResult) + "s");
    TextField punkty = new TextField();
    TextField czas = new TextField();




    private Parent createContent() {
        Pane root = new Pane(); /* JavaFX 9. Tworzenie graficznych interfejsów użytkownika str314 */
        root.setPrefSize(400, 400);

        Button p1startButton = new Button("Start gracz 1");
        Button p2startButton = new Button("Start gracz 2");

        dachowkaCreator(root);



        pole1.relocate(250, 330);
        pole1.setEditable(false);
        root.getChildren().add(pole1);

        pole2.relocate(250, 360);
        pole2.setEditable(false);
        root.getChildren().add(pole2);

        pole3.relocate(250, 260);
        pole3.setEditable(false);
        root.getChildren().add(pole3);

        pole4.relocate(250, 300);
        pole4.setEditable(false);
        root.getChildren().add(pole4);



        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");


        p1startButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                if (timeline != null) {
                    timeline.stop();

                }
                timeResult = -1; /* oznacza że nie zmieścił się w limicie */
                gameActive = true;
                activePlayer = 1;

                System.out.println(activePlayer);
                startGame();


            }
        });

        p2startButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                if (timeline != null) {
                    timeline.stop();

                }
                score = 0;
                timeResult = -1;
                gameActive = true;
                activePlayer = 2;
                System.out.println(activePlayer);
                ArrayList<Dachowka> dachowki = new ArrayList<>();
                System.out.println(activePlayer);
                dachowkaDestroyer(root, dachowki);
                dachowkaCreator(root);

                startGame();

                }
                }



        );


        p1startButton.relocate(30, 350);
        p2startButton.relocate(120, 350);
        TextField timerText = new TextField("Walcz z czasem!");
        timerText.relocate(270, 10);
        timerText.setEditable(false);


        VBox vb = new VBox(20);             // gap between components is 20


        vb.relocate(300, 30);
        vb.getChildren().addAll(timerLabel);
        vb.setLayoutY(30);
        timeResultText.relocate(250, 180);
        root.getChildren().add(p1startButton);
        root.getChildren().add(p2startButton);
        root.getChildren().add(timerText);
        root.getChildren().add(timeResultText);
        root.getChildren().add(vb); // Dodajemy przełącznik do rozkładu.
        return root;

    }



    public List<Dachowka> dachowkaCreator(Pane root) {




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
        return dachowki;
    }
    public void dachowkaDestroyer (Pane root, ArrayList<Dachowka> dachowkaArrayList) {
        for (Dachowka element: dachowkaArrayList
        ) {root.getChildren().remove(element);

        }

    }
    public void startGame(){

        startTime = System.nanoTime();

        System.out.println(startTime);
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1000),
                        new KeyValue(timeSeconds, 1000)));
        timeline.playFromStart();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Memory Simple Game");
        primaryStage.setOpacity(0.9); /* ustawia przeźroczystość sceny */
        primaryStage.show();
    }

    public class Dachowka extends StackPane { /* JavaFX 9. Tworzenie graficznych interfejsów użytkownika str316 */
        private Text text = new Text();



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Dachowka dachowka = (Dachowka) o;

            return text != null ? text.equals(dachowka.text) : dachowka.text == null;
        }

        @Override
        public int hashCode() {
            return text != null ? text.hashCode() : 0;
        }

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
        private void showAlert() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Koniec gry");
            alert.setHeaderText("Zwyciestwo dla " + playerNumber);
            alert.setContentText("Dziękujemy za wspolną zabawę");

            alert.show();
        }

        public void handleMouseClick(MouseEvent event) {

            System.out.println(gameActive);
            if (activePlayer == 1) {
                punkty = pole1;

                czas = pole3;
                playerNumber = "Gracz 1 ";

            }
            else {

                punkty = pole2;
                czas = pole4;

                playerNumber = "Gracz 2 ";

            }
            if ( gameActive == true){

                if (isOpen() || clickCount == 0)
                    return;

                clickCount--;

                if (selected == null) {
                    selected = this;

                    open(() -> {
                    });

                } else {
                    open(() -> {
                        if (!hasSameValue(selected)) {

                            selected.close();

                            this.close();
                        }

                        if (hasSameValue(selected)) {
                            score++;
                            System.out.println("Score " + score);



                            punkty.setText("Punkty " + playerNumber + score);
                            if (score == Liczba_Par) {

                                timeline.stop();
                                stopTime = System.nanoTime();
                                System.out.println(stopTime);

                                timeResult = (stopTime - startTime) / 1_000_000_000;

                                String stringTimeResult = String.format("%.2f", timeResult);
                                timeResultText.setText("Twój czas to " + stringTimeResult + "s");

                                czas.setText("Czas " + playerNumber + stringTimeResult + "s");


                            }

                            if(activePlayer == 1) {
                                timeResultP1 = timeResult;
                                System.out.println("TimeResultP1 " + timeResultP1);
                                scoreP1 = score;
                                System.out.println("ScoreP1 " + scoreP1);

                            }
                            else{
                                timeResultP2 = timeResult;
                                System.out.println("TimeResultP2 " + timeResultP2);
                                scoreP2 = score;
                                System.out.println("ScoreP2 " + scoreP2);
                            }

                        }

                        selected = null;
                        clickCount = 2;


                        if(scoreP1 > 0  && scoreP2 >0 && timeResultP1 >0  && timeResultP2 > 0){
                         if(timeResultP1 < timeResultP2){
                         System.out.println("Gracz 1 wygrał");
                         playerNumber = "Gracz 1";

                             showAlert();
                         }
                         else {
                         System.out.println("Gracz 2 wygrał");
                             playerNumber = "Gracz 2";
                             showAlert();
                         }
                         }
                        if(timeResultP1 == -1 && timeResultP2 == -1 && scoreP1 !=0 && scoreP2 !=0){
                            if(scoreP1 < scoreP2){
                                System.out.println("Gracz 2 wygrał");
                                playerNumber = "Gracz 2";

                                showAlert();
                            }
                            if( scoreP1 > scoreP2) {
                                System.out.println("Gracz 1 wygrał");
                                playerNumber = "Gracz 1";
                                showAlert();
                            }
                            if (scoreP2 == scoreP1) {
                                System.out.println("Remis");
                                playerNumber = "Gracz 1 i Gracz 2 - REMIS";
                                showAlert();
                            }
                        }


                    });
                }


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