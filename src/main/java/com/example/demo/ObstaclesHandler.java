package com.example.demo;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ObstaclesHandler {

    private AnchorPane plane;
    private double planeHeight;
    private double planeWidth;
    Random random = new Random();

    private double velocity = 3.5;

    public ObstaclesHandler(AnchorPane plane, double planeHeight, double planeWidth) {
        this.plane = plane;
        this.planeHeight = planeHeight;
        this.planeWidth = planeWidth;
    }

    public ArrayList<Rectangle> createObstacles(){

        int width = 25;
        double xPos = planeWidth;
        double space = 200;
        double recTopHeight = random.nextInt((int)(planeHeight - space - 100)) + 50;
        double recBottomHeight = planeHeight - space - recTopHeight;

        //                                     x      y   width   height
        Rectangle rectangleTop = new Rectangle(xPos,0,width,recTopHeight);
        Rectangle rectangleBottom = new Rectangle(xPos, recTopHeight + space, width, recBottomHeight);

        rectangleTop.getStyleClass().add("pipe");       //css stuff from stylesGame.css
        rectangleBottom.getStyleClass().add("pipe");        //" " " "

        plane.getChildren().addAll(rectangleTop,rectangleBottom);
        return new ArrayList<>(Arrays.asList(rectangleTop,rectangleBottom));
    }


    public void moveObstacles(ArrayList<Rectangle> obstacles){

        ArrayList<Rectangle> outOfScreen = new ArrayList<>();
        String data="3.5";

        /*velocity m/s of the bird
            Let 1px = 1m
            Therefore -> velocity(m/s) = velocity (px/s)
            but using frames: real velocity / 62 = new velocity
         */
        try {
            File myObj = new File("Data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            velocity = Double.parseDouble(data);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        for (Rectangle rectangle: obstacles) {

            moveRectangle(rectangle, - velocity);

            if(rectangle.getX() <= -rectangle.getWidth()){
                outOfScreen.add(rectangle);
            }
        }
        obstacles.removeAll(outOfScreen);
        plane.getChildren().removeAll(outOfScreen);
    }

    private void moveRectangle(Rectangle rectangle, double amount){
        rectangle.setX(rectangle.getX() + amount);
    }
}
