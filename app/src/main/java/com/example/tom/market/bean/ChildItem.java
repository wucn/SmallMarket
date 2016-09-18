package com.example.tom.market.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2016/9/12.
 */
public class ChildItem implements Parcelable {
   private List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList;

    public List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> getChildItemsBeanList() {
        return childItemsBeanList;
    }

    public void setChildItemsBeanList(List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList) {
        this.childItemsBeanList = childItemsBeanList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.childItemsBeanList);
    }

    public ChildItem() {
    }

    protected ChildItem(Parcel in) {
        this.childItemsBeanList = new ArrayList<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean>();
        in.readList(this.childItemsBeanList, Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ChildItem> CREATOR = new Parcelable.Creator<ChildItem>() {
        @Override
        public ChildItem createFromParcel(Parcel source) {
            return new ChildItem(source);
        }

        @Override
        public ChildItem[] newArray(int size) {
            return new ChildItem[size];
        }
    };
}
