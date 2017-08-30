package com.example.viet.wallpaperapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.viet.wallpaperapp.R;
import com.example.viet.wallpaperapp.model.Image;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by viet on 30/08/2017.
 */

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Image> mArrImage;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public ImageRecyclerViewAdapter(ArrayList<Image> arrImage) {
        this.mArrImage = arrImage;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_recycler_view, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageViewHolder viewHolder = (ImageViewHolder) holder;
        Image image = mArrImage.get(position);
        viewHolder.setData(image);
    }

    @Override
    public int getItemCount() {
        return mArrImage.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.tvId)
        TextView tvId;
        @BindView(R.id.tvCatId)
        TextView tvCatId;
        @BindView(R.id.tvTotalViews)
        TextView tvTotalViews;
        @BindView(R.id.tvCategoryName)
        TextView tvCategoryName;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setData(Image image) {
            Glide.with(mContext)
                    .load(image.getWallpagerImageThumb())
                    .placeholder(android.R.drawable.screen_background_light)
                    .error(android.R.drawable.screen_background_dark)
                    .centerCrop()
                    .into(ivImage);
            tvId.setText("Id : " + image.getId());
            tvCatId.setText("Cat id : " + image.getCatId());
            tvCategoryName.setText("Cat name : " + image.getCategoryName());
            tvTotalViews.setText("Total views : " + image.getTotalViews());
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(view, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if(mOnItemLongClickListener!=null){
                mOnItemLongClickListener.onLongClick(view,getPosition());
            }
            return false;
        }
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onLongClick(View view, int position);
    }
}
