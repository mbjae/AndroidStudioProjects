package com.mb.agentcontacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

/*
 * Submitted by: MB Jae Camacho
 * Date: March 18, 2015
 * Course Module: CMPP 264
 * Day 10 & 13 Assignment
 */

public class AgentsDB {
    // database constants
    public static final String DB_NAME = "travelexperts.db";
    public static final int    DB_VERSION = 3;

    // agent table constants
    public static final String AGENT_TABLE = "agent";
    public static final String AGENT_ID = "agentID";
    public static final int    AGENT_ID_COL = 0;

    public static final String AGENT_NAME = "agentName";
    public static final int    AGENT_NAME_COL = 1;

    public static final String AGENT_PHONE = "agentPhone";
    public static final int    AGENT_PHONE_COL = 2;

    public static final String AGENT_EMAIL = "agentEmail";
    public static final int    AGENT_EMAIL_COL = 3;

    // CREATE and DROP TABLE statements
    public static final String CREATE_AGENT_TABLE =
            "CREATE TABLE " + AGENT_TABLE + " (" +
                    AGENT_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AGENT_NAME + " TEXT NOT NULL, "+
                    AGENT_PHONE + " TEXT NOT NULL UNIQUE, "+
                    AGENT_EMAIL + " TEXT NOT NULL UNIQUE);";

    public static final String DROP_AGENT_TABLE =
            "DROP TABLE IF EXISTS " + AGENT_TABLE;

    // database and database helper objects
    private SQLiteDatabase db;
    private TravelExpertsDB dbHelper;

    // constructor
    public AgentsDB(Context context) {
        dbHelper = new TravelExpertsDB(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    // public methods
    public ArrayList<Agent> getAgents() {
        ArrayList<Agent> agents = new ArrayList<Agent>();
        openReadableDB();
        Cursor cursor;

        //if(findMe.length()==0){//get all agents
            cursor = db.query(AGENT_TABLE,
                    null, null, null, null, null, null);
        //}

        while (cursor.moveToNext()) {
            Agent agent = new Agent();
            agent.agentID(cursor.getInt(AGENT_ID_COL));
            agent.agentName(cursor.getString(AGENT_NAME_COL));
            agent.agentPhone(cursor.getString(AGENT_PHONE_COL));
            agent.agentEmail(cursor.getString(AGENT_EMAIL_COL));

            agents.add(agent);
        }
        if (cursor != null)
            cursor.close();
        closeDB();

        return agents;
    }
}
