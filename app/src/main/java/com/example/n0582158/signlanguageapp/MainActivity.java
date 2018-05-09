package com.example.n0582158.signlanguageapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import static android.R.attr.button;


// references

// https://www.youtube.com/watch?v=Xk8HsXyICog
// https://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-touch-interaction--mobile-19202
// https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
// https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures


public class MainActivity extends AppCompatActivity {


    ImageView lessons, games, paint, video;
    final Context c = this;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        lessons = (ImageView)findViewById(R.id.lessons);
        games = (ImageView)findViewById(R.id.games);
        paint = (ImageView)findViewById(R.id.paint);
        video = (ImageView)findViewById(R.id.video);

        lessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LessonsOptions.class);
                MainActivity.this.startActivity(intent);
                lessons.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise));


            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, gamesOptions.class);
                MainActivity.this.startActivity(intent);
                games.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise));


            }
        });

        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, paintOptions.class);
                MainActivity.this.startActivity(intent);
                paint.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise));


            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, videoOptions.class);
                MainActivity.this.startActivity(intent);
                video.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise));


            }
        });

    }




    public void alert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("About the App");
        alertDialogBuilder.setMessage("This application is designed for user who would like to learn the " +
                "basics of sign language " +
                "& and provide simple exercises to test your knowledge ");

        alertDialogBuilder.setNeutralButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(intent);

                return true;



            case R.id.info:

                intent = new Intent(MainActivity.this, info.class);
                MainActivity.this.startActivity(intent);

                return true;

            case R.id.about:

                intent = new Intent(MainActivity.this, about.class);
                MainActivity.this.startActivity(intent);

                return true;


            case R.id.exit:

                listItems = getResources().getStringArray(R.array.Languages);
                checkedItems = new boolean[listItems.length];

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

                                LocaleHelper.setLocale(MainActivity.this, "en");
                                MainActivity.this.recreate();
                                break;

                            case 1:

                                selection = listItems[arg1];

                                LocaleHelper.setLocale(MainActivity.this, "fr");
                                MainActivity.this.recreate();
                                break;

                            case 2:
                                selection = listItems[arg1];

                                LocaleHelper.setLocale(MainActivity.this, "ar");
                                MainActivity.this.recreate();
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



}
