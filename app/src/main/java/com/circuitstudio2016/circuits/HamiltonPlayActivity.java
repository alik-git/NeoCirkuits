package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HamiltonPlayActivity extends HamiltonTestActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void checkWon(){
        if(getPath().isDone()){
            Toast t1 = Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG);
            t1.show();
            //getPath().reset();
            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            int chlevel = prefs.getInt(getResources().getString(R.string.current_hamilton_level), 1);
            System.out.println("CURRENT NUM IS: " + super.getCurrentNum() + " AND THE OTHER ONE " +
                "IS JUST OPENED TO : " + chlevel + "\n");
            if (super.getCurrentNum() == chlevel) {
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
            nextButton.setText("Nextjijijijiji");
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
                    intent.putExtra("level_string", getResources().getString(R.string.current_hamilton_level));
                    intent.putExtra("type", "hamilton");
                    intent.putExtra("rush", HamiltonPlayActivity.super.getCurrentNum());
                    intent.setClass(getApplicationContext(), HCircuitLevelsActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            getRelativeLayout().addView(nextButton);
        }
    }
}
