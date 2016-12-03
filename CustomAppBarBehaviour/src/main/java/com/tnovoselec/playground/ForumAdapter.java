package com.tnovoselec.playground;


import com.tnovoselec.playground.bridge.ForumBridge.ForumData;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {

  public interface ForumListener {

    void onPostSelected(ForumData forumData);
  }

  private final ForumListener forumListener;

  public ForumAdapter(ForumListener forumListener) {
    this.forumListener = forumListener;
  }

  @Override
  public ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forum, parent, false);
    return new ForumViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ForumViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 10;
  }

  class ForumViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_forum_title)
    TextView forumTitle;

    ForumViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);


      itemView.setOnClickListener(view -> {
        int[] titlePosition = new int[2];
        forumTitle.getLocationOnScreen(titlePosition);

        ForumData forumData = new ForumData(titlePosition[0], titlePosition[1]);
        forumListener.onPostSelected(forumData);
      });
    }
  }
}
