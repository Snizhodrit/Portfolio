package com.example.trifonov463467.PopupWindows;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.trifonov463467.Model.Album;
import com.example.trifonov463467.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static com.example.trifonov463467.MainActivity.admin;
import static com.example.trifonov463467.MainActivity.albumAdapter;

public class AddAlbum extends AppCompatActivity {
    private static final int GALLERY_ACTIVITY = 1;
    private String photo;
    private ImageView imageView;
    private Bitmap bitmap;
    private static int imageCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            getWindow().setLayout((int) (width*0.8), (int) (height*0.6));

        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getWindow().setLayout((int) (width*0.8), (int) (height*0.7));
        }
        imageView = findViewById(R.id.imageView);
    }

    public void onAddAlbumClick(View v) {
        CheckBox checkBox = findViewById(R.id.checkBox);
        EditText albumName = findViewById(R.id.enterAlbumName);
        EditText artistName = findViewById(R.id.enterArtistName);
        String name = albumName.getText().toString().trim();
        String artist = artistName.getText().toString().trim();

        if(photo != null && !name.equals("") && !artist.equals("") && checkBox.isChecked()) {
            if(admin.findByName(name) != null) {
                String message = getString(R.string.album_with_name_exists);
                Toast.makeText(AddAlbum.this, message, Toast.LENGTH_LONG).show();
            } else {
                Album album = new Album(name, artist, bitmap);
                admin.addAlbum(album);
                albumAdapter.notifyDataSetChanged();
                finish();
            }
        } else if(!name.equals("") && !artist.equals("") && !checkBox.isChecked()){
            if(admin.findByName(name) != null) {
                String message = getString(R.string.album_with_name_exists);
                Toast.makeText(AddAlbum.this, message, Toast.LENGTH_LONG).show();
            } else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
                Album album = new Album(name, artist, bitmap);
                admin.addAlbum(album);
                albumAdapter.notifyDataSetChanged();
                finish();
            }
        } else {
            String message = getString(R.string.fill_fields);
            Toast.makeText(AddAlbum.this, message, Toast.LENGTH_LONG).show();
        }
    }

    public void onImageButtonClick(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, GALLERY_ACTIVITY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
                this.photo = saveToInternalStorage(bitmap);
            }
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        File directory = cw.getDir("newDir", Context.MODE_PRIVATE);
        File myPath = new File(directory, "image" + (imageCode + 1) + ".jpg");

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(myPath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }
}