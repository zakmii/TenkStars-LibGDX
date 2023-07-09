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
import com.badlogic.gdx.utils.Disposable;
import com.prototype.game.TenkStars;
import com.prototype.game.tween.ActorAccessor;

public class GameOver implements Screen, Disposable {
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton buttonResume, buttonExitGame, buttonMainMenu;
    private BitmapFont white,black;
    private Label heading;
    private TweenManager tweenManager;
    TenkStars game;
    public GameOver(final TenkStars game) {
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

        heading=new Label("GAME OVER",headingStyle);
        heading.setFontScale(5);

        buttonExitGame =new TextButton("MAIN MENU",textButtonStyle);
        buttonExitGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
            }
        });

        buttonResume =new TextButton("REMATCH",textButtonStyle);
        buttonResume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){

            }
        });

        buttonExitGame.pad(10);
        buttonResume.pad(10);

        table.add(heading);
        table.getCell(heading).spaceBottom(100);
        table.row();
        table.add(buttonResume);
        table.getCell(buttonResume).spaceBottom(15);
        table.row();
        table.add(buttonExitGame);
        //table.debug();   //shows buttons outline for better allignment
        stage.addActor(table);

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
                .push(Tween.set(buttonResume, ActorAccessor.ALPHA).target(0))
                .push(Tween.set(buttonExitGame, ActorAccessor.ALPHA).target(0))
                .push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
                .push(Tween.to(buttonResume, ActorAccessor.ALPHA, .25f).target(1))
                .push(Tween.to(buttonExitGame, ActorAccessor.ALPHA, .25f).target(1))
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
        Gdx.gl.glClearColor(0,0,0,1);
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
