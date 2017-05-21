package teotws.dialog.bottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import teotws.dialog.R;

/**
 * Created by skyland on 2017/5/21
 */

public class BottomDialog extends Dialog {

    public static final int DEFAULT_PADDING = 12;
    public static final int DEFAULT_OPTION_SIZE = 15;
    public static final int DEFAULT_OPTION_DIVIDER_HEIGHT = 3;

    public TextView textTitle;
    public View lineTitle;
    public LinearLayout layoutOptions;
    public TextView textCancel;
    public View lineGroup;


    public BottomDialog(@NonNull Context context) {
        super(context, R.style.bottom_dialog);
        setContentView(R.layout.layout_lib_dialog_bottom_dialog);
        initView();
    }

    private void initView() {
        textTitle = (TextView) findViewById(R.id.text_bottom_dialog_title);
        lineTitle = findViewById(R.id.line_bottom_dialog_title);
        layoutOptions = (LinearLayout) findViewById(R.id.layout_bottom_dialog_options);
        lineGroup = findViewById(R.id.divider_bottom_dialog_group);
        textCancel = (TextView) findViewById(R.id.text_bottom_dialog_cancle);

        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialog.this.dismiss();
            }
        });

        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);

        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_dialog_anim); //设置window动画
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }


    public interface OnOptionClickListener {
        void onOptionClick();
    }

    /**
     * Builder类  参考AlertDialog类
     */
    public static class Builder {
        private Context context;
        private String title;
        private int titleColor;
        private String cancel;
        private int cancelColor;
        private int dividerColor;
        private int optionBackgroundDrable;
        private List<Option> options = new ArrayList<>();

        public Builder(Context setContext) {
            this.context = setContext;
        }

        public Builder setTitle(@NonNull String title, @ColorInt int titleColor) {
            this.title = title;
            this.titleColor = titleColor;
            return this;
        }

        public Builder setDividerColor(@ColorInt int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        public Builder setOpitonBackgroundDrable(@DrawableRes int drawable) {
            this.optionBackgroundDrable = drawable;
            return this;
        }

        public Builder addOption(@NonNull String name, int color, @NonNull BottomDialog.OnOptionClickListener listener) {
            options.add(new Option(name, color, listener));
            return this;
        }

        public Builder setCancle(@Nullable String cancel, @ColorInt int cancelColor) {
            this.cancel = cancel;
            this.cancelColor = cancelColor;
            return this;
        }


        public BottomDialog create() {
            final BottomDialog dialog = new BottomDialog(context);
            if (title == null || title.length() == 0) {
                dialog.textTitle.setVisibility(View.GONE);
                dialog.lineTitle.setVisibility(View.GONE);
            } else {
                dialog.textTitle.setText(title);
                dialog.textTitle.setTextColor(titleColor);
                dialog.textTitle.setVisibility(View.VISIBLE);
                dialog.lineTitle.setVisibility(View.VISIBLE);
            }

            if (dividerColor == 0) {
                dividerColor = ContextCompat.getColor(context, R.color.color_divider);
            }
            dialog.lineTitle.setBackgroundColor(dividerColor);
            dialog.lineGroup.setBackgroundColor(dividerColor);


            if (options.size() == 0) {
                dialog.layoutOptions.setVisibility(View.GONE);
            } else {
                dialog.layoutOptions.setVisibility(View.VISIBLE);
                for (int i = 0; i < options.size(); i++) {
                    final Option option = options.get(i);
                    TextView optionTextView = new TextView(context);
                    int padding = dp2px(context, DEFAULT_PADDING);
                    optionTextView.setPadding(padding, padding, padding, padding);
                    optionTextView.setText(option.name);
                    optionTextView.setTextColor(ContextCompat.getColor(context, option.color));
                    if (optionBackgroundDrable != 0) {
                        optionTextView.setBackgroundResource(optionBackgroundDrable);
                    }
                    optionTextView.setGravity(Gravity.CENTER);
                    optionTextView.setTextSize(DEFAULT_OPTION_SIZE);
                    optionTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (option.listener != null) {
                                option.listener.onOptionClick();
                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.layoutOptions.addView(optionTextView);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DEFAULT_OPTION_DIVIDER_HEIGHT);
                    if (i != options.size() - 1) {
                        View divider = new View(context);
                        divider.setBackgroundColor(dividerColor);
                        dialog.layoutOptions.addView(divider, params);
                    }
                }
            }


            if (cancel == null || cancel.isEmpty()) {
                dialog.textCancel.setText("取消");
            } else {
                dialog.textCancel.setText(title);
            }
            if (cancelColor != 0) {
                dialog.textCancel.setTextColor(cancelColor);
            }

            if (optionBackgroundDrable != 0) {
                dialog.textCancel.setBackgroundResource(optionBackgroundDrable);
            }

            return dialog;
        }


    }


    private static class Option {
        public String name;
        public int color;
        public BottomDialog.OnOptionClickListener listener;

        public Option(String name, int color, BottomDialog.OnOptionClickListener listener) {
            this.name = name;
            this.color = color;
            this.listener = listener;
        }
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
