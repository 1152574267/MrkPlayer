package com.mrk.mrkplayer.easyijkplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mrk.mrkplayer.R;
import com.mrk.mrkplayer.easyijkplayer.bean.VideoijkBean;

import java.util.List;

public class StreamSelectAdapter extends BaseAdapter {
    // 上下文
    private Context mContext;
    // 不同分辨率播放地址集合
    private List<VideoijkBean> listVideos;

    public StreamSelectAdapter(Context context, List<VideoijkBean> listVideos) {
        this.mContext = context;
        this.listVideos = listVideos;
    }

    public int getCount() {
        return listVideos == null ? 0 : listVideos.size();
    }

    public VideoijkBean getItem(int position) {
        return listVideos == null ? null : listVideos.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.simple_player_list_item, null);
            holder = new ViewHolder();
            holder.streamName = (TextView) convertView.findViewById(R.id.simple_player_stream_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        VideoijkBean videoijkBean = getItem(position);
        String streamName = videoijkBean.getStream();
        holder.streamName.setText(streamName);
        if (videoijkBean.isSelect()) {
            holder.streamName.setTextColor(mContext.getResources().getColor(R.color.simple_player_stream_name_playing));
        } else {
            holder.streamName.setTextColor(mContext.getResources().getColor(R.color.simple_player_stream_name_normal));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView streamName;
    }

}
