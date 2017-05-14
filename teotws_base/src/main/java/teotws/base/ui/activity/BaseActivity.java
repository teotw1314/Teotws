package teotws.base.ui.activity;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import teotws.base.R;
/**
 * Created by skyland on 2017/5/11
 */

@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TextView titleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initToolbar();
        initView();
        initData();
        initLogic();
    }

    protected abstract @LayoutRes
    int setLayoutId();

    protected void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_lib_base);
        titleText = (TextView) findViewById(R.id.text_lib_base_toolbar_title);
        titleText.setText(setToolbarTitle());
        setSupportActionBar(this.toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract String setToolbarTitle();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initLogic();


    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
