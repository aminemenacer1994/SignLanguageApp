package com.example.n0582158.signlanguageapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.n0582158.signlanguageapp.R.id.brush;
import static com.example.n0582158.signlanguageapp.R.id.question;

public class MultipleChoiceAlph extends AppCompatActivity {


    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ImageView number_question;

    MediaPlayer mySound;
    MediaPlayer mySound1;


    List<StateModel> list;

    Random random;
    int turn = 1;
    final Context c = this;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_num);

        random = new Random();


        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        number_question = (ImageView)findViewById(R.id.number_question);

        list = new ArrayList<>();

        for(int i = 0; i < new QuestionMultipleNum().answers.length; i++){
            list.add(new StateModel(new QuestionMultipleNum().answers[i], new QuestionMultipleNum().numbers[i]));
        }

        Collections.shuffle(list);
        newQuestion(turn);

        mySound = MediaPlayer.create(this, R.raw.beep);
        mySound1 = MediaPlayer.create(this, R.raw.coolsmstone);


        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                if(button1.getText().toString().equalsIgnoreCase(list.get(turn -1).getName())) {
                    Toast.makeText(MultipleChoiceAlph.this, "Correct", Toast.LENGTH_SHORT).show();
                    mySound1.start();
                    if (turn < list.size()) {
                        turn++;
                        newQuestion(turn);
                    }

                }else{
                    Toast.makeText(MultipleChoiceAlph.this, "Incorrect, please try again", Toast.LENGTH_SHORT).show();
                    mySound.start();

                }
            }

        });


        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(button2.getText().toString().equalsIgnoreCase(list.get(turn -1).getName())) {
                    Toast.makeText(MultipleChoiceAlph.this, "Correct", Toast.LENGTH_SHORT).show();
                    mySound1.start();
                    if (turn < list.size()) {
                        turn++;
                        newQuestion(turn);
                    }

                }else{
                    Toast.makeText(MultipleChoiceAlph.this, "Incorrect, please try again", Toast.LENGTH_SHORT).show();
                    mySound.start();
                }

            }
        });


        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(button3.getText().toString().equalsIgnoreCase(list.get(turn -1).getName())) {
                    Toast.makeText(MultipleChoiceAlph.this, "Correct", Toast.LENGTH_SHORT).show();
                    mySound1.start();
                    if (turn < list.size()) {
                        turn++;
                        newQuestion(turn);
                    }

                }else{
                    Toast.makeText(MultipleChoiceAlph.this, "Incorrect", Toast.LENGTH_SHORT).show();
                    mySound.start();
                }


            }
        });


        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(button4.getText().toString().equalsIgnoreCase(list.get(turn -1).getName())) {
                    Toast.makeText(MultipleChoiceAlph.this, "Correct", Toast.LENGTH_SHORT).show();
                    mySound1.start();
                    if (turn < list.size()) {
                        turn++;
                        newQuestion(turn);
                    }

                }else{
                    Toast.makeText(MultipleChoiceAlph.this, "Incorrect", Toast.LENGTH_SHORT).show();
                    mySound.start();
                }

            }
        });


    }


    public void gamess(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Multiple choices");
        alertDialogBuilder.setMessage("Game options");

        alertDialogBuilder.setPositiveButton("Play again",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(MultipleChoiceAlph.this, MultipleChoiceAlph.class);
                        MultipleChoiceAlph.this.startActivity(intent);

                    }
                });

        alertDialogBuilder.setNegativeButton("Back to game options",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MultipleChoiceAlph.this, gamesOptions.class);
                        MultipleChoiceAlph.this.startActivity(intent);

                    }
                });

        alertDialogBuilder.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    private void newQuestion(int number){

        number_question.setImageResource(list.get(number -1).getImage());

        int correct_answer = random.nextInt(4) + 1;

        int firstButton = number - 1;
        int secondButton;
        int thirdButton;
        int forthButton;

        switch (correct_answer){

            case 1:
                button1.setText(list.get(firstButton).getName());

                do{
                    secondButton = random.nextInt(list.size());
                } while (secondButton == firstButton);

                do {
                    thirdButton = random.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);

                do {
                    forthButton = random.nextInt(list.size());
                } while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                button2.setText(list.get(firstButton).getName());
                button3.setText(list.get(firstButton).getName());
                button4.setText(list.get(firstButton).getName());

                break;

            case 2:

                button2.setText(list.get(firstButton).getName());

                do{
                    secondButton = random.nextInt(list.size());
                } while (firstButton == secondButton);

                do {
                    thirdButton = random.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);

                do {
                    forthButton = random.nextInt(list.size());
                } while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                button1.setText(list.get(secondButton).getName());
                button3.setText(list.get(thirdButton).getName());
                button4.setText(list.get(forthButton).getName());

                break;

            case 3:

                button3.setText(list.get(firstButton).getName());

                do{
                    secondButton = random.nextInt(list.size());
                } while (firstButton == secondButton);

                do {
                    thirdButton = random.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);

                do {
                    forthButton = random.nextInt(list.size());
                } while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                button1.setText(list.get(secondButton).getName());
                button2.setText(list.get(thirdButton).getName());
                button4.setText(list.get(forthButton).getName());

                break;

            case 4:

                button4.setText(list.get(firstButton).getName());

                do{
                    secondButton = random.nextInt(list.size());
                } while (firstButton == secondButton);

                do {
                    thirdButton = random.nextInt(list.size());
                } while (thirdButton == firstButton || thirdButton == secondButton);

                do {
                    forthButton = random.nextInt(list.size());
                } while (forthButton == firstButton || forthButton == secondButton || forthButton == thirdButton);

                button1.setText(list.get(secondButton).getName());
                button3.setText(list.get(thirdButton).getName());
                button2.setText(list.get(forthButton).getName());
                break;

        }

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

                Intent intent = new Intent(MultipleChoiceAlph.this, MainActivity.class);
                MultipleChoiceAlph.this.startActivity(intent);

                return true;


            case R.id.info:

                intent = new Intent(MultipleChoiceAlph.this, info.class);
                MultipleChoiceAlph.this.startActivity(intent);

                return true;

            case R.id.about:

                intent = new Intent(MultipleChoiceAlph.this, about.class);
                MultipleChoiceAlph.this.startActivity(intent);

                return true;


            case R.id.exit:

                listItems = getResources().getStringArray(R.array.Languages);
                checkedItems = new boolean[listItems.length];

                AlertDialog.Builder builder = new AlertDialog.Builder(MultipleChoiceAlph.this);
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

                                LocaleHelper.setLocale(MultipleChoiceAlph.this, "en");
                                MultipleChoiceAlph.this.recreate();
                                break;

                            case 1:

                                selection = listItems[arg1];

                                LocaleHelper.setLocale(MultipleChoiceAlph.this, "fr");
                                MultipleChoiceAlph.this.recreate();
                                break;

                            case 2:
                                selection = listItems[arg1];

                                LocaleHelper.setLocale(MultipleChoiceAlph.this, "ar");
                                MultipleChoiceAlph.this.recreate();
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



