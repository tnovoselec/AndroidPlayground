package com.tnovoselec.playground;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class HomeView extends RecyclerView {

  public HomeView(Context context) {
    super(context);
    init();
  }

  public HomeView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public HomeView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    setAdapter(new HomeAdapter());
    addOnScrollListener(new ScrollListener());
  }

  private class ScrollListener extends OnScrollListener {

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);

      View firstChild = getChildAt(0);

      float childY = firstChild.getY();
      float scaleFactor = (float) Math.max(0.85, 1 + childY / firstChild.getHeight() / 5);

      firstChild.setPivotY(firstChild.getHeight());

      firstChild.setScaleX(scaleFactor);
      firstChild.setScaleY(scaleFactor);
    }
  }

  @Override
  public void onChildDetachedFromWindow(View child) {
    super.onChildDetachedFromWindow(child);
    child.setScaleY(1);
    child.setScaleX(1);
  }
}
