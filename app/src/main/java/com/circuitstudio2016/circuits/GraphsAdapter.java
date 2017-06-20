package com.circuitstudio2016.circuits;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by aliqk on 6/19/2017.
 */

public class GraphsAdapter extends BaseAdapter {

    private final Activity mContext;



    private final Graph[] graphs;
    private final String[] type;

    public GraphsAdapter(Activity context, Graph[] graphs, String[] type) {
        this.mContext = context;
        this.graphs = graphs;
        this.type = type;
    }

    @Override
    public int getCount() {
        return graphs.length;
    }

    @Override
    public Object getItem(int position) {
        return graphs[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button = new Button(mContext);
        int graphNum = position + 1;
        String text = type[0];
        text+= "" + graphNum;
        button.setText(text);
        button.setTextSize(16);
        //button.setBackgroundColor(Color.BLACK);
        final int num = position;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                System.out.println("load func: " + graphs);
                System.out.println("load func graph: " + graphs[num]);
                System.out.println(num);
                bundle.putParcelable("graph", graphs[num]);
                intent.putExtras(bundle);
                String message = type[0] + (Integer.toString(num + 1));
                intent.putExtra("message", message);
                if (type[0].equals("Graph ")) {
                    //type = mContext.getType();
                    if (type[1].equals("Hamilton")) {
                        intent.setClass(mContext, HamiltonTestActivity.class);
                    } else {
                        intent.setClass(mContext, EulerTestActivity.class);
                    }
                } else {
                    if (type[1].equals("Hamilton")) {
                        intent.setClass(mContext, HamiltonPlayActivity.class);
                    } else {
                        intent.setClass(mContext, EulerPlayActivity.class);
                    }
                }
                intent.setAction("circuit");
                mContext.startActivity(intent);
                if (type[0].equals("Level ")) {
                    mContext.finish();
                }
                //mContext.finish();
            }
        });
        return button;
    }

    public Activity getmContext() {
        return mContext;
    }

    public Graph[] getGraphs() {
        return graphs;
    }

    public String[] getType() {
        return type;
    }
}
