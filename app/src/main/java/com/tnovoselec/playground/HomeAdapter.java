package com.tnovoselec.playground;


import com.bumptech.glide.Glide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {


  @Override
  public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
    return new HomeViewHolder(view);
  }

  @Override
  public void onBindViewHolder(HomeViewHolder holder, int position) {
  }

  @Override
  public int getItemCount() {
    return 10;
  }

  class HomeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_home_image)
    ImageView imageView;

    public HomeViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      Glide.with(itemView.getContext()).load(
          "http://hdwallpaperbackgrounds.net/wp-content/uploads/2015/10/huge-interior-design-and-white-wall-also-glass-picture-sygnia-09.jpg")
          .into(imageView);
    }
  }
}
