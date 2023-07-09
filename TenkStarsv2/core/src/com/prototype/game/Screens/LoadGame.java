package com.prototype.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.prototype.game.TenkStars;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class LoadGame implements Screen {
    TenkStars game;
    private Stage stage;
    private Table table;
    private Skin skin;
    private List list;
    private TextureAtlas atlas;
    ScrollPane scrollPane;

    public LoadGame(TenkStars game){
        this.game=game;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //stage.setViewport(width, height, false);
        table.invalidateHierarchy();
    }

    @Override
    public void show() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);
        atlas=new TextureAtlas("buttons.pack");
        skin = new Skin(Gdx.files.internal("menuSkin.json"), atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        list = new List<String>(skin,"default");
        list.setItems(new String[] {"Save Game 1 adfsdafasdfsdafdsafsadfdasfsadfadsfsadfsadfasf ","Save Game 2 ","Save Game 3 ","Save Game 4 ","Save Game 5","Save Game 6 ","Save Game 7 ","Save Game 8 ","Save Game 9 ","Save Game 10 ","Save Game 11","Save Game 12 "});

        scrollPane = new ScrollPane(list, skin);

        TextButton t1 = new TextButton("Play", skin);
        t1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });
        t1.pad(15);

        TextButton t2 = new TextButton("Back",skin);
        t2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
            }
        });
        t2.pad(15);

        Label heading=new Label("LOAD SAVED GAME", skin, "big");
        //heading.setFontScale(1);
        //heading.setColor(1,0,0,1);

        table.add().width(table.getWidth()/3);
        table.add(heading).width(table.getWidth()/3);
        table.add().width(table.getWidth()/3).row();
        table.add(scrollPane).expandY().left();
        table.add(t1);
        table.add(t2).bottom().right();

        stage.addActor(table);

        stage.addAction(sequence(moveTo(0, stage.getHeight()), moveTo(0, 0, .5f))); // coming in from top animation
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
