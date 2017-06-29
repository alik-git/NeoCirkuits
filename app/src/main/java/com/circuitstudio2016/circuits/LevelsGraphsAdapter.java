package com.circuitstudio2016.circuits;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by aliqk on 6/20/2017.
 */

public class LevelsGraphsAdapter extends GraphsAdapter {

    public LevelsGraphsAdapter(Activity context, Graph[] graphs, String[] type) {
        super(context, graphs, type);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button = new Button(getmContext());
        int graphNum = position + 1;
        String text = getType()[0];
        text+= "" + graphNum;
        button.setText(text);
        button.setTextSize(16);
        button.setBackgroundColor(Color.BLACK);
        final int num = position;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                System.out.println("load func: " + getGraphs());
                System.out.println("load func graph: " + getGraphs()[num]);
                System.out.println(num);
                bundle.putParcelable("graph", getGraphs()[num]);
                intent.putExtras(bundle);
                String message = getType()[0] + (Integer.toString(num + 1));
                intent.putExtra("message", message);
                intent.putExtra("currentNum", num + 1);

                if (getType()[1].equals("Hamilton")) {
                    intent.setClass(getmContext(), HamiltonPlayActivity.class);
                } else {
                    intent.setClass(getmContext(), EulerPlayActivity.class);
                }
                //intent.setAction("circuit");
                getmContext().startActivity(intent);
                getmContext().finish();
                //mContext.finish();
            }
        });
        return button;
    }
}
