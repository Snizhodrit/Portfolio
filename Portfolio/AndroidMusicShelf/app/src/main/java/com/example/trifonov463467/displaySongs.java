package com.example.trifonov463467;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.example.trifonov463467.Adapters.SongAdapter;
import com.example.trifonov463467.Model.Album;
import com.example.trifonov463467.Model.Song;
import com.example.trifonov463467.PopupWindows.AddSong;
import static com.example.trifonov463467.MainActivity.EXTRA_MESSAGE;
import static com.example.trifonov463467.MainActivity.admin;

public class displaySongs extends AppCompatActivity {

    public static SongAdapter songAdapter;
    private Album album;
    private Song songToEdit;
    public static final String EXTRA_MESSAGE_SONG = "a";
    private Double albumLength;
    private CustomDrawing customDrawing;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_songs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        album = admin.findByName(getIntent().getExtras().getString(EXTRA_MESSAGE));
    }
    @Override
    protected void onResume() {
        super.onResume();
        albumLength = 0.0;
        for(Song song: album.getSongs()) {
            albumLength += song.getLength();
            albumLength = Math.round(albumLength * 100.0) / 100.0;
        }
        customDrawing = findViewById(R.id.customDrawingAlbum);
        if(albumLength != 0.00) {
            customDrawing.setEmptyAlbum(false);
            textView = findViewById(R.id.albumLength);
            textView.setText(Double.toString(albumLength));
        }

        songAdapter = new SongAdapter(this, album.getSongs());
        ListView listView = findViewById(R.id.songView);
        listView.setAdapter(songAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = album.getSongs().get(position);
                Intent intent = new Intent(displaySongs.this, SongDetails.class);
                intent.putExtra(EXTRA_MESSAGE_SONG, song);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopup(view);
                songToEdit = album.getSongs().get(position);
                return true;
            }
        });

    }

    private void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_delete_item);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete_object:
                        albumLength -= songToEdit.getLength();
                        admin.removeSong(songToEdit);
                        albumLength = Math.round(albumLength * 100.0) / 100.0;
                        textView.setText(Double.toString(albumLength));
                        if(albumLength == 0.00) {
                            customDrawing.setEmptyAlbum(true);
                            textView.setText("");
                        }
                        songAdapter.notifyDataSetChanged();
                        return true;

                    case R.id.edit_object:
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(displaySongs.this);
                        View dialogView = getLayoutInflater().inflate(R.layout.edit_element, null);

                        final EditText newSongName = dialogView.findViewById(R.id.field1);
                        final EditText newSongLength = dialogView.findViewById(R.id.field2);
                        newSongName.setText(songToEdit.getName());
                        newSongLength.setText(Double.toString(songToEdit.getLength()));
                        newSongName.setHint(R.string.new_name);
                        newSongLength.setHint(R.string.new_song_length);
                        Button saveChanges = dialogView.findViewById(R.id.edit_element_button);

                        dialogBuilder.setView(dialogView);
                        final AlertDialog dialog = dialogBuilder.create();

                        saveChanges.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                double length = 0.1;
                                boolean invalidSongLength = false;

                                try {
                                    length = Double.parseDouble(newSongLength.getText().toString());
                                } catch (Exception e) {
                                    invalidSongLength = true;
                                }

                                String newName = newSongName.getText().toString().trim();

                                if(newName.equals("") || newSongLength.getText().toString().equals("")) {
                                    String message = getString(R.string.fill_fields);
                                    Toast.makeText(displaySongs.this, message, Toast.LENGTH_LONG).show();
                                } else if(invalidSongLength) {
                                    String message = getString(R.string.enter_number);
                                    Toast.makeText(displaySongs.this, message, Toast.LENGTH_LONG).show();
                                } else {
                                    if(album.findByName(newName) != null && album.findByName(newName) != songToEdit) {
                                        String message = getString(R.string.song_with_name_exists);
                                        Toast.makeText(displaySongs.this, message, Toast.LENGTH_LONG).show();
                                    } else {
                                        songToEdit.setName(newName);
                                        albumLength -= songToEdit.getLength();
                                        songToEdit.setLength(length);
                                        albumLength += length;
                                        albumLength = Math.round(albumLength * 100.0) / 100.0;
                                        TextView textView = findViewById(R.id.albumLength);
                                        textView.setText(Double.toString(albumLength));
                                        songAdapter.notifyDataSetChanged();
                                        dialog.cancel();
                                    }
                                }
                            }
                        });
                        dialog.show();
                        return true;
                    default:
                        return false;
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_songs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_song:
                Intent  intent = new Intent(displaySongs.this, AddSong.class);
                intent.putExtra(EXTRA_MESSAGE_SONG, album.getName());
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

}
