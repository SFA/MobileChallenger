package com.js.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/16/12
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerProfileActivity extends Activity {
    
    String userName;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load up the layout
        setContentView(R.layout.player_profile);

        Intent intent = getIntent();

        String[] s = intent.getStringArrayExtra("extras");
        userName = s[0];
        String rank = s[1];

        TextView userNameTextView = (TextView) findViewById(R.id.txtUsername);
        userNameTextView.setText(userName);

        TextView rankTextView = (TextView) findViewById(R.id.txtRank);
        rankTextView.setText(rank);

        //get the button resource in the xml file and assign it to a local variable of type Button
        Button challenge = (Button)findViewById(R.id.buttonChallenge);

        //this is the action listener
        challenge.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //This gets the resources in the xml file and assigns it to a local variable of type EditText
                TextView rankTextView = (TextView) findViewById(R.id.txtRank);
                rankTextView.setText("Hello");

            }
        }); //end of launch.setOnclickListener
    }
}