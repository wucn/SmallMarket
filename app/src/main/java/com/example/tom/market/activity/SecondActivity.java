package com.example.tom.market.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tom.market.R;
import com.example.tom.market.bean.SecondShop;
import com.example.tom.market.http.OkhttpUnits;
import com.example.tom.market.myAdapter.SeconAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    String path_pre = "http://api-v2.mall.hichao.com/search/skus?query=";
    String path_end = "&sort=all&ga=/search/skus&flag=&cat=&asc=1";
    List<SecondShop.DataBean.ItemsBean.ComponentBean> componentBeenlist = new ArrayList<>();
    GridView gridView;
    ActionBar actionBar;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                Log.e("===","===="+componentBeenlist.size());
                SeconAdapter seconAdapter = new SeconAdapter(SecondActivity.this,componentBeenlist);
                gridView.setAdapter(seconAdapter);
            }
        }
    };
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //显示返回上一级的按钮(箭头(当做home(菜单中的id为android.R.id.home)))
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("列表详情");
        Intent intent = getIntent();
        String id = intent.getStringExtra("msg");
        String path = path_pre+id+path_end;
        initdata(path);
        gridView = (GridView) findViewById(R.id.listViewSecond);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SecondShop.DataBean.ItemsBean.ComponentBean componentBean = componentBeenlist.get(position);
                String id_src = componentBean.getAction().getSourceId();
                Intent intent = new Intent(SecondActivity.this,ThridActivity.class);
                intent.putExtra("id",id_src);
                startActivity(intent);
            }
        });
    }

    public void initdata(final String path){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = OkhttpUnits.getData(path);
                Gson gson = new Gson();
                SecondShop secondShop = gson.fromJson(data,SecondShop.class);
                List<SecondShop.DataBean.ItemsBean> itemsBeanList = secondShop.getData().getItems();
                int size = itemsBeanList.size();
                for (int i = 0; i <size ; i++) {
                    SecondShop.DataBean.ItemsBean.ComponentBean componentBean = itemsBeanList.get(i).getComponent();
                    componentBeenlist.add(componentBean);
                }
                handler.obtainMessage(2,componentBeenlist).sendToTarget();
            }
        }).start();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
