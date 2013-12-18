package com.example.BroadbandMobile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Handler;
import com.jjoe64.graphview.*;
import com.jjoe64.graphview.GraphView.*;
import android.view.Window;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);

        final JSONParser parser = new JSONParser();
        final BroadbandGrapher grapher = new BroadbandGrapher();
        final Context context = this;
        final LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

        final Handler handler = new Handler();

        /**
         * Thread
         * Hakee dataa joka 4:s sekunti.
         */
        Runnable realtime = new Runnable() {
            @Override
            public void run() {
                parser.simulateLoadData();
                handler.postDelayed(this, 4000);
            }
        };
        realtime.run();


        /**
         * Thread
         * Tarkistaa, onko data valmis -> piirretään
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (parser.getIsReady()) {
                    Toast.makeText(getApplicationContext(), "On valmis", Toast.LENGTH_SHORT).show();
                    grapher.setData(parser.getResult());
                    grapher.draw(context);
                    layout.addView(grapher.getGraphView());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Ei ole valmis", Toast.LENGTH_SHORT).show();
                }

                handler.postDelayed(this, 5000);
            }
        };
        runnable.run();
    }
}
