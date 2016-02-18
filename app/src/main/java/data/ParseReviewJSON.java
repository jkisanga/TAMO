package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tzchoice on 1/22/2016.
 */
public class ParseReviewJSON {
    public static float[] rating;
    public static String[] comments;

    private JSONArray parkReviews = null;

    private String json;

    public ParseReviewJSON(String json) {
        this.json = json;
    }

    public void ParseReviewJSON(){
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(json);
            parkReviews = jsonObject.getJSONArray(Constants.JSON_REVIEW_ARRAY);
            rating = new float[parkReviews.length()];
            comments = new String[parkReviews.length()];


            for(int i = 0; i < parkReviews.length(); i++){
                JSONObject jo = parkReviews.getJSONObject(i);
                rating[i] = Float.parseFloat(jo.getString(Constants.KEY_RATING));
                comments[i] = jo.getString(Constants.KEY_COMMENT);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
