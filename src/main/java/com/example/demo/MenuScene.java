package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


//This class handles all the components in the menu screen.
public class MenuScene extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        Text flappyTitle = new Text("Realistic Flappy Bird");
        Font font = Font.font("Arial Black", FontWeight.BOLD, 75);
        flappyTitle.setFont(font);
        Button playButton = new Button("PLAY");
        playButton.setStyle("-fx-font-family: Arial; -fx-font-size: 20px; -fx-font-weight: bold;");
        playButton.setMinWidth(500);
        playButton.setMinHeight(100);
//        playButton.setOnAction(actionEvent -> switchToGameScene());  //event handler
        Label gravityLbl = new Label("Set gravity...");
        Label velocityLbl = new Label("Set velocity...");
        gravityLbl.setAlignment(Pos.BASELINE_LEFT);
        TextField gravityTF = new TextField("");
        TextField velocityTF = new TextField("");

        GridPane gridPane = new GridPane(); // GridPane for the lbl + txtFields
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(gravityLbl, 0, 0);
        gridPane.add(gravityTF, 0, 1);
        gridPane.add(velocityLbl, 1, 0);
        gridPane.add(velocityTF, 1, 1);
        gridPane.setHgap(10);
        gridPane.setVgap(1);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(flappyTitle, playButton, gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        VBox.setMargin(playButton, new Insets(75, 0, 0, 0));

        Scene scene = new Scene(vBox, 1600, 800);
        primaryStage.setTitle("Flappy Bird!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //method for playButton to switch to game scene
//    public void switchToGameScene() {
//        try {
//            Scene gameScene = GameScene.getGameScene();
//            primaryStage.setScene(gameScene);
//            primaryStage.setTitle("Game Scene");
//            gameScene.getRoot().requestFocus();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}