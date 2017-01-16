/*
 * Mitchell Van Braeckel
 * 03/01/2017
 * Final Project ICS4U -- Battle Arena -- 
-*- GameRenderer    --> RENDERING helper class for GameScreen
 */
package com.manstudios.gameworld;

// imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.manstudios.gameobjects.Avatar;
import com.manstudios.gameobjects.Ranged;
import com.manstudios.helpers.AssetLoader;
import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 *
 * @author Mitchell Van Braeckel
 */
public class GameRenderer {
        // DECLARE FORMATS
    DecimalFormat pf = new DecimalFormat("##0.00%");
    
        // ATTRIBUTES
    private GameWorld myWorld;  //GameRenderer needs to have a reference to the GameWorld that it will be drawing,
                                //whenever we want to refer to an object inside our GameWorld, we can retrieve it
    private OrthographicCamera cam;         //camera that views 3D as 2D
    
    private ShapeRenderer shapeRenderer;    //draws shapes and lines for us
    
    private SpriteBatch batcher;            //draws images for us using the indices provided (x, y, width and height, typically)
    private int gameWidth, gameHeight;
    
        // CONSTRUCTORS
    /**
     * Primary Constructor - instantiates a new GameRenderer (to render the game) as a helper to the GameScreen
     * @param world the GameWorld that will be rendered
     * @param gameWidth the width of the game when drawn
     * @param gameHeight the height of the game when drawn
     */
    public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
        myWorld = world; 
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        
        cam = new OrthographicCamera();                 //init cam
        cam.setToOrtho(true, gameWidth/2, gameHeight/2);//true = we want an orthographic projection || width || height
                            //(true, 600, 400);         //since GameScreen res = 272,408 ; game scaled x2 when drawn
        batcher = new SpriteBatch();                    //init batcher
        batcher.setProjectionMatrix(cam.combined);      //attach it to cam
        
        shapeRenderer = new ShapeRenderer();            //init shapeRenderer
        shapeRenderer.setProjectionMatrix(cam.combined);//attach it to cam
    }
        // BEHAVIOURS
    /**
     * The looping method that renders the game
     * @param runTime uses this value (and the previously determined frame duration) to determine which TextureRegion to display
     */
    public void render(float runTime) {
        // We will move these outside of the loop for performance later************************
        Avatar p1 = myWorld.getP1();
        Avatar p2 = myWorld.getP2();
         
        // Fill the entire screen with black, to prevent potential flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
            // Begin ShapeRenderer
        shapeRenderer.begin(ShapeType.Filled);
        
        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 600, 400);
        
        shapeRenderer.end();    // End ShapeRenderer
        
            // Begin SpriteBatch
        batcher.begin();
        
        AssetLoader.shadow.draw(batcher, "Battle Arena", 250, 5);
        AssetLoader.font.draw(batcher, "Battle Arena", 250, 5);
        AssetLoader.shadow.draw(batcher, "P1 HEALTH: " + p1.getHealth(), 20, 350);
        AssetLoader.font.draw(batcher, "P1 HEALTH: " + p1.getHealth(), 20, 350);
        AssetLoader.shadow.draw(batcher, "P2 HEALTH: " + p2.getHealth(), 410, 350);
        AssetLoader.font.draw(batcher, "P2 HEALTH: " + p2.getHealth(), 410, 350);
        
        AssetLoader.shadow.draw(batcher, "P1 Accuracy: " + pf.format(p1.getHitCount() / p1.getShotCount()), 20, 300);
        AssetLoader.font.draw(batcher, "P1 Accuracy: " + pf.format(p1.getHitCount() / p1.getShotCount()), 20, 300);
        AssetLoader.shadow.draw(batcher, "P2 Accuracy: " + pf.format(p2.getHitCount() / p2.getShotCount()), 410, 300);
        AssetLoader.font.draw(batcher, "P2 Accuracy: " + pf.format(p2.getHitCount() / p2.getShotCount()), 410, 300);
        /*float m = 4.4118f;
        batcher.disableBlending();  //Disable transparency (good for performance when drawing images that do not require transparency)
        batcher.draw(AssetLoader.bg, 0, actualGameHeight-43*m, 136*m, 43*m);
        batcher.enableBlending();   //Enable transparency (bird needs it)*/
        
        // Draw bird at its coordinates. Retrieve the Animation object from AssetLoader
        // Pass in the runTime variable to get the current frame.
        //batcher.draw((TextureRegion)AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getRadius(), bird.getHeight());
        
        batcher.end();  // End SpriteBatch
        
        Color p1Colour = p1.getColour();
        Color p2Colour = p2.getColour();
        
        shapeRenderer.begin(ShapeType.Filled);
            // draw player #1
        shapeRenderer.setColor(p1Colour);
        shapeRenderer.circle(p1.getX(), p1.getY(), p1.getRadius()*1f);
            // draw player #2
        shapeRenderer.setColor(p2Colour);
        shapeRenderer.circle(p2.getX(), p2.getY(), p2.getRadius()*1f);
        
            //draw projectiles
        ArrayList<Ranged> p1RangedAttacks = p1.getRangedAttackList();
        ArrayList<Ranged> p2RangedAttacks = p2.getRangedAttackList();
        
        for (int i = 0; i < p1RangedAttacks.size(); i++) {
            Ranged p1Attack = p1RangedAttacks.get(i);
            shapeRenderer.setColor(p1Colour);
            shapeRenderer.circle(p1Attack.getX(), p1Attack.getY(), p1Attack.getRadius());
        }
        for (int i = 0; i < p2RangedAttacks.size(); i++) {
            Ranged p2Attack = p2RangedAttacks.get(i);
            shapeRenderer.setColor(p2Colour);
            shapeRenderer.circle(p2Attack.getX(), p2Attack.getY(), p2Attack.getRadius());
        }
        
        
        shapeRenderer.end();    // End ShapeRenderer
            /*/ 2. We draw the Filled rectangle
        shapeRenderer.begin(ShapeType.Filled);                          // Tells shapeRenderer to begin drawing filled shapes
        shapeRenderer.setColor(87/255.0f, 109/255.0f, 120/255.0f, 1);   // Chooses RGB Color of 87, 109, 120 at full opacity
        shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y,    // Draws the rectangle from myWorld (Using ShapeType.Filled)
                myWorld.getRect().width, myWorld.getRect().height);
        shapeRenderer.end();    // Tells the shapeRenderer to finish rendering  // We MUST do this every time

            // 3. We draw the rectangle's outline
        shapeRenderer.begin(ShapeType.Line);                            // Tells shapeRenderer to draw an outline of the following shapes
        shapeRenderer.setColor(255/255.0f, 109/255.0f, 120/255.0f, 1);  // Chooses RGB Color of 255, 109, 120 at full opacity
        shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y,    // Draws the rectangle from myWorld (Using ShapeType.Line)
                myWorld.getRect().width, myWorld.getRect().height);
        shapeRenderer.end();    // Tells the shapeRenderer to finish rendering  // We MUST do this every time*/
    }
} // end GameRenderer class
