package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class SelectActivity extends AppCompatActivity {



    private GraphList graphs = new GraphList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);



//        GridView gridView = (GridView) findViewById(R.id.activity_select_grid);
//        String[]
//        final GraphsAdapter graphsAdapter = new GraphsAdapter(this, graphs.getGraphsArray());
//        gridView.setAdapter(graphsAdapter);

//        final Context context = this;

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//
//                System.out.println("load woooooooooooooooooooooooooooooo");
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                System.out.println("load func: " + graphs);
//                System.out.println("load func graph: " + graphs.getGraph(position));
//                System.out.println(position);
//                bundle.putParcelable("graph", graphs.getGraph(position));
//                intent.putExtras(bundle);
//                String message = "Graph " + (Integer.toString(position + 1));
//                intent.putExtra("message", message);
//                intent.setClass(context, HamiltonTestActivity.class);
//                intent.setAction("circuit");
//                startActivity(intent);
//
//                // This tells the GridView to redraw itself
//                // in turn calling your BooksAdapter's getView method again for each cell
//                graphsAdapter.notifyDataSetChanged();
//            }
//        });

    }

    public GraphList getMyGraphs() {
        return graphs;
    }

    public void setMyGraphs(GraphList graphs) {
        this.graphs = graphs;
    }
}
