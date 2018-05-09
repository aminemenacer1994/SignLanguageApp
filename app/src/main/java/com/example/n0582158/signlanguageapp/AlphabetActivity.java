package com.example.n0582158.signlanguageapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlphabetActivity extends AppCompatActivity {

    ImageView draw, infoo, full_screen;
    ImageButton play_action1, next_action1, back_action1;


    final Context c = this;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    View view;
    String selection;

    private int current_image_index;
    MediaPlayer mySound;



    int[] images = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
            R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n,
            R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t,
            R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x,
            R.drawable.y, R.drawable.z};


    private int sounds;
    private int[] sounds_num = { R.raw.a, R.raw.b, R.raw.c, R.raw.d, R.raw.e, R.raw.f, R.raw.g,
            R.raw.h, R.raw.i, R.raw.j, R.raw.k, R.raw.l, R.raw.m, R.raw.n, R.raw.o, R.raw.p,
            R.raw.q, R.raw.r, R.raw.s, R.raw.t, R.raw.u, R.raw.v, R.raw.w, R.raw.x,
            R.raw.y, R.raw.z};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_lessons);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setTitle("Alphabet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        full_screen = (ImageView) findViewById(R.id.full_screen);
        play_action1 = (ImageButton) findViewById(R.id.play_action1);
        next_action1 = (ImageButton)findViewById(R.id.next_action1);
        back_action1 = (ImageButton)findViewById(R.id.back_action1);
        draw = (ImageView)findViewById(R.id.draw);
        infoo = (ImageView)findViewById(R.id.infoo);


        next_action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                current_image_index++;
                current_image_index = current_image_index % images.length;
                draw.setImageResource(images[current_image_index]);

                sounds++;
                sounds = sounds % sounds_num.length;
                mySound = MediaPlayer.create(getApplicationContext(), sounds_num[sounds]);
                mySound.start();

            }
        });


        back_action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                current_image_index--;
                if (current_image_index < 0)
                    current_image_index = images.length;
                draw.setImageResource(images[current_image_index]);


                if (sounds < 0)
                    sounds = sounds_num.length;
                sounds--;

                mySound = MediaPlayer.create(getApplicationContext(), sounds_num[sounds]);
                mySound.start();

            }
        });


        play_action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mySound = MediaPlayer.create(getApplicationContext(), sounds_num[sounds]);
                mySound.start();

            }
        });


        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        infoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert();
            }
        });




        class OnSwipeTouchListener implements View.OnTouchListener {

            private final GestureDetector gestureDetector;

            public OnSwipeTouchListener(Context ctx) {
                gestureDetector = new GestureDetector(ctx, new GestureListener());
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }

            final class GestureListener extends GestureDetector.SimpleOnGestureListener {

                private static final int SWIPE_THRESHOLD = 100;
                private static final int SWIPE_VELOCITY_THRESHOLD = 100;

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    boolean result = false;
                    try {
                        float diffY = e2.getY() - e1.getY();
                        float diffX = e2.getX() - e1.getX();
                        if (Math.abs(diffX) > Math.abs(diffY)) {
                            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                                if (diffX > 0) {
                                    onSwipeRight();
                                } else {
                                    onSwipeLeft();
                                }
                            }
                            result = true;
                        } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                onSwipeBottom();
                            } else {
                                onSwipeTop();
                            }
                        }
                        result = true;

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    return result;
                }
            }

            public void onSwipeRight() {

            }

            public void onSwipeLeft() {

            }

            public void onSwipeTop() {

            }

            public void onSwipeBottom() {

            }
        }

        full_screen.setOnTouchListener(new OnSwipeTouchListener(AlphabetActivity.this) {


            public void onSwipeRight() {
                next_action1.callOnClick();
            }

            public void onSwipeLeft() {
                back_action1.callOnClick();
            }

        });

    }




    public void alert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("About the App");
        alertDialogBuilder.setMessage("This section will teach the user the signs of each letter including sound");
        alertDialogBuilder.setMessage("Swipe right for next letter or swipe left for previous letter");
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
    public boolean onSupportNavigateUp(){
        finish();
        return true;
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

                Intent intent = new Intent(AlphabetActivity.this, MainActivity.class);
                AlphabetActivity.this.startActivity(intent);

                return true;



            case R.id.info:

                intent = new Intent(AlphabetActivity.this, info.class);
                AlphabetActivity.this.startActivity(intent);

                return true;

            case R.id.about:

                intent = new Intent(AlphabetActivity.this, about.class);
                AlphabetActivity.this.startActivity(intent);

                return true;


            case R.id.exit:

                listItems = getResources().getStringArray(R.array.Languages);
                checkedItems = new boolean[listItems.length];

                AlertDialog.Builder builder = new AlertDialog.Builder(AlphabetActivity.this);
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

                                LocaleHelper.setLocale(AlphabetActivity.this, "en");
                                AlphabetActivity.this.recreate();
                                break;

                            case 1:

                                selection = listItems[arg1];

                                LocaleHelper.setLocale(AlphabetActivity.this, "fr");
                                AlphabetActivity.this.recreate();
                                break;

                            case 2:
                                selection = listItems[arg1];

                                LocaleHelper.setLocale(AlphabetActivity.this, "ar");
                                AlphabetActivity.this.recreate();
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
