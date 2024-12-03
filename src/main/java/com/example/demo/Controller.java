package com.example.demo;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    private FpsFxTest fpsFxTest = new FpsFxTest();

    private GameScene gameScene;

    // If bird jumps
    public boolean flag = false;

    public double gravity = 9.8/62;
    public double velocity = 3.5;

    public ArrayList<Double> dataArray = new ArrayList<>();

    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;

    @FXML
    private Rectangle bird;

    @FXML
    private Text score;

    private double jumptime = 0;
    private double accelerationTime = 0;
    private int gameTime = 0;
    private int scoreCounter = 0;

    private Bird2 birdComponent;
    private ObstaclesHandler obstaclesHandler;

    ArrayList<Rectangle> obstacles = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //starting the fps meter
        fpsFxTest.setup();
        fpsFxTest.start();

        bird.getStyleClass().add("bird");   //make the bird yellow from stylesGame.css

        /*Find gravity
            Since fps = 62fps -> 1seconds = 62 frames
            real gravity/62 = game gravity
         */
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
        gravity = dataArray.get(0)/62;
        velocity = dataArray.get(1)/0.02;

        // recording the position of the bird



        /*Calculation of the jump Velocity
        Delta X = 7.5m -> 75px (10px = 1m)  Delta t = 0.2s -> 12.4 frames (1s = 62frames)
        Then:  velocity = Delta X / Delta T ->  velocity = 75px/12.4 = 6px/frame
                                            ->  velocity = 7.5m / 0.2s = 375m/s
         */
        int jumpVelocity = 6;
        birdComponent = new Bird2(300,200, gravity, jumpVelocity, bird);
        double planeHeight = 600;
        double planeWidth = 400;
        obstaclesHandler = new ObstaclesHandler(plane, planeHeight, planeWidth);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };

        load();

        gameLoop.start();
    }

    @FXML
    void pressed(KeyEvent event) {
        if(event.getCode() == KeyCode.SPACE){
            jumptime = 0;
            flag = true;
        }
    }

    public void setGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    //Called every game frame
    private void update() {
        if (flag){
            birdComponent.jump();
            jumptime++;

            if (jumptime>=13){flag=false;accelerationTime=0;}
        }else {
            gameTime++;
            accelerationTime++;

            //change the bird position formula       delta y = gravity * delta time
            birdComponent.moveBirdY(gravity * accelerationTime);
        }

            if (pointChecker(obstacles, bird)) {
                scoreCounter++;
                System.out.print("Score +1");
                score.setText(String.valueOf(scoreCounter));
            }


            obstaclesHandler.moveObstacles(obstacles);
            //frequency of obstacles
            if (gameTime % 100 == 0) {
                obstacles.addAll(obstaclesHandler.createObstacles());
            }

            //added the death scene to the if statement
            if (birdComponent.isBirdDead(obstacles, plane)) {
                //when bird dies
                System.out.print(fpsFxTest.averageFps());
                fpsFxTest.stop();
                gameLoop.stop();
                gameScene.switchToDeathScene(scoreCounter);
            }

    }

    //Everything called once, at the game start
    private void load(){
        obstacles.addAll(obstaclesHandler.createObstacles());
    }

    //changed to public
    public void resetGame() {
        bird.setY(0);
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        accelerationTime = 0;
        scoreCounter = 0;
        score.setText(String.valueOf(scoreCounter));
    }

    private boolean pointChecker(ArrayList<Rectangle> obstacles, Rectangle bird){
        for (Rectangle obstacle: obstacles) {
            if (velocity % 2 == 0) {
                int birdPositionX = (int) (bird.getLayoutX() + bird.getX() + velocity / 100);

                int obstaclePositionX = (int) (obstacle.getLayoutX() + obstacle.getX());
                System.out.println(birdPositionX);
                System.out.println(obstaclePositionX);
                System.out.println("a");

                if (obstaclePositionX == birdPositionX || obstaclePositionX + 1 == birdPositionX) {
                    return true;
                }
            }
            else {
                int birdPositionX = (int) (bird.getLayoutX() + bird.getX() );

                int obstaclePositionX = (int) (obstacle.getLayoutX() + obstacle.getX());
                System.out.println(birdPositionX);
                System.out.println(obstaclePositionX);
                System.out.println("b");

                if (obstaclePositionX == birdPositionX || obstaclePositionX + 3 == birdPositionX || obstaclePositionX == birdPositionX+3 || obstaclePositionX + 1 == birdPositionX || obstaclePositionX == birdPositionX+1) {
                    return true;
                }
            }

        }
        return false;
    }
}