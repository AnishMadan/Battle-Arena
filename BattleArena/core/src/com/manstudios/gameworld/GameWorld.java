/*
 * Mitchell Van Braeckel
 * 03/01/2017
 * Final Project ICS4U -- Battle Arena -- 
-*- GameWorld      --> UPDATING helper class for GameScreen
 */
package com.manstudios.gameworld;

// imports
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.manstudios.gameobjects.Avatar;
import com.manstudios.gameobjects.Ranged;
import java.util.ArrayList;
/**
 *
 * @author Mitchell Van Braeckel
 */
public class GameWorld {
        // ATTRIBUTES
    //private Rectangle rect = new Rectangle(0, 0, 17, 12);
    private Avatar p1, p2;  //player #1, player #2
    
        // CONSTRUCTORS
    /**
     * Primary Constructor - instantiates a new GameWorld (to update the game)
     * @param gameWidth the width of the game when drawn
     * @param gameHeight the height of the game when drawn
     */
    public GameWorld(int gameWidth, int gameHeight) {
        p1 = new Avatar(50, gameHeight/4, 10, Color.BLUE);
        p2 = new Avatar(540, gameHeight/4, 10, Color.RED);
    }
        // BEHAVIOURS
    /**
     * The looping method that updates all the game objects as a helper to the GameWorld
     * @param delta the number of seconds (usually a small fraction) that has passed since the last time that the render method(from GameWorld) was called
     */
    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        p1.update(delta);
        p2.update(delta);
        
        // update Ranged objects and check screen collision
        ArrayList<Ranged> p1RangedAttacks = p1.getRangedAttackList();
        ArrayList<Ranged> p2RangedAttacks = p2.getRangedAttackList();
        
        for (int i = 0; i < p1RangedAttacks.size(); i++) {
            Ranged p1Attack = p1RangedAttacks.get(i);
            p1Attack.update(delta);
        }
        for (int i = 0; i < p2RangedAttacks.size(); i++) {
            Ranged p2Attack = p2RangedAttacks.get(i);
            p2Attack.update(delta);
        }
        
        // First should check if collides w/ opponent **************************88
        
        // Check if projectile is off the screen, if so remove it
        p1.checkScreenEdgeCollision();
        p2.checkScreenEdgeCollision();
        for (int i = 0; i < p1RangedAttacks.size(); i++) {
            Ranged p1Attack = p1RangedAttacks.get(i);
            if(p1Attack.isOffScreen()) {
                p1RangedAttacks.remove(i);
            }
        }
        for (int i = 0; i < p2RangedAttacks.size(); i++) {
            Ranged p2Attack = p2RangedAttacks.get(i);
            if(p2Attack.isOffScreen()) {
                p2RangedAttacks.remove(i);
            }
        }
        
        // Check for Avatar body collision and negate movement accordingly
        if(avatarBodyCollision()) {
            //Gdx.app.log("GameWorld", "P1 old position: (" + p1.getPosition().x + ", " + p1.getPosition().y + ")");
            //Gdx.app.log("GameWorld", "P1 velocity: (" + p1.getVelocity().x + ", " + p1.getVelocity().y + ")");
            
            float a = 2.08f;
            float newPosChangeX = 0;
            if(p1.getVelocity().x > 0) {
                newPosChangeX = a*-1f;
            } else if(p1.getVelocity().x < 0) {
                newPosChangeX = a*1f;
            }
            
            float newPosChangeY = 0;
            if(p1.getVelocity().y > 0) {
                newPosChangeY = a*-1f;
            } else if(p1.getVelocity().y < 0) {
                newPosChangeY = a*1f;
            }
            
            p1.setFullPosition(p1.getPosition().add(newPosChangeX, newPosChangeY)); 
            
            float b = 2.08f;
            float newPosChangeX2 = 0;
            if(p2.getVelocity().x > 0) {
                newPosChangeX2 = b*-1f;
            } else if(p2.getVelocity().x < 0) {
                newPosChangeX2 = b*1f;
            }
            
            float newPosChangeY2 = 0;
            if(p2.getVelocity().y > 0) {
                newPosChangeY2 = b*-1f;
            } else if(p2.getVelocity().y < 0) {
                newPosChangeY2 = b*1f;
            }
            
            p2.setFullPosition(p2.getPosition().add(newPosChangeX2, newPosChangeY2));
            
            // lookee here!
            // looker here! 2
            
            //Gdx.app.log("GameWorld", "P1 new position: (" + p1.getPosition().x + ", " + p1.getPosition().y + ")");
            
            /*float collisionPointX =  ((p1.getPosition().x * p2.getRadius()) + (p2.getPosition().x * p1.getRadius()))
                    / (p1.getRadius() + p2.getRadius());

            float collisionPointY =  ((p1.getPosition().y * p2.getRadius()) + (p2.getPosition().y * p1.getRadius()))
                    / (p1.getRadius() + p2.getRadius());*/
            
            /*float newVelX1 = (p1.getVelocity().x * (p1.getMass() - p2.getMass())
                    + (2 * p2.getMass() * p2.getVelocity().x)) / (p1.getMass() + p2.getMass());
            float newVelY1 = (p1.getVelocity().y * (p1.getMass() - p2.getMass())
                    + (2 * p2.getMass() * p2.getVelocity().y)) / (p1.getMass() + p2.getMass());
            float newVelX2 = (p2.getVelocity().x * (p2.getMass() - p1.getMass()) +
                    (2 * p1.getMass() * p1.getVelocity().x)) / (p1.getMass() + p2.getMass());
            float newVelY2 = (p2.getVelocity().y * (p2.getMass() - p1.getMass()) +
                    (2 * p1.getMass() * p1.getVelocity().y)) / (p1.getMass() + p2.getMass());

            p1.setVelocity(newVelX1, newVelY1);
            p2.setVelocity(newVelX2, newVelY2);*/
        }
    }
    
    /**
     * Checks if the two Avatar's have body collision
     * @return true if the Avatars' bodies collide
     */
    public boolean avatarBodyCollision() {
        return (Intersector.overlaps(p1.getBoundingCircle(), p2.getBoundingCircle()));
    }
    // ================================ Getters ================================
    /**
     * Retrieves the first player's Avatar
     * @return the first player's Avatar
     */
    public Avatar getP1() {
        return p1;
    }
    /**
     * Retrieves the second player's Avatar
     * @return the second player's Avatar
     */
    public Avatar getP2() {
        return p2;
    }
    // ================================ Setters ================================
    // =========================================================================
} // end GameWorld class
