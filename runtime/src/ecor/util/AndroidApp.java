package ecor.util;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AndroidApp extends Application implements SensorEventListener {
  public static Context context;

  private SensorManager sensorManager;
  private Sensor temperatureSensor;

  public static int temperature;

  @Override
  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();

    sensorManager = (SensorManager) AndroidApp.context.getSystemService(SENSOR_SERVICE);
    temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);

    if (sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE) != null) {
      System.out.println("There is no temperature sensor");
      temperature = 1;
      return;
    }

    sensorManager.registerListener(this, 
                                   temperatureSensor, 
                                   SensorManager.SENSOR_DELAY_NORMAL);
  }

  /*
  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this);
  }
  */

  public void onAccuracyChanged(Sensor sensor, int accuracy) { }

  public void onSensorChanged(SensorEvent event) {
    if (event.sensor.getType() == Sensor.TYPE_TEMPERATURE) {
      System.out.println("Registered temperature change");
      temperature = (int) event.values[0];
    }
  }
}
