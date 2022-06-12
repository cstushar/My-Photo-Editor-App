package com.example.totalitycorp;

import static com.example.totalitycorp.MainActivity.imgUri;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.example.totalitycorp.databinding.ActivityFinalBinding;

public class FinalActivity extends AppCompatActivity {
    ActivityFinalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFinalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    // If the input image uri for DS Photo Editor is "inputImageUri", launch the editor UI
    // using the following code
    Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);

    dsPhotoEditorIntent.setData(imgUri);

        // This is optional. By providing an output directory, the edited photo
        // will be saved in the specified folder on your device's external storage;
        // If this is omitted, the edited photo will be saved to a folder
    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Photo Editor");

    // Optional customization: hide some tools you don't need as below
    // int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};
    //dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
        startActivityForResult(dsPhotoEditorIntent,200);
}

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 200:
                    Uri outputUri = data.getData();
                    // handle the result uri as you want, such as display it in an imageView;
                    binding.imgView.setImageURI(outputUri);
                    break;
            }
        }
    }
}