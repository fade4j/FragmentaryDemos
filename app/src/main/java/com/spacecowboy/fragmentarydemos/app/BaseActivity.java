package com.spacecowboy.fragmentarydemos.app;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.spacecowboy.fragmentarydemos.R;
import com.spacecowboy.fragmentarydemos.utils.UiUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected String NAME = this.getClass().getSimpleName();
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // 这句很关键，注意是调用父类的方法
        setContentView(R.layout.activity_base);
        initDataBeforeViews();
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        FrameLayout rootContainer = (FrameLayout) findViewById(R.id.root_container_base);
        View subView = getSubContentView();
        try {
            if (subView != null) {
                rootContainer.addView(subView);
                mUnbinder = ButterKnife.bind(this, subView);
                initView(subView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setBackgroundDrawable(UiUtil.getResDrawable(R.drawable.transparent));
        }
        if (getFloatingButtonClickListener() == null) {
            findViewById(R.id.fab).setVisibility(View.GONE);
        } else {
            findViewById(R.id.fab).setOnClickListener(getFloatingButtonClickListener());
        }

        initDataAfterViews();
    }

    protected void initDataAfterViews() {
    }

    protected void initDataBeforeViews() {

    }

    protected abstract void initView(View view);

    protected View.OnClickListener getFloatingButtonClickListener() {
        return null;
    }

    protected abstract View getSubContentView();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    protected <T extends View> T find(int id, View... views) {
        if (views != null && views.length > 0) {
            return (T) views[0].findViewById(id);
        }

        return (T) findViewById(id);
    }
}
