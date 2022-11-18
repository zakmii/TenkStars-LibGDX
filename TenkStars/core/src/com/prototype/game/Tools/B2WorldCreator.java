package com.prototype.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.prototype.game.TenkStars;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map) {

        //create body and fixture variables
        BodyDef bdef=new BodyDef();
        //PolygonShape shape=new PolygonShape();

        FixtureDef fdef=new FixtureDef();
        Body body;
        for(MapObject object:map.getLayers().get(2).getObjects()){
            Shape shape;
            if(object instanceof PolygonMapObject){
                shape=createPolyLine((PolygonMapObject) object);
            }
            else{
                System.out.println("NOT POLYGON");
                continue;
            }
            //Rectangle rect =((RectangleMapObject) object).getRectangle();

            bdef.type=BodyDef.BodyType.StaticBody;
            //bdef.position.set((rect.getX()+rect.getWidth()/2),(rect.getY()+rect.getHeight()/2));

            body= world.createBody(bdef);

            //shape.setAsBox(rect.getWidth()/2/ TenkStars.PPM,rect.getHeight()/2/TenkStars.PPM);
            fdef.shape=shape;
            body.createFixture(fdef);
        }
    }

    private static ChainShape createPolyLine(PolygonMapObject polyline) {
        float[] vertices = polyline.getPolygon().getTransformedVertices();
        Vector2[] worldVertices=new Vector2[vertices.length/2];

        for(int i=0;i< worldVertices.length;i++){
            worldVertices[i]=new Vector2(vertices[i*2]/TenkStars.PPM,vertices[i*2+1]/TenkStars.PPM);
        }
        ChainShape cs=new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }
}
