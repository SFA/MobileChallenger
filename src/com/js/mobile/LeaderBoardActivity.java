package com.js.mobile;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/16/12
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class LeaderBoardActivity extends ListActivity {
    
    private String userName;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, retrievePlayers()));
        
        setContentView(R.layout.leaderboard_listview);
//        ListView lv = getListView();
//        lv.setTextFilterEnabled(true);
        
        SimpleAdapter adapter = new SimpleAdapter(this, 
                                                  retrievePlayers(), 
                                                  R.layout.custom_leader_list_item,
                                                  new String[]{"fname", "lname", "wins", "losses"},
                                                  new int[]{R.id.fname, R.id.lname, R.id.wins, R.id.losses});

        setListAdapter(adapter);
        
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent myIntent = new Intent(LeaderBoardActivity.this, PlayerProfileActivity.class);
                HashMap<String, String> profile = (HashMap<String, String>) getListAdapter().getItem(position);
                String totalPlayers = Integer.toString(getListAdapter().getCount());

                String[] extras = {profile.get("username"), Integer.toString(++position), totalPlayers, userName, profile.get("wins"), profile.get("losses"), profile.get("fname"), profile.get("lname")};
                
                myIntent.putExtra("extras", extras);
                LeaderBoardActivity.this.startActivity(myIntent);
            }
        });
    }
    
    private ArrayList<HashMap<String,String>> retrievePlayers() {
        // Load up the Users in DataHandler
        DataHandler.retrieveUsers();

        return DataHandler.getAllUsers();
    }
}