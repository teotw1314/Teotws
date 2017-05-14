package teotws.demo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import teotws.demo.R;

/**
 * Created by skyland on 2017/5/12
 */

public class MainRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MainRvAdapter(List<String> list) {
        super(R.layout.item_mian_rv, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_main_name, item);
        helper.convertView.setTag(item);
    }
}
