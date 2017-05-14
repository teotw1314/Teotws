package teotws.base.ui.widget;


import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import teotws.base.R;


/**
 * Created by skyland on 2017/3/29
 */

@SuppressWarnings("unused")
public class LoadingView extends FrameLayout {

    FrameLayout mFrameProgress;
    TextView mProgressText;
    FrameLayout mFrameError;
    TextView mErrorTitleText;
    TextView mErrorInfoText;
    ImageView mErrorImage;

    LoadingViewListener listener;

    AlphaAnimation mFadeOutAnim;
    AlphaAnimation mFadeInAnim;

    public LoadingView(Context context) {
        super(context);
        initView(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_loading_layout, this);
        LoadingView.this.setVisibility(VISIBLE);
        LoadingView.this.setEnabled(false);
        mFrameProgress = (FrameLayout) findViewById(R.id.view_loading_progress_framge);
        mProgressText = (TextView) findViewById(R.id.view_loading_progress_text);
        mFrameError = (FrameLayout) findViewById(R.id.view_loading_error_frame);
        mErrorTitleText = (TextView) findViewById(R.id.view_loading_error_title);
        mErrorInfoText = (TextView) findViewById(R.id.view_loading_error_info);
        mErrorImage = (ImageView) findViewById(R.id.view_loading_error_image);
        initAnim();

        mFrameError.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClickRetry();
                }
                mFrameError.setVisibility(GONE);
                mFrameProgress.setVisibility(VISIBLE);
            }
        });
    }

    private void initAnim(){
        mFadeOutAnim = new AlphaAnimation(1.0f, 0.0f);
        mFadeOutAnim.setDuration(300);

        mFadeInAnim = new AlphaAnimation(0.0f, 1.0f);
        mFadeInAnim.setDuration(300);
    }

    /**
     * 显示LoadingView
     * @param progressText 正在加载提示文本
     */
    public void showLoading(@Nullable String progressText){
        this.setVisibility(VISIBLE);
        if(progressText == null){
            mProgressText.setText(getResources().getString(R.string.view_loading_loadingtext));
        }else{
            mProgressText.setText(progressText);
        }
        mFrameError.setVisibility(GONE);
        mFrameProgress.setVisibility(VISIBLE);
    }

    /**
     * 显示加载错误
     * @param imageId 图标
     * @param titleText 标题文字
     * @param infoText 文本信息
     */
    public void showError(@DrawableRes int imageId, @Nullable String titleText, @Nullable String infoText){
        this.setVisibility(VISIBLE);
        mErrorImage.setImageResource(imageId);
        if(titleText != null){
            mErrorTitleText.setText(titleText);
        }else{
            mErrorTitleText.setText(R.string.view_loading_error_title);
        }

        if(infoText != null){
            mErrorInfoText.setText(infoText);
        }else{
            mErrorInfoText.setText(R.string.view_loading_error_info);
        }

        mFrameError.setVisibility(VISIBLE);
        mFrameError.startAnimation(mFadeInAnim);
        mFrameProgress.setVisibility(GONE);
    }

    public void showError(@DrawableRes int imageId, @StringRes int titleId, @StringRes int infoId){
        showError(imageId, getContext().getResources().getString(titleId), getContext().getResources().getString(infoId));
    }

    public void showError(){
        showError(R.mipmap.ic_loading_error, null, null);
    }

    public void showInfo(@DrawableRes int imageId, @Nullable String titleText, @Nullable String infoText){
        showError(imageId, titleText, infoText);
    }

    public void dismiss(){
        this.startAnimation(mFadeOutAnim);
        this.setVisibility(GONE);
    }

    public interface LoadingViewListener{

        void onClickRetry();
    }

    public void setLoadingViewListener(LoadingViewListener listener){
        this.listener = listener;
    }



}
