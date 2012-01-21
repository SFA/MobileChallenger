package com.js.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/20/12
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load up the layout
        setContentView(R.layout.results);

        Button regButton = (Button)findViewById(R.id.score_it_button);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = ((EditText)findViewById(R.id.txt_password)).getText().toString();
                String oppScore = ((EditText)findViewById(R.id.txt_opp_score)).getText().toString();
                String yourScore = ((EditText)findViewById(R.id.txt_your_score)).getText().toString();
                String trashTalk = ((EditText)findViewById(R.id.txt_trash_talk)).getText().toString();

                int x;
//                DataHandler.doRegistration(username, password, fname, lname, email);
            }
        });
    }
}