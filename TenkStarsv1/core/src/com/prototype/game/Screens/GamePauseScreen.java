package com.prototype.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.prototype.game.TenkStars;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GamePauseScreen implements Screen, Disposable {
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public GamePauseScreen(Game game){
        this.game=game;
        viewport=new FitViewport(TenkStars.V_WIDTH/TenkStars.PPM,TenkStars.V_HEIGHT/TenkStars.PPM,new OrthographicCamera());
        stage=new Stage(viewport,((TenkStars) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table=new Table();
        table.center();
        table.setFillParent(true);

        Label gamePauseLabel=new Label("PAUSED",font);
        table.add(gamePauseLabel).expandX();

        stage.addActor(table);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            dispose();
        }
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
    }
}
