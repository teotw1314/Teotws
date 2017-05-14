package teotws.demo.router;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import teotws.demo.R;

/**
 * Created by skyland on 2017/5/14
 * 使用单例模式创建一个路由类
 */
@SuppressWarnings("unused")
public class Router {

    private static Router instance;

    public static Router defaultRouter(){
        if(instance == null){
            instance = new Router();
        }
        return instance;
    }

    public static void setRouter(Router setRouter){
        instance = setRouter;
    }

    public void pushPlayBillActivity(Context setContext){
        showFailed(setContext);
    }

    public void pushShoppingCartActivity(Context setContext){
        showFailed(setContext);
    }



    public void showFailed(@Nullable Context setContext){
        if(setContext != null){
            Toast.makeText(setContext, R.string.info_show_failed, Toast.LENGTH_SHORT);
        }
    }

}
