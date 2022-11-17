package com.prototype.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.prototype.game.Screens.PlayScreen;
import com.prototype.game.TenkStars;

public class Tanks extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion tankStand;
    public Tanks(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("Abrams"));
        this.world=world;
        defineTanks();
        tankStand=new TextureRegion(getTexture(),0,0,16,16);
        setBounds(0,0,16/TenkStars.PPM,16/TenkStars.PPM);
        setRegion(tankStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
    }
    public void defineTanks(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(32/ TenkStars.PPM,550/TenkStars.PPM);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body= world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        CircleShape shape =new CircleShape();
        shape.setRadius(5/TenkStars.PPM);

        fdef.shape=shape;
        b2body.createFixture(fdef);
    }
}
