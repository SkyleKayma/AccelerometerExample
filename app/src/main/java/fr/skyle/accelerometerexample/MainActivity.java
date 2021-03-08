package fr.skyle.accelerometerexample;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Views
    private TextView tvAcceleroX;
    private TextView tvAcceleroY;
    private TextView tvAcceleroZ;
    private TextView tvAcceleroFrontOrBack;
    private TextView tvAcceleroLeftOrRight;

    // Sensor part
    private SensorManager mSensorManager;

    private final SensorEventListener accelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            tvAcceleroX.setText(getString(R.string.main_value_of_x, x));
            tvAcceleroY.setText(getString(R.string.main_value_of_y, y));
            tvAcceleroZ.setText(getString(R.string.main_value_of_z, z));

            if (x > 0) {
                tvAcceleroLeftOrRight.setText(getString(R.string.main_left_or_right, getString(R.string.main_left)));
            } else {
                tvAcceleroLeftOrRight.setText(getString(R.string.main_left_or_right, getString(R.string.main_right)));
            }

            if (y > 0) {
                tvAcceleroFrontOrBack.setText(getString(R.string.main_front_or_back, getString(R.string.main_back)));
            } else {
                tvAcceleroFrontOrBack.setText(getString(R.string.main_front_or_back, getString(R.string.main_front)));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Nothing special to do here
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        tvAcceleroX = findViewById(R.id.textViewAcceleroX);
        tvAcceleroY = findViewById(R.id.textViewAcceleroY);
        tvAcceleroZ = findViewById(R.id.textViewAcceleroZ);
        tvAcceleroFrontOrBack = findViewById(R.id.textViewAcceleroFrontOrBack);
        tvAcceleroLeftOrRight = findViewById(R.id.textViewAcceleroLeftOrRight);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(
                accelerometerListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI
        );
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(accelerometerListener);
    }
}