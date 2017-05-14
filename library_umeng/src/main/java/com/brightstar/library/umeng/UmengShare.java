package com.brightstar.library.umeng;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 *
 * 友盟分享
 */
public abstract class UmengShare {

//    public static final String SHARE_URL = "http://www.96335" + ".com/hitv2013/?from=singlemessage&isappinstalled=1";
    public static final String SHARE_URL = "http://www.baidu.com";

    private Activity activity;

    /**
     * 分享成功
     * @param platform
     */
    public abstract void onSuccess(SHARE_MEDIA platform);

    /**
     * 分享失败
     * @param platform
     * @param throwable
     */
    public abstract void onError(SHARE_MEDIA platform, Throwable throwable);

    /**
     * 分享取消
     * @param platform
     */
    public abstract void onCancel(SHARE_MEDIA platform);

    /**
     *  分享
     * @param activity
     * @param title         标题
     * @param text          内容
     * @param targetUrl    跳转的URL
     * @param iconBitmap       图标
     */
    public void shareWithBitmap(Activity activity, String title, String text, String targetUrl, Bitmap iconBitmap){
        if(isNull(activity, title, text, targetUrl, iconBitmap)){
            return;
        }
        this.activity = activity;
        init();
        UMImage uMImage = new UMImage(activity, getWhiteBackgroundBitmap(iconBitmap));
        showDialog(title, text, targetUrl, uMImage);
    }

    /**
     *  分享
     * @param activity
     * @param title         标题
     * @param text          内容
     * @param targetUrl    跳转的URL
     * @param iconUrl      图标url
     */
    public void shareWithUrl(Activity activity, String title, String text, String targetUrl, String iconUrl){
        if(isNull(activity, title, text, targetUrl, iconUrl)){
            return;
        }
        this.activity = activity;
        init();
        UMImage uMImage;
        if("".equals(iconUrl)){
            Bitmap iconBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_launcher);
            uMImage = new UMImage(activity, getWhiteBackgroundBitmap(iconBitmap));
        }else {
            uMImage = new UMImage(activity, iconUrl);
        }
        showDialog(title, text, targetUrl, uMImage);
    }

    private boolean isNull(Activity activity, String title, String text, String targetUrl, Object icon){
        if(title == null){
            Toast.makeText(activity, "标题不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (text == null){
            Toast.makeText(activity, "内容不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (targetUrl == null){
            Toast.makeText(activity, "链接不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (!targetUrl.startsWith("http")){
            Toast.makeText(activity, "链接格式不正确",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (icon == null){
            Toast.makeText(activity, "图标不能为空",Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return false;
        }
    }

    private void showDialog(final String title, final String text, final String targetUrl, final UMImage uMImage){

        final UMWeb web = new UMWeb(targetUrl);
        web.setTitle(title);
        web.setThumb(uMImage);
        web.setDescription(text);


        new ShareAction(activity).setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,
                SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        new ShareAction(activity)
//                                .withTitle(title)//标题
                                .withText(text)//内容
//                                .withMedia(uMImage)//图标
//                                .withTargetUrl(targetUrl)//跳转地址
                                .withMedia(web)
                                .setPlatform(share_media)
                                .setCallback(umShareListener)
                                .share();
                    }
                }).open();
    }

    /**
     * 将Bitmap转换为白色背景的Bitmap以防止部分透明色而出现的黑色背景
     * @param bitmap
     * @return
     */
    private Bitmap getWhiteBackgroundBitmap(Bitmap bitmap) {
        int wh = (bitmap.getWidth() > bitmap.getHeight()) ? bitmap.getWidth() : bitmap.getHeight();
        Bitmap newb = Bitmap.createBitmap(wh, wh, Bitmap.Config.ARGB_8888);//创建一个新的位图
        Canvas cv = new Canvas(newb);
        cv.drawColor(Color.WHITE);//白色画布
        float left = (float) ((wh - bitmap.getWidth()) / 2.0);
        float top = (float) ((wh - bitmap.getHeight()) / 2.0);
        cv.drawBitmap(bitmap, left, top, null);//画入
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        return newb;
    }

    private UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("ss__", "onResult: ");
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(activity,platform + " 收藏成功",Toast.LENGTH_SHORT).show();
            }else{
                UmengShare.this.onSuccess(platform);
                Toast.makeText(activity, platform + " 分享成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Log.d("ss__", "error: ");
            UmengShare.this.onError(platform, t);
            Toast.makeText(activity,platform + " 分享失败", Toast.LENGTH_SHORT).show();
//            if(t!=null){
//                Log.d("throw","throw:"+t.getMessage());
//            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Log.d("ss__", "onCancel: " + platform.toString());
            UmengShare.this.onCancel(platform);
//            Toast.makeText(activity,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 初始化标记，确保只初始化一次
     */
    private static boolean initTag = false;
    private void init(){
        if (initTag){
            return;
        }
        Config.DEBUG = true;
        initTag = true;
        //各个平台的配置，建议放在全局Application或者程序入口
        //微信 appid appsecret
        PlatformConfig.setWeixin("wx5faa7ff1829c1786", "fea13459d981ac9a8781bfc56b753046");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("101348653", "add179e8d95ae111b980bb1b6547ea18");

        //可以将一下代码加到你的MainActivity中，或者在任意一个需要调用分享功能的activity当中
        String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.SET_DEBUG_APP,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.GET_ACCOUNTS};
        ActivityCompat.requestPermissions(activity,mPermissionList, 100);


    }

}
