package com.mrk.mrkplayer.threadpool;

import java.util.List;

public interface Callback<T> {
    void onFailure(Call call, Exception e);

    void onResponse(Call call, List<T> response);
}
