package com.mythace.dialogplus;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dou361.dialogui.R;
import com.mythace.dialogplus.bean.BuildBean;
import com.mythace.dialogplus.bean.TieBean;
import com.mythace.dialogplus.listener.DialogAssigner;
import com.mythace.dialogplus.listener.DialogUIDateTimeSaveListener;
import com.mythace.dialogplus.listener.DialogUIItemListener;
import com.mythace.dialogplus.listener.DialogUIListener;
import com.mythace.dialogplus.listener.TdataListener;
import com.mythace.dialogplus.utils.ToolUtils;
import com.mythace.dialogplus.widget.PopuWindowView;

import java.util.List;

/**
 * Created by Administrator on 2018/8/9 8
 */
public class DialogUIUtils {


    private static Handler handler = new Handler(Looper.getMainLooper());

    private static void postUI(Runnable runnable) {
        handler.post(runnable);
    }


    /**
     * 全局上下文
     */
    public static Context appContext;

    /**
     * 如果有使用到showTost...相关的方法使用之前需要初始化该方法
     */
    public static void init(Context appContext) {
        DialogUIUtils.appContext = appContext;
    }

    /**
     * 关闭弹出框
     */
    public static void dismiss(DialogInterface... dialogs) {
        if (dialogs != null && dialogs.length > 0) {
            for (DialogInterface dialog : dialogs) {
                if (dialog instanceof Dialog) {
                    Dialog dialog1 = (Dialog) dialog;
                    if (dialog1.isShowing()) {
                        dialog1.dismiss();
                    }
                } else if (dialog instanceof AppCompatDialog) {
                    AppCompatDialog dialog2 = (AppCompatDialog) dialog;
                    if (dialog2.isShowing()) {
                        dialog2.dismiss();
                    }
                }
            }

        }
    }

    /**
     * 关闭弹出框
     */
    public static void dismiss(BuildBean buildBean) {
        if (buildBean != null) {
            if (buildBean.dialog != null && buildBean.dialog.isShowing()) {
                buildBean.dialog.dismiss();
            }
            if (buildBean.alertDialog != null && buildBean.alertDialog.isShowing()) {
                buildBean.alertDialog.dismiss();
            }
        }
    }

