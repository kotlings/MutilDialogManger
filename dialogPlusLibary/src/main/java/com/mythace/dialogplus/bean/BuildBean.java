package com.mythace.dialogplus.bean;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.mythace.dialogplus.adapter.SuperAdapter;
import com.mythace.dialogplus.config.DialogConfig;
import com.mythace.dialogplus.listener.Buildable;
import com.mythace.dialogplus.listener.DialogDisMissListener;
import com.mythace.dialogplus.listener.DialogUIDateTimeSaveListener;
import com.mythace.dialogplus.listener.DialogUIItemListener;
import com.mythace.dialogplus.listener.DialogUIListener;
import com.mythace.dialogplus.listener.Styleable;
import com.mythace.dialogplus.utils.ToolUtils;

import java.util.List;
import java.util.Map;

/**
 * DialaLog构建处理包装类
 * <p>
 * Created by Administrator on 2018/8/9 8
 */
public class BuildBean extends Buildable implements Styleable {


    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 构建dialog的类型
     */
    public int type;
    public boolean isVertical;

    public View customView;

    public int gravity;
    public int dateType;
    public long date;
    public String dateTitle;
    public int tag;

    public CharSequence title;
    public CharSequence msg;
    public CharSequence text1 = DialogConfig.dialogui_btnTxt1;
    public CharSequence text2 = DialogConfig.dialogui_btnTxt2;
    public CharSequence text3;
    public CharSequence bottomTxt = DialogConfig.dialogui_bottomTxt;

    public CharSequence hint1;
    public CharSequence hint2;


    public DialogUIListener listener;
    public DialogUIDateTimeSaveListener dateTimeListener;
    public DialogUIItemListener itemListener;

    /**
     * 是否是白色背景
     */
    public boolean isWhiteBg = true;
    /**
     * 是否可以取消
     */
    public boolean cancelable = true;
    /**
     * 面板外是否可以点击
     */
    public boolean outsideTouchable = true;

    public Dialog dialog;
    public AlertDialog alertDialog;


    public int viewHeight;


    //各类对话框特有的参数
    public CharSequence[] wordsMd;
    public int defaultChosen;//
    public boolean[] checkedItems;

    //bottomsheet
    public SuperAdapter mAdapter;
    public List<TieBean> mLists;
    public int gridColumns = 4;

    DialogDisMissListener disMissListener;

    @LEVEL
    private int mLevel;

    public void setmLevel(@LEVEL int lv) {
        this.mLevel = lv;
    }


    //样式

    //三个以下按钮,颜色按此顺序
    @ColorRes
    public int btn1Color = DialogConfig.iosBtnColor;
    @ColorRes
    public int btn2Color = DialogConfig.iosBtnColor;
    @ColorRes
    public int btn3Color = DialogConfig.iosBtnColor;


    @ColorRes
    public int titleTxtColor = DialogConfig.titleTxtColor;
    @ColorRes
    public int msgTxtColor = DialogConfig.msgTxtColor;
    @ColorRes
    public int lvItemTxtColor = DialogConfig.lvItemTxtColor;
    @ColorRes
    public int inputTxtColor = DialogConfig.inputTxtColor;

    public Map<Integer, Integer> colorOfPosition;//listview 的item的特殊颜色:ColorRes

    //字体大小
    public int btnTxtSize = 17;// in sp
    public int titleTxtSize = 14;
    public int msgTxtSize = 14;
    public int itemTxtSize = 14;
    public int inputTxtSize = 14;


    @Override
    public BuildBean setBtnColor(@ColorRes int btn1Color, @ColorRes int btn2Color, @ColorRes int btn3Color) {
        if (btn1Color > 0)
            this.btn1Color = btn1Color;
        if (btn2Color > 0)
            this.btn2Color = btn2Color;
        if (btn3Color > 0)
            this.btn3Color = btn3Color;
        return this;
    }

    @Override
    public BuildBean setListItemColor(@ColorRes int lvItemTxtColor, Map<Integer, Integer> colorOfPosition) {
        if (lvItemTxtColor > 0)
            this.lvItemTxtColor = lvItemTxtColor;
        if (colorOfPosition != null && colorOfPosition.size() > 0) {
            this.colorOfPosition = colorOfPosition;
        }
        return this;
    }

    @Override
    public BuildBean setTitleColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.titleTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean setMsgColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.msgTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean seInputColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.inputTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean setTitleSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.titleTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setMsgSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.msgTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setBtnSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.btnTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setLvItemSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.itemTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setInputSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.inputTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public Dialog show() {
        buildByType(this);
        if (dialog != null && !dialog.isShowing()) {
            ToolUtils.showDialog(dialog);

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    disMissListener.onDissMiss();
                }
            });
            return dialog;
        } else if (alertDialog != null && !alertDialog.isShowing()) {
            ToolUtils.showDialog(alertDialog);

            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    disMissListener.onDissMiss();
                }
            });

            return alertDialog;
        }
        return null;
    }




    public void dissmiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        } else if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }


    public void setDissMissListener(final DialogDisMissListener listener) {

        disMissListener = listener;

    }


    @Override
    public BuildBean setBtnText(CharSequence btn1Text, @Nullable CharSequence btn2Text, @Nullable CharSequence btn3Text) {
        this.text1 = btn1Text;
        this.text2 = btn2Text;
        this.text3 = btn3Text;

        return this;
    }

    @Override
    public BuildBean setBtnText(CharSequence positiveTxt, @Nullable CharSequence negtiveText) {
        return setBtnText(positiveTxt, negtiveText, "");
    }

    @Override
    public BuildBean setListener(DialogUIListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
        return this;
    }

    @Override
    public BuildBean setCancelable(boolean cancelable, boolean outsideCancelable) {
        this.cancelable = cancelable;
        this.outsideTouchable = outsideCancelable;
        return this;
    }


}
