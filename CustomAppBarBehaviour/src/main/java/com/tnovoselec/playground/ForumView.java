package com.tnovoselec.playground;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumView extends FrameLayout implements ForumAdapter.ForumListener{

  @BindView(R.id.forum_recycler)
  RecyclerView recyclerView;

  @BindView(R.id.forum_details)
  PostDetailsView postDetailsView;

  public ForumView(Context context) {
    super(context);
    init(context);
  }

  public ForumView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public ForumView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context);
  }

  private void init(Context context){
    inflate(context, R.layout.forum_view, this);
    ButterKnife.bind(this, this);
    postDetailsView.setVisibility(GONE);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(new ForumAdapter(this));
    recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
  }

  @Override
  public void onPostSelected() {
    postDetailsView.setVisibility(VISIBLE);
  }
}
