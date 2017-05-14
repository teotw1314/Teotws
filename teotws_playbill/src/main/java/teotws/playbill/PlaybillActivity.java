package teotws.playbill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;
import com.oubowu.stickyitemdecoration.StickyHeadContainer;
import com.oubowu.stickyitemdecoration.StickyItemDecoration;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import teotws.playbill.adapter.PlaybillDateAdapter;
import teotws.playbill.adapter.PlaybillProgramMultiItem;
import teotws.playbill.adapter.PlaybillProgramAdapter;
import teotws.playbill.bean.DateModel;
import teotws.playbill.bean.VideoModel;
import teotws.playbill.widget.SnappingLinearLayoutManager;

public class PlaybillActivity extends AppCompatActivity {

    @BindView(R.id.playbill_date_rv)
    RecyclerView mDateRecyclerView;

    @BindView(R.id.playbill_program_rv)
    RecyclerView mProgramRecyclerView;

    @BindView(R.id.playbill_stickyhead)
    StickyHeadContainer mStickyHead;

    private List<PlaybillProgramMultiItem> programListData = new ArrayList<>();
    private PlaybillProgramAdapter mProgramAdapter;
    private SnappingLinearLayoutManager mProgramLayoutManager;
    private boolean isMove = false;
    private int mIndex = 0;

    private List<DateModel> mDateList = new ArrayList<>();
    private PlaybillDateAdapter mDateAdapter;

    private int programSelectPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playbill);

        initData();
        initView();


    }

    private void initView(){
        ButterKnife.bind(this);

        /**
         * program list view
         */
        mProgramLayoutManager = new SnappingLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mProgramRecyclerView.setLayoutManager(mProgramLayoutManager);
        mProgramAdapter = new PlaybillProgramAdapter(programListData);
        mProgramRecyclerView.addItemDecoration(new StickyItemDecoration(mStickyHead, PlaybillProgramMultiItem.TYPE_HEADER));
        mProgramRecyclerView.setAdapter(mProgramAdapter);

        mProgramRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter.getItemViewType(position) == PlaybillProgramMultiItem.TYPE_PROGRAM) {
                    VideoModel video = programListData.get(position).getVideo();
//                    Logger.d(programListData.get(position).getVideo().getName() + "  pos:" + position);

                    //设置点击后的高亮选项
                    if (video.isPlaying()) {
                        return;
                    } else {
                        Logger.d("programSelectPos:" + programSelectPos);
                        if (programSelectPos != 0) {
                            programListData.get(programSelectPos).getVideo().setPlaying(false);
                        }
                        video.setPlaying(true);
                        programSelectPos = position;
                        mProgramAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        mProgramRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Logger.d(newState);
                if(isMove && newState == RecyclerView.SCROLL_STATE_IDLE){
                    isMove = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int first = mProgramLayoutManager.findFirstVisibleItemPosition();
                Logger.d(first);
                if(isMove){
                    return;
                }
                /*
                if(mProgramAdapter.getItemViewType(first) == PlaybillProgramMultiItem.TYPE_HEADER){
                    ///设置日期高亮
                    for (Date item : mDateList) {
                        if (item.getProgramPos() == first) {
                            item.setSelected(true);
                        } else {
                            item.setSelected(false);
                        }
                    }
                    mDateAdapter.notifyDataSetChanged();
                }
                */

            }
        });

        /**
         * date list view
         */
        mDateRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDateAdapter = new PlaybillDateAdapter(mDateList);
        mDateRecyclerView.setAdapter(mDateAdapter);
        mDateRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Logger.d(mDateList.get(position).getDate() + "  pos:" + mDateList.get(position).getProgramPos());
                ///点击左边日期后右边跳转到相应日期的节目//
                int programPos = mDateList.get(position).getProgramPos();
//                mProgramRecyclerView.scrollToPosition(programPos);
                isMove = true;
                mProgramRecyclerView.smoothScrollToPosition(programPos);
                ///设置日期高亮
                for (DateModel item : mDateList) {
                    if (item.getProgramPos() == programPos) {
                        item.setSelected(true);
                    } else {
                        item.setSelected(false);
                    }
                }
                mDateAdapter.notifyDataSetChanged();


            }
        });

        /**
         * 粘性标签
         */
        final TextView tvDate = (TextView) mStickyHead.findViewById(R.id.item_playbill_date_day_text);
        mStickyHead.setDataCallback(new StickyHeadContainer.DataCallback() {
            @Override
            public void onDataChange(int pos) {
                tvDate.setText(programListData.get(pos).getHeaderName());

            }
        });

    }

    private void initData(){
        programListData = DataServer.getMultipleItemData();
        Logger.d(programListData.size());
        /**
         * date_list data
         */
        int programPos = 0;
        int size = programListData.size();
        for (int i = 0; i < size; i++) {
            PlaybillProgramMultiItem item = programListData.get(i);
            if (item.getHeaderName() != null) {
                mDateList.add(new DateModel(i, programPos, item.getHeaderName(), false));
                programPos++;
            } else {
                programPos++;
            }
        }

    }


}
