package com.example.hoangtu.tintuc24h.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoangtu.tintuc24h.R;
import com.example.hoangtu.tintuc24h.WebViewActivity;
import com.example.hoangtu.tintuc24h.adapter.NewAdapter;
import com.example.hoangtu.tintuc24h.model.Item;
import com.example.hoangtu.tintuc24h.parse.XMLAsync;

import java.util.ArrayList;

public class FragmentNew extends Fragment implements NewAdapter.OnItemNewsClickListener {
    private static final String KEY_NEW_URL = "key_new_url";
    private NewAdapter newAdapter;
    private ArrayList<Item> arrItem = new ArrayList<>();
    private RecyclerView rc_New;

    public static FragmentNew newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(KEY_NEW_URL, url);
        FragmentNew fragment = new FragmentNew();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentNew() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        parseData();

    }
    private void initView(View view) {
        newAdapter = new NewAdapter(arrItem,getContext());
        newAdapter.setOnItemNewsClickListener(this);
        rc_New = view.findViewById(R.id.rc_new);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_New.setLayoutManager(linearLayoutManager);

        rc_New.setAdapter(newAdapter);

    }
    private void parseData() {
        String url = getArguments().getString(KEY_NEW_URL, "");
        XMLAsync xmlAsync = new XMLAsync(handler);
        xmlAsync.execute(url);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == XMLAsync.WHAT_DATA) {
                ArrayList<Item> arr = (ArrayList<Item>) msg.obj;
                arrItem.addAll(arr);
                newAdapter.notifyDataSetChanged();

            }
        }
    };

    @Override
    public void onClickItemNews(int position) {
        String link = newAdapter.getItem(position).getLink();
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra("KEY_LINK_NEWS", link);
        startActivity(intent);
    }
}
