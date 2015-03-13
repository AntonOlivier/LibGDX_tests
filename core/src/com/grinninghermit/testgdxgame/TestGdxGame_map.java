package com.grinninghermit.testgdxgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class TestGdxGame_map extends ApplicationAdapter implements GestureDetector.GestureListener {

	private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextureAtlas planeAtlas;
    private Texture map, dragon;
    private Sprite sprite1, sprite2;
    private Animation animation;
    private float timePassed = 0;


	@Override
	public void create () {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        map = new Texture(Gdx.files.internal("thedas_map.jpg"));
        dragon = new Texture(Gdx.files.internal("dragon.png"));
        sprite1 = new Sprite(map);
        sprite2 = new Sprite(dragon);
        planeAtlas = new TextureAtlas(Gdx.files.internal("plane_test.txt"));

        animation = new Animation(1/30f, planeAtlas.getRegions());

        sprite1.setPosition(-sprite1.getWidth()/2,-sprite1.getHeight()/2);

        Gdx.input.setInputProcessor(new GestureDetector(this));
	}

    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
        dragon.dispose();
        planeAtlas.dispose();
    }

    @Override
	public void render () {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite1.draw(batch);
        sprite2.draw(batch);
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed, true), -125, -125);
        batch.end();
	}


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX, deltaY);
        camera.update();
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
