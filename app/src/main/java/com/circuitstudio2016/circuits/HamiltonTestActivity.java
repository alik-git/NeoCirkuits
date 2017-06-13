package com.circuitstudio2016.circuits;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HamiltonTestActivity extends HamiltonActivity {
    private String message;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        message = "";
        if (this.getIntent().hasExtra("message")) {
            message = this.getIntent().getStringExtra("message");
        }

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

    public void checkWon(){
        if(getPath().isFinished()){
            Toast t1 = Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG);
            t1.show();
            getPath().reset();
        }
    }
}
