package com.prototype.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.prototype.game.TenkStars;


public class HUD implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer player2HP;
    private float timeCount;
    private Integer player1HP;

    Label player2HPLabel;
    Label player1HPLabel;
    Label player2NameLabel;
    Label levelLabel;
    Label worldLabel;
    Label player1NameLabel;

    public HUD(SpriteBatch sb){
        player2HP =100;
        timeCount=0;
        player1HP =100;
        viewport=new FitViewport(TenkStars.V_WIDTH,TenkStars.V_HEIGHT,new OrthographicCamera());
        stage =new Stage(viewport,sb);

        Table table=new Table();
        table.top();
        table.setFillParent(true);

        player2HPLabel =new Label(String.format("%03d", player2HP), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        player1HPLabel =new Label(String.format("%03d", player1HP), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        player2NameLabel =new Label("Player 2", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        //levelLabel=new Label("1-1", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        //worldLabel=new Label("WORLD", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        player1NameLabel =new Label("Player 1", new Label.LabelStyle(new BitmapFont(),Color.WHITE));

        table.add(player1NameLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(player2NameLabel).expandX().padTop(10);
        table.row();
        table.add(player1HPLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(player2HPLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
