package com.prototype.game.Screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.prototype.game.TenkStars;
import com.prototype.game.tween.ActorAccessor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MainMenu implements Screen {
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton buttonPlay,buttonExit,buttonSettings;
    private BitmapFont white,black;
    private Label heading;
    private TweenManager tweenManager;
    TenkStars game;
    public MainMenu(final TenkStars game) {
        this.game=game;

        stage=new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas=new TextureAtlas("buttons.pack");
        skin=new Skin(atlas);

        table=new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        white=new BitmapFont(Gdx.files.internal("font/white32.fnt"),false);
        black=new BitmapFont(Gdx.files.internal("font/black32.fnt"),false);

        textButtonStyle=new TextButton.TextButtonStyle();
        textButtonStyle.up=skin.getDrawable("buttonUp");
        textButtonStyle.down=skin.getDrawable("buttonDown");
        textButtonStyle.pressedOffsetX=1;
        textButtonStyle.pressedOffsetY=-1;
        textButtonStyle.font=black;

        Label.LabelStyle headingStyle=new Label.LabelStyle(white, Color.WHITE);

        heading=new Label("TENKSTARS",headingStyle);
        heading.setFontScale(5);

        buttonExit=new TextButton("EXIT",textButtonStyle);
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                Gdx.app.exit();
            }
        });

        buttonSettings = new TextButton("NEW GAME", textButtonStyle);
        buttonSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new TankSelect(game));
            }
        });

        buttonPlay=new TextButton("CONTINUE",textButtonStyle);
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LoadGame(game));
            }
        });

        buttonSettings.pad(10);
        buttonExit.pad(10);
        buttonPlay.pad(10);

        table.add(heading);
        table.getCell(heading).spaceBottom(100);
        table.row();
        table.add(buttonPlay);
        table.getCell(buttonPlay).spaceBottom(15);
        table.row();
        table.add(buttonSettings);
        table.getCell(buttonSettings).spaceBottom(15);
        table.row();
        table.add(buttonExit);
        //table.debug();   //shows buttons outline for better allignment
        stage.addActor(table);

        //+stage.addAction(sequence(moveTo(0, stage.getHeight()), moveTo(0, 0, .5f))); // coming in from top animation

        // creating animations
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAccessor());

        // heading color animation
        Timeline.createSequence().beginSequence()
                .push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 0, 1))
                .push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 1))
                .push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 1))
                .push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 1))
                .end().repeat(Tween.INFINITY, 0).start(tweenManager);

        // heading and buttons fade-in
        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
                .push(Tween.set(buttonSettings, ActorAccessor.ALPHA).target(0))
                .push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
                .push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
                .push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .25f).target(1))
                .push(Tween.to(buttonSettings, ActorAccessor.ALPHA, .25f).target(1))
                .push(Tween.to(buttonExit, ActorAccessor.ALPHA, .25f).target(1))
                .end().start(tweenManager);

        // table fade-in
        Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
        Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

        tweenManager.update(Gdx.graphics.getDeltaTime());
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f,.1f,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        tweenManager.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        table.invalidateHierarchy();
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
        stage.dispose();
        skin.dispose();
    }
}
