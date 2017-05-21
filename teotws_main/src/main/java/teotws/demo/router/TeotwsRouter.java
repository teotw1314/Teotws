package teotws.demo.router;

import android.content.Context;


import teotws.base.core.Router;
import teotws.dialog.DialogActivity;
import teotws.playbill.PlaybillActivity;

/**
 * Created by skyland on 2017/5/14
 */

@SuppressWarnings("unused")
public class TeotwsRouter extends Router {

    @Override
    public void pushPlayBillActivity(Context setContext) {
        PlaybillActivity.startActivity(setContext);
    }

    @Override
    public void pushDialogActivity(Context setContext) {
        DialogActivity.startActivity(setContext);
    }
}
