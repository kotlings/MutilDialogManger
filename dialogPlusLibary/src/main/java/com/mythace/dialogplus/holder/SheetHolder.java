package com.mythace.dialogplus.holder;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mythace.dialogplus.DialogUIUtils;
import com.dou361.dialogui.R;
import com.mythace.dialogplus.adapter.TieAdapter;
import com.mythace.dialogplus.bean.BuildBean;
import com.mythace.dialogplus.listener.OnItemClickListener;
import com.mythace.dialogplus.widget.DialogUIDividerItemDecoration;

/**
 * Created by Administrator on 2018/8/9 8
 */
public class SheetHolder extends SuperHolder {

    private TextView tvTitle;
    private RecyclerView rView;
    private Button btnBottom;
    private boolean isItemType;

    public SheetHolder(Context context, boolean isItemType) {
        super(context);
        this.isItemType = isItemType;
    }

    @Override
    protected void findViews() {
        tvTitle = (TextView) rootView.findViewById(R.id.dialogui_tv_title);
        rView = (RecyclerView) rootView.findViewById(R.id.rlv);
        btnBottom = (Button) rootView.findViewById(R.id.btn_bottom);

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialogui_holder_sheet;
    }

    @Override
    public void assingDatasAndEvents(final Context context, final BuildBean bean) {
        if (TextUtils.isEmpty(bean.bottomTxt)) {
            btnBottom.setVisibility(View.GONE);
        } else {
            btnBottom.setVisibility(View.VISIBLE);
            btnBottom.setText(bean.bottomTxt);
            btnBottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                    bean.itemListener.onBottomBtnClick();

                }
            });
        }
        if (TextUtils.isEmpty(bean.title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(bean.title);
        }
        if (bean.isVertical) {
            rView.setLayoutManager(new LinearLayoutManager(bean.mContext));
            rView.addItemDecoration(new DialogUIDividerItemDecoration(bean.mContext));
        } else {
            rView.setLayoutManager(new GridLayoutManager(bean.mContext, bean.gridColumns));// 布局管理器。
        }
        rView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        rView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        if (bean.mAdapter == null) {
            TieAdapter adapter = new TieAdapter(bean.mContext, bean.mLists, isItemType);
            bean.mAdapter = adapter;
        }
        rView.setAdapter(bean.mAdapter);
        bean.mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                bean.itemListener.onItemClick(bean.mLists.get(position).getTitle(), position);
            }
        });
    }


}
