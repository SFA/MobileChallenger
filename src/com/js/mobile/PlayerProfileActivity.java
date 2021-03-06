package com.js.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private String userName;
    private String profileName;
    private Button challenge = null;
    private Button showChallenges = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load up the layout
        setContentView(R.layout.player_profile);

        Intent intent = getIntent();

        String[] s = intent.getStringArrayExtra("extras");
        profileName = s[0];
        String rank = s[1];
        String totalPlayers = s[2];
        userName = s[3];
        String wins = s[4];
        String losses = s[5];
        String fname = s[6];
        String lname = s[7];

        //Set Profile Name at top of screen
        TextView profileNameTextView = (TextView) findViewById(R.id.txtProfileName);
        profileNameTextView.setText(profileName);

        //Set rank based on position in the Leaderboard list
        TextView rankTextView = (TextView) findViewById(R.id.txtRank);
        rankTextView.setText(rank + " of " + totalPlayers);

        //Set Record
        TextView recordTextView = (TextView) findViewById(R.id.txtRecord);
        recordTextView.setText("Wins: " + wins + ", Losses: " + losses);
        
        //Set First Name
        TextView firstNameTextView = (TextView)findViewById(R.id.txtFirstName);
        firstNameTextView.setText(fname);
        
        //Set Last Name
        TextView lastNameTextView = (TextView)findViewById(R.id.txtLastName);
        lastNameTextView.setText(lname);

        //get the button resource in the xml file and assign it to a local variable of type Button
        challenge = (Button) findViewById(R.id.buttonChallenge);
        showChallenges = (Button) findViewById(R.id.buttonShowChallenges);

        if (userName.equalsIgnoreCase(profileName)) {
            challenge.setClickable(false);
            challenge.setVisibility(View.GONE);
        }

        challenge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new AlertDialog.Builder(PlayerProfileActivity.this)
                        .setTitle("Challenge")
                        .setMessage("Are you sure you want to challenge " + profileName + "?")
                        .setPositiveButton("Bring it on!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestAChallenge();
                                challenge.setClickable(false);
                            }
                        })
                        .setNegativeButton("I'm scared", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                TextView tempTextView = (TextView) findViewById(R.id.txtFirstName);
                                tempTextView.setText("");
                            }
                        })
                        .show();
            }
        });

    showChallenges.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            Intent myIntent = new Intent(PlayerProfileActivity.this, ChallengesActivity.class);

            String[] extras = {profileName, userName};

            myIntent.putExtra("extras", extras);
            PlayerProfileActivity.this.startActivity(myIntent);
        }
    });
}

    private void requestAChallenge() {
        DataHandler.doChallenge(profileName, userName);
    }
}