package com.tnovoselec.playground;


import com.bumptech.glide.Glide;
import com.tnovoselec.playground.bridge.ForumBridge.ForumData;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostDetailsView extends FrameLayout {

  @BindView(R.id.post_details_title)
  TextView title;

  @BindView(R.id.post_details_container)
  View container;

  @BindView(R.id.post_details_content_container)
  View contentContainer;

  @BindView(R.id.post_details_additional_content_container)
  View additionalContentContainer;

  @BindView(R.id.animation_helper_view)
  View helperView;

  @BindView(R.id.post_details_additional_content_image)
  ImageView imageView;

  @BindView(R.id.post_details_close)
  View closePostDetailsView;

  private ForumData currentForumData;

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

  private void init(Context context) {
    inflate(context, R.layout.post_details_view, this);
    ButterKnife.bind(this, this);
    Glide.with(context)
        .load("https://st.hzcdn.com/fimgs/43119d07045c50e0_2972-w500-h400-b0-p0--modern-kitchen.jpg")
        .into(imageView);
  }

  @OnClick(R.id.post_details_close)
  void onClosePostDetailsClicked() {
    animateOut();
  }

  public void show(ForumData forumData) {
    this.currentForumData = forumData;
    getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
      @Override
      public boolean onPreDraw() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        animateIn();
        return true;
      }
    });
    setVisibility(VISIBLE);
  }

  private void animateIn() {
    contentContainer.setTranslationY(0);
    int[] titlePosition = new int[2];
    title.getLocationOnScreen(titlePosition);
    int topDiff = titlePosition[1] - currentForumData.titleTop;

    helperView.getLayoutParams().height = contentContainer.getMeasuredHeight() - topDiff;
    helperView.requestLayout();

    float elevation = getResources().getDimension(R.dimen.post_details_content_elevation);

    ValueAnimator elevationAnimator = ValueAnimator.ofFloat(1, elevation);
    elevationAnimator.addUpdateListener(valueAnimator -> {
      float elevation1 = valueAnimator.getAnimatedFraction();
      contentContainer.setElevation(elevation1);
    });

    closePostDetailsView.setAlpha(0);
    contentContainer.setElevation(1);
    contentContainer.bringToFront();
    contentContainer.setTranslationY(-topDiff);

    elevationAnimator.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        contentContainer.setElevation(elevation);
      }
    });

    container.setAlpha(0);
    container.animate().alpha(1).withEndAction(() -> {

      elevationAnimator.start();

      closePostDetailsView.animate().alpha(1);
      contentContainer.animate()
          .translationY(0)
          .setDuration(500)
          .setInterpolator(new AccelerateDecelerateInterpolator())
          .withStartAction(PostDetailsView.this::animateAdditionalContentContainerIn);
      helperView.setTranslationY(0);
      helperView.animate()
          .translationY(-helperView.getLayoutParams().height)
          .setInterpolator(new AccelerateDecelerateInterpolator())
          .setDuration(500);
    });

  }

  private void animateAdditionalContentContainerIn() {
    additionalContentContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
      @Override
      public boolean onPreDraw() {
        additionalContentContainer.getViewTreeObserver().removeOnPreDrawListener(this);
        additionalContentContainer.setTranslationY(-additionalContentContainer.getMeasuredHeight());
        additionalContentContainer.setAlpha(0);
        additionalContentContainer.animate()
            .translationY(0)
            .alpha(1)
            .setDuration(500)
            .setInterpolator(new DecelerateInterpolator());
        return true;
      }
    });
    additionalContentContainer.setVisibility(VISIBLE);
  }

  public void animateOut() {
    int[] titlePosition = new int[2];
    title.getLocationOnScreen(titlePosition);
    int topDiff = titlePosition[1] - currentForumData.titleTop;

    helperView.getLayoutParams().height = Math.abs(topDiff);
    helperView.setTranslationY(-helperView.getLayoutParams().height);
    helperView.requestLayout();

    float elevation = contentContainer.getElevation();
    ValueAnimator elevationAnimator = ValueAnimator.ofFloat(elevation, 0);
    elevationAnimator.addUpdateListener(valueAnimator -> {
      float elevation1 = valueAnimator.getAnimatedFraction();
      contentContainer.setElevation(elevation1);
    });

    elevationAnimator.start();
    closePostDetailsView.animate().alpha(0);
    contentContainer.bringToFront();
    contentContainer.animate()
        .translationY(-topDiff)
        .setInterpolator(new DecelerateInterpolator())
        .withStartAction(this::animateAdditionalContentContainerOut);
    helperView.animate()
        .translationY(0)
        .setInterpolator(new DecelerateInterpolator());

  }


  private void animateAdditionalContentContainerOut() {
    additionalContentContainer.animate()
        .translationY(-additionalContentContainer.getHeight())
        .alpha(0)
        .setDuration(500)
        .setInterpolator(new AccelerateInterpolator())
        .withEndAction(() -> container.animate().alpha(0).withEndAction(() -> setVisibility(GONE)));
  }
}
