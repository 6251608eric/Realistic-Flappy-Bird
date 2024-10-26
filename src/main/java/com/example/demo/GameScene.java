package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameScene {
    private Stage stage;
    private Controller controller;

    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        controller.setGameScene(this);
        controller.resetGame(); // Make sure this method exists and resets all game state
        scene.getRoot().requestFocus();
        stage.setTitle("Flappy Bird!");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDeathScene(int finalScore) {
        DeathScene deathScene = new DeathScene(stage, finalScore);
        stage.setScene(deathScene.getScene());
    }
}