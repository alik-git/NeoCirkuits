package com.circuitstudio2016.circuits;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class GraphLoadActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_load);

        //ConstraintLayout layout = (ConstraintLayout)findViewById();
        ArrayList<ConstraintLayout> layouts = new ArrayList<>();
        layouts.add((ConstraintLayout) findViewById(R.id.activity_graph_load_1));
        layouts.add((ConstraintLayout) findViewById(R.id.activity_graph_load_2));
        layouts.add((ConstraintLayout) findViewById(R.id.activity_graph_load_3));

        ArrayList<ConstraintSet> sets = new ArrayList<>();

        sets.add(new ConstraintSet());
        sets.get(0).clone(layouts.get(0));
        sets.add(new ConstraintSet());
        sets.get(1).clone(layouts.get(1));
        sets.add(new ConstraintSet());
        sets.get(2).clone(layouts.get(2));

        Button prev = new Button(this);

        int curr;
        for (int i = 1; i <= 3; i++) {
            curr = i%3;
            Button button = new Button(this);
            String text = "Graph " + i;
            button.setText(text);
            button.setTextSize(20);
            //button.setTextAppearance(this, R.style.Widget_AppCompat_Button_Borderless);
            button.setBackgroundColor(Color.BLACK);
            button.setId(i + 500);
//            layout.addView(button);
//            if (i == 1) {
//                //set.centerHorizontally(button.getId(), );
//                set.connect(button.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
//                set.connect(button.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
//                set.constrainHeight(button.getId(), 150);
//                set.applyTo(layout);
//                prev = button;
//            } else if ( i == 2 ){
//                set.centerHorizontally(button.getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, (float) 0.5);
//                //set.connect(button.getId(), ConstraintSet.LEFT, prev.getId(), ConstraintSet.RIGHT, 32);
//                set.connect(button.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,0);
//                set.constrainHeight(button.getId(), 150);
//                set.applyTo(layout);
//                prev = button;
//            } else if ( i == 3 ) {
//                set.connect(button.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
//                set.connect(button.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0);
//                set.constrainHeight(button.getId(), 150);
//                set.applyTo(layout);
//                prev = button;
//
//            }


        }
//        Button nextButton = new Button(this);
//        nextButton.setText("Next");
//        nextButton.setTextSize(24);
//        nextButton.setTextAppearance(this, R.style.Widget_AppCompat_Button_Borderless);
//        nextButton.setBackgroundColor(Color.BLACK);
//
//        set.conn
//        set.connect(button.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
//        set.connect(button.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0);
//        set.connect(button.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
//
//        set.connect(newButton.getId(), ConstraintSet.BOTTOM, button.getId(), ConstraintSet.TOP, 0);
//        set.connect(newButton.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0);
//        set.connect(newButton.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);


    }


}