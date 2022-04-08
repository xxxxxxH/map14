package fire.faker.tes;

import java.io.Serializable;

public class DataEntity implements Serializable {
    private String title;
    private String description;
    private String panoid;
    private String lat;
    private String lng;
    private String pitch;
    private String heading;
    private String pano;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPanoid() {
        return panoid;
    }

    public void setPanoid(String panoid) {
        this.panoid = panoid;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getPano() {
        return pano;
    }

    public void setPano(String pano) {
        this.pano = pano;
    }
}
