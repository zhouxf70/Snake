package com.example.zhouxf.snake;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;

    private int[] mData;

    public MyAdapter(Context mContext, int[] data) {
        this.mContext = mContext;
        this.mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("----", position + "," + mData[position]);
        switch (mData[position]) {
            case -1:
                holder.imageView.setBackgroundColor(Color.GREEN);
                break;
            case 1:
                holder.imageView.setBackgroundColor(Color.RED);
                break;
            default:
                holder.imageView.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img);
        }
    }
}
