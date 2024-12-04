package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuScene extends Application {
    private Stage primaryStage;
    private TextField gravityTF = new TextField("9.8");
    private TextField velocityTF = new TextField("3.5");
    private ArrayList<Double> dataArray = new ArrayList<>();


    private Slider slider = new Slider(2, 10, 4);

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        try {
            File myObj = new File("Data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                dataArray.add(Double.parseDouble(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        double gravity = dataArray.get(0);
        double velocity = dataArray.get(1);

        gravityTF.setText(String.valueOf(gravity));
        slider.setValue(velocity);

        Text flappyTitle = new Text("Realistic Flappy Bird");
        flappyTitle.getStyleClass().add("title");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(flappyTitle.scaleXProperty(), 1),
                        new KeyValue(flappyTitle.scaleYProperty(), 1)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(flappyTitle.scaleXProperty(), 1.1),
                        new KeyValue(flappyTitle.scaleYProperty(), 1.1)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(flappyTitle.scaleXProperty(), 1),
                        new KeyValue(flappyTitle.scaleYProperty(), 1)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Button playButton = new Button("PLAY");
        playButton.getStyleClass().add("play-button");
        playButton.setOnAction(actionEvent -> openGameScene());

        Label gravityLbl = new Label("Set gravity (m/s^2)");
        Label velocityLbl = new Label("Set velocity (m/s)");
        gravityLbl.getStyleClass().add("label");
        velocityLbl.getStyleClass().add("label");

        gravityTF.getStyleClass().add("text-field");
        velocityTF.getStyleClass().add("text-field");

        // Creates a slider


        // enable the marks
        slider.setShowTickMarks(true);

        // enable the Labels
        slider.setShowTickLabels(true);

        // set Major tick unit
        slider.setMajorTickUnit(1.0f);
        slider.getStyleClass().add("text-field");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(gravityLbl, 0, 0);
        gridPane.add(gravityTF, 0, 1);
        gridPane.add(velocityLbl, 1, 0);
        gridPane.add(slider, 1, 1);
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));



        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(flappyTitle, playButton, gridPane);
        VBox.setMargin(playButton, new Insets(75, 0, 0, 0));

        Scene scene = new Scene(vBox, 1600, 800);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());     //css file

        primaryStage.setTitle("Flappy Bird!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openGameScene() {
        try {
            FileWriter myWriter = new FileWriter("Data.txt"); 
            myWriter.write(gravityTF.getText() + "\n" + slider.getValue());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {

            GameScene gameScene = new GameScene();
            gameScene.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}