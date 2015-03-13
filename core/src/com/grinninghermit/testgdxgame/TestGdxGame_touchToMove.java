package com.grinninghermit.testgdxgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class TestGdxGame_touchToMove extends ApplicationAdapter implements GestureDetector.GestureListener {

	private SpriteBatch batch;
    private TextureAtlas planeAtlas;
    private Texture planeTexture;
    private Sprite planeSprite;
    private Animation animation, rotateUpAnimation, rotateDownAnimation, circleLeftAnimation, circleRightAnimation;
    private float timePassed = 0;
    private float posX, posY;


	@Override
	public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        batch = new SpriteBatch();

        planeAtlas = new TextureAtlas(Gdx.files.internal("plane_test.txt"));
        planeTexture = new Texture(Gdx.files.internal("0001.png"));
        planeSprite = new Sprite(planeTexture);
        animation = new Animation(1/30f, planeAtlas.getRegions());

        posX = w/2 - planeSprite.getWidth()/2;
        posY = h/2 - planeSprite.getHeight()/2;
        planeSprite.setPosition(posX, posY);
        //planeSprite.setRotation(-90);

        rotateDownAnimation = new Animation(0.1f,
                (planeAtlas.findRegion("0001")),
                (planeAtlas.findRegion("0002")),
                (planeAtlas.findRegion("0003")),
                (planeAtlas.findRegion("0004")),
                (planeAtlas.findRegion("0005")),
                (planeAtlas.findRegion("0006")),
                (planeAtlas.findRegion("0007")),
                (planeAtlas.findRegion("0008")),
                (planeAtlas.findRegion("0009")),
                (planeAtlas.findRegion("0010")),
                (planeAtlas.findRegion("0011")),
                (planeAtlas.findRegion("0012")),
                (planeAtlas.findRegion("0013")),
                (planeAtlas.findRegion("0014")),
                (planeAtlas.findRegion("0015"))
        );

        rotateUpAnimation = new Animation(0.1f,
                (planeAtlas.findRegion("0016")),
                (planeAtlas.findRegion("0017")),
                (planeAtlas.findRegion("0018")),
                (planeAtlas.findRegion("0019")),
                (planeAtlas.findRegion("0020")),
                (planeAtlas.findRegion("0021")),
                (planeAtlas.findRegion("0022")),
                (planeAtlas.findRegion("0023")),
                (planeAtlas.findRegion("0024")),
                (planeAtlas.findRegion("0025")),
                (planeAtlas.findRegion("0026")),
                (planeAtlas.findRegion("0027")),
                (planeAtlas.findRegion("0028")),
                (planeAtlas.findRegion("0029")),
                (planeAtlas.findRegion("0030"))
        );

        circleLeftAnimation = new Animation(0.1f,
                (planeAtlas.findRegion("0030")),
                (planeAtlas.findRegion("0029")),
                (planeAtlas.findRegion("0028")),
                (planeAtlas.findRegion("0027")),
                (planeAtlas.findRegion("0026")),
                (planeAtlas.findRegion("0025")),
                (planeAtlas.findRegion("0024")),
                (planeAtlas.findRegion("0023"))
        );

        circleRightAnimation = new Animation(0.1f,
                (planeAtlas.findRegion("0001")),
                (planeAtlas.findRegion("0002")),
                (planeAtlas.findRegion("0003")),
                (planeAtlas.findRegion("0004")),
                (planeAtlas.findRegion("0005")),
                (planeAtlas.findRegion("0006")),
                (planeAtlas.findRegion("0007")),
                (planeAtlas.findRegion("0008"))
        );

        Gdx.input.setInputProcessor(new GestureDetector(this));
	}

    @Override
    public void dispose() {
        batch.dispose();
        planeAtlas.dispose();
        planeTexture.dispose();
    }

    @Override
	public void render () {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        planeSprite.setPosition(posX, posY);

        batch.begin();

        planeSprite.draw(batch);

        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed, true), 0, 0);

        batch.end();
	}

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        posX = x - planeSprite.getWidth()/2;
        posY = Gdx.graphics.getHeight() - y - planeSprite.getHeight()/2;
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
