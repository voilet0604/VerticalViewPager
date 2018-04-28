package cn.lei.verticalviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.lei.vertical_viewpager.ExtendViewPager;

public class VerticalActivity01 extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vertical_01);

        ExtendViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new SimpleAdapter(getSupportFragmentManager(), getFragment()));
    }

    private List<Fragment> getFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
        fragments.add(new Fragment05());

        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
        fragments.add(new Fragment05());
        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
        fragments.add(new Fragment05());

        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
        fragments.add(new Fragment05());
        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
        fragments.add(new Fragment05());
        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
        fragments.add(new Fragment05());
        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
        fragments.add(new Fragment05());

        return fragments;
    }
}
