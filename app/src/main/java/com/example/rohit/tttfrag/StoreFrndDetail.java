package com.example.rohit.tttfrag;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StoreFrndDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_frnd_detail);
    }

    public void addFrnd(View v)
    {
        if(!((EditText)findViewById(R.id.editText2)).getText().toString().equals("")) {
            FrndDetail q = new FrndDetail(1,((EditText)findViewById(R.id.editText2)).getText().toString());
            DatabaseHandler dh = new DatabaseHandler(this);
            dh.addQuote(q);
            Toast.makeText(this, "Frnd added!", Toast.LENGTH_SHORT).show();
            Intent startscreen = new Intent(this, MainActivity.class);
            startActivity(startscreen);
        }
        else
        {
            Toast.makeText(this, "Cannot add an empty quote!", Toast.LENGTH_SHORT).show();
        }
    }
    public void viewFrnds(View v){
        DatabaseHandler dh = new DatabaseHandler(this);
        List<FrndDetail> ls = dh.getAllQuotes();
        String ns = new String();
        Log.v("List Size",Integer.toString(ls.size()));
        for(int i = 0; i < ls.size(); i++) {
            Log.v("Pappu",ls.get(i).getText());
            ns = ns.concat(ls.get(i).get_id()+"  "+ls.get(i).getText());
        }
        Log.v("Database: ",ns);
        ((TextView)findViewById(R.id.textView4)).setText(ns);
    }

    public void reset(View v){
        DatabaseHandler dh = new DatabaseHandler(this);
        SQLiteDatabase db = dh.getWritableDatabase();
        dh.onReset(db);
        Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
