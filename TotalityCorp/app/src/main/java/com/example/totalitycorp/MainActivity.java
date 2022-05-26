package com.example.totalitycorp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // One Button
    Button BSelectImage;
    Button BCaptureImage;

    // One Preview Image
    ImageView IVPreviewImage;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // register the UI widgets with their appropriate IDs
        BSelectImage = findViewById(R.id.BSelectImage);
        BCaptureImage = findViewById(R.id.BCaptureImage);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);

        // handle the Choose Image button to trigger
        // the image chooser function
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        //handle the Capture Image button to trigger
        // the image capture function
        BCaptureImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {   i = 1;
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera_intent, SELECT_PICTURE);
                }
            });
    }

    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {

                // BitMap is data structure of image file
                // which stor the image in memory
                if (i == 1){
                    Bitmap photo = (Bitmap)data.getExtras().get("data");
                    // Set the image in imageview for display
                    IVPreviewImage.setImageBitmap(photo);
                }
                else{
                    // Get the url of the image from data
                    Uri selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        // update the preview image in the layout
                        IVPreviewImage.setImageURI(selectedImageUri);
                    }
                }
            }
        }
    }
}
