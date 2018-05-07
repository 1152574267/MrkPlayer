package com.mrk.mrkplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrk.mrkplayer.R;
import com.mrk.mrkplayer.bean.TabItem;

import java.util.List;

public class XRecyclerViewAdapter extends RecyclerView.Adapter<XRecyclerViewAdapter.ViewHolder> {
    private Context mContext;

    private List<TabItem> mDataList;

    public XRecyclerViewAdapter(Context context, List<TabItem> dataList) {
        super();

        mContext = context;
        mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mDataList.get(position).getTv());
        holder.img.setImageResource(mDataList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}