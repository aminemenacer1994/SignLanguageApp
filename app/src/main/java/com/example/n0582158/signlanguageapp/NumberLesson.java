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

import java.util.ArrayList;

public class NumberLesson extends AppCompatActivity {

    ImageView draw, infoo, full_screen;
    ImageButton play_action1, next_action1, back_action1;



    MediaPlayer mySound;



    private int current_image_index;
    int[] images = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
            R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine};


    private int sounds;
    private int[] sounds_num = { R.raw.one, R.raw.two, R.raw.three, R.raw.four, R.raw.five, R.raw.six, R.raw.seven,
                            R.raw.eight, R.raw.nine};

    final Context c = this;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    View view;
    String selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_lesson);

        getSupportActionBar().setTitle("Numbers"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // f

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        draw = (ImageView)findViewById(R.id.draw);

        play_action1 = (ImageButton) findViewById(R.id.play_action1);
        next_action1 = (ImageButton)findViewById(R.id.next_action1);
        back_action1 = (ImageButton)findViewById(R.id.back_action1);
        full_screen = (ImageView) findViewById(R.id.full_screen);
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


        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

        full_screen.setOnTouchListener(new OnSwipeTouchListener(NumberLesson.this) {




            public void onSwipeRight() {
                next_action1.callOnClick();
            }

            public void onSwipeLeft() {
                back_action1.callOnClick();
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
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {



            case R.id.home:

                Intent intent = new Intent(NumberLesson.this, MainActivity.class);
                NumberLesson.this.startActivity(intent);

                return true;



            case R.id.info:

                intent = new Intent(NumberLesson.this, info.class);
                NumberLesson.this.startActivity(intent);

                return true;

            case R.id.about:

                intent = new Intent(NumberLesson.this, about.class);
                NumberLesson.this.startActivity(intent);

                return true;





            case R.id.exit:

                listItems = getResources().getStringArray(R.array.Languages);
                checkedItems = new boolean[listItems.length];

                AlertDialog.Builder builder = new AlertDialog.Builder(NumberLesson.this);
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

                                LocaleHelper.setLocale(NumberLesson.this, "en");
                                NumberLesson.this.recreate();
                                break;

                            case 1:

                                selection = listItems[arg1];

                                LocaleHelper.setLocale(NumberLesson.this, "fr");
                                NumberLesson.this.recreate();
                                break;

                            case 2:
                                selection = listItems[arg1];

                                LocaleHelper.setLocale(NumberLesson.this, "ar");
                                NumberLesson.this.recreate();
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


    public void alert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("About the App");
        alertDialogBuilder.setMessage("This section will teach the user the signs of each number including sound");
        alertDialogBuilder.setMessage("Swipe right for next number or swipe left for previous number");
        alertDialogBuilder.setNeutralButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
