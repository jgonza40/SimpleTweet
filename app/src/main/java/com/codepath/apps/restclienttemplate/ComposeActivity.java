package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeActivity extends AppCompatActivity {

    EditText etCompose;
    Button btnTweet;
    TextView tvCounter;
    public static final int MAX_TWEET_LENGTH = 280;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etCompose = findViewById(R.id.etCompose);
        btnTweet = findViewById(R.id.btnTweet);
        tvCounter = findViewById(R.id.tvCounter);

        etCompose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int tweetLength = etCompose.getText().toString().length();
                //int charsLeft = 280 - tweetLength;
                //String charsLeftMessage = Integer.toString(charsLeft) + " characters left";
                String charsLeftMessage = Integer.toString(tweetLength) + " / 280";
                tvCounter.setText(charsLeftMessage);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Set click listener on button
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweetContent = etCompose.getText().toString();
                if(tweetContent.isEmpty()){
                    Toast.makeText(ComposeActivity.this, "your tweet cannot be empty :/", Toast.LENGTH_LONG).show();
                    return;
                }
                if(tweetContent.length() > MAX_TWEET_LENGTH){
                    Toast.makeText(ComposeActivity.this, "your tweet is too long :/", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(ComposeActivity.this, tweetContent, Toast.LENGTH_LONG).show();
                //Make API call to twitter
            }
        });

    }
}