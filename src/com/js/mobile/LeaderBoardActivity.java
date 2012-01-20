package com.js.mobile;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

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

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, retrievePlayers()));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent myIntent = new Intent(LeaderBoardActivity.this, PlayerProfileActivity.class);
                String profileName = (String) getListAdapter().getItem(position);
                String totalPlayers = Integer.toString(getListAdapter().getCount());
                
                String[] extras = {profileName, Integer.toString(++position), totalPlayers, userName};
                
                myIntent.putExtra("extras", extras);
                LeaderBoardActivity.this.startActivity(myIntent);
            }
        });
    }
    
    private List<String> retrievePlayers() {
        // Load up the Users in DataHandler
        DataHandler.retrieveUsers();

        return DataHandler.getAllUsers();
    }
}