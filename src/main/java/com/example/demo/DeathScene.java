package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DeathScene {
    private Scene scene;
    private Stage stage;
    private int finalScore;

    public DeathScene(Stage stage, int finalScore) {
        this.stage = stage;
        this.finalScore = finalScore;
        createScene();
    }

    private void createScene() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Label deathLabel = new Label("Game Over!");
        deathLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        Label scoreLabel = new Label("Your score: " + finalScore);
        scoreLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 30));

        Button retryButton = new Button("Retry");
        retryButton.setOnAction(e -> retryGame());

        Button returnButton = new Button("Return to Menu");
        returnButton.setOnAction(e -> returnToMenu());

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(retryButton, returnButton);

        root.getChildren().addAll(deathLabel, scoreLabel, buttonBox);

        scene = new Scene(root, 1600, 800);
    }

    private void retryGame() {
        try {
            GameScene gameScene = new GameScene();
            gameScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error restarting game: " + e.getMessage());
        }
    }

    private void returnToMenu() {
        try {
            MenuScene menuScene = new MenuScene();
            menuScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error returning to menu: " + e.getMessage());
        }
    }

    public Scene getScene() {
        return scene;
    }
}