package com.example.demo;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Bird {

    private Rectangle bird;
    private double jumpVelocity;
    CollisionHandler collisionHandler = new CollisionHandler();

    public Bird(Rectangle bird, double jumpVelocity) {
        this.bird = bird;
        this.jumpVelocity = jumpVelocity;
    }

    public void jump(){
        double movement = -jumpVelocity;
        if(bird.getLayoutY() + bird.getY() <= jumpVelocity){
            movement = -(bird.getLayoutY() + bird.getY());
        }

        moveBirdY(movement);
    }

    public void moveBirdY(double positionChange){
        bird.setY(bird.getY() + positionChange);
    }

    public boolean isBirdDead(ArrayList<Rectangle> obstacles, AnchorPane plane){
        double birdY = bird.getLayoutY() + bird.getY();

        if(collisionHandler.collisionDetection(obstacles, bird)){
            return  true;
        }

        return birdY >= plane.getHeight();
    }
}