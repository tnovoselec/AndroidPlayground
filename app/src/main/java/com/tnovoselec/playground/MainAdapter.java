package com.tnovoselec.playground;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MainAdapter extends PagerAdapter {

  @Override
  public int getCount() {
    return 2;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return position == 1 ? "FORUM" : "HOME";
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    View view = position == 1 ? new ForumView(container.getContext()) : new HomeView(container.getContext());
    container.addView(view);
    return view;
  }
}
