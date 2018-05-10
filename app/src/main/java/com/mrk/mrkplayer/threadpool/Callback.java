package com.mrk.mrkplayer.threadpool;

import java.io.IOException;
import java.util.List;

public interface Callback<T> {
    void onFailure(Call call, IOException e);

    void onResponse(Call call, List<T> response) throws IOException;
}
