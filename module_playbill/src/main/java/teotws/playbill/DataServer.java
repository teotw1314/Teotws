package teotws.playbill;


import java.util.ArrayList;
import java.util.List;

import teotws.playbill.adapter.ProgramMultiItem;
import teotws.playbill.bean.VideoModel;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class DataServer {

    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";
    private static final String CYM_CHAD = "CymChad";

    private DataServer() {
    }

    public static List<ProgramMultiItem> getMultipleItemData() {
        List<ProgramMultiItem> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            list.add(new ProgramMultiItem("04月0" + i + "日"));
            list.add(new ProgramMultiItem(new VideoModel("fsef", "节目名称2", false)));
            list.add(new ProgramMultiItem(new VideoModel("fsef", "节目名称3", false)));
            if (i == 3) {
                list.add(new ProgramMultiItem(new VideoModel("fsef", "节目名称3", true)));
            }
            list.add(new ProgramMultiItem(new VideoModel("fsef", "节目名称4", false)));
            list.add(new ProgramMultiItem(new VideoModel("fsef", "节目名称5", false)));
            list.add(new ProgramMultiItem(new VideoModel("fsef", "节目名称6", false)));
        }
        return list;
    }


    public static List<String> getStrData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String str = HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK;
            if (i % 2 == 0) {
                str = CYM_CHAD;
            }
            list.add(str);
        }
        return list;
    }


}
