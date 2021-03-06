/*
 * MAN Studios
 * 03/01/2017
 * Final Project ICS4U -- Battle Arena -- 
 -*- InputHandler   --> helper class for reacting to input
 */
package com.manstudios.helpers;

// imports
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.manstudios.gameobjects.Avatar;
/**
 *
 * @author MAN Studios
 */
public class InputHandler implements InputProcessor{
        // ATTRIBUTES
    private Avatar p1, p2; //player #1, player #2
    
        // CONSTRUCTORS
    /**
     * Primary Constructor - instantiates a new InputHandler that reacts to various inputs and handles them appropriately
        (asks for a reference to the Avatars from GameWorld when InputHandler is created)
     * @param p1 the first player's Avatar that will be changed based on input
     * @param p2 the second player's Avatar that will be changed based on input
     */
    public InputHandler(Avatar p1, Avatar p2) {
        this.p1 = p1;  //p1 now represents the GameWorld's p1 (player #1)
        this.p2 = p2;  //p2 now represents the GameWorld's p2 (player #2)
    }
        // BEHAVIOURS
    /**
     * Called when a key is pressed down
     * @param keycode the reported keycode of the key that is being pressed down
     * @return true to say the key being pressed down was handled
     */
     @Override
    public boolean keyDown(int keycode) {
        // ============================= Player 1 ==============================
        // checks for arrow key presses and moves player #1 accordingly
        if(keycode == Input.Keys.W) {
            p1.moveUpOn();
        }
        if(keycode == Input.Keys.S) {
            p1.moveDownOn();
        }
        if(keycode == Input.Keys.A) {
            p1.moveLeftOn();
        }
        if(keycode == Input.Keys.D) {
            p1.moveRightOn();
        }
        
        // checks for ranged attacks and shoots a projectile accordingly
        if(keycode == Input.Keys.F) {   // shoot up
            p1.doRangedAttack(p1, 0, -1);
        }
        if(keycode == Input.Keys.V) {   // shoot down
            p1.doRangedAttack(p1, 0, 1);
        }
        if(keycode == Input.Keys.C) {   // shoot left
            p1.doRangedAttack(p1, -1, 0);
        }
        if(keycode == Input.Keys.B) {   // shoot right
            p1.doRangedAttack(p1, 1, 0);
        }
        
        // ============================= Player 2 ==============================
        // checks for WASD key presses and moves player #2 accordingly
        if(keycode == Input.Keys.UP) {
            p2.moveUpOn();
        }
        if(keycode == Input.Keys.DOWN) {
            p2.moveDownOn();
        }
        if(keycode == Input.Keys.LEFT) {
            p2.moveLeftOn();
        }
        if(keycode == Input.Keys.RIGHT) {
            p2.moveRightOn();
        }
        
        // checks for ranged attacks and shoots a projectile accordingly
        if(keycode == Input.Keys.L) {   // shoot up
            p2.doRangedAttack(p1, 0, -1);
        }
        if(keycode == Input.Keys.PERIOD) {   // shoot down
            p2.doRangedAttack(p1, 0, 1);
        }
        if(keycode == Input.Keys.COMMA) {   // shoot left
            p2.doRangedAttack(p1, -1, 0);
        }
        if(keycode == Input.Keys.SLASH) {   // shoot right
            p2.doRangedAttack(p1, 1, 0);
        }
        
        // =====================================================================
        return true;    //return true to say we handled the touch
    }
    /**
     * Called when a key is lifted
     * @param keycode the reported keycode of the key that is lifted
     * @return true to say the key that was lifted was handled
     */
     @Override
    public boolean keyUp(int keycode) {
        // ============================= Player 1 ==============================
        // checks for arrow key releases and stops moving player #1 accordingly
        if(keycode == Input.Keys.W) {
            p1.moveUpOff();
        }
        if(keycode == Input.Keys.S) {
            p1.moveDownOff();
        }
        if(keycode == Input.Keys.A) {
            p1.moveLeftOff();
        }
        if(keycode == Input.Keys.D) {
            p1.moveRightOff();
        }
        
        // ============================= Player 2 ==============================
        // checks for WASD key releases and stops moving player #2 accordingly
        if(keycode == Input.Keys.UP) {
            p2.moveUpOff();
        }
        if(keycode == Input.Keys.DOWN) {
            p2.moveDownOff();
        }
        if(keycode == Input.Keys.LEFT) {
            p2.moveLeftOff();
        }
        if(keycode == Input.Keys.RIGHT) {
            p2.moveRightOff();
        }
        // =====================================================================
        return true;    //return true to say we handled the touch
    }
    /**
     * Called when a Unicode character is generated by the keyboard input
     * @param character the reported character that is generated
     * @return false because we don't handle this input type
     */
     @Override
    public boolean keyTyped(char character) {
        return false;
    }
    /**
     * Called when a mouse button is pressed on the screen
     * @param screenX the x-coordinate of the screen when the mouse button was pressed
     * @param screenY the y-coordinate of the screen when the mouse button was pressed
     * @param pointer the pointer index when the mouse button was pressed
     * @param button the button that was used to press on the screen
     * @return false because we don't handle this input type
     */
     @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    /**
     * Called when a mouse button is released
     * @param screenX the last known x-coordinate of the screen before the mouse button was released
     * @param screenY the last known y-coordinate of the screen before the mouse button was released
     * @param pointer the pointer index when the mouse button was pressed
     * @param button the button that was used to press on the screen
     * @return false because we don't handle this input type
     */
     @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    /**
     * Called when a mouse is dragged while a button is pressed
     * @param screenX the x-coordinate of the screen while the mouse button is pressed
     * @param screenY the y-coordinate of the screen while the mouse button is pressed
     * @param pointer the pointer index when the mouse button was pressed
     * @return false because we don't handle this input type
     */
     @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    /**
     * Called when a mouse is moved over the screen without a mouse button being down
     * @param screenX the x-coordinate of the screen while the mouse button is not down
     * @param screenY the y-coordinate of the screen while the mouse button is not down
     * @return false because we don't handle this input type
     */
     @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    /**
     * Called when the scroll wheel of a mouse is turned
     * @param amount Reports either -1 or 1 depending on the direction of spin
     * @return false because we don't handle this input type
     */
     @Override
    public boolean scrolled(int amount) {
        return false;
    }
} // end InputHandler class
