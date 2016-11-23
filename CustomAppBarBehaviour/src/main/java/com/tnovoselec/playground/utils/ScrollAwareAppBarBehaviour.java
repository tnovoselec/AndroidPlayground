package com.tnovoselec.playground.utils;


import com.tnovoselec.playground.R;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class ScrollAwareAppBarBehaviour extends AppBarLayout.Behavior {

  public ScrollAwareAppBarBehaviour(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View directTargetChild,
      View target, int nestedScrollAxes) {
    return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy,
      int[] consumed) {
    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
    int height = child.getChildAt(0).getHeight();
    int appbarMargin = child.getResources().getDimensionPixelSize(R.dimen.appbar_margin);
    float x = -child.getY() / height;

    int margin = appbarMargin - (int) (appbarMargin * x);
    params.setMargins(margin, margin, margin, params.bottomMargin);
    child.requestLayout();
  }
}
