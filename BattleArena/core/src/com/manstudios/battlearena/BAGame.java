/*
 * Mitchell Van Braeckel
 * 03/01/2017
 * Final Project ICS4U -- Battle Arena --
    -> sets up the main game and ends it
 */
package com.manstudios.battlearena;

// imports
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.manstudios.helpers.AssetLoader;
import com.manstudios.screens.GameScreen;
/**
 * 
 * @author Mitchell Van Braeckel
 */
public class BAGame extends Game {
    /**
     * Called when it's first created (loads all assets and creates the startup screen).
     */
     @Override
    public void create() {
        Gdx.app.log("BAGame", "created");
        AssetLoader.load();             //loads assets
        setScreen(new GameScreen());
    }
    /**
     * Called when it's destroyed (disposes of all loaded assets).
     */
     @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
} // end BAGame
