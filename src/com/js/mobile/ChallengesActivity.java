package com.js.mobile;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/20/12
 * Time: 9:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChallengesActivity extends ListActivity {

    private String userName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String[] s = intent.getStringArrayExtra("extras");
        userName = s[0];

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, retrieveChallenges()));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

    }

    private List<String> retrieveChallenges() {

        return DataHandler.getChallenges(userName);
    }
}