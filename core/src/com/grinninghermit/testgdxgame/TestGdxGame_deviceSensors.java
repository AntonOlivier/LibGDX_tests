package com.grinninghermit.testgdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestGdxGame_deviceSensors implements ApplicationListener {

	private SpriteBatch batch;
    private BitmapFont font;
    private String message = "Do something already";
    private int w, h;
    private float highestY = 0.0f;

	@Override
	public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.scale(2);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
	}


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
	public void render () {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // START
        int deviceAngle = Gdx.input.getRotation();
        Input.Orientation orientation = Gdx.input.getNativeOrientation();
        float accelY = Gdx.input.getAccelerometerY();
        if(accelY > highestY)
            highestY = accelY;
        message = "Device rotated to:" + Integer.toString(deviceAngle) + " degrees\n";
        message += "Device orientation is ";
        switch(orientation){
            case Landscape:
                message += " landscape.\n";
                break;
            case Portrait:
                message += " portrait. \n";
                break;
            default:
                message += " complete crap!\n";
                break;
        }

        message += "Device Resolution: " + Integer.toString(w) + "," + Integer.toString(h) + "\n";
        message += "Y axis accel: " + Float.toString(accelY) + " \n";
        message += "Highest Y value: " + Float.toString(highestY) + " \n";
        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Vibrator)){
            if(accelY > 7){
                Gdx.input.vibrate(100);
            }
        }
        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Compass)){
            message += "Azmuth:" + Float.toString(Gdx.input.getAzimuth()) + "\n";
            message += "Pitch:" + Float.toString(Gdx.input.getPitch()) + "\n";
            message += "Roll:" + Float.toString(Gdx.input.getRoll()) + "\n";
        }
        else{
            message += "No compass available\n";
        }
        font.drawMultiLine(batch, message, 0, h);        // END
        batch.end();
	}

    @Override
    public void resize(int width, int height) {
        batch.dispose();
        batch = new SpriteBatch();
        String resolution = Integer.toString(width) + "," + Integer.toString(height);
        Gdx.app.log("MJF", "Resolution changed " + resolution);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

}
