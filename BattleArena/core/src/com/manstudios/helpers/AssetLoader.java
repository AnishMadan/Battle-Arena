/*
 * Mitchell Van Braeckel
 * 03/01/2017
 * Final Project ICS4U -- Battle Arena -- 
-*- AssetLoader     --> helper class for loading images, sounds, animations, etc
 */
package com.manstudios.helpers;

// imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
/**
 *
 * @author Mitchell Van Braeckel
 */
public class AssetLoader {
        // ATTRIBUTES
    //public static Texture texture;
    public static Sound flap, coin, dead, hitmarker, song;
    public static BitmapFont font, shadow;
    
    /*public static TextureRegion bg, grass;

    public static Animation birdAnimation;
    public static TextureRegion bird, birdDown, birdUp;

    public static TextureRegion skullUp, skullDown, bar;*/
    
        // CONSTRUCTORS (no constructors, only one copy is created)
        // BEHAVIOURS
    /**
     * Called when the game starts up(via BAGame) to load all assets.
     */
    public static void load() {

        //texture = new Texture(Gdx.files.internal("texture.png"));
        //each pixel will retain its shape rather than becoming blurry when art is stretched
        //texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.getData().setScale(0.25f, -0.25f);
        shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
        shadow.getData().setScale(0.25f, -0.25f);
        
        /*/ Since libGDX uses y-up coordinate system, we must flip all images because we are using a y-down system
        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);
        
        //create Bird Animation w/ 3 frames that changes frames every 0.06 seconds (down, middle, up, middle, down, ...etc)
        TextureRegion[] birds = {birdDown, bird, birdUp};           //creates an array of TextureRegions
        birdAnimation = new Animation(0.06f, (Object[])birds);                //creates a new Animation in which each frame is 0.06 seconds long, using the above array
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);//sets play mode to be ping pong, in which we will see a bounce

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        skullDown = new TextureRegion(skullUp); // Create by flipping existing skullUp
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);*/
        
        dead = Gdx.audio.newSound(Gdx.files.internal("dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("coin.wav"));
        hitmarker = Gdx.audio.newSound(Gdx.files.internal("Hitmarker Sound.wav"));
        song = Gdx.audio.newSound(Gdx.files.internal("narutomusic.wav"));
    }
    /**
     * Called when the game is being closed (to dispose of all loaded assets).
     */
    public static void dispose() {
        //texture.dispose();  // We must dispose of the texture when we are finished.
        dead.dispose();
        flap.dispose();
        coin.dispose();
        hitmarker.dispose();
        song.dispose();
        
        font.dispose();
        shadow.dispose();
    }
} // end AssetLoader class
