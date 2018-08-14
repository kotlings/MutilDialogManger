package com.mythace.dialogplus.utils;

import android.util.Log;

import com.mythace.dialogplus.bean.BuildBean;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 类描述:多弹窗显示管理
 * 只需将DialogBase,Push进队列中即可
 * DialogManager.getInstance().pushToQueue(BuildBean)
 * Created by zxj on 2018/8/10.
 */
public class DialogManager {

    private static final String TAG = "DialogManager";
    private static Queue<BuildBean> queue = new ConcurrentLinkedQueue<>(); //弹窗队列(线程安全)
    private static DialogManager mInstance;
    private BuildBean mDialogBase;

    public static DialogManager getInstance() {
        if (mInstance == null) {
            synchronized (DialogManager.class) {
                if (mInstance == null) {
                    mInstance = new DialogManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始为0,pushToQueue 基数必然为1,所以小于2即可
     *
     * @return
     */
    public boolean canShow() {
        return queue.size() < 2/* && !isLock*/;
    }


    /**
     * 每次弹窗调用PushQueue方法
     *
     * @param dialogBase
     */
    public void pushToQueue(BuildBean dialogBase) {
        //添加到队列中
        if (dialogBase != null) {
            dialogBase.setDissMissListener(() -> {
                        Log.e(TAG, "nextTask");
                        nextTask();
                    }
            );
            Log.e(TAG, "add..");
            queue.add(dialogBase);
            //只有当前队列数量为1时才能进行下一步操作
            if (canShow()) {
                startNextIf();
            }
        }
    }


    /**
     * 显示下一个弹窗任务
     */
    private void startNextIf() {
        if (queue != null && queue.isEmpty()) {
            return;
        }
        //Todo 可在此处对弹窗进行排序
        oderDialog();
        mDialogBase = queue.element();
        if (mDialogBase != null) {
            mDialogBase.show();
        } else {
            Log.e(TAG, "任务队列为空...");
        }
    }

    private void oderDialog() {


    }


    /**
     * 清除对象
     */
    public void clear() {
        queue.clear();
        mDialogBase.dissmiss();
        mDialogBase = null;
    }

    /**
     * 移除队列的头,获取最新队列头
     */
    private void removeTopTask() {
        queue.poll(); //出栈
    }

    /**
     * 提供外部下一个任务的方法,在弹窗消失时候调用
     */
    private void nextTask() {
        removeTopTask();
        startNextIf();
    }


}
