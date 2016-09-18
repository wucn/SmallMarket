package com.example.tom.market.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tom.market.R;

import java.util.List;

/**
 * Created by tom on 2016/8/23.
 */
public class TextAdapter extends BaseAdapter {
    Context context;
    List<String> list;

    public TextAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left,null);
            hodler.tv_text = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(hodler);
        }else{
            hodler = (Hodler) convertView.getTag();
        }
        hodler.tv_text.setText(list.get(position));
        return convertView;
    }
    class Hodler{
        TextView tv_text;
    }
}
