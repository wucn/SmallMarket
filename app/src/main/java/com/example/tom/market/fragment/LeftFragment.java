package com.example.tom.market.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tom.market.http.OkhttpUnits;
import com.example.tom.market.R;
import com.example.tom.market.bean.Shopping;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment {
    ListView listView;
    List<String> list = new ArrayList<>();
    String path = "http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist";
    List<Shopping.DataBean.ItemsBean> itemsBeanList;
    LeftMsgCallback leftMsgCallback;

    public void setLeftMsgCallback(LeftMsgCallback leftMsgCallback) {
        this.leftMsgCallback = leftMsgCallback;
    }

    public interface LeftMsgCallback{
        public void getdata(List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    public LeftFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("====", "==left====onCreateView==");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
//        TextView textView = (TextView) getActivity().findViewById(R.id.textView2);
        initLeftData();
        if (list!=null) {
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (getActivity(), R.layout.item_left,R.id.textView2,list);
//            TextAdapter arrayAdapter= new TextAdapter(getActivity(),list);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Shopping.DataBean.ItemsBean.ComponentBean componentBean = itemsBeanList.get(position).getComponent();
                    List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList = componentBean.getItems();
                    leftMsgCallback.getdata(childItemsBeanList);
//                Intent intent = new Intent();
//                intent.setAction("leftMsg");
//                Bundle bundle = new Bundle();
//                ChildItem childItem = new ChildItem();
//                childItem.setChildItemsBeanList(childItemsBeanList);
//                bundle.putParcelable("msg",childItem);
//                getActivity().sendBroadcast(intent);
                }
            });
        }
        return view;
    }

    public void initLeftData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = OkhttpUnits.getData(path);
                Gson gson = new Gson();
                Shopping shopping = gson.fromJson(data, Shopping.class);
                handler.obtainMessage(1,shopping).sendToTarget();
                Shopping.DataBean dataBean = shopping.getData();
                itemsBeanList = dataBean.getItems();
                int size = itemsBeanList.size();
                for (int i = 0; i < size; i++) {
                    Shopping.DataBean.ItemsBean.ComponentBean componentBean = itemsBeanList.get(i).getComponent();
                    String title = componentBean.getTitle();
                    Log.e("====", "====" + title);
                    list.add(title);
                }

//                Shopping.DataBean.ItemsBean.ComponentBean componentBean = itemsBeanList.get(0).getComponent();
//                List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList = componentBean.getItems();
//                leftMsgCallback.getdata(childItemsBeanList);
            }
        }).start();
    }

    public void transferData(Shopping.DataBean.ItemsBean.ComponentBean componentBean) {
        List<Shopping.DataBean.ItemsBean.ComponentBean.ChildItemsBean> childItemsBeanList = componentBean.getItems();
        int size = childItemsBeanList.size();
        for (int i = 0; i < size; i++) {

        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LeftMsgCallback){
            leftMsgCallback = (LeftMsgCallback) context;
        }
    }


}
