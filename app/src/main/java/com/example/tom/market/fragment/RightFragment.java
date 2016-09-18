//package com.example.tom.fargment.fragment;
//
//
//import android.content.Context;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.tom.fargment.R;
//import com.example.tom.fargment.broadcast.Reciver;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class RightFragment extends Fragment {
//    Reciver reciver;
//
//    public RightFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        Log.e("====","==right====onCreateView==");
//        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
//        // Inflate the layout for this fragment
//        return view;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////getActivity().
////        reciver = new Reciver();
////        IntentFilter filter = new IntentFilter();
////        filter.addAction("leftMsg");
////        getActivity().registerReceiver(reciver,filter);
//        Log.e("====","==right====onCreate==");
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        Log.e("====","==right====onAttach==");
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
////        getActivity().unregisterReceiver(reciver);
//        Log.e("====","==right====onDestroy==");
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Log.e("====","==right====onDestroyView==");
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        Log.e("====","==right====onDetach==");
//    }
//
//
//
//}
