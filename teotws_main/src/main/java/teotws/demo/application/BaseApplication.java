package teotws.demo.application;

import android.app.Application;


import teotws.base.core.Router;
import teotws.demo.router.TeotwsRouter;

/**
 * Created by skyland on 2017/5/14
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Router.setRouter(new TeotwsRouter());
    }
}
