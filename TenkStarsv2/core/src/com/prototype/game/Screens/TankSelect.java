package com.prototype.game.Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.prototype.game.TenkStars;

public class TankSelect implements Screen {

    TenkStars game;
    private Stage stage;
    private Table table;
    private Skin skin;
    private List  list;
    private TextureAtlas atlas;

    public TankSelect(TenkStars game){
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
        table.setFillParent(true);

        //list = new List<String>(skin,"small");
        //list.setItems(new String[] {"Abrams", "Toxic","Spectre"});
        //ScrollPane scrollPane = new ScrollPane(list, skin);

        TextButton t1 = new TextButton("Abrams", skin);
        t1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });
        t1.pad(10);

        TextButton t2 = new TextButton("Toxic",skin);
        t2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
            }
        });
        t2.pad(10);

        TextButton t3 = new TextButton("Spectre",skin);
        t3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
            }
        });
        t3.pad(10);

        TextButton back = new TextButton("BACK",skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
            }
        });
        back.pad(5);
        //table.debug();
        table.add(new Label("SELECT TANK", skin, "big")).colspan(3).expandX().spaceBottom(50).row();
        table.add(t1).uniformX();
        table.add(t2).uniformX();
        table.add(t3).uniformX();
        //table.add(back).right().bottom();

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
