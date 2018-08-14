package com.mythace.dialogplus.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/9 8
 */
public class TieBean implements Serializable {

    private int id;
    private String title;
    private boolean isSelect;

    public TieBean(String title) {
        this.title = title;
    }

    public TieBean(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
