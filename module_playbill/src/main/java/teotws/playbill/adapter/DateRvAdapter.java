package teotws.playbill.adapter;
import android.support.v4.content.ContextCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

import teotws.playbill.R;
import teotws.playbill.bean.DateModel;

/**
 * Created by skyland on 2017/4/5
 */

public class DateRvAdapter extends BaseQuickAdapter<DateModel, BaseViewHolder> {


    public DateRvAdapter(List<DateModel> data) {
        super(R.layout.item_playbill_date, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DateModel item) {
        helper.setText(R.id.item_playbill_date_day_text, item.getDate());
        if(item.isSelected()){
            helper.getConvertView().setBackgroundColor(ContextCompat.getColor(mContext, R.color.playbill_item_date_selected_bg));
        }else{
            helper.getConvertView().setBackgroundColor(ContextCompat.getColor(mContext, R.color.playbill_item_date_normal_bg));
        }
    }


}
