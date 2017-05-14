package teotws.playbill.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oubowu.stickyitemdecoration.FullSpanUtil;
import java.util.List;

import teotws.playbill.R;


/**
 * Created by skyland on 2017/4/5
 */

public class PlaybillProgramAdapter extends BaseMultiItemQuickAdapter<PlaybillProgramMultiItem, BaseViewHolder> {


    public PlaybillProgramAdapter(List<PlaybillProgramMultiItem> data) {
        super(data);
        addItemType(PlaybillProgramMultiItem.TYPE_HEADER, R.layout.item_playbill_date);
        addItemType(PlaybillProgramMultiItem.TYPE_PROGRAM, R.layout.item_playbill_program);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlaybillProgramMultiItem item) {
        if (item.getItemType() == PlaybillProgramMultiItem.TYPE_HEADER) {
            helper.setText(R.id.item_playbill_date_day_text, item.getHeaderName());
        } else {
            helper.setText(R.id.item_playbill_program_normal_name_text, item.getVideo().getName());
            if(item.getVideo().isPlaying()){
                helper.getConvertView().setBackgroundColor(ContextCompat.getColor(mContext, R.color.playbill_item_program_selected_bg));
            }else{
                helper.getConvertView().setBackgroundColor(ContextCompat.getColor(mContext, R.color.playbill_item_program_normal_bg));
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, PlaybillProgramMultiItem.TYPE_HEADER);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, PlaybillProgramMultiItem.TYPE_HEADER);
    }
}
