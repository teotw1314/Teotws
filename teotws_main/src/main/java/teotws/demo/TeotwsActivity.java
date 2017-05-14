package teotws.demo;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import teotws.base.ui.activity.BaseActivity;
import teotws.demo.adapter.MainRvAdapter;
import teotws.demo.router.TeotwsRouter;

public class TeotwsActivity extends BaseActivity {

    RecyclerView mRecyclerView;
    private MainRvAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_teotws;
    }

    @Override
    protected String setToolbarTitle() {
        return "TEOTWS";
    }

    @Override
    protected void initView() {
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

    }

    private void onClickItem(String tag) {
        if (tag.equals("PlayBill")) {
            TeotwsRouter.defaultRouter().pushPlayBillActivity(this);
        }else if(tag.equals("ShoppingCart")){
            TeotwsRouter.defaultRouter().pushShoppingCartActivity(this);
        }
    }


}
