package com.example.BroadbandMobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
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


        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
                new GraphViewData(1, 2.0d)
                , new GraphViewData(2, 2.5d)
                , new GraphViewData(2.5, 3.0d) // another frequency
                , new GraphViewData(3, 2.5d)
                , new GraphViewData(4.5, 1.0d)
                , new GraphViewData(5, 3.0d)
                , new GraphViewData(6, 1.0d)
                , new GraphViewData(7, 2.0d)
                , new GraphViewData(8, 1.2d)
                , new GraphViewData(9, 3.0d)
                , new GraphViewData(10, 4.0d)
                , new GraphViewData(11, 1.5d)
                , new GraphViewData(12, 2.0d)
        });

        GraphView graphView = new LineGraphView(
                this // context
                , "Sähkönkulutus" // heading
        );
        graphView.setManualYAxisBounds(8, 0);
        graphView.addSeries(exampleSeries); // data
        graphView.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.addView(graphView);
    }
}
