package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HamiltonTestActivity extends HamiltonActivity {
    private String message;

    private int currentNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        message = "";
        if (this.getIntent().hasExtra("message")) {
            message = this.getIntent().getStringExtra("message");
            currentNum =  Character.getNumericValue(message.charAt(message.length()- 1));
        }
//        System.out.println("CURRENT NUM IS: " + currentNum + " AND THE OTHER ONE " +
//                "IS: " + clevel + "\n");
//        System.out.println(prefs.getInt(getResources().getString(R.string.current_level), 60000));

        //make button
//        Button endButton = new Button(this);
//        endButton.setText("Go Back");
//        endButton.setX(0);
//        endButton.setY(0);
//        endButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(400, 200);
//        getLayout().addView(endButton, lp);

        //make message
        TextView messageView = new TextView(this);
        messageView.setTextSize(25);
        messageView.setText(message);
        messageView.setX(32);
        messageView.setY(50);
        messageView.setTextColor(getResources().getColor(R.color.neon_green));
        getLayout().addView(messageView, lp);
    }

    public int getCurrentNum() {
        return currentNum;
    }


    public void checkWon(){
        if(getPath().isFinished()){
            Toast t1 = Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG);
            t1.show();
            //getPath().reset();
        }
    }
}
