package com.mb.agentcontacts;

/*
 * Submitted by: MB Jae Camacho
 * Date: March 18, 2015
 * Course Module: CMPP 264
 * Day 10 & 13 Assignment
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class AgentContacts extends Activity implements OnItemClickListener{

    protected ListView itemsListView;
    private ArrayList<Agent> agents;
    protected AgentsDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);

        db = new AgentsDB(this);

        itemsListView = (ListView) findViewById (R.id.itemsListView);
        itemsListView.setOnItemClickListener(this);

        // get all agents
        agents = db.getAgents();

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data =
                new ArrayList<HashMap<String, String>>();
        for (Agent agent : agents) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", ""+agent.agentID());
            map.put("name", agent.agentName());
            data.add(map);
        }

        // create the resource, from, and to variables
        int resource = R.layout.listview_item;
        String[] from = {"id", "name"};
        int[] to = {R.id.idTextView, R.id.nameTextView};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agent_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // get the agent at the specified position
        Agent agent = agents.get(position);

        // create an intent
        Intent intent = new Intent(this, AgentInfo.class);

        intent.putExtra("id", agent.agentID());
        intent.putExtra("name", agent.agentName());
        intent.putExtra("phone", agent.agentPhone());
        intent.putExtra("email", agent.agentEmail());

        this.startActivity(intent);
    }
}
