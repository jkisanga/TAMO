package modal;

import android.widget.ImageView;

/**
 * Created by tzchoice on 1/21/2016.
 */
public class Activity {
    private String title;
    private ImageView activityIcon;
    private String body;
    public Activity(String title, ImageView activityIcon, String body) {

        this.title = title;
        this.activityIcon = activityIcon;
        this.body = body;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageView getActivityIcon() {
        return activityIcon;
    }

    public void setActivityIcon(ImageView activityIcon) {
        this.activityIcon = activityIcon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
