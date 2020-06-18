package com.hy.cascade.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author:MtBaby
 * @date:2020/06/11 10:01
 * @desc:
 */
public class BaseBean {

    public final static int TYPE_GROUP = 0x1000;
    public final static int TYPE_ITEM = 0x2000;

    @IntDef({TYPE_GROUP, TYPE_ITEM})
    @Retention(RetentionPolicy.SOURCE)
    @interface CascadeType {
    }

    private int itemType;
    private boolean select;
    private int level;
    private boolean isEnd;

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(@CascadeType int itemType) {
        this.itemType = itemType;
    }
}
