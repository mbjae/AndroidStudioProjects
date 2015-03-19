package com.murach.newsreader;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemsActivity extends Activity 
implements OnItemClickListener {

    private AgentsList agentsList;
    private Agent agent;
    //private FileIO io;

    private TextView titleTextView;
    private ListView itemsListView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        
        //io = new FileIO(getApplicationContext());
        
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        itemsListView = (ListView) findViewById(R.id.itemsListView);
        
        itemsListView.setOnItemClickListener(this);
        
        new DownloadFeed().execute();
    }

    private AgentsList loadData(){
        AgentsList agentsList1 = new AgentsList();
        AgentDB agentDB = new AgentDB(ItemsActivity.this);
        ArrayList <Agent> agentList = agentDB.getAgents();
        for (Agent agt : agentList){
            agentsList1.addAgent(agt);
        }
        return agentsList1;
    }

    
    class DownloadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            //io.downloadFile();
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result) {
            Log.d("News reader", "Feed downloaded");
            new ReadFeed().execute();
        }
    }
    
    class ReadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            //agentsList = agentDB.getList();
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result) {
            Log.d("News reader", "Feed read");
            
            // update the display for the activity
            ItemsActivity.this.updateDisplay();
        }
    }
    
    public void updateDisplay()
    {
        if (agentsList == null) {
            titleTextView.setText("Unable to get RSS feed");
            return;
        }

        // set the title for the feed
        titleTextView.setText(agentsList.getTitle());

        
        // get the items for the feed
        ArrayList<Agent> items = agentsList.getAgentsList();

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data = 
                new ArrayList<HashMap<String, String>>();
        for (Agent item : items) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", item.getPubDateFormatted());
            map.put("title", item.getTitle());
            data.add(map);
        }
        
        // create the resource, from, and to variables 
        int resource = R.layout.listview_item;
        String[] from = {"date", "title"};
        int[] to = {R.id.pubDateTextView, R.id.titleTextView};

        // create and set the adapter
        SimpleAdapter adapter = 
            new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);
        
        Log.d("News reader", "Feed displayed");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, 
            int position, long id) {

        // get the item at the specified position
        Agent item = agentsList.getAgent(position);

        // create an intent
        Intent intent = new Intent(this, ItemActivity.class);

        intent.putExtra("AgtFirstName", item.getAgtFirstName());
        intent.putExtra("AgtMiddleInitial", item.getAgtMiddleInitial());
        intent.putExtra("AgtLastName", item.getAgtLastName());
        intent.putExtra("AgtBusPhone", item.getAgtBusPhone());
        intent.putExtra("AgtEmail", item.getAgtEmail());
        intent.putExtra("AgtPosition", item.getAgtPosition());
        intent.putExtra("AgencyId", item.getAgencyId());
        //intent.putExtra("link", item.getLink());

        this.startActivity(intent);
    }
}