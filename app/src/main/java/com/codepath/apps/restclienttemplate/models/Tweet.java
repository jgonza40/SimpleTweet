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

    // Empty constructor needed for the Parceler library
    public Tweet() {}

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        // Getting info for all components in a tweet
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.id = jsonObject.getLong("id");
        JSONObject entities = jsonObject.getJSONObject("entities");
        //If there is media, then the link with media will be store, else empty string
        if(entities.has("media")){
            tweet.imgURL = entities.getJSONArray("media")
                    .getJSONObject(0)
                    .getString("media_url_https");
        } else{
            Log.d("Tweet", "nope!");
            tweet.imgURL = "";
        }
        return tweet;
    }

    // Method to add individual tweet to list of tweets
    public static List<Tweet> fromJSONArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            tweets.add(fromJSON(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

}
