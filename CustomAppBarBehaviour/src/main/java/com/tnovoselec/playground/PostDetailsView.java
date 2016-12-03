package com.tnovoselec.playground;


import com.bumptech.glide.Glide;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailsView extends FrameLayout {

  @BindView(R.id.post_details_additional_content_image)
  ImageView imageView;

  public PostDetailsView(Context context) {
    super(context);
    init(context);
  }

  public PostDetailsView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public PostDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context){
    inflate(context, R.layout.post_details_view, this);
    ButterKnife.bind(this, this);
    Glide.with(context)
        .load("https://st.hzcdn.com/fimgs/43119d07045c50e0_2972-w500-h400-b0-p0--modern-kitchen.jpg")
        .into(imageView);
  }
}