    /**
     * 关闭弹出框
     */
    public static void dismiss(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /***
     * 弹出日期选择器
     *
     * @param context   上下文
     * @param gravity   显示位置
     * @param dateTitle 显示标题
     * @param date      当前选择日志
     * @param dateType  显示日期样式DateSelectorWheelView.TYPE_YYYYMMDD TYPE_YYYYMMDDHHMM TYPE_YYYYMMDDHHMMSS
     * @param tag       view标记tag 一个页面多个日期选择器是可以加标记区分
     * @param listener
     * @return
     */
    public static BuildBean showDatePick(Context context, int gravity, String dateTitle, long date, int dateType, int tag, DialogUIDateTimeSaveListener listener) {
        return DialogAssigner.getInstance().assignDatePick(context, gravity, dateTitle, date, dateType, tag, listener);
    }

    /**
     * 加载框
     *
     * @param context          上下文
     * @param msg              提示文本
     * @param isVertical       true为竖直方向false为水平方向
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param isWhiteBg        true为白色背景false为灰色背景
     */
    public static BuildBean showLoading(Context context, CharSequence msg, boolean isVertical, boolean cancleable, boolean outsideTouchable, boolean isWhiteBg) {
        return DialogAssigner.getInstance().assignLoading(context, msg, isVertical, cancleable, outsideTouchable, isWhiteBg);
    }

    /**
     * md风格加载框
     *
     * @param context          上下文
     * @param msg              提示文本
     * @param isVertical       true为竖直方向false为水平方向
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param isWhiteBg        true为白色背景false为灰色背景
     */
    public static BuildBean showMdLoading(Context context, CharSequence msg, boolean isVertical, boolean cancleable, boolean outsideTouchable, boolean isWhiteBg) {
        return DialogAssigner.getInstance().assignMdLoading(context, msg, isVertical, cancleable, outsideTouchable, isWhiteBg);
    }

    /***
     * md风格弹出框 默认可取消可点击
     *
     * @param activity 所在activity
     * @param title    标题 不传则无标题
     * @param msg      消息
     * @param listener 事件监听
     * @return
     */
    public static BuildBean showMdAlert(Activity activity, CharSequence title, CharSequence msg, DialogUIListener listener) {
        return showMdAlert(activity, title, msg, true, true, listener);
    }

    /***
     * md风格弹出框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param msg              消息
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdAlert(Activity activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener) {
        return DialogAssigner.getInstance().assignMdAlert(activity, title, msg, cancleable, outsideTouchable, listener);
    }

    /**
     * md风格多选框  默认可取消可点击
     *
     * @param activity     所在activity
     * @param title        标题 不传则无标题
     * @param words        消息数组
     * @param checkedItems 默认选中项
     * @param listener     事件监听
     */
    public static BuildBean showMdMultiChoose(Activity activity, CharSequence title, CharSequence[] words, boolean[] checkedItems, DialogUIListener listener) {
        return showMdMultiChoose(activity, title, words, checkedItems, true, true, listener);
    }

    /***
     * md风格多选框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param words            消息数组
     * @param checkedItems     默认选中项
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdMultiChoose(Activity activity, CharSequence title, CharSequence[] words, boolean[] checkedItems, boolean cancleable, boolean outsideTouchable, DialogUIListener listener) {
        return DialogAssigner.getInstance().assignMdMultiChoose(activity, title, words, checkedItems, cancleable, outsideTouchable, listener);
    }

    /**
     * 单选框  默认可取消可点击
     *
     * @param activity      所在activity
     * @param title         标题 不传则无标题
     * @param defaultChosen 默认选中项
     * @param words         消息数组
     * @param listener      事件监听
     */
    public static BuildBean showSingleChoose(Activity activity, CharSequence title, int defaultChosen, CharSequence[] words, DialogUIItemListener listener) {
        return showSingleChoose(activity, title, defaultChosen, words, true, true, listener);
    }

    /**
     * 单选框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param defaultChosen    默认选中项
     * @param words            消息数组
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     */
    public static BuildBean showSingleChoose(Activity activity, CharSequence title, int defaultChosen, CharSequence[] words, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener) {
        return DialogAssigner.getInstance().assignSingleChoose(activity, title, defaultChosen, words, cancleable, outsideTouchable, listener);
    }

    /**
     * 提示弹出框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     */
    public static BuildBean showAlert(Activity activity, CharSequence title, CharSequence msg, CharSequence hint1, CharSequence hint2,
                                      CharSequence firstTxt, CharSequence secondTxt, boolean isVertical, boolean cancleable, boolean outsideTouchable, DialogUIListener listener) {
        return DialogAssigner.getInstance().assignAlert(activity, title, msg, hint1, hint2, firstTxt, secondTxt, isVertical, cancleable, outsideTouchable, listener);
    }


    /***
     * 中间弹出列表
     *
     * @param context          上下文
     * @param datas            素组集合
     * @param gravity
     * @param bottomTxt        底部item文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showSheet(Context context, List<TieBean> datas, CharSequence bottomTxt, int gravity, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener) {
        return DialogAssigner.getInstance().assignSheet(context, datas, bottomTxt, gravity, cancleable, outsideTouchable, listener);
    }

    /**
     * md风格竖向底部弹出列表 默认可取消可点击
     *
     * @param context  上下文
     * @param title    标题
     * @param datas    集合需要BottomSheetBean对象
     * @param listener 事件监听
     * @return
     */
    public static BuildBean showMdBottomSheet(Context context, boolean isVertical, CharSequence title, List<TieBean> datas, int columnsNum, DialogUIItemListener listener) {
        return showMdBottomSheet(context, isVertical, title, datas, columnsNum, true, true, listener);
    }

    /***
     * md风格弹出列表
     *
     * @param context          上下文
     * @param title            标题
     * @param datas            集合需要BottomSheetBean对象
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdBottomSheet(Context context, boolean isVertical, CharSequence title, List<TieBean> datas, int columnsNum, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener) {
        return DialogAssigner.getInstance().assignMdBottomSheet(context, isVertical, title, datas, columnsNum, cancleable, outsideTouchable, listener);
    }

    /**
     * 自定义弹出框 默认居中可取消可点击
     *
     * @param context     上下问
     * @param contentView 自定义view
     * @return
     */
    public static BuildBean showCustomAlert(Context context, View contentView) {
        return showCustomAlert(context, contentView, Gravity.CENTER, true, true);
    }

    /**
     * 自定义弹出框 默认可取消可点击
     *
     * @param context     上下文
     * @param contentView 自定义view
     * @param gravity     显示window的位置例如Gravity.center
     * @return
     */
    public static BuildBean showCustomAlert(Context context, View contentView, int gravity) {
        return showCustomAlert(context, contentView, gravity, true, true);
    }

    /***
     * 自定义弹出框
     *
     * @param context          上下文
     * @param contentView      自定义view
     * @param gravity          显示window的位置例如Gravity.center
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @return
     */
    public static BuildBean showCustomAlert(Context context, View contentView, int gravity, boolean cancleable, boolean outsideTouchable) {
        return DialogAssigner.getInstance().assignCustomAlert(context, contentView, gravity, cancleable, outsideTouchable);
    }

    /**
     * 自定义底部弹出框 默认居中可取消可点击
     *
     * @param context     上下问
     * @param contentView 自定义view
     * @return
     */
    public static BuildBean showCustomBottomAlert(Context context, View contentView) {
        return showCustomBottomAlert(context, contentView, true, true);
    }

    /***
     * 自定义底部弹出框
     *
     * @param context          上下文
     * @param contentView      自定义view
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @return
     */
    public static BuildBean showCustomBottomAlert(Context context, View contentView, boolean cancleable, boolean outsideTouchable) {
        return DialogAssigner.getInstance().assignCustomBottomAlert(context, contentView, cancleable, outsideTouchable);
    }


    /**
     * 短时间中下位置显示。线程安全，可以在非UI线程调用。
     */
    public static void showToast(final int resId) {
        showToast(ToolUtils.getString(appContext, resId));
    }

    /**
     * 短时间中下位置显示。
     */
    public static void showToast(final String str) {
        showToast(str, Toast.LENGTH_SHORT, Gravity.BOTTOM);
    }

    /**
     * 长时间中下位置显示。
     */
    public static void showToastLong(final int resId) {
        showToastLong(ToolUtils.getString(appContext, resId));
    }

    /**
     * 长时间中下位置显示。
     */
    public static void showToastLong(final String str) {
        showToast(str, Toast.LENGTH_LONG, Gravity.BOTTOM);
    }


    /**
     * 短时间居中位置显示。
     */
    public static void showToastCenter(final int resId) {
        showToastCenter(ToolUtils.getString(appContext, resId));
    }

    /**
     * 短时间居中位置显示。
     */
    public static void showToastCenter(final String str) {
        showToast(str, Toast.LENGTH_SHORT, Gravity.CENTER);
    }

    /**
     * 长时间居中位置显示。
     */
    public static void showToastCenterLong(final int resId) {
        showToastCenterLong(ToolUtils.getString(appContext, resId));
    }

    /**
     * 长时间居中位置显示。
     */
    public static void showToastCenterLong(final String str) {
        showToast(str, Toast.LENGTH_LONG, Gravity.CENTER);
    }

    /**
     * 短时间居中位置显示。
     */
    public static void showToastTop(final int resId) {
        showToastTop(ToolUtils.getString(appContext, resId));
    }

    /**
     * 短时间居中位置显示。
     */
    public static void showToastTop(final String str) {
        showToast(str, Toast.LENGTH_SHORT, Gravity.TOP);
    }

    /**
     * 长时间居中位置显示。
     */
    public static void showToastTopLong(final int resId) {
        showToastTopLong(ToolUtils.getString(appContext, resId));
    }

    /**
     * 长时间居中位置显示。
     */
    public static void showToastTopLong(final String str) {
        showToast(str, Toast.LENGTH_LONG, Gravity.TOP);
    }

    /**
     * 只定义一个Toast
     */
    private static Toast mToast;
    private static Toast mToastTop;
    private static Toast mToastCenter;
    private static Toast mToastBottom;

    /**
     * 对toast的简易封装。线程不安全，不可以在非UI线程调用。
     */
    private static void showToast(String str, int showTime, int gravity) {
        if (appContext == null) {
            throw new RuntimeException("DialogUIUtils not initialized!");
        }
        if (gravity == Gravity.TOP) {
            if (mToastTop == null) {
                mToastTop = Toast.makeText(appContext, str, showTime);
                LayoutInflater inflate = (LayoutInflater)
                        appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(R.layout.dialogui_toast, null);
                mToastTop.setView(view);
                mToastTop.setGravity(gravity, 0, appContext.getResources().getDimensionPixelSize(R.dimen.dialogui_toast_margin));
            }
            mToast = mToastTop;
            mToast.setText(str);
            mToast.show();
        } else if (gravity == Gravity.CENTER) {
            if (mToastCenter == null) {
                mToastCenter = Toast.makeText(appContext, str, showTime);
                LayoutInflater inflate = (LayoutInflater)
                        appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(R.layout.dialogui_toast, null);
                mToastCenter.setView(view);
                mToastCenter.setGravity(gravity, 0, 0);
            }
            mToast = mToastCenter;
            mToast.setText(str);
            mToast.show();
        } else if (gravity == Gravity.BOTTOM) {
            if (mToastBottom == null) {
                mToastBottom = Toast.makeText(appContext, str, showTime);
                LayoutInflater inflate = (LayoutInflater)
                        appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflate.inflate(R.layout.dialogui_toast, null);
                mToastBottom.setView(view);
                mToastBottom.setGravity(gravity, 0, appContext.getResources().getDimensionPixelSize(R.dimen.dialogui_toast_margin));
            }
            mToast = mToastBottom;
            mToast.setText(str);
            mToast.show();
        }

    }

    /**
     * 只定义一个mBuildBean
     */
    private static BuildBean mBuildBean;

    /**
     * 对mBuildBean的简易封装。线程不安全，不可以在非UI线程调用。
     */
    public static void showTie(Context context) {
        showTie(context, "加载中...");
    }

    /**
     * 对mBuildBean的简易封装。线程不安全，不可以在非UI线程调用。
     */
    public static void showTie(Context context, String str) {
        if (mBuildBean != null) {
            dismiss(mBuildBean);
        }
        mBuildBean = showLoading(context, str, false, true, false, true);
        mBuildBean.show();
    }

    /**
     * 对mBuildBean的简易封装。线程不安全，不可以在非UI线程调用。
     */
    public static void dismssTie() {
        dismiss(mBuildBean);
    }

    public static void showPopuWindow(Context context, int widthGravity, int maxLine, View view, TdataListener tdataListener) {
        PopuWindowView popuWindowView = new PopuWindowView(context, widthGravity);
        popuWindowView.setMaxLines(maxLine);
        popuWindowView.initPupoData(tdataListener);
        popuWindowView.showing(view);
    }


}
