package com.example.demo;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
public class BirdPhysics {
    private double xPos, yPos;        // Current position
    private double yVelocity;      // Velocity
    private double gravity;
    private double airResistance;
    private double jumpVelocity;
    private Rectangle bird;
    //Using the CollisionHandler class to check if the bird collides with the obstacles
    CollisionHandler collisionHandler = new CollisionHandler();

    public BirdPhysics(double xPos, double yPos, double yVelocity, double gravity, double airResistance, double jumpVelocity, Rectangle bird) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.yVelocity = yVelocity;
        this.gravity = gravity;
        this.airResistance = airResistance;
        this.jumpVelocity = jumpVelocity;
        this.bird = bird;
    }

    // everytime you jump, it will cause velocity to be negative, making the bird go up
    // Since the velocity is px/frame jumpVelocity * 1 frame = delta X (per frame)
    public void jump(){
        double movement = -jumpVelocity;
        if(bird.getLayoutY() + bird.getY() <= jumpVelocity * 1){
            movement = -(bird.getLayoutY() + bird.getY());
        }
        moveBirdY(movement);
    }

    public void moveBirdY(double positionChange){
        bird.setY(bird.getY() + positionChange);
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
    public double getX() {
        return xPos;
    }
    public double getY() {
        return yPos;
    }

    public boolean isBirdDead(ArrayList<Rectangle> obstacles, AnchorPane plane){
        double birdY = bird.getLayoutY() + bird.getY();

        if(collisionHandler.collisionDetection(obstacles, bird)){
            return  true;
        }

        return birdY >= plane.getHeight();
    }
}