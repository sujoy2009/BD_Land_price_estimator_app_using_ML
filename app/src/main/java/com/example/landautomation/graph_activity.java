package com.example.landautomation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PanZoom;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;

public class graph_activity extends AppCompatActivity {
    TextView t1,t2;
    XYPlot plot;
    private LineChart mchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_activity);
        t1=(TextView)findViewById(R.id.t1id);
        t2=(TextView)findViewById(R.id.t2id);
       // plot = findViewById(R.id.plot);
        mchart=(LineChart)findViewById(R.id.chart1);
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(false);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));
        Python py=Python.getInstance();
        final PyObject pyob=py.getModule("mylr");
        int flag=1;
        PyObject pob=pyob.callAttr("ret0",flag);
        PyObject pob1=pyob.callAttr("ret1",flag);
        PyObject pob3=pyob.callAttr("ret3",flag);
        PyObject pob5=pyob.callAttr("ret5",flag);
        PyObject pob10=pyob.callAttr("ret10",flag);

        String pp0=pob.toString();
        int intp0=Integer.parseInt(pp0);
        String pp1=pob1.toString();
        int intp1=Integer.parseInt(pp1);
        String pp3=pob3.toString();
        int intp3=Integer.parseInt(pp3);
        String pp5=pob5.toString();
        int intp5=Integer.parseInt(pp5);
        String pp10=pob10.toString();
        int intp10=Integer.parseInt(pp10);


        Integer.toString(intp0);
       t1.setText("Current Price :\n"+intp0);
       // Toast.makeText(graph_activity.this ," "+intp0,Toast.LENGTH_LONG).show();
        intp0=intp0/1000;
        intp1=intp1/1000;
        intp3=intp3/1000;
        intp5=intp5/1000;
        intp10=intp10/1000;
      // Toast.makeText(graph_activity.this ," "+intp0,Toast.LENGTH_LONG).show();
        //for graph showing
        double a=1.2;
        ArrayList<Entry> yvalue=new ArrayList<>();
        yvalue.add(new Entry(0,intp0));
        yvalue.add(new Entry(1,intp1));
        yvalue.add(new Entry(3,intp3));
        yvalue.add(new Entry(5,intp5));
        yvalue.add(new Entry(10,intp10));

        LineDataSet set1=new LineDataSet(yvalue,"Prices are in Thousands ");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);

        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(set1);
        LineData data=new LineData(dataSets);
        mchart.setData(data);


        //Create a arrays of y-value to plot:
       // final   Number[] domainLabels = {0,1,3,5,10,11,12,13,14,15};
        //final   Number[] series1Numbers = {intp0,intp1,intp3,intp5,intp10,intp10,intp10,intp10,intp10,intp10};
        /*
        final   Number[] domainLabels = {0,1,3,5,10};
        final   Number[] series1Numbers ={6.66,7.3,7.5,7.8,8.0};

        // Turn the above arrays into XYSeries
        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Series 1");

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED,Color.BLACK,null,null);


        series1Format.setInterpolationParams(new CatmullRomInterpolator.Params(100,
                CatmullRomInterpolator.Type.Centripetal));


        plot.addSeries(series1,series1Format);



        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.LEFT).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round( ((Number)obj).floatValue());
                return toAppendTo.append(series1Numbers[i]);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });



        PanZoom.attach(plot);

         */

    }
}
