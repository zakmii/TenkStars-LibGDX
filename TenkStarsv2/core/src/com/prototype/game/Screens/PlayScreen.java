package com.prototype.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.prototype.game.Scenes.HUD;
import com.prototype.game.Tanks.Toxic;
import com.prototype.game.TenkStars;
import com.prototype.game.Tools.B2WorldCreator;

public class PlayScreen implements Screen {
    private TenkStars game;
    private TextureAtlas atlas;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private HUD hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    Toxic player;
    //private ArrayList<Object> player;
    private float torque;
    public PlayScreen(TenkStars game){
        this.torque=0.1f;

        atlas=new TextureAtlas("Tanks_weapons_boxes.pack");

        this.game=game;

        gameCam=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //FitViewPort for virtual aspect ratio despite screen size
        gamePort=new FitViewport(TenkStars.V_WIDTH/TenkStars.PPM,TenkStars.V_HEIGHT/TenkStars.PPM,gameCam);
        hud=new HUD(game.batch);
        mapLoader=new TmxMapLoader();
        map=mapLoader.load("level2.tmx");
        renderer= new OrthogonalTiledMapRenderer(map,1/TenkStars.PPM);

        gameCam.position.set(gamePort.getWorldWidth()+120,gamePort.getWorldHeight(),0);

        world=new World(new Vector2(0,-300),true); //1st parameter is gravity
        b2dr=new Box2DDebugRenderer();

        new B2WorldCreator(world,map);

        player=new Toxic(world,this);
//        player.add(new Toxic(world,this));
//        player.add(new Abrams(world,this));
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        //for pause screen
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new GamePauseScreen(game));
        }

        //for game over screen
        if(Gdx.input.isKeyPressed(Input.Keys.TAB)){
            //game.setScreen(new GamePauseScreen(game));
            game.setScreen(new GameOver(game));
        }
        //for player
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x<=200){
            player.b2body.setLinearVelocity(new Vector2(60f,0));

            //torque-=1000f;
            //player.b2body.applyLinearImpulse(new Vector2(300f/TenkStars.PPM,0),player.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x>=-200){
            player.b2body.setLinearVelocity(new Vector2(-60f,0));
            //player.b2body.applyLinearImpulse(new Vector2(-0300f/TenkStars.PPM,0),player.b2body.getWorldCenter(),true);
        }

        //for map navigation
        /*if(Gdx.input.isKeyPressed(Input.Keys.D)){
            gameCam.position.x+=100*dt;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            gameCam.position.x-=100*dt;
        }
         */
    }
    public void update(float dt){
        handleInput(dt);


        world.step(1/60f,6,2);

        player.update(dt);

        //attach our camera to players x position
        //gameCam.position.x=player.b2body.getPosition().x;

        gameCam.update();

        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our map
        renderer.render();

        b2dr.render(world,gameCam.combined);

        player.b2body.setLinearVelocity(0,player.b2body.getLinearVelocity().y);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        //set our batch to now draw what our game camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        hud.dispose();
        b2dr.dispose();
    }
}
