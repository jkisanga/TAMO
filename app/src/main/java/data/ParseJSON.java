package data;

import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tzchoice on 1/22/2016.
 */
public class ParseJSON {
    public static float[] rating_caches;
    public static Integer[] rating_counts;
    public static String[] short_desc;
    public static String[] long_desc;
    public static String[] icon;
    public static String[] names;
    public static Integer[] ids;

    private JSONArray parks = null;

    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(json);
            parks = jsonObject.getJSONArray(Constants.JSON_ARRAY);
            rating_caches = new float[parks.length()];
            rating_counts = new Integer[parks.length()];
            names = new String[parks.length()];
            short_desc = new String[parks.length()];
            long_desc = new String[parks.length()];
            icon = new String[parks.length()];
            ids = new Integer[parks.length()];

            for(int i = 0; i < parks.length(); i++){
                JSONObject jo = parks.getJSONObject(i);
                rating_caches[i] = Float.parseFloat(jo.getString(Constants.KEY_RATING_CACHE));
                names[i] = jo.getString(Constants.KEY_NAME);
                rating_counts[i] = Integer.parseInt(jo.getString(Constants.KEY_RATING_COUNT)) ;
                short_desc[i] = jo.getString(Constants.KEY_SHORT_DESC);
                long_desc[i] = jo.getString(Constants.KEY_LONG_DESC);
                icon[i] = jo.getString(Constants.KEY_ICON);
                ids[i] = Integer.parseInt(jo.getString(Constants.KEY_ID));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
