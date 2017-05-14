package teotws.playbill.bean;

/**
 * Created by skyland on 2017/4/7
 */

public class DateModel {
    private int datePos;
    private int programPos;
    private String date;
    private boolean isSelected;

    public DateModel(int setDatePos, int setProgramPos, String setDate, Boolean isSelected){
        this.datePos = setDatePos;
        this.programPos = setProgramPos;
        this.date = setDate;
        this.isSelected = isSelected;
    }

    public int getDatePos() {
        return datePos;
    }

    public int getProgramPos() {
        return programPos;
    }

    public void setDatePos(int datePos) {
        this.datePos = datePos;
    }

    public void setProgramPos(int programPos) {
        this.programPos = programPos;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
