package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.managePost;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class addImageUni extends AppCompatActivity {

    private String uname;
    private Button postBtn;
    private int SELECT_PICTURE = 200;
    private Bitmap selectedImageBitmap = null;
    private ByteArrayOutputStream byteArrayOutputStream=null;
    private byte[] bytesImage=null;
    private String encodedImage=null;
    //University obj;
    private managePost m;
    private EditText et;
    private String desc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image_uni);

        uname = getIntent().getExtras().getString("name");

        m = new managePost();

        postBtn = findViewById(R.id.post);
        et = findViewById(R.id.imagedesc);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
                /*Intent in = new Intent(addImageUni.this, homePageUni.class);
                startActivity(in);*/
            }

        });


    }

    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);

                            byteArrayOutputStream = new ByteArrayOutputStream();
                            selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                            bytesImage = byteArrayOutputStream.toByteArray();
                            encodedImage = Base64.encodeToString(bytesImage, Base64.DEFAULT);

                            desc = et.getText().toString();
                            if(desc.length() != 0) {
                                m.connectToDb(addImageUni.this);
                                m.insertImage(addImageUni.this, uname, encodedImage, desc);
                            }
                            else
                            {
                                  Toast.makeText(this, "Must add a caption", Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

}