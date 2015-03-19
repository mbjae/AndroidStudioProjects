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
import android.widget.Button;
import android.widget.TextView;

public class AgentInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);

        // get references to widgets
        TextView idTextView = (TextView) findViewById(R.id.idTextView);
        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        TextView emailTextView = (TextView) findViewById(R.id.emailTextView);

        Button callButton = (Button) findViewById(R.id.callButton);
        Button emailButton = (Button) findViewById(R.id.emailButton);

        // get the intent
        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        final String phone = intent.getStringExtra("phone");
        String email = intent.getStringExtra("email");

        //display data on the widgets
        idTextView.setText(""+id);
        nameTextView.setText(name);
        phoneTextView.setText(phone);
        emailTextView.setText(email);

        callButton.setText(phone);
        emailButton.setText(email);
/*
        ImageButton callButton = (ImageButton) findViewById(R.id.callButton);

        callButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:"+phone)));
            }
        });
*/
    }

}
