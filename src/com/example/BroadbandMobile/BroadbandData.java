package com.example.BroadbandMobile;


import java.util.ArrayList;

public class BroadbandData
{
	private int _xtype;
    private int _ytype;
    private String _xlabel;
    private String _ylabel;
    private int _graphtype;
	private ArrayList<BroadbandPoint> _data;
    private BroadbandGrapher broadbandGrapher;

    public final int LINEGRAPH = 1;
    public final int BARGRAPH = 2;

	public BroadbandData(){
		super();
	}

    public int getXtype() {
        return _xtype;
    }

    public void setXtype(int _xtype) {
        this._xtype = _xtype;
    }

    public int getYtype() {
        return _ytype;
    }

    public void setYtype(int _ytype) {
        this._ytype = _ytype;
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
}

