package com.tnovoselec.playground;


import com.bumptech.glide.Glide;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeDetailsView extends FrameLayout {

  @BindView(R.id.home_details_comment_recycler)
  RecyclerView recyclerView;

  @BindView(R.id.home_details_image)
  ImageView imageView;

  @BindView(R.id.home_details_close)
  View closeHomeDetails;

  public HomeDetailsView(Context context) {
    super(context);
    init(context);
  }

  public HomeDetailsView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public HomeDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    inflate(context, R.layout.home_details_view, this);
    ButterKnife.bind(this, this);
    recyclerView.setLayoutManager(new LinearLayoutManager(context));
    recyclerView.setAdapter(new CommentAdapter());
    recyclerView.setNestedScrollingEnabled(false);
    Glide.with(context)
        .load(
            "http://hdwallpaperbackgrounds.net/wp-content/uploads/2015/10/huge-interior-design-and-white-wall-also-glass-picture-sygnia-09.jpg")
        .into(imageView);
  }

  @OnClick(R.id.home_details_close)
  void onCloseHomeDetailsClicked(){
    setVisibility(GONE);
  }

  public void show(){

  }
}
