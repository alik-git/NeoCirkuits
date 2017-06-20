package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EulerPlayActivity extends EulerTestActivity {

    private String message;
    private int currentNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        message = "";
        if (this.getIntent().hasExtra("message")) {
            message = this.getIntent().getStringExtra("message");
            currentNum =  Character.getNumericValue(message.charAt(message.length()- 1));
        }

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

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public int getCurrentNum() {
        return currentNum;
    }


    @Override
    public void checkWon(){
        if (getPath().isEulerFinished()) {
            Toast t1 = Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG);
            t1.show();

            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            int celevel = prefs.getInt(getResources().getString(R.string.current_euler_level), 1);
            System.out.println("CURRENT EULER  NUM IS: " + getCurrentNum() + " AND THE OTHER  EULER ONE " +
                    "IS: " + celevel + "\n");
            if (getCurrentNum() == celevel) {
                System.out.println("THIS HAPPENENEN ENENFNEOF NIWF ISNI J O  EULER ");
                celevel++;
                prefs.edit().putInt(getResources().getString(
                        R.string.current_euler_level), celevel).apply();
                System.out.println("UPDATED EULER VALUE IS: " +
                        prefs.getInt(getResources().getString(R.string.current_euler_level), 1));
            }
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
                    System.out.println("CURRENT  EULER NUM IS: " +
                            getCurrentNum());
                    intent.putExtra("level_string", getResources().getString(R.string.current_euler_level));
                    intent.putExtra("type", "euler");
                    intent.putExtra("rush", getCurrentNum());
                    intent.setClass(getApplicationContext(), ECircuitLevelsActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            getLayout().addView(nextButton);
        }

    }
}
