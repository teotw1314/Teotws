package teotws.playbill.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import teotws.playbill.bean.VideoModel;

/**
 * Created by skyland on 2017/4/5
 */

public class PlaybillProgramMultiItem implements MultiItemEntity {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_PROGRAM = 2;
    public static final int TYPE_STICKY_HEAD = 3;


    private int itemType;

    String headerName;
    VideoModel video;

    public PlaybillProgramMultiItem(String setHeaderName) {
        itemType = TYPE_HEADER;
        this.headerName = setHeaderName;
    }

    public PlaybillProgramMultiItem(VideoModel video) {
        itemType = TYPE_PROGRAM;
        this.video = video;
    }

    public String getHeaderName() {
        return headerName;
    }

    public VideoModel getVideo() {
        return video;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
