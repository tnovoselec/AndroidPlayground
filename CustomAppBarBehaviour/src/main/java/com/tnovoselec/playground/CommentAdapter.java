package com.tnovoselec.playground;


import com.bumptech.glide.Glide;
import com.tnovoselec.playground.utils.CircleTransform;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.HomeViewHolder> {


  @Override
  public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
    return new HomeViewHolder(view);
  }

  @Override
  public void onBindViewHolder(HomeViewHolder holder, int position) {
    Context context = holder.itemView.getContext();
    Glide.with(context)
        .load("http://www.ikea.com/ms/en_US/media/visual_nav_images/seo_image/living_room__Living_room_sofas_vn_520x250.gif")
        .into(holder.itemHomeImage);
    Glide.with(context)
        .load("http://www.faceaface-paris.com/wp-content/uploads/2015/07/carre_homme.jpg")
        .transform(new CircleTransform(context))
        .into(holder.itemHomeUserIcon);
    holder.itemHomeUserName.setText("Pero Peric");
    holder.itemHomeDescription.setText("Jako lijepa i funkcionalna dnvvna soba. Party ready. Ne podnosi mačke najbolje.Jako lijepa i funkcionalna dnvvna soba. Party ready. Ne podnosi mačke najbolje");
  }

  @Override
  public int getItemCount() {
    return 10;
  }

  class HomeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_comment_image)
    ImageView itemHomeImage;

    @BindView(R.id.item_comment_description)
    TextView itemHomeDescription;

    @BindView(R.id.item_comment_user_icon)
    ImageView itemHomeUserIcon;

    @BindView(R.id.item_comment_user_name)
    TextView itemHomeUserName;

    public HomeViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

  }
}
