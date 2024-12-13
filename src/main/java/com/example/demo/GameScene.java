package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameScene {
    private Stage stage;
    private Controller controller;

    public void start(Stage stage) throws IOException {
        //initialize the base scene
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("view.fxml"));
        StackPane root = new StackPane();
        root.getChildren().add(fxmlLoader.load());

        controller = fxmlLoader.getController();
        controller.setGameScene(this);
        controller.resetGame();

        Label instructionLabel = new Label("Press SPACE to jump!");
        instructionLabel.getStyleClass().add("instruction-label");
        root.getChildren().add(instructionLabel);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/stylesGame.css").toExternalForm());     //css file

        stage.setTitle("Flappy Bird!");
        stage.setScene(scene);
        stage.show();

        //space bar and jump
        scene.setOnKeyPressed(event -> controller.pressed(event));

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), instructionLabel);
        fadeOut.setDelay(Duration.seconds(1.5));
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> root.getChildren().remove(instructionLabel));
        fadeOut.play();

        scene.getRoot().requestFocus();
    }

    public void switchToDeathScene(int finalScore) {
        DeathScene deathScene = new DeathScene(stage, finalScore);
        stage.setScene(deathScene.getScene());
    }
}