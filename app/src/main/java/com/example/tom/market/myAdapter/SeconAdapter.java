package com.example.tom.market.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tom.market.R;
import com.example.tom.market.bean.SecondShop;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tom on 2016/9/17.
 */
public class SeconAdapter extends BaseAdapter{
    Context context;
    List<SecondShop.DataBean.ItemsBean.ComponentBean> componentBeenlist;

    public SeconAdapter(Context context, List<SecondShop.DataBean.ItemsBean.ComponentBean> componentBeenlist) {
        this.context = context;
        this.componentBeenlist = componentBeenlist;
    }


    @Override
    public int getCount() {
        return componentBeenlist.size();
    }

    @Override
    public Object getItem(int position) {
        return componentBeenlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Hodler hodler ;
        if (convertView==null){
            hodler = new Hodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_second,null);
            hodler.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            hodler.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            hodler.tv_orpr = (TextView) convertView.findViewById(R.id.tv_or);
            hodler.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            hodler.iv_pic = (ImageView) convertView.findViewById(R.id.iv_se);
            convertView.setTag(hodler);
        }else{
            hodler = (Hodler) convertView.getTag();
        }
        SecondShop.DataBean.ItemsBean.ComponentBean componentBean = componentBeenlist.get(position);
        String title = componentBean.getDescription();
        String price = componentBean.getPrice();
        String orign = componentBean.getOrigin_price();
        String num = componentBean.getSales();
        hodler.tv_title.setText(title);
        hodler.tv_price.setText("¥"+price);
        hodler.tv_orpr.setText("原¥"+orign);
        hodler.tv_num.setText("销量:"+num);
        final String path = componentBean.getPicUrl();
//        hodler.iv_pic.setImageResource();
//        hodler.iv_pic.setTag(position);
        Picasso.with(context).load(path).into(hodler.iv_pic);
        return convertView;
    }
    class Hodler{
        TextView tv_title;
        TextView tv_price;
        TextView tv_orpr;
        TextView tv_num;
        ImageView iv_pic;
    }
}
