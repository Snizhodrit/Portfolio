package com.example.trifonov463467;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.example.trifonov463467.Adapters.AlbumAdapter;
import com.example.trifonov463467.Model.*;
import com.example.trifonov463467.PopupWindows.AddAlbum;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    public static Admin admin = new Admin();
    public static AlbumAdapter albumAdapter;
    public static final String EXTRA_MESSAGE = "1";
    private Album albumToEdit;
    private static boolean appStarted = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!appStarted) {
            admin.generateData();
            appStarted = true;
        }
        Log.i("a", "onCreate: ");
        albumAdapter = new AlbumAdapter(this, admin.getAlbums());
    }

    public void onResume() {
        super.onResume();
        Log.i("b", "onResume: ");
        final ListView listView = findViewById(R.id.albumView);
        listView.setAdapter(albumAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Album album = admin.getAlbums().get(position);
                Intent intent = new Intent(MainActivity.this, displaySongs.class);
                intent.putExtra(EXTRA_MESSAGE, album.getName());
                startActivity(intent);
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopup(view);
                albumToEdit = admin.getAlbums().get(position);
                return true;
            }
        });

    }

    private void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_delete_item);
        popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_object:
                admin.remove(albumToEdit);
                albumAdapter.notifyDataSetChanged();
                return true;
            case R.id.edit_object:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.edit_element, null);


                final EditText newAlbumName = dialogView.findViewById(R.id.field1);
                final EditText newArtistName = dialogView.findViewById(R.id.field2);
                newAlbumName.setText(albumToEdit.getName());
                newArtistName.setText(albumToEdit.getArtist());
                newArtistName.setInputType(InputType.TYPE_CLASS_TEXT);
                newAlbumName.setHint(R.string.new_name);
                newArtistName.setHint(R.string.new_artist_name);
                Button saveChanges = dialogView.findViewById(R.id.edit_element_button);

                dialogBuilder.setView(dialogView);
                final AlertDialog dialog = dialogBuilder.create();

                saveChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!newAlbumName.getText().toString().trim().equals("") && !newArtistName.getText().toString().trim().equals("")) {
                            if(admin.findByName(newAlbumName.getText().toString().trim()) != null && admin.findByName(newAlbumName.getText().toString().trim()) != albumToEdit) {
                                String message = getString(R.string.album_with_name_exists);
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                            } else {
                                albumToEdit.setArtist(newArtistName.getText().toString().trim());
                                albumToEdit.setName(newAlbumName.getText().toString().trim());
                                for(Song song: albumToEdit.getSongs()) {
                                    song.setAlbumName(newAlbumName.getText().toString().trim());
                                }
                                albumAdapter.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        } else {
                            String message = getString(R.string.fill_fields);
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dialog.show();
                return true;
        default:
            return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_album:
                startActivity(new Intent(MainActivity.this, AddAlbum.class));
                return true;
            default:
                return false;
        }
    }
}
