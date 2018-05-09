package com.example.n0582158.signlanguageapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class information1 extends AppCompatActivity {

    ImageView red, green, yellow, blue, grey, eraser, number;
    DrawingView dv;
    private static Paint paint;
    String selection;

    ImageButton play_action1, next_action1, back_action1;
    MediaPlayer mySound;
    final Context c = this;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();


    private int current_image_index;
    int[] images = {R.drawable.one, R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
            R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine};


    private int sounds;
    private int[] sounds_num = { R.raw.one, R.raw.one, R.raw.two, R.raw.three, R.raw.four, R.raw.five, R.raw.six, R.raw.seven,
            R.raw.eight, R.raw.nine};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information1);

        red = (ImageView) findViewById(R.id.red);
        blue = (ImageView) findViewById(R.id.blue);
        grey = (ImageView) findViewById(R.id.grey);
        green = (ImageView) findViewById(R.id.green);
        yellow = (ImageView) findViewById(R.id.yellow);
        eraser = (ImageView) findViewById(R.id.eraser);
        number = (ImageView) findViewById(R.id.number);
        play_action1 = (ImageButton) findViewById(R.id.play_action1);
        next_action1 = (ImageButton)findViewById(R.id.next_action1);
        back_action1 = (ImageButton)findViewById(R.id.back_action1);


        next_action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                current_image_index++;
                current_image_index = current_image_index % images.length;
                number.setImageResource(images[current_image_index]);


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
                number.setImageResource(images[current_image_index]);


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






        dv = new DrawingView(this);

        final RelativeLayout lay = (RelativeLayout) findViewById(R.id.draw);
        lay.addView(dv);

        RelativeLayout relay = (RelativeLayout) findViewById(R.id.draw);
        relay.setBackgroundResource(R.drawable.chalkboard);


        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(6);


        eraser.setOnClickListener(new View.OnClickListener() {
            int debut;

            @Override
            public void onClick(View v) {
                if (debut == 0) {
                    debut = 1;
                } else {
                    dv.clearDrawing();
                }
            }
        });


        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Colour red chosen !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.BLUE);
                Toast.makeText(getApplicationContext(), "Colour blue chosen !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.YELLOW);
                Toast.makeText(getApplicationContext(), "Colour yellow chosen !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.GREEN);
                Toast.makeText(getApplicationContext(), "Colour green chosen !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        grey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.GRAY);
                Toast.makeText(getApplicationContext(), "Colour grey chosen !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setTitle("Painting Numbers"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // f


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public boolean onSupportNavigateUp() {
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

                Intent intent = new Intent(information1.this, MainActivity.class);
                information1.this.startActivity(intent);

                return true;


            case R.id.info:

                intent = new Intent(information1.this, info.class);
                information1.this.startActivity(intent);

                return true;

            case R.id.about:

                intent = new Intent(information1.this, about.class);
                information1.this.startActivity(intent);

                return true;


            case R.id.exit:

                listItems = getResources().getStringArray(R.array.Languages);
                checkedItems = new boolean[listItems.length];

                AlertDialog.Builder builder = new AlertDialog.Builder(information1.this);
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

                                LocaleHelper.setLocale(information1.this, "en");
                                information1.this.recreate();
                                break;

                            case 1:

                                selection = listItems[arg1];

                                LocaleHelper.setLocale(information1.this, "fr");
                                information1.this.recreate();
                                break;

                            case 2:
                                selection = listItems[arg1];

                                LocaleHelper.setLocale(information1.this, "ar");
                                information1.this.recreate();
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


    public static class DrawingView extends View {

        public int width;
        public int height;
        public Bitmap mBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mBitmapPaint;
        Context context;
        private Paint circlePaint;
        private Path circlePath;

        public DrawingView(Context c) {
            super(c);
            context = c;
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            circlePaint = new Paint();
            circlePath = new Path();
            circlePaint.setAntiAlias(true);
            circlePaint.setColor(Color.BLACK);
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeJoin(Paint.Join.MITER);
            circlePaint.setStrokeWidth(4f);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath(mPath, paint);
            canvas.drawPath(circlePath, circlePaint);
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;

                circlePath.reset();
                circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            circlePath.reset();
            mCanvas.drawPath(mPath, paint);
            mPath.reset();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }

        public void clearDrawing() {

            setDrawingCacheEnabled(false);


            onSizeChanged(width, height, width, height);
            invalidate();

            setDrawingCacheEnabled(true);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            width = w;      // don't forget these
            height = h;

            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }


    }


    public void paint_colors(){

        listItems = getResources().getStringArray(R.array.paint_colors);
        checkedItems = new boolean[listItems.length];

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Choose an option...").setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                switch (arg1) {

                    case 0:
                        selection = listItems[arg1];
                        Toast.makeText(getApplicationContext(), "Color has been chosen!",
                                Toast.LENGTH_LONG).show();

                        paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setDither(true);
                        paint.setColor(Color.GRAY);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeJoin(Paint.Join.ROUND);
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setStrokeWidth(8);

                        break;

                    case 1:
                        selection = listItems[arg1];

                        paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setDither(true);
                        paint.setColor(Color.BLUE);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeJoin(Paint.Join.ROUND);
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setStrokeWidth(8);

                        break;

                    case 2:
                        selection = listItems[arg1];

                        paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setDither(true);
                        paint.setColor(Color.GREEN);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeJoin(Paint.Join.ROUND);
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setStrokeWidth(8);

                        break;

                    case 3:
                        selection = listItems[arg1];

                        paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setDither(true);
                        paint.setColor(Color.RED);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeJoin(Paint.Join.ROUND);
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setStrokeWidth(8);
                        break;

                    case 4:
                        selection = listItems[arg1];

                        paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setDither(true);
                        paint.setColor(Color.YELLOW);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeJoin(Paint.Join.ROUND);
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setStrokeWidth(8);
                        break;

                    case 5:
                        selection = listItems[arg1];

                        eraser.setOnClickListener(new View.OnClickListener() {
                            int debut;

                            @Override
                            public void onClick(View v) {
                                if (debut == 0) {
                                    debut = 1;
                                } else {
                                    dv.clearDrawing();
                                }
                            }
                        });
                        break;

                }
            }
        });

        mBuilder.setNeutralButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        AlertDialog mdialog = mBuilder.create();
        mdialog.show();
    }



}
