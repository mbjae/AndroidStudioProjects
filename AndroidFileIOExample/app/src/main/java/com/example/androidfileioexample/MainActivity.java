package com.example.androidfileioexample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button readBtn = (Button)findViewById(R.id.button1);
		Button writeBtn = (Button)findViewById(R.id.button2);
		
		readBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(
							openFileInput("output1.txt")));
					String myString = "";
					StringBuffer buffer = new StringBuffer();
					while ((myString = br.readLine()) != null) {
						buffer.append(myString + "\n");
					}
					EditText et = (EditText) findViewById(R.id.editText1);
					et.setText(buffer.toString());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		writeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				try {
					EditText et = (EditText) findViewById(R.id.editText1);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
							openFileOutput("output1.txt", 0)));
					bw.write(et.getText().toString());
					bw.flush();
					bw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
