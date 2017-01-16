/*
 * Mitchell Van Braeckel
 * 13/01/2017
 * Final Project ICS4U -- Battle Arena -- 
 -*- Boulder    --> a boulder or rock obstacle in the arena (3 diff sizes w/ diff health)
    Small:  radius = 10,  health = 10
    Medium: radius = 15 health = 20
    Large:  radius = 20, health = 30
 */
package com.manstudios.gameobjects;

// imports
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
/**
 *
 * @author Mitchell Van Braeckel
 */
public class Boulder {
        // ATTRIBUTES
    private Vector2 position;
    
    private int radius, health;
    private Circle boundingCircle;  //for collision intersector
    private Color colour;
    
    //private ArrayList<Boulder> boulderList;// = new ArrayList<Boulder>();
    
    // constants for actual game size (edge of game screen)
    public static final int ACTUAL_GAME_WIDTH = 600;
    public static final int ACTUAL_GAME_HEIGHT = 400;
        // CONSTRUCTORS
    /**
     * Primary Constructor - instantiates a new Boulder w/ default values
     * @param x the x-coordinate of the Avatar
     * @param y the y-coordinate of the Avatar
     * @param r the radius of the Avatar
     * @param c the colour of the Avatar
     */
    public Boulder(float x, float y, int r, int hp, Color c) {
        position = new Vector2(x, y);
        health = hp;
        radius = r;
        colour = c;
        boundingCircle = new Circle();
    }
        // BEHAVIOURS
    /**
     * Updates the Avatar's position using acceleration and velocity (called when GameWorld updates)
     * @param delta the number of seconds (usually a small fraction) that has passed since the last time that the render method (from GameWorld) was called
     */
    public void update(float delta) {
        boundingCircle.set(position.x, position.y, radius);     //moves bounds with the avatar
    }
    
    // ================================ Getters ================================
    /**
     * Retrieves the x-position of an Avatar
     * @return the x-position of the Avatar
     */
    public float getX() {
        return position.x;
    }
    /**
     * Retrieves the y-position of an Avatar
     * @return the y-position of the Avatar
     */
    public float getY() {
        return position.y;
    }
    /**
     * Retrieves the radius of an Avatar
     * @return the radius of the Avatar
     */
    public float getRadius() {
        return radius;
    }
    /**
     * Retrieves an Avatar's body hurtbox
     * @return the Avatar's body hurtbox
     */
    public Circle getBoundingCircle() {
        return boundingCircle;
    }
    /**
     * Retrieves an Avatar's position
     * @return the Avatar's position
     */
    public Vector2 getPosition() {
        return position;
    }
    public Color getColour() {
        return colour;
    }
    /**
     * Retrieves an Avatar's health
     * @return the Avatar's health
     */
    public int getHealth() {
        return health;
    }
    // ================================ Setters ================================
    /**
     * Changes an Avatar's health
     * @param hp the new health of the Avatar
     */
    public void setHealth(int hp) {
        health = hp;
    }
    /**
     * Changes an Avatar's health
     * @param hp the amount of health the Avatar lost
     */
    public void subtractHealth(int hp) {
        health -= hp;
        // make sure health doesn't go below zero
        if(health < 0) {
            health = 0;
        }
    }
    /**
     * Changes an Avatar's position
     * @param newPos the new position of the Avatar
     */
    public void setFullPosition(Vector2 newPos) {
        position = newPos;
    }
    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }
    // =========================================================================
} // end Boulder class
