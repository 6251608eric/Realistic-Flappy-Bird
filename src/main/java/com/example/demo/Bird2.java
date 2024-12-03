package com.example.demo;

public class Bird2 {
    private double xPos, yPos;        // Current position
    private double xVelocity, yVelocity;      // Velocity
    private double gravity;
    private double jumpVelocity;

    public Bird2(double xPos, double yPos, double gravity, double jumpVelocity) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.gravity = gravity;
        this.jumpVelocity = jumpVelocity;
    }

    // everytime you jump, it will cause velocity to be negative, making the bird go up
    public void jump() {
        this.yVelocity = -jumpVelocity;
    }

    public void update(double deltaTime) {

        //Vertical Displacement Formula: Displacement =  (initialPos * time) + 1/2(g * time^2)
        double newXPos = xPos + xVelocity * deltaTime; //xVelocity is 0 because the bird is not moving in x-axis
        double newYPos = yPos += yVelocity * deltaTime + 0.5 * gravity * deltaTime * deltaTime; //yVelocity will be negative if the bird jumps

        xPos = newXPos;
        yPos = newYPos;

        //Velocity Formula:
        yVelocity = yVelocity + gravity * deltaTime;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
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
}