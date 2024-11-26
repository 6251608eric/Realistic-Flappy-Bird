package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class MenuScene extends Application {
    private Stage primaryStage;
    private TextField gravityTF = new TextField("9.8");
    private TextField velocityTF = new TextField("3.5");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        Text flappyTitle = new Text("Realistic Flappy Bird");
        flappyTitle.getStyleClass().add("title");

        Button playButton = new Button("PLAY");
        playButton.getStyleClass().add("play-button");
        playButton.setOnAction(actionEvent -> openGameScene());

        Label gravityLbl = new Label("Set gravity (m/s^2)");
        Label velocityLbl = new Label("Set velocity (m/s)");
        gravityLbl.getStyleClass().add("label");
        velocityLbl.getStyleClass().add("label");

        gravityTF.getStyleClass().add("text-field");
        velocityTF.getStyleClass().add("text-field");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(gravityLbl, 0, 0);
        gridPane.add(gravityTF, 0, 1);
        gridPane.add(velocityLbl, 1, 0);
        gridPane.add(velocityTF, 1, 1);
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(flappyTitle, playButton, gridPane);
        VBox.setMargin(playButton, new Insets(75, 0, 0, 0));

        Scene scene = new Scene(vBox, 1600, 800);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Flappy Bird!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openGameScene() {
        try {
            FileWriter myWriter = new FileWriter("Data.txt");
            myWriter.write(gravityTF.getText() + "\n" + velocityTF.getText());
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