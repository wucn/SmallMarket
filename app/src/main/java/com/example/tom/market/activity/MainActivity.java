package com.example.tom.market.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.tom.market.R;
import com.example.tom.market.bean.Shopping;
import com.example.tom.market.http.OkhttpUnits;
import com.example.tom.market.myAdapter.MyAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ListView listView_drw;
    List<String> list = new ArrayList<>();
    List<String> list_drw = new ArrayList<>();
    String path = "http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist";
    String child_path = "http://m.hichao.com/lib/interface.php?m=goodsdetail&sid=";
    GridView gridView;
    MyAdapter myAdapter;
    LinearLayout linearLayout;
    DrawerLayout drawerLayout;
    ActionBar actionBar;
    List<Shopping.DataBean.ItemsBean> itemsBeanList;
    List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList;
    Shopping.DataBean.ItemsBean.ComponentBean componentBean;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                componentBean = itemsBeanList.get(0).getComponent();
                childItemsBeanList = componentBean.getItems();
                myAdapter = new MyAdapter(MainActivity.this, childItemsBeanList);
                gridView.setAdapter(myAdapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("====", "==onCreate==");
        gridView = (GridView) findViewById(R.id.gridView);
        listView = (ListView) findViewById(R.id.listView1);
        listView_drw = (ListView) findViewById(R.id.listView_drw);


        initLeftData();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_drw);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                (this, R.layout.item_left,R.id.textView2,list);
//            TextAdapter arrayAdapter= new TextAdapter(getActivity(),list);
        listView_drw.setAdapter(arrayAdapter1);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                componentBean = itemsBeanList.get(position).getComponent();
                childItemsBeanList = componentBean.getItems();
                myAdapter = new MyAdapter(MainActivity.this, childItemsBeanList);
                gridView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean childItemsBean =
                        childItemsBeanList.get(position);
                Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean.ChildComponentBean childComponentBean =
                        childItemsBean.getComponent();
                String msg = childComponentBean.getWord();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("msg",msg);
                startActivity(intent);
            }
        });

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //显示返回上一级的按钮(箭头(当做home(菜单中的id为android.R.id.home)))
        actionBar.setDisplayHomeAsUpEnabled(true);
//设置图标
        actionBar.setTitle("首页");
//以下2方法必须同时调用Icon才可显示
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        listView_drw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        componentBean = itemsBeanList.get(0).getComponent();
                        childItemsBeanList = componentBean.getItems();
                        myAdapter = new MyAdapter(MainActivity.this, childItemsBeanList);
                        gridView.setAdapter(myAdapter);
                        myAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //左边的抽屉是否打开
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    //打开状态,要关闭
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initLeftData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = OkhttpUnits.getData(path);
                Gson gson = new Gson();
                Shopping shopping = gson.fromJson(data, Shopping.class);
                Shopping.DataBean dataBean = shopping.getData();
                itemsBeanList = dataBean.getItems();
                handler.obtainMessage(1, itemsBeanList).sendToTarget();
                int size = itemsBeanList.size();
                for (int i = 0; i < size; i++) {
                    Shopping.DataBean.ItemsBean.ComponentBean componentBean = itemsBeanList.get(i).getComponent();
                    String title = componentBean.getTitle();
                    Log.e("====", "====" + title);
                    list.add(title);
                }

            }
        }).start();
        list_drw.add("首页");
        list_drw.add("收藏");
        list_drw.add("购物车");
        list_drw.add("退出登录");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //系统引用actionProviderClass
        MenuItem itemShare = menu.findItem(R.id.share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(itemShare);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setDataAndType(Uri.parse(""), "image/*");
        //设置分享意图
        shareActionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
    }
}
