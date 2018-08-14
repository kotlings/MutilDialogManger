package com.mythace.dialogplus.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dou361.dialogui.R;
/**
 * Created by Administrator on 2018/8/9 8
 */
public class DialogUIDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public DialogUIDividerItemDecoration(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.dialogui_live_divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
} 