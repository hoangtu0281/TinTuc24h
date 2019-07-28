package com.example.hoangtu.tintuc24h.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoangtu.tintuc24h.MainActivity;
import com.example.hoangtu.tintuc24h.R;
import com.example.hoangtu.tintuc24h.adapter.MainAdapter;

public class FragmentMain extends Fragment implements MainAdapter.OnItemMainClickListenr {
    private RecyclerView lvfragmentmain;
    private MainActivity mainActivity;
    private MainAdapter mainAdapter;


    public FragmentMain() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity){
            mainActivity=(MainActivity) getActivity();
            mainActivity.setTitleToolBar(mainActivity.getResources().getString(R.string.app_name));

        }
    }

    private void initView(View view) {
        lvfragmentmain = view.findViewById(R.id.lv_fragment_main);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        lvfragmentmain.setLayoutManager(staggeredGridLayoutManager);
        mainAdapter = new MainAdapter(getContext());
        mainAdapter.setOnItemCaterogyClickListenr(this);
        lvfragmentmain.setAdapter(mainAdapter);


    }

    @Override
    public void onClickItemMain(int position) {
        if (mainActivity!=null){
            mainActivity.replaceFragment(FragmentNew.newInstance(mainAdapter.getItem(position).getUrl()),
                    R.id.fragment_container, true);
            mainActivity.setTitleToolBar(mainAdapter.getItem(position).getTitle());
        }
    }
}
