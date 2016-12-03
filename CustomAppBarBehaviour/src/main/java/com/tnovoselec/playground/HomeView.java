package com.tnovoselec.playground;


import com.tnovoselec.playground.bridge.HomeBridge;
import com.tnovoselec.playground.bridge.HomeBridge.HomeData;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeView extends FrameLayout implements HomeAdapter.HomeListener {

  @BindView(R.id.home_view_recycler)
  RecyclerView recyclerView;

  @BindView(R.id.home_details_view)
  HomeDetailsView homeDetailsView;

  private final HomeBridge homeBridge = HomeBridge.INSTANCE;;

  public HomeView(Context context) {
    super(context);
    init(context);
  }

  public HomeView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public HomeView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context);
  }

  private void init(Context context) {
    inflate(context, R.layout.home_view, this);
    ButterKnife.bind(this, this);
    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(new HomeAdapter(this));
    recyclerView.addOnScrollListener(new ScrollListener());
    recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
      @Override
      public void onChildViewAttachedToWindow(View view) {

      }

      @Override
      public void onChildViewDetachedFromWindow(View view) {
        view.setScaleY(1);
        view.setScaleX(1);
      }
    });
  }

  @Override
  public void onHomeClicked(HomeData homeData) {
    homeBridge.next(homeData);
    showHomeDetails(homeData);
  }

  private void showHomeDetails(HomeData homeData){
    homeDetailsView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
      @Override
      public boolean onPreDraw() {
        homeDetailsView.getViewTreeObserver().removeOnPreDrawListener(this);

        int[] homeDetailsPosition = new int[2];
        homeDetailsView.getLocationOnScreen(homeDetailsPosition);

        int topDiff = homeDetailsPosition[1] - homeData.homeItemTop;

        homeDetailsView.setTranslationY(-topDiff);
        homeDetailsView.animate().translationY(0);
        return true;
      }
    });
    homeDetailsView.setVisibility(VISIBLE);
    homeDetailsView.show();
  }

  private class ScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);

      View firstChild = recyclerView.getChildAt(0);

      float childY = firstChild.getY();
      float scaleFactor = (float) Math.max(0.85, 1 + childY / firstChild.getHeight() / 5);

      firstChild.setPivotY(firstChild.getHeight());

      firstChild.setScaleX(scaleFactor);
      firstChild.setScaleY(scaleFactor);
    }
  }

}
