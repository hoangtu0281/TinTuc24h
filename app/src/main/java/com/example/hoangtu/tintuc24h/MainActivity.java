package com.example.hoangtu.tintuc24h;

import android.content.ClipData;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hoangtu.tintuc24h.fragment.FragmentMain;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private TextView tvTitileToolbar;
    private Button btnOK;
    private float x;
    private float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
            btnOK = findViewById(R.id.btnOK);
            btnOK.setOnClickListener(this);
            btnOK.setOnTouchListener(this);
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
                case R.id.btnOK:
                        Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
                        startActivity(intent);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(btnOK);

            btnOK.startDrag(data, shadowBuilder, btnOK, 0);
//                    v.setAlpha(0);
//                    img.setVisibility(View.INVISIBLE);
            return true;
        }
        else
        {
            return false;
        }
    }
}

