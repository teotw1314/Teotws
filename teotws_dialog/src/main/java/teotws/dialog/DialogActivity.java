package teotws.dialog;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import teotws.base.ui.activity.BaseSubActivity;
import teotws.dialog.bottomdialog.BottomDialog;

/**
 * Created by skyland on 2017/5/21
 */

public class DialogActivity extends BaseSubActivity {


    private Button btnShowBottomDialog;


    public static void startActivity(@NonNull Context setContext) {
        Intent intent = new Intent(setContext, DialogActivity.class);
        setContext.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.acticity_lib_dialog;
    }

    @Override
    protected String setToolbarTitle() {
        return "Dialog";
    }

    @Override
    protected void initView() {
        btnShowBottomDialog = (Button) findViewById(R.id.btn_show_bottom_dialog);
        btnShowBottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLogic() {

    }

    private void showBottomDialog() {
        BottomDialog.Builder builder = new BottomDialog.Builder(this);
        builder.setOpitonBackgroundDrable(R.drawable.color_bottom_dialog_option)
                .addOption("使用浏览器打开", R.color.base_black, new BottomDialog.OnOptionClickListener() {
                    @Override
                    public void onOptionClick() {
                        Toast.makeText(DialogActivity.this, "one", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOption("刷新", R.color.base_black, new BottomDialog.OnOptionClickListener() {
                    @Override
                    public void onOptionClick() {
                        Toast.makeText(DialogActivity.this, "two", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
    }
}
