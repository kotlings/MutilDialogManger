package com.mythace.dialogplus.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import com.mythace.dialogplus.listener.OnItemClickListener;


/**
 * Created by Administrator on 2018/8/9 8
 */
public abstract class SuperItemHolder<T> extends ViewHolder implements View.OnClickListener {

    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 加载得到的数据
     */
    public T mDatas;
    public int itemPositionType;
    /**
     * 只有当该holder作为item使用，并且使用带参构造函数实例化position才有意义，使用无参构造函数则position没有意义
     */
    protected int position;
    private OnItemClickListener mlistener;

    public SuperItemHolder(Context mContext, OnItemClickListener listener, View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mContext = mContext;
        this.mlistener = listener;
    }

    /**
     * 设置数据
     */
    public void setData(T data,int itemPositionType) {
        this.mDatas = data;
        this.itemPositionType = itemPositionType;
        refreshView();
    }

    /**
     * 刷新数据
     */
    public abstract void refreshView();

    /**
     * 获得数据
     */
    public T getData() {
        return mDatas;
    }

    /**
     * 当复用holder的时候，需要调用该方法来同步holder对应的索引位置
     */
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        if (mlistener != null) {
            mlistener.onItemClick(position);
        }
    }
}