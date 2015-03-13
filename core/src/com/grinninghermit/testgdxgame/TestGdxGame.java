package com.grinninghermit.testgdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;


public class TestGdxGame implements ApplicationListener {

    public class PlaneActor extends Actor {
        private TextureAtlas planeAtlas= new TextureAtlas(Gdx.files.internal("plane_test.txt"));
        private TextureRegion plane = planeAtlas.findRegion("0001");
        public boolean started = false;
        private int planeCurrentFrame = 1;
        private int planeMaxFrames = 30;
        private MoveToAction moveAction = new MoveToAction();

        public PlaneActor(){
            setBounds(getX(), getY(), plane.getRegionWidth(), plane.getRegionHeight());
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    if(((PlaneActor)event.getTarget()).started){
                        ((PlaneActor)event.getTarget()).started = false;
                    } else ((PlaneActor)event.getTarget()).started = true;
                    return true;
                }
            });
        }

        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(plane,this.getX(),getY(),this.getOriginX(),this.getOriginY(),this.getWidth(),
                    this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation());
        }

        @Override
        public void act(float delta){
            super.act(delta);
            if(!init){
                moveAction = new MoveToAction();
                float distX = destX - this.getX();
                float distY = destY - this.getY();
                float diffXY = ((distX + distY)/2)/250;
                if (diffXY < 0){
                    diffXY = -diffXY;
                }
                moveAction.setPosition(destX, destY);
                moveAction.setDuration(diffXY);
                this.addAction(moveAction);
               init = true;
            }

//            Gdx.app.log("TAG", String.valueOf(destX) + ",, " + String.valueOf(destY));

            if(started){
                planeCurrentFrame++;
                if(planeCurrentFrame > planeMaxFrames){
                    planeCurrentFrame = 1;
                }
                plane = planeAtlas.findRegion(String.format("%04d",planeCurrentFrame));
            } else {
                if (planeCurrentFrame != 1) {
                    if (planeCurrentFrame < 16) {
                        planeCurrentFrame--;
                        if (planeCurrentFrame < 1) {
                            planeCurrentFrame = 1;
                        }
                    } else {
                        planeCurrentFrame++;
                        if (planeCurrentFrame > planeMaxFrames) {
                            planeCurrentFrame = 1;
                        }
                    }
                    plane = planeAtlas.findRegion(String.format("%04d", planeCurrentFrame));
                }
            }
        }
    }



    private TouchStage stage;
    private PlaneActor planeActor;
    private boolean init = false;
    private float destX, destY, screenW, screenH;

    public class TouchStage extends Stage {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            super.touchDown(screenX, screenY, pointer, button);
            destX = screenX - planeActor.getWidth()/2;
            destY = screenH - screenY - planeActor.getHeight()/2;
            init = false;
            return false;
        }
    }

    @Override
    public void create () {
        stage = new TouchStage();
        Gdx.input.setInputProcessor(stage);

        screenW = Gdx.graphics.getWidth();
        screenH = Gdx.graphics.getHeight();

        planeActor = new PlaneActor();
        planeActor.setTouchable(Touchable.enabled);

        destX = screenW/2 - planeActor.getWidth()/2;
        destY = screenH/2 - planeActor.getHeight()/2;

        planeActor.setOrigin(planeActor.getWidth()/2,planeActor.getHeight()/2);
        planeActor.setRotation(-90);
        planeActor.setPosition(-planeActor.getWidth(), screenH/2 - planeActor.getHeight()/2);

        stage.addActor(planeActor);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


}
