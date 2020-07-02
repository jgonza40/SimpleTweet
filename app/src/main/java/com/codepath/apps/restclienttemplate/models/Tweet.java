package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public User user;
    public String imgURL;
    public long id;

    // empty constructor needed by the Parceler library
    public Tweet() {}

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.id = jsonObject.getLong("id");

        //String mediaURL = jsonObject.getString("entities");
        JSONObject entities = jsonObject.getJSONObject("entities");
        //Log.d("Tweet", mediaURL);
        if(entities.has("media")){
            tweet.imgURL = entities.getJSONArray("media").getJSONObject(0).getString("media_url_https");
            //entities.getString("media");
            Log.d("Tweet", "has media!");
        } else{
            Log.d("Tweet", "nope!");
            tweet.imgURL = "";
        }

        return tweet;
    }

    public static List<Tweet> fromJSONArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            tweets.add(fromJSON(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

}
