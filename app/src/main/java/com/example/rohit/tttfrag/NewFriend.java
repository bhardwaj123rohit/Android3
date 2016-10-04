package com.example.rohit.tttfrag;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.duration;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class NewFriend extends AppCompatActivity {

    static int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    Intent newData;
    String imageSrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        if (savedInstanceState != null) {


            Bitmap myBitmap = BitmapFactory.decodeFile(savedInstanceState.getString("imgsrc"));

            ImageView myImage = (ImageView) findViewById(R.id.imageView);

            myImage.setImageBitmap(myBitmap);
            newData = new Intent();
            newData.putExtra("uname",((EditText)findViewById(R.id.editText)).getText().toString());
            setResult(RESULT_OK,newData);
            newData.putExtra("src",savedInstanceState.getString("imgsrc"));
            imageSrc = savedInstanceState.getString("imgsrc");
            setResult(RESULT_OK,newData);

        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString("imgsrc",imageSrc );

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    public void picPlease(View v){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        Log.v("Intent Pic Sent","Started");

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
              /*  Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.rohit.tttfrag.fileprovider",
                        photoFile);
                */
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoPath);
                //Log.v("Intent Pic Sent",photoURI.toString());
                Log.v("PhotoPath",mCurrentPhotoPath);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }



    }
    public void saveFrnd(View v){

        if(newData == null){
            Toast.makeText(this, "Please take Picture", Toast.LENGTH_SHORT).show();
        }else if(((EditText)findViewById(R.id.editText)).getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please Enter a name ", Toast.LENGTH_SHORT).show();
        }
        else{
            newData.putExtra("uname",((EditText)findViewById(R.id.editText)).getText().toString());
            setResult(RESULT_OK,newData);
            Log.v("Friend Saved","Yes");
            finish();

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("Image Received",Integer.toString(resultCode));

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.v("BitMap","Yes");

            String root = getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
            File myDir = new File(root + "/saved_images");
            myDir.mkdirs();

            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            File destination = new File(myDir,System.currentTimeMillis() + ".jpg");

            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
                Log.v("ImagFiles Stored", destination.getPath());
            newData = new Intent();
            newData.putExtra("src",destination.getPath());
            imageSrc=destination.getPath();

                ImageView mImageView = (ImageView) findViewById(R.id.imageView);
                mImageView.setImageBitmap(imageBitmap);
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir =  getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
