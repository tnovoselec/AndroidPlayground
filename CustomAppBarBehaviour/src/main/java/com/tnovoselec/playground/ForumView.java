package com.tnovoselec.playground;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class ForumView extends RecyclerView {

  public ForumView(Context context) {
    super(context);
    init();
  }

  public ForumView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public ForumView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init(){
    setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    setAdapter(new ForumAdapter());
    addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
  }
}
