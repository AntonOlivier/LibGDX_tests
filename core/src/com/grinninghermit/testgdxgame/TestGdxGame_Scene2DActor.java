package com.grinninghermit.testgdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class TestGdxGame_Scene2DActor implements ApplicationListener {

    public class PlaneActor extends Actor {
        Texture planeTexture = new Texture(Gdx.files.internal("0001.png"));
        public boolean started = false;

        public PlaneActor(){
            setBounds(getX(),getY(),this.getWidth(),this.getHeight());
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
            batch.draw(planeTexture,this.getX(),getY(),this.getOriginX(),this.getOriginY(),this.getWidth(),
                    this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation(),0,0,
                    planeTexture.getWidth(),planeTexture.getHeight(),false,false);
        }

        @Override
        public void act(float delta){
            super.act(delta);

            if(started){
                this.rotateBy(1);
            }
        }
    }


    private Stage stage;

    @Override
    public void create () {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        PlaneActor planeActor = new PlaneActor();
        planeActor.setTouchable(Touchable.enabled);

        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        RotateToAction rotateAction = new RotateToAction();
        ScaleToAction scaleAction = new ScaleToAction();

        moveAction.setPosition(300f, 0f);
        moveAction.setDuration(5f);
        rotateAction.setRotation(90f);
        rotateAction.setDuration(5f);
        scaleAction.setScale(0.5f);
        scaleAction.setDuration(5f);

        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(rotateAction);
        sequenceAction.addAction(scaleAction);

        planeActor.addAction(sequenceAction);

        planeActor.setOrigin(planeActor.getWidth()/2,planeActor.getHeight()/2);
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
