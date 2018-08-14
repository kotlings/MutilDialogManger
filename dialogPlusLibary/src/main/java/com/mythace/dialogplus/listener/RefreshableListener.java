package com.mythace.dialogplus.listener;

import java.util.List;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public interface RefreshableListener {
    void refresh(List newData);

    void addAll(List newData);

    void clear();

    void delete(int position);

    void add(Object object);
}
