/*
 * Mitchell Van Braeckel
 * 03/01/2017
 * Final Project ICS4U -- Battle Arena -- 
-*- GameScreen --> the screen of the actual gameplay
    • has 2 helper classes:
            -> GameWorld    --> updating helper class
            -> GameRenderer --> rendering helper class
 */
package com.manstudios.screens;

// imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.manstudios.helpers.InputHandler;
import com.manstudios.gameworld.GameRenderer;
import com.manstudios.gameworld.GameWorld;
/**
 *
 * @author Mitchell Van Braeckel
 */
public class GameScreen implements Screen {
        // ATTRIBUTES
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;      //keeps track of how long the game has been running for
    
        // CONSTRUCTORS
    /**
     * Default Constructor - instantiates a new GameScreen w/ a GameWorld (to help updating) and a GameRenderer (to help render).
     */
    public GameScreen() {
        // calc screen dimensions
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 1200;
        float gameHeight = 800;
        
        //Gdx.app.log("GameScreen", "Attached");
        world = new GameWorld((int)gameWidth, (int)gameHeight);                                 //init world
        renderer = new GameRenderer(world, (int)gameWidth, (int)gameHeight);    //init renderer
        
        // tells libGDX to take our new InputHandler as its processor
        Gdx.input.setInputProcessor(new InputHandler(world.getP1(), world.getP2()));   //player #1, player #2
    }
        // BEHAVIOURS
    /**
     * Game Looping Render method: updates all game objects(via GameWorld), and renders them(via GameRenderer)
     * @param delta the number of seconds (usually a small fraction) that has passed since the last time that the render method was called
     */
     @Override
    public void render(float delta) {
        runTime += delta;           //used to render the animation properly
        // We are passing in delta to our update method so that we can perform frame-rate independent movement. 
        world.update(delta);        //GameWorld updates 
        renderer.render(runTime);   //GameRenderer renders
        
        /*// Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
        Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
        // Fills the screen with the selected color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Convert Frame rate to String, print it
        //(1/delta) = how many times that the render method would be called in one second if that rate was sustained
        Gdx.app.log("GameScreen FPS", (1/delta) + "");*/
    }
    /**
     * Called when it's resized  (can happen at any point during a non-paused state, but will never happen before create() call).
     * @param width the new width in pixels
     * @param height the new height in pixels
     */
     @Override
    public void resize(int width, int height) {
        //Gdx.app.log("GameScreen", "resizing");
    }
    /**
     * Called when this screen becomes the current screen for a Game.
     */
     @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }
    /**
     * Called when this screen is no longer the current screen for a Game.
     */
     @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }
    /**
     * Called when it's paused (usually when it's not active or visible on screen) (also paused before it's destroyed).
     */
     @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }
    /**
     * Called when it's resumed from a paused state (usually when it regains focus).
     */
     @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }
    /**
     * Called when this screen should release all resources.
     */
     @Override
    public void dispose() {
        // Leave blank
    }
} // end GameScreen class