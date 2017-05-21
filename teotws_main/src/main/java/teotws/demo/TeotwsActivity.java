package teotws.demo;


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import teotws.base.ui.activity.BaseActivity;
import teotws.base.ui.widget.LoadingView;
import teotws.demo.adapter.MainRvAdapter;
import teotws.demo.router.TeotwsRouter;

public class TeotwsActivity extends BaseActivity {

    private LoadingView mLoadingView;
    private RecyclerView mRecyclerView;
    private MainRvAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_teotws;
    }

    @Override
    protected String setToolbarTitle() {
        return "Teotws";
    }


    @Override
    protected void initView() {
        mLoadingView = (LoadingView) findViewById(R.id.main_loading_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MainRvAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.d(view.getTag().toString());
                onClickItem(view.getTag().toString());
            }
        });
    }

    @Override
    protected void initData() {
        mList = Arrays.asList(getResources().getStringArray(R.array.app_list));
        mAdapter.addData(mList);
    }

    @Override
    protected void initLogic() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingView.dismiss();
//                mLiadingView.showError();
            }
        }, 3000);
    }

    private void onClickItem(String tag) {
        if (tag.equals("PlayBill")) {
            TeotwsRouter.defaultRouter().pushPlayBillActivity(this);
        }else if(tag.equals("DialogView")){
            TeotwsRouter.defaultRouter().pushDialogActivity(this);
        }
    }


}
