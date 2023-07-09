package com.prototype.game.Tanks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.prototype.game.Screens.PlayScreen;
import com.prototype.game.TenkStars;

interface TankInterface{
    void setPos(float x,float y);

    float getPosX();

    float getPosY();

    void update(float dt);

    //defining tank body using BodyDef,FixtureDef,Shape
    void defineTanks();
}
