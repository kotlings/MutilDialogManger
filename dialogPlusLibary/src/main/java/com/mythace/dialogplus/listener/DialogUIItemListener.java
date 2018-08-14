package com.mythace.dialogplus.listener;

/**
 * Created by Administrator on 2018/8/9 8
 */
public abstract class DialogUIItemListener {

    /**
     * item点击事件
     */
    public abstract void onItemClick(CharSequence text, int position);

    /**
     * 底部点击事件
     */
    public void onBottomBtnClick() {
    }

}
