package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HamiltonTestActivity extends HamiltonActivity {

    //private String message;
    private int currentNum;
    private RelativeLayout relativeLayout;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph_test);
        relativeLayout = (RelativeLayout) findViewById(R.id.graph_test_relative);
        init(new HamiltonCircuit(getGraph()), relativeLayout);

        message = "";
        if (this.getIntent().hasExtra("message")) {
            message = this.getIntent().getStringExtra("message");
            currentNum =  this.getIntent().getIntExtra("currentNum", 1);
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
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        getLayout().addView(endButton, lp);

        //make message
        TextView messageView = new TextView(this);
        String text = "(H)" + message;
        messageView.setText(text);
        messageView.setTextSize(Math.round(screenX/43.2));
        messageView.setX(Math.round(screenX/33.75));
        messageView.setY(Math.round(screenY/38.4));
        messageView.setTextColor(getResources().getColor(R.color.neon_green));
        relativeLayout.addView(messageView, lp);
    }

    public int getCurrentNum() {
        return currentNum;
    }


    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public void switchMode(View w) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        System.out.println(currentNum);
        bundle.putParcelable("graph", new Graph(getGraph()));
        intent.putExtras(bundle);
        String message = "Graph " + (Integer.toString(currentNum));
        intent.putExtra("message", message);
        intent.setClass(this, EulerTestActivity.class);
        //intent.setAction("circuit");
        startActivity(intent);
        finish();

    }

    public void checkWon(){
        //System.out.println("yopoooooooooooooooooooooooooooo22222fgdsgsgf222222");
        if(getPath().isDone()){
            super.getDrawView().beDone();
            Toast t1 = Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_LONG);
            t1.show();
            //getPath().reset();
        }
    }
}
