package com.example.BroadbandMobile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BroadbandData
{
	private int _xtype;
    private int _ytype;
    private String _xlabel;
    private String _ylabel;
    private int _graphtype;
	private ArrayList<BroadbandPoint> _data;
    private Map<String, Integer> _xdata;
    private Map<String, Integer> _ydata;

    public static final int LINEGRAPH = 101;
    public static final int BARGRAPH = 102;

    public static final int XHOUR = 1001;
    public static final int XDAY = 1002;
    public static final int XMONTH = 1003;
    public static final int XYEAR = 1004;
    public static final int XMANYYEARS = 1005;

    public static final int YKWH = 2001;




	public BroadbandData(){

        this._data = new ArrayList<BroadbandPoint>();

        this._xdata = new HashMap<String, Integer>();
        this._ydata = new HashMap<String, Integer>();

        this._xdata.put("year", XYEAR);

        this._ydata.put("kwh", YKWH);
	}

    public int getXtype() {
        return _xtype;
    }

    public double getMin() {
        double ret = this._data.get(0).getY();
        for (int i = 1; i < this._data.size(); i++) {
            if (ret > this._data.get(i).getY()) {
                ret = this._data.get(i).getY();
            }
        }

        return ret;
    }

    public double getMax() {
        double ret = this._data.get(0).getY();
        for (int i = 1; i < this._data.size(); i++) {
            if (ret < this._data.get(i).getY()) {
                ret = this._data.get(i).getY();
            }
        }
        return ret;
    }

    public void setXtype(int _xtype) {
        this._xtype = _xtype;
    }

    public void setXtype(String _xtype) {
        this._xtype = this._xdata.get(_xtype);
    }

    public int getYtype() {
        return _ytype;
    }

    public void setYtype(String _ytype) {
        this._ytype = this._ydata.get(_ytype);
    }

    public String getXlabel() {
        return _xlabel;
    }

    public void setXlabel(String _xlabel) {
        this._xlabel = _xlabel;
    }

    public String getYlabel() {
        return _ylabel;
    }

    public void setYlabel(String _ylabel) {
        this._ylabel = _ylabel;
    }

    public int getGraphType() {
        return _graphtype;
    }

    public void setGraphType(int _graphtype) {
        this._graphtype = _graphtype;
    }

    public void addPoint(BroadbandPoint point) {
		this._data.add(point);
	}

	public void addPoint(double x, double y) {
		BroadbandPoint point = new BroadbandPoint();
        point.setX(x);
        point.setY(y);
        this._data.add(point);
	}

	public int size() {
		return this._data.size();
	}

	public void clear() {
		this._data.clear();
	}

    public BroadbandPoint getPoint(int index) {
        return this._data.get(index);
    }
}

