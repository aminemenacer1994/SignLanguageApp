package com.example.n0582158.signlanguageapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class videoOptions extends AppCompatActivity {

    ImageView animal, dates, food, color;

    final Context c = this;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_options);

        getSupportActionBar().setTitle("Video options"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // f

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        animal = (ImageView)findViewById(R.id.animal);
        dates = (ImageView)findViewById(R.id.dates);
        food = (ImageView)findViewById(R.id.food);
        color = (ImageView)findViewById(R.id.color);

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(videoOptions.this, VideoList.class);
                videoOptions.this.startActivity(intent);
            }
        });

        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(videoOptions.this, VideoListDates.class);
                videoOptions.this.startActivity(intent);

            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(videoOptions.this, VideoListFood.class);
                videoOptions.this.startActivity(intent);

            }
        });

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(videoOptions.this, VideoListColor.class);
                videoOptions.this.startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {



            case R.id.home:

                Intent intent = new Intent(videoOptions.this, MainActivity.class);
                videoOptions.this.startActivity(intent);

                return true;



            case R.id.info:

                intent = new Intent(videoOptions.this, info.class);
                videoOptions.this.startActivity(intent);

                return true;

            case R.id.about:

                intent = new Intent(videoOptions.this, about.class);
                videoOptions.this.startActivity(intent);

                return true;





            case R.id.exit:

                listItems = getResources().getStringArray(R.array.Languages);
                checkedItems = new boolean[listItems.length];

                AlertDialog.Builder builder = new AlertDialog.Builder(videoOptions.this);
                builder.setTitle("Sign language");
                builder.setMessage("Exit !");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        System.exit(0);

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                Dialog dialog = builder.create();
                dialog.show();



                return true;

            case R.id.languages:

                listItems = getResources().getStringArray(R.array.Languages);
                checkedItems = new boolean[listItems.length];

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
                mBuilder.setTitle("Choose a language...").setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        switch (arg1) {

                            case 0:

                                selection = listItems[arg1];

                                LocaleHelper.setLocale(videoOptions.this, "en");
                                videoOptions.this.recreate();
                                break;

                            case 1:

                                selection = listItems[arg1];

                                LocaleHelper.setLocale(videoOptions.this, "fr");
                                videoOptions.this.recreate();
                                break;

                            case 2:
                                selection = listItems[arg1];

                                LocaleHelper.setLocale(videoOptions.this, "ar");
                                videoOptions.this.recreate();
                                break;


                        }
                    }
                })

                        .setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });


                AlertDialog mdialog = mBuilder.create();
                mdialog.show();



                return true;


            default:
                return super.onOptionsItemSelected(item);

        }

    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
