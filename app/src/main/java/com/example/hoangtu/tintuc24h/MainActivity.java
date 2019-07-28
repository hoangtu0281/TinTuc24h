package com.example.hoangtu.tintuc24h;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hoangtu.tintuc24h.fragment.FragmentMain;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitileToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
            findViewById(R.id.imvBack).setOnClickListener(this);
            tvTitileToolbar = findViewById(R.id.tv_title_toolbar);
            addFragment(new FragmentMain(),R.id.fragment_container,false);
    }
    public void addFragment(Fragment fragment, int rootId, boolean isAddToBackStack){
        if (isAddToBackStack) {
            getSupportFragmentManager().beginTransaction()
                    .add(rootId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(rootId, fragment, fragment.getClass().getSimpleName()).commit();
        }
    }
    public void replaceFragment(Fragment fragment, int rootId, boolean isAddToBackStack) {
        if (isAddToBackStack) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit)
                    .replace(rootId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName()).commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit)
                    .replace(rootId, fragment, fragment.getClass().getSimpleName()).commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imvBack:
                popBack();
                break;
        }
    }

    private void popBack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }
    }

    public void setTitleToolBar(String title) {
        tvTitileToolbar.setText(title);
    }
}

