package com.tnovoselec.playground;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.viewpager)
  ViewPager viewPager;

  @BindView(R.id.tabs)
  TabLayout tabLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    viewPager.setAdapter(new MainAdapter());
    tabLayout.setupWithViewPager(viewPager);

  }
}
