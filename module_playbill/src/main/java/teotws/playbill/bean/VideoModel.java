package teotws.playbill.bean;


public class VideoModel {
    private String img;
    private String name;
    private boolean isPlaying;

    public VideoModel(String img, String name, boolean isPlaying) {
        this.img = img;
        this.name = name;
        this.isPlaying = isPlaying;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
