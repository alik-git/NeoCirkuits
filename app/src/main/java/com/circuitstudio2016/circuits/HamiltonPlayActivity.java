package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HamiltonPlayActivity extends HamiltonTestActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void checkWon(){
        if(getPath().isFinished()){
            Toast t1 = Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG);
            t1.show();
            //getPath().reset();
            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            int clevel = prefs.getInt(getResources().getString(R.string.current_level), 1);
            System.out.println("CURRENT NUM IS: " + super.getCurrentNum() + " AND THE OTHER ONE " +
                "IS: " + clevel + "\n");
            if (super.getCurrentNum() == clevel) {
                System.out.println("THIS HAPPENENEN ENENFNEOF NIWF ISNI J O");
                clevel++;
                prefs.edit().putInt(getResources().getString(
                        R.string.current_level), clevel).apply();
                System.out.println("UPDATED VALUE IS: " +
                        prefs.getInt(getResources().getString(R.string.current_level), 1));
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
                            HamiltonPlayActivity.super.getCurrentNum());
                    intent.putExtra("rush", HamiltonPlayActivity.super.getCurrentNum());
                    intent.setClass(getApplicationContext(), HCircuitLevelsActivity.class);
                    startActivity(intent);
                }
            });
            getLayout().addView(nextButton);
        }
    }
}
