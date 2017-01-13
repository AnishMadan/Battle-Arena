/*
 * Mitchell Van Braeckel
 * 04/01/2017
 * Final Project ICS4U -- Battle Arena -- 
 -*- Ranged     --> an avatar's ranged attack
 */
package com.manstudios.gameobjects;

// imports
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
/**
 *
 * @author Mitchell Van Braeckel
 */
public class Ranged {
        // ATTRIBUTES
    private Vector2 position, velocity;
    
    private int radius;
    private Circle boundingCircle;  //for collision intersector
    private Color colour;
    
    private Vector2 movementSpeed;// = 500;
    
    // constants for actual game size (edge of game screen)
    public static final int ACTUAL_GAME_WIDTH = 600;
    public static final int ACTUAL_GAME_HEIGHT = 400;
    
        // CONSTRUCTORS
    
    /**
     * Primary Constructor - instantiates a new Ranged attack
     * @param x the x-coordinate of the Avatar's edge
     * @param y the y-coordinate of the Avatar's edge
     * @param r the radius of the projectile
     * @param c the colour of the projectile
     */
    public Ranged (float x, float y, int r, Color c, float xv, float yv) {
        colour = c;
        radius = r;
        position = new Vector2(x, y);
        velocity = new Vector2(500*xv, 500*yv);
        movementSpeed = new Vector2(500*xv, 500*yv);
        boundingCircle = new Circle();
    }
        // BEHAVIOURS
    /**
     * Updates the projectile's position using acceleration and velocity (called when GameWorld updates)
     * @param delta the number of seconds (usually a small fraction) that has passed since the last time that the render method (from GameWorld) was called
     */
    public void update(float delta) {
        velocity.add(movementSpeed.cpy().scl(delta));
        // ================= Check speed caps (all directions) =================
        if(velocity != movementSpeed) { //up
            velocity = movementSpeed;
        }
        // =====================================================================
        position.add(velocity.cpy().scl(delta));        //add the updated scaled velocity to the Avatar's position (gives us our new position)
        
        boundingCircle.set(position.x, position.y, radius);
    }
    
    // need to change "Avatar" to "projectile" *************************************************************
    
    /**
     * Check Screen Boundaries.
     * @return true if off of screen
     */
    public boolean isOffScreen() {
        return position.y + radius < 0 || position.y - radius > ACTUAL_GAME_HEIGHT
                || position.x + radius < 0 || position.x - radius > ACTUAL_GAME_WIDTH;
        // top, btm, left, right sides of screen check
    }
    /**
     * Checks if projectile collides with other game objects
     * @param a the opposing Avatar
     * @return true if it collides with another game object
     */
    public boolean hits(Avatar a) {
        return Intersector.overlaps(getBoundingCircle(), a.getBoundingCircle());
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
    /**
     * Retrieves an Avatar's velocity
     * @return the Avatar's velocity
     */
    public Vector2 getVelocity() {
        return velocity;
    }
    /**
     * Retrieves an Avatar's colour
     * @return the Avatar's colour
     */
    public Color getColour() {
        return colour;
    }
    // ================================ Setters ================================
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
    /**
     * Changes an Avatar's velocity
     * @param x the x-vector of the Avatar's new velocity
     * @param y the y-vector of the Avatar's new velocity
     */
    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }
    // =========================================================================
} // end class ranged
