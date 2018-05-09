package com.example.n0582158.signlanguageapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TrueOrFalseLetterResult extends AppCompatActivity {

    TextView mGrade, mFinalScore;
    Button mRetryButton, again;

    MediaPlayer mySound;

    final Context c = this;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_or_false_num_result);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setTitle("True or false (Results)"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // f

        mGrade = (TextView)findViewById(R.id.grade);
        mFinalScore = (TextView)findViewById(R.id.outOf);
        mRetryButton = (Button)findViewById(R.id.retry);
        again = (Button)findViewById(R.id.again);

        mySound = MediaPlayer.create(this, R.raw.party);


        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        mFinalScore.setText("You scored " + score + " out of " + QuestionNumberDiary.questions.length);

        if (score == 9){
            mGrade.setText("Outstanding");
            mySound.start();
        }else if (score == 8){
            mGrade.setText("Good Work");
            mySound.start();
        }else if (score == 7) {
            mGrade.setText("Good Effort");
            mySound.start();
        }else if (score == 5) {
            mGrade.setText("Not very good");
            mySound.start();
        }else {
            mGrade.setText("Go over the lessons again");
            mySound.start();
        }

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrueOrFalseLetterResult.this, TrueOrFalseAlphabet.class));
                TrueOrFalseLetterResult.this.finish();
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrueOrFalseLetterResult.this, gamesOptions.class));
                TrueOrFalseLetterResult.this.finish();

            }
        });

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

                Intent intent = new Intent(TrueOrFalseLetterResult.this, MainActivity.class);
                TrueOrFalseLetterResult.this.startActivity(intent);

                return true;


            case R.id.info:

                intent = new Intent(TrueOrFalseLetterResult.this, info.class);
                TrueOrFalseLetterResult.this.startActivity(intent);

                return true;

            case R.id.about:

                intent = new Intent(TrueOrFalseLetterResult.this, about.class);
                TrueOrFalseLetterResult.this.startActivity(intent);

                return true;


            case R.id.exit:

                listItems = getResources().getStringArray(R.array.Languages);
                checkedItems = new boolean[listItems.length];

                AlertDialog.Builder builder = new AlertDialog.Builder(TrueOrFalseLetterResult.this);
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

                                LocaleHelper.setLocale(TrueOrFalseLetterResult.this, "en");
                                TrueOrFalseLetterResult.this.recreate();
                                break;

                            case 1:

                                selection = listItems[arg1];

                                LocaleHelper.setLocale(TrueOrFalseLetterResult.this, "fr");
                                TrueOrFalseLetterResult.this.recreate();
                                break;

                            case 2:
                                selection = listItems[arg1];

                                LocaleHelper.setLocale(TrueOrFalseLetterResult.this, "ar");
                                TrueOrFalseLetterResult.this.recreate();
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
