package com.example.proyectofinal3.Registro;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal3.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class uploadImage  extends AppCompatActivity {


/*
    public class MainActivity {
        Button elegirImg, subirImg;
        ImageView imagenView;
        int PICK_IMAGE_REQUEST = 111;
        Uri filePath;
        ProgressDialog pd;

        //creating reference to firebase storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://recestascatrachasapp.appspot.com");    //change the url according to your firebase app


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            elegirImg = (Button)findViewById(R.id.elegirImg);
            subirImg = (Button)findViewById(R.id.subirImg);
            imagenView = (ImageView)findViewById(R.id.imgView);

            pd = new ProgressDialog(this);
            pd.setMessage("Subiendo....");

            elegirImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    //intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
                }
            });

            subirImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(filePath != null) {
                        pd.show();

                        StorageReference childRef = storageRef.child("image.jpg");

                        //uploading the image
                        UploadTask uploadTask = childRef.putFile(filePath);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                pd.dismiss();
                                Toast.makeText(MainActivity.this, "Subida exitosamente", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pd.dismiss();
                                Toast.makeText(MainActivity.this, "Problema al subir -> " + e, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Seleccionar una imagen", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                filePath = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                    imagenView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}
