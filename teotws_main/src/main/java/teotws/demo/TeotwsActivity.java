package teotws.demo;



import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import teotws.demo.adapter.MainRvAdapter;
import teotws.demo.base.BaseActivity;

public class TeotwsActivity extends BaseActivity {

    @BindView(R.id.main_recyclerview)
    RecyclerView mRecyclerView;

    private MainRvAdapter mAdapter;

    private List<String> mList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_teotws;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MainRvAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mList = Arrays.asList(getResources().getStringArray(R.array.app_list));
        mAdapter.addData(mList);
    }

    @Override
    protected void initLogic() {

    }


}
