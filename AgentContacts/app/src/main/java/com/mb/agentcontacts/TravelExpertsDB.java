package com.mb.agentcontacts;

/*
 * Submitted by: MB Jae Camacho
 * Date: March 18, 2015
 * Course Module: CMPP 264
 * Day 10 & 13 Assignment
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TravelExpertsDB extends SQLiteOpenHelper {

    public TravelExpertsDB(Context context, String name,
                    CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create tables
        db.execSQL(AgentsDB.CREATE_AGENT_TABLE);

        // insert default lists
        db.execSQL("INSERT INTO "+AgentsDB.AGENT_TABLE+" " +
                "VALUES (null, 'MBJae Camacho','403 123 1234','mb@gmail.com')");
        db.execSQL("INSERT INTO "+AgentsDB.AGENT_TABLE+" " +
                "VALUES (null, 'Maive Andrea','403 123 1235','maive@gmail.com')");
        db.execSQL("INSERT INTO "+AgentsDB.AGENT_TABLE+" " +
                "VALUES (null, 'Arn Benjamin','403 123 1236','arn@gmail.com')");
        db.execSQL("INSERT INTO "+AgentsDB.AGENT_TABLE+" " +
                "VALUES (null, 'Ruby Angela','403 123 1237','ruby@gmail.com')");
        db.execSQL("INSERT INTO "+AgentsDB.AGENT_TABLE+" " +
                "VALUES (null, 'Louise Ann','403 123 1238','louise@gmail.com')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

        db.execSQL(AgentsDB.DROP_AGENT_TABLE);
        onCreate(db);
    }
}
