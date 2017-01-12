/*
 * Mitchell Van Braeckel
 * 03/01/2017
 * Final Project ICS4U -- Battle Arena --
 */
package com.manstudios.battlearena.desktop;

// imports
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.manstudios.battlearena.BAGame;

/**
 * Launches a desktop version of the game
 * @author Mitchell Van Braeckel
 */
public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        // set title and size
        config.title = "Battle Arena";
        config.width = 1200;
        config.height = 800;
        
        new LwjglApplication(new BAGame(), config);
    } // end main
} // end DesktopLauncher
