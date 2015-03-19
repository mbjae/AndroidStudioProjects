package com.murach.newsreader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Leisy Moliner
 */
public class AgentDB {

    // database constants
    public static final String DB_NAME = "travelExperts.db";
    public static final int DB_VERSION = 1;

    // task table constants
    public static final String AGENTS_TABLE = "Agents";

    public static final String AGENT_ID = "agentId";
    public static final int AGENT_ID_COL = 0;

    public static final String AGENT_FIRST_NAME = "agtFirstName";
    public static final int AGENT_FIRST_NAME_COL = 1;

    public static final String AGENT_MIDDLE_INITIAL = "agtMiddleInitial";
    public static final int AGENT_MIDDLE_INITIAL_COL = 2;

    public static final String AGENT_LAST_NAME = "agtLastName";
    public static final int AGENT_LAST_NAME_COL = 3;

    public static final String AGENT_PHONE = "agtBusPhone";
    public static final int AGENT_PHONE_COL = 4;

    public static final String AGENT_EMAIL = "agtEmail";
    public static final int AGENT_EMAIL_COL = 5;

    public static final String AGENT_POSITION = "agtPosition";
    public static final int AGENT_POSITION_COL = 6;

    public static final String AGENCY_ID = "agencyId";
    public static final int AGENCY_ID_COL = 7;


    public static final String CREATE_AGENTS_TABLE =
            "CREATE TABLE " + AGENTS_TABLE + " (" +
                    AGENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AGENT_FIRST_NAME + " TEXT    NOT NULL, " +
                    AGENT_MIDDLE_INITIAL + " TEXT, " +
                    AGENT_LAST_NAME + " TEXT  NOT NULL, " +
                    AGENT_PHONE + " TEXT  NOT NULL, " +
                    AGENT_EMAIL + " TEXT  NOT NULL, " +
                    AGENT_POSITION + " TEXT  NOT NULL, " +
                    AGENCY_ID + " TEXT NOT NULL);";


    public static final String DROP_AGENTS_TABLE =
            "DROP TABLE IF EXISTS " + AGENTS_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_AGENTS_TABLE);

            // insert default lists
            db.execSQL("INSERT INTO list VALUES ('Luis', 'L', 'Moliner', '(403)-123-4567', 'lmoliner@te.com', 'Senior Agent', 2)");
            db.execSQL("INSERT INTO list VALUES ('Mayra', 'M', 'Hernandez', '(403)-678-6876', 'mhernandez@te.com', 'Senior Agent', 1)");
            db.execSQL("INSERT INTO list VALUES ('Carmen', 'X', 'Lopez', '(403)-566-6867', 'clopez@te.com', 'Junior Agent', 1)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.d("Agents information", "Upgrading db from version " + oldVersion + " to " + newVersion);
            db.execSQL(AgentDB.DROP_AGENTS_TABLE);
            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public AgentDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() { if (db != null) db.close(); }




    // public methods
    public ArrayList<Agent> getAgents() {

        this.openReadableDB();
        Cursor cursor = db.query(AGENTS_TABLE, null, null, null, null, null, null, null);
        ArrayList<Agent> agents = new ArrayList<Agent>();

        while (cursor.moveToNext()) {
            agents.add(getAgentFromCursor(cursor));
        }
        if (cursor != null) cursor.close();

        this.closeDB();
        return agents;
    }

    public Agent getAgent(int id) {
        String where = AGENT_ID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(AGENTS_TABLE, null, where, whereArgs, null, null, null, null);///////////////////////////////////////////////
        cursor.moveToFirst();
        Agent agent = getAgentFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return agent;
    }

    private static Agent getAgentFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Agent agent = new Agent(
                        cursor.getInt(AGENT_ID_COL),
                        cursor.getString(AGENT_FIRST_NAME_COL),
                        cursor.getString(AGENT_MIDDLE_INITIAL_COL),
                        cursor.getString(AGENT_LAST_NAME_COL),
                        cursor.getString(AGENT_PHONE_COL),
                        cursor.getString(AGENT_EMAIL_COL),
                        cursor.getString(AGENT_POSITION_COL),
                        cursor.getInt(AGENCY_ID_COL));
                return agent;
            }
            catch(Exception e) {
                return null;
            }
        }
    }

    /*public long insertAgent(Agent agent) {
        ContentValues cv = new ContentValues();
        cv.put(AGENT_ID, agent.getAgentId());
        cv.put(AGENT_FIRST_NAME, agent.getAgtFirstName());
        cv.put(AGENT_MIDDLE_INITIAL, agent.getAgtMiddleInitial());
        cv.put(AGENT_LAST_NAME, agent.getAgtLastName());
        cv.put(AGENT_PHONE, agent.getAgtBusPhone());
        cv.put(AGENT_EMAIL, agent.getAgtEmail());
        cv.put(AGENT_POSITION, agent.getAgtPosition());
        cv.put(AGENCY_ID, agent.getAgencyId());

        this.openWriteableDB();
        long rowID = db.insert(AGENTS_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public int updateAgent(Agent agent) {
        ContentValues cv = new ContentValues();
        //cv.put(AGENT_ID, agent.getAgentId());
        cv.put(AGENT_FIRST_NAME, agent.getAgtFirstName());
        cv.put(AGENT_MIDDLE_INITIAL, agent.getAgtMiddleInitial());
        cv.put(AGENT_LAST_NAME, agent.getAgtLastName());
        cv.put(AGENT_PHONE, agent.getAgtBusPhone());
        cv.put(AGENT_EMAIL, agent.getAgtEmail());
        cv.put(AGENT_POSITION, agent.getAgtPosition());
        cv.put(AGENCY_ID, agent.getAgencyId());

        String where = AGENT_ID + "= ?";
        String[] whereArgs = { String.valueOf(agent.getAgentId()) };

        this.openWriteableDB();
        int rowCount = db.update(AGENTS_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteAgent(long id) {
        String where = AGENT_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(AGENTS_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }*/
}

