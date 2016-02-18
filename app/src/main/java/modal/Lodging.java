package modal;

import android.widget.ImageView;

/**
 * Created by tzchoice on 1/21/2016.
 */
public class Lodging {
    private String title;
    private ImageView lodgeIcon;
    private String body;

    public Lodging(String title, ImageView lodgeIcon, String body) {

        this.title = title;
        this.lodgeIcon = lodgeIcon;
        this.body = body;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageView getLodgeIcon() {
        return lodgeIcon;
    }

    public void setLodgeIcon(ImageView lodgeIcon) {
        this.lodgeIcon = lodgeIcon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
