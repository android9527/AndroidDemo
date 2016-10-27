package com.flyco.indicatorsamples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flyco.indicatorsamples.banner.SimpleImageBanner;
import com.flyco.indicatorsamples.utils.ViewFindUtils;
import com.flyco.pageindicator.anim.select.ZoomInEnter;
import com.flyco.pageindicator.indicator.FlycoPageIndicaor;
import com.android.materialdemo.R;

import java.util.ArrayList;

public class FlycoPageIndicaorActivity extends AppCompatActivity {
    private int[] resIds = {R.mipmap.flyco_item1, R.mipmap.flyco_item2,
            R.mipmap.flyco_item3, R.mipmap.flyco_item4};
    private ArrayList<Integer> resList;
    private View decorView;
    private SimpleImageBanner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flyco_activity_api);

        resList = new ArrayList<>();
        for (int i = 0; i < resIds.length; i++) {
            resList.add(resIds[i]);
        }

        decorView = getWindow().getDecorView();
        banner = ViewFindUtils.find(decorView, R.id.banner);
        banner.setSource(resList).startScroll();

        indicator(R.id.indicator_circle);
        indicator(R.id.indicator_square);
        indicator(R.id.indicator_round_rectangle);
        indicator(R.id.indicator_circle_stroke);
        indicator(R.id.indicator_square_stroke);
        indicator(R.id.indicator_round_rectangle_stroke);
        indicator(R.id.indicator_circle_snap);

        indicatorAnim();
        indicatorRes();
    }

    private void indicator(int indicatorId) {
        final FlycoPageIndicaor indicator = ViewFindUtils.find(decorView, indicatorId);
        indicator.setViewPager(banner.getViewPager(), resList.size());
    }

    private void indicatorAnim() {
        final FlycoPageIndicaor indicator = ViewFindUtils.find(decorView, R.id.indicator_circle_anim);
        indicator
                .setIsSnap(true)
                .setSelectAnimClass(ZoomInEnter.class)
                .setViewPager(banner.getViewPager(), resList.size());
//        final FlycoPageIndicaor indicator_round_rectangle_anim = ViewFindUtils.find(decorView, R.id.indicator_round_rectangle_anim);
//        indicator_round_rectangle_anim
//                .setIsSnap(true)
//                .setSelectAnimClass(RotateEnter.class)
//                .setUnselectAnimClass(NoAnimExist.class)
//                .setViewPager(banner.getViewPager(), resList.size());
    }

    private void indicatorRes() {
        final FlycoPageIndicaor indicator_res = ViewFindUtils.find(decorView, R.id.indicator_res);
        indicator_res
                .setViewPager(banner.getViewPager(), resList.size());
    }
}
