package com.example.poplify.baby_guru_sample.child_profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poplify.baby_guru_sample.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class All_Graph_frag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.all_graph_frag, container, false);

        GraphView graph =  view.findViewById(R.id.graph);
        GridLabelRenderer glr = graph.getGridLabelRenderer();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 12),
                new DataPoint(24, 32),
                new DataPoint(30, 22),
                new DataPoint(44, 96)
        });
        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);

        glr.setPadding(12);

        return view;
    }


}
