package com.example.BroadbandMobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import com.jjoe64.graphview.*;

public class BroadbandGrapher
{
	private GraphView _graphview;
	private BroadbandData _data;

	public BroadbandGrapher(){
		super();
	}

    public GraphView getGraphView() {
        return this._graphview;
    }

    public void setData(BroadbandData _data) {
        this._data = _data;
    }

    public void draw(Context context) {
        if (this._data != null) {

            /**
             *      Piirtämisen asetukset
             */
            this._graphview = new BarGraphView(context, "Energian kulutus");

            if (this._data.getYtype() == BroadbandData.YKWH) {
                this._graphview.setShowLegend(true);
                this._graphview.setLegendAlign(GraphView.LegendAlign.TOP);
            }

            if (this._data.getXtype() == BroadbandData.XYEAR) {
                //this._graphview.setHorizontalLabels(new String[] {"0", "1","2","3","4","5","6","7","8","9","10","11","12"});
                this._graphview.setHorizontalLabels(new String[] {"tammi", "helmi","maalis","huhti","touko","kesä","heinä","elo","syys","loka","marras","joulu"});
            }

            this._graphview.setManualYAxisBounds(100, 0);

            /**
             *      Piirtämisen data
             */

            GraphView.GraphViewData[] gdata = new GraphView.GraphViewData[this._data.size()];

            for (int i = 0; i < this._data.size(); i++) {
                BroadbandPoint point = this._data.getPoint(i);
                gdata[i] = new GraphView.GraphViewData(point.getX(), point.getY());
            }


            GraphViewSeries.GraphViewSeriesStyle style = new GraphViewSeries.GraphViewSeriesStyle(Color.CYAN, 1);
            GraphViewSeries series = new GraphViewSeries("kWh", style, gdata);
            //this._graphview.setViewPort(0, 6);

            this._graphview.addSeries(series);

        }
    }
}

