package com.example.rohit.tttfrag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rohit.tttfrag.friends.FriendContent;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity implements detailsFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //int defaultValue = getResources().getInteger(R.string.saved_high_score_default);
        String name = sharedPref.getString("name1234", null);

        if(name!=null){
            Log.v("Name","Stored");
            ((Button)findViewById(R.id.button2)).setVisibility(View.GONE);
            ((EditText)findViewById(R.id.saveNtext)).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.textView3)).setText(name);
            ((TextView)findViewById(R.id.textView3)).setVisibility(View.VISIBLE);

            String filename = "nameFile";
            String string = ((EditText)findViewById(R.id.saveNtext)).getText().toString()+"AGE 12 ";
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(string.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else
            Log.v("Name","Not stored");



    }
    public void onListFragmentInteraction(FriendContent.FriendData item){

    }
    public void addFrnd(View v){
        Log.v("Add Friend", "Called");
        Intent picIntent = new Intent(this,NewFriend.class);
        startActivityForResult(picIntent,1);

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        Log.v("Picture Taken", Integer.toString(resultCode));
        if(resultCode==0){

        }
            else {


            Bundle extras = data.getExtras();
            Log.v("Image path received", extras.get("src").toString());
            File imgFile = new File(extras.get("src").toString());

            if (imgFile.exists()) {

                Log.v("Image File", "exitst");

                Bitmap myBitmap = BitmapFactory.decodeFile((extras.get("src").toString()));

                ImageView myImage = (ImageView) findViewById(R.id.imageView2);

                myImage.setImageBitmap(myBitmap);

            } else {
                Log.v("Image File", "Does not exitst");
                Bitmap myBitmap = BitmapFactory.decodeFile((extras.get("src").toString()));

                ImageView myImage = (ImageView) findViewById(R.id.imageView2);

                myImage.setImageBitmap(myBitmap);

            }
            //ImageView)findViewById(R.id.imageView2).set
            //FriendContent.addItem(FriendContent.createDummyItem2("R.id.friends"));

            //getSupportFragmentManager().findFragmentById(R.id.frag1).onCreateView();

        }
    }
    public  void frndDetails(View v){
        Intent Frnd = new Intent(this,StoreFrndDetail.class);
        startActivityForResult(Frnd,1);

    }

    public void saveMyName(View v){
        Log.v("SaveMyname","Called");
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name1234", ((EditText)findViewById(R.id.saveNtext)).getText().toString());
        editor.commit();

        ((Button)findViewById(R.id.button2)).setVisibility(View.GONE);
        ((EditText)findViewById(R.id.saveNtext)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.textView3)).setText(((EditText)findViewById(R.id.saveNtext)).getText().toString());
        ((TextView)findViewById(R.id.textView3)).setVisibility(View.VISIBLE);

        String filename = "nameFile";
        String string = ((EditText)findViewById(R.id.saveNtext)).getText().toString()+"AGE 12 ";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
