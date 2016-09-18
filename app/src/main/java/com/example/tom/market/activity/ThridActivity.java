package com.example.tom.market.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.tom.market.R;

public class ThridActivity extends AppCompatActivity {
    ActionBar actionBar;
    String path = "http://m.hichao.com/lib/interface.php?m=goodsdetail&sid=";
    WebView webView;
    Button btn_buy ;
    Button btn_store ;
    Button btn_add ;
    TextView tv_buynum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        webView= (WebView) findViewById(R.id.web);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //显示返回上一级的按钮(箭头(当做home(菜单中的id为android.R.id.home)))
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("列表详情");
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        path = path+id;
        webView.loadUrl(path);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ThridActivity.this,SecondActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView(){
        btn_store = (Button) findViewById(R.id.btn_store);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_buy = (Button) findViewById(R.id.btn_buy);
    }
}
