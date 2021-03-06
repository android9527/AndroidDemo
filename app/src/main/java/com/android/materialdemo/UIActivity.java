package com.android.materialdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.toolbar.ToolbarActivity;
import com.demo.toolbar.ToolbarActivity2;
import com.eroad.widget.calendar.CalendarMainActivity;
import com.flyco.indicatorsamples.ui.SimpleHomeActivity;
import com.pitt.freshdownloadview.FreshDownloadViewMainActivity;
import com.rd.pageindicatorview.home.PageIndicatorActivity;
import com.rey.material.demo.MainActivity;

import java.util.ArrayList;

import com.yyh.lib.IncrMainActivity;

import app.dinus.com.example.LoadingMainActivity;
import mr_immortalz.com.stereoview.StereoViewMainActivity;
import no.agens.depth.RootActivity;
import top.wuhaojie.installer.InstallMainActivity;

/**
 * Created by chenfeiyue on 16/10/25.
 */

public class UIActivity extends BaseActivity implements UIAdapter.OnRecycleViewListener{

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        UIAdapter adapter = new UIAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setViewModels(demos);
        adapter.setOnRecycleViewListener(this);
    }

    @Override
    public void setupContentView() {
        setContentView(R.layout.activity_ui);
    }


    private static final ArrayList<DemoDetails> demos = new ArrayList<>();

    static {
        //MainActivity
        demos.add(new DemoDetails("material", "material",
                MainActivity.class));
        //ToolBar2
        demos.add(new DemoDetails("ToolBar2", "ToolBar2",
                ToolbarActivity2.class));
        //Depth-LIB-Android
        demos.add(new DemoDetails("Depth-LIB-Android", "Depth-LIB-Android",
                RootActivity.class));
        //FlycoPageIndicator
        demos.add(new DemoDetails("FlycoPageIndicator", "FlycoPageIndicator",
                SimpleHomeActivity.class));
        //PageIndicatorActivity
        demos.add(new DemoDetails("PageIndicatorActivity", "PageIndicatorActivity",
                PageIndicatorActivity.class));
        //FreshDownloadView
        demos.add(new DemoDetails("FreshDownloadView", "FreshDownloadView",
                FreshDownloadViewMainActivity.class));
        //增量更新
        demos.add(new DemoDetails("增量更新", "IncrMainActivity",
                IncrMainActivity.class));
        //日历控件
        demos.add(new DemoDetails("日历控件", "CalendarMainActivity",
                CalendarMainActivity.class));
        //Loading
        demos.add(new DemoDetails("LoadingDrawable", "LoadingMainActivity",
                LoadingMainActivity.class));

        //stereoview
        demos.add(new DemoDetails("3D立体旋转", "StereoViewMainActivity",
                StereoViewMainActivity.class));

        //autoinstall
        demos.add(new DemoDetails("autoinstall", "InstallMainActivity",
                InstallMainActivity.class));
    }

    @Override
    public void onItemClicked(int position) {
        DemoDetails demo = demos.get(position);
        startActivity(new Intent(UIActivity.this.getApplicationContext(),
                demo.getActivityClass()));
    }
}
