package com.prototype.game.Tanks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.prototype.game.Screens.PlayScreen;
import com.prototype.game.TenkStars;

public class Toxic extends Sprite implements TankInterface {
    public World world;
    public Body b2body;
    private TextureRegion tankStand;
    private double HP;
    private double range;
    //52,550
    private float posX;
    private float posY;
    public Toxic(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("Toxic"));
        this.world=world;
        this.posX=52f;
        this.posY=550f;
        defineTanks();

        tankStand=new TextureRegion(getTexture(),0,64,128,72);
        setBounds(0,0,64/ TenkStars.PPM,72/TenkStars.PPM);
        setRegion(tankStand);
    }
    @Override
    public void setPos(float x, float y) {
        this.posX=x;
        this.posY=y;
    }

    @Override
    public float getPosX() {
        return this.b2body.getPosition().x;
    }

    @Override
    public float getPosY() {
        return this.b2body.getPosition().y;
    }

    @Override
    public void update(float dt) {
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
    }
    //defining tank body using BodyDef,FixtureDef,Shape
    @Override
    public void defineTanks() {
        BodyDef bdef=new BodyDef();
        bdef.position.set(posX/ TenkStars.PPM,posY/TenkStars.PPM);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body= world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        //fdef.friction=1f;
        //PolygonShape shape=new PolygonShape();
        //shape.setAsBox(5,5);
        CircleShape shape =new CircleShape();
        shape.setRadius(10/TenkStars.PPM);


        fdef.shape=shape;
        b2body.createFixture(fdef);
    }
}

