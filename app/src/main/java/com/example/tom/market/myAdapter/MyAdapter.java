package com.example.tom.market.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tom.market.R;
import com.example.tom.market.bean.Shopping;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tom on 2016/8/23.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList;

    public MyAdapter(Context context, List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList) {
        this.context = context;
        this.childItemsBeanList = childItemsBeanList;
    }

//    public List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> getChildItemsBeanList() {
//        return childItemsBeanList;
//    }
//
//    public void setChildItemsBeanList(List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList) {
//        this.childItemsBeanList = childItemsBeanList;
//    }

    @Override
    public int getCount() {
        return childItemsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return childItemsBeanList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            hodler.tv_text = (TextView) convertView.findViewById(R.id.tv_right);
            hodler.iv_pic = (ImageView) convertView.findViewById(R.id.iv_right);
            convertView.setTag(hodler);
        }else{
            hodler = (Hodler) convertView.getTag();
        }
       Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean.ChildComponentBean  childComponentBean=
               childItemsBeanList.get(position).getComponent();
        hodler.tv_text.setText(childComponentBean.getWord());
        final String path = childComponentBean.getPicUrl();
//        hodler.iv_pic.setImageResource();
//        hodler.iv_pic.setTag(position);
        Picasso.with(context).load(path).into(hodler.iv_pic);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final Bitmap bitmap = MainActivity.getBitmap(path);
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        hodler.iv_pic.setImageBitmap(bitmap);
//                    }
//                });
//            }
//        }).start();

//        hodler.iv_del.setVisibility(getSelsectde()?View.VISIBLE:View.GONE);
//        hodler.iv_del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jokeList.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        return convertView;
    }
    class Hodler{
        TextView tv_text;
        ImageView iv_pic;
        ImageView iv_del;
    }
}
