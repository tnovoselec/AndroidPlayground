package com.tnovoselec.playground;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {

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

    public ForumViewHolder(View itemView) {
      super(itemView);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          
        }
      });
    }
  }
}
