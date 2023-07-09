package com.prototype.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen {
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton buttonPlay,buttonExit;
    private BitmapFont white,black;
    private Label heading;

    public MainMenu() {
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas=new TextureAtlas("buttons.pack");
        skin=new Skin(atlas);

        table=new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        white=new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        black=new BitmapFont(Gdx.files.internal("font/black.fnt"),false);

        textButtonStyle=new TextButton.TextButtonStyle();
        textButtonStyle.up=skin.getDrawable("buttonUp");
        textButtonStyle.down=skin.getDrawable("buttonDown");
        textButtonStyle.pressedOffsetX=1;
        textButtonStyle.pressedOffsetY=-1;
        textButtonStyle.font=black;

        buttonExit=new TextButton("EXIT",textButtonStyle);
        buttonExit.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                Gdx.app.exit();
            }
        });
        //buttonPlay=new TextButton("PLAY",skin);

        buttonExit.pad(20);

        table.add(buttonExit);
        table.debug();
        stage.addActor(table);
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

    }
}
