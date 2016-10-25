package com.rey.material;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.toolbar.ToolBarActivity;
import com.rey.material.demo.MainActivity;

import java.util.ArrayList;

import com.rey.material.demo.R;

/**
 * Created by chenfeiyue on 16/10/25.
 */

public class UIActivity extends AppCompatActivity implements UIAdapter.OnRecycleViewListener{

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        UIAdapter adapter = new UIAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setViewModels(demos);
        adapter.setOnRecycleViewListener(this);
    }


    private static final ArrayList<DemoDetails> demos = new ArrayList<>();

    static {
        //MainActivity
        demos.add(new DemoDetails("material", "material",
                MainActivity.class));
        //ToolBar
        demos.add(new DemoDetails("ToolBar", "ToolBar",
                ToolBarActivity.class));
    }

    @Override
    public void onItemClicked(int position) {
        DemoDetails demo = demos.get(position);
        startActivity(new Intent(UIActivity.this.getApplicationContext(),
                demo.getActivityClass()));
    }
}
