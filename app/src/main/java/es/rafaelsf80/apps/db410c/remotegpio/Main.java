/**
 * Copyright 2016 Rafael Sanchez Fuentes
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Author: Rafael Sanchez Fuentes rafaelsf80 at gmail dot com
 */

package es.rafaelsf80.apps.db410c.remotegpio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Main extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    boolean IS_DRAGONBOARD = false;

    Button btPin23, btPin24, btPin25, btPin26, btPin27, btPin28, btPin29, btPin30, btPin31, btPin32, btPin33, btPin34;
    TextView tvTitle;
    GpioProcessor.Gpio pin;
    Gpio gpio;
    private ValueEventListener mGpioListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.d(TAG, "Model: " + android.os.Build.MODEL);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(android.os.Build.MODEL);


        String model = new String(Build.MODEL);
        if (model.contains("MSM8916"))
            IS_DRAGONBOARD = true;
        Log.d(TAG, "Is this a Dragonboard device ? " + String.valueOf(IS_DRAGONBOARD));

        checkWifi();

        btPin23 = (Button) findViewById(R.id.bt_pin_23);
        btPin24 = (Button) findViewById(R.id.bt_pin_24);
        btPin25 = (Button) findViewById(R.id.bt_pin_25);
        btPin26 = (Button) findViewById(R.id.bt_pin_26);
        btPin27 = (Button) findViewById(R.id.bt_pin_27);
        btPin28 = (Button) findViewById(R.id.bt_pin_28);
        btPin29 = (Button) findViewById(R.id.bt_pin_29);
        btPin30 = (Button) findViewById(R.id.bt_pin_30);
        btPin31 = (Button) findViewById(R.id.bt_pin_31);
        btPin32 = (Button) findViewById(R.id.bt_pin_32);
        btPin33 = (Button) findViewById(R.id.bt_pin_33);
        btPin34 = (Button) findViewById(R.id.bt_pin_34);

        Firebase.setAndroidContext(this);
        final Firebase user1Ref = new Firebase("https://<YOUR-PROJECT>.firebaseio.com/user1");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd,MMMM,yyyy hh,mm,a");
        String now = sdf.format(c.getTime());
        user1Ref.child("LastAccess").setValue(now);

        // IMPORTANT: First launch
        // If there is no data, DB -> create tree and wait, Phone -> init to zero and wait
        // LastAccess is properly created, but not GPIO. Exuste this the very first time:
        // gpio = new Gpio(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0);
        // user1Ref.child("gpio").setValue(gpio);

        // Common code for both, only difference for Dragonboard to perform physical change on pins
        mGpioListener = user1Ref.child("gpio").addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot snapshot) {
                     AlertDialog alertDialog = new AlertDialog.Builder(Main.this).create();
                     alertDialog.setTitle("Gpio Update !!");

                     gpio = snapshot.getValue( Gpio.class );

                     // updateUI
                     btPin23.setText("Pin23: " + gpio.getPin23());
                     btPin24.setText("Pin24: " + gpio.getPin24());
                     btPin25.setText("Pin25: " + gpio.getPin25());
                     btPin26.setText("Pin26: " + gpio.getPin26());
                     btPin27.setText("Pin27: " + gpio.getPin27());
                     btPin28.setText("Pin28: " + gpio.getPin28());
                     btPin29.setText("Pin29: " + gpio.getPin29());
                     btPin30.setText("Pin30: " + gpio.getPin30());
                     btPin31.setText("Pin31: " + gpio.getPin31());
                     btPin32.setText("Pin32: " + gpio.getPin32());
                     btPin33.setText("Pin33: " + gpio.getPin33());
                     btPin34.setText("Pin34: " + gpio.getPin34());

                     alertDialog.setMessage(Integer.toString(gpio.getPin23()) + " " + Integer.toString(gpio.getPin24()) + " " + Integer.toString(gpio.getPin25()) + " " + Integer.toString(gpio.getPin26()) + " " +
                             Integer.toString(gpio.getPin27()) + " " + Integer.toString(gpio.getPin28()) + " " + Integer.toString(gpio.getPin29()) + " " +
                             Integer.toString(gpio.getPin30()) + " " + Integer.toString(gpio.getPin31()) + " " + Integer.toString(gpio.getPin32()) + " " +
                             Integer.toString(gpio.getPin33()) + " " + Integer.toString(gpio.getPin34()));
                     alertDialog.show();

                     if (IS_DRAGONBOARD)
                        setAllPins(gpio);
                 }

                 @Override
                 public void onCancelled(FirebaseError firebaseError) {

                 }
             });

        btPin23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin23() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin23").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin23").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin24() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin24").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin24").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin25() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin25").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin25").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin26() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin26").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin26").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin27() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin27").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin27").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin28() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin28").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin28").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin29() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin29").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin29").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin30() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin30").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin30").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin31() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin31").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin31").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin32() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin32").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin32").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin33() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin33").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin33").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

        btPin34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gpio.getPin34() == GpioProcessor.Gpio.HIGH) {
                    user1Ref.child("gpio").child("pin34").setValue(GpioProcessor.Gpio.LOW);
                } else {
                    user1Ref.child("gpio").child("pin34").setValue(GpioProcessor.Gpio.HIGH);
                }
            }
        });

    }

    public void setAllPins(Gpio gpio) {

        GpioProcessor gpioProcessor = new GpioProcessor();

        GpioProcessor.Gpio led = gpioProcessor.getPin23();
        led.out();
        if (gpio.getPin23() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin24();
        led.out();
        if (gpio.getPin24() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin25();
        led.out();
        if (gpio.getPin25() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin26();
        led.out();
        if (gpio.getPin26() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin27();
        led.out();
        if (gpio.getPin27() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin28();
        led.out();
        if (gpio.getPin28() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin29();
        led.out();
        if (gpio.getPin29() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin30();
        led.out();
        if (gpio.getPin30() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin31();
        led.out();
        if (gpio.getPin31() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin32();
        led.out();
        if (gpio.getPin32() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin33();
        led.out();
        if (gpio.getPin33() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();

        led = gpioProcessor.getPin34();
        led.out();
        if (gpio.getPin34() == GpioProcessor.Gpio.HIGH) led.high(); else led.low();
    }

    /**
     * Name: connectWifiNetwork
     * Description: Opens the setting page to enable the user to turn
     *              Wifi on and connect to a specific network
     *
     * @param
     *
     *
     */
    public void checkWifi() {
        WifiManager wifiManager =
                (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {

            Toast.makeText(Main.this, "Wifi is enabled", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Main.this, "Wifi is DISABLED", Toast.LENGTH_LONG).show();

            Intent wifiIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivityForResult(wifiIntent, 0);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        // Unregister the listener
        //user1Ref.child("gpio").removeEventListener(mGpioListener);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}