package com.mythace.dialogmanger;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.mythace.dialogplus.DialogUIUtils;
import com.mythace.dialogplus.listener.DialogUIDateTimeSaveListener;
import com.mythace.dialogplus.listener.DialogUIListener;
import com.mythace.dialogplus.utils.DialogManager;
import com.mythace.dialogplus.widget.DateSelectorWheelView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    MyHandler myHandler = new MyHandler(this);


    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //先是有升级提醒弹窗，活动提示弹窗，vip到期提示弹窗

        myHandler.postDelayed(() -> {
            DialogManager.getInstance().pushToQueue(DialogUIUtils.showAlert(this, "", "发现新版本，是否更新", "", "",
                    "确定", "", true, true, true, new DialogUIListener() {
                        @Override
                        public void onPositive() {

                        }

                        @Override
                        public void onNegative() {

                        }
                    }));

            DialogManager.getInstance().pushToQueue(DialogUIUtils.showAlert(this, "", "会员买1送1，快快了解一下吧", "", "",
                    "去开通", "", true, true, true, new DialogUIListener() {
                        @Override
                        public void onPositive() {


                        }

                        @Override
                        public void onNegative() {

                        }
                    }));

            DialogManager.getInstance().pushToQueue(DialogUIUtils.showDatePick(this, Gravity.BOTTOM, "添加新的记事",
                    System.currentTimeMillis() + 60000, DateSelectorWheelView.TYPE_YYYYMMDDHHMMSS, 0, new DialogUIDateTimeSaveListener() {
                        @Override
                        public void onSaveSelectedDate(int tag, String selectedDate) {

                        }
                    }));

        }, 6000);


    }

    public static class MyHandler extends Handler {

        WeakReference<MainActivity> weakReference;

        public MyHandler(MainActivity mainActivity) {
            weakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (null == weakReference || null == weakReference.get()) {
                return;
            }
            weakReference.get().handleMessage(msg);

        }
    }
}
