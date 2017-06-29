package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HamiltonPlayActivity extends HamiltonActivity {

    private int currentNum;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_play);
        relativeLayout = (RelativeLayout) findViewById(R.id.graph_play_relative);
        init(new HamiltonCircuit(getGraph()), relativeLayout);

        message = "";
        if (this.getIntent().hasExtra("message")) {
            message = this.getIntent().getStringExtra("message");
            currentNum =  this.getIntent().getIntExtra("currentNum", 1);
        }

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        getLayout().addView(endButton, lp);

        //make message
        TextView messageView = new TextView(this);
        messageView.setTextSize(Math.round(screenX/43.2));
        String text = "Hamilton\n" + message;
        messageView.setText(text);
        messageView.setX(Math.round(screenX/33.75));
        messageView.setY(Math.round(screenY/38.4));
        messageView.setTextColor(getResources().getColor(R.color.neon_green));
        relativeLayout.addView(messageView, lp);

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int chlevel = prefs.getInt(getResources().getString(R.string.current_hamilton_level), 1);
        System.out.println("CURRENT NUM IS: " + getCurrentNum() + " AND THE OTHER ONE " +
                "IS JUST OPENED TO : " + chlevel + "\n");

        //if(currentNum < chlevel) {
            Button nextButton = new Button(this);
            nextButton.setText("Next");
            nextButton.setTextSize(Math.round(screenX/43.2));
            nextButton.setX(screenX - Math.round(screenX/3.1));
            nextButton.setY(screenY - screenY/7);
            nextButton.setTextAppearance(this, R.style.Widget_AppCompat_Button_Borderless);
            nextButton.setBackgroundColor(Color.BLACK);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    System.out.println("CURRENT NUM IS: " +
                            getCurrentNum());
                    intent.putExtra("unlocked", getResources().getString(R.string.current_hamilton_level));
                    intent.putExtra("type", "Hamilton");
                    intent.putExtra("rush", getCurrentNum());
                    intent.setClass(getApplicationContext(), GraphLevelsActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            getRelativeLayout().addView(nextButton);

        //}
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent intent = new Intent();
            intent.setClass(this, GraphLevelsActivity.class);
            intent.putExtra("unlocked", getResources().getString(R.string.current_hamilton_level));
            intent.putExtra("type", "Hamilton");
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void checkWon(){
        if(getPath().isDone()){
            super.getDrawView().beDone();
            Toast t1 = Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG);
            t1.show();
            //getPath().reset();
            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            int chlevel = prefs.getInt(getResources().getString(R.string.current_hamilton_level), 1);
            System.out.println("CURRENT NUM IS: " + getCurrentNum() + " AND THE OTHER ONE " +
                "IS JUST OPENED TO : " + chlevel + "\n");
            if (getCurrentNum() == chlevel) {
                System.out.println("THIS HAPPENENEN ENENFNEOF NIWF ISNI J O");
                chlevel++;
                prefs.edit().putInt(getResources().getString(
                        R.string.current_hamilton_level), chlevel).apply();
                System.out.println("UPDATED VALUE IS: " +
                        prefs.getInt(getResources().getString(R.string.current_hamilton_level), 1));
            }

            //make button
//            System.out.println("THIS HAPNENDNDNDNNDNDNDNDNNDDDDDDDDDDDDDDDDDDDDDDDD");
//            Button nextButton = (Button) findViewById(R.id.button30);
//            nextButton.setVisibility(View.VISIBLE);
//            System.out.println("THIS HAPNENDNDNDNNDNDNDNDNNDDDDDDDDDDDDDDDDDDDDDDDD22222222222");


            //make button
            Button nextButton = new Button(this);
            nextButton.setText("Next");
            nextButton.setTextSize(24);
            nextButton.setX(screenX - Math.round(screenX/3.1));
            nextButton.setY(screenY - screenY/7);
            nextButton.setTextAppearance(this, R.style.Widget_AppCompat_Button_Borderless);
            nextButton.setBackgroundColor(Color.BLACK);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    System.out.println("CURRENT NUM IS: " +
                            getCurrentNum());
                    intent.putExtra("unlocked", getResources().getString(R.string.current_hamilton_level));
                    intent.putExtra("type", "Hamilton");
                    intent.putExtra("rush", getCurrentNum());
                    intent.setClass(getApplicationContext(), GraphLevelsActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            getRelativeLayout().addView(nextButton);
        }
    }

    public int getCurrentNum() {
        return currentNum;
    }


    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

}
