package com.example.izumin.mymentalcalccountdown;

import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mCountTxt;
    MyCountDownTimer mTimer;
    Random r = new java.util.Random();
    String[] s = {"+","-","*","÷"};
    int correctCnt,falseCnt;
    SoundPool mSoundPool;
    int mSoundResId;

    Button mAnsbtn1;
    Button mAnsbtn2;
    Button mAnsbtn3;
    Button mAnsbtn4;


    public class MyCountDownTimer extends CountDownTimer {
        final FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.fab);
        public boolean isRunning = false;

        public MyCountDownTimer(long millisInFuture,long countDownInterval){
            super(millisInFuture,countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished){
            ImageView mDora = (ImageView)findViewById(R.id.dora);
            long minute = millisUntilFinished / 1000 / 60;
            long second = millisUntilFinished / 1000 % 60;
            mCountTxt.setText(String.format("%1d:%2$02d",minute,second));

            if(minute < 2){
                mDora.setImageResource(R.drawable.draemon);
            }else if(minute < 1){
                mDora.setImageResource(R.drawable.doraemon2);
                if(second < 30){
                    mDora.setImageResource(R.drawable.doraemon4);
                }
            }
        }

        @Override
        public void onFinish(){
            mCountTxt.setText("0:00");
            mSoundPool.play(mSoundResId,1.0f,1.0f,0,0,1.0f);
            mFab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            isRunning = false;
            gstop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCountTxt = (TextView) findViewById(R.id.counttxt);
        mCountTxt.setText("3:00");
        mTimer = new MyCountDownTimer(3 * 60 * 1000,100);

        final FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimer.isRunning){
                    mTimer.isRunning = false;
                    mTimer.cancel();
                    mSoundPool.stop(mSoundResId);
                    mFab.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    gstop();
                }else{
                    mTimer.isRunning = true;
                    mTimer.start();
                    mFab.setImageResource(R.drawable.ic_stop_black_24dp);
                    set(0);
                    gstart();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void set(int n){
        int result = 0;
        int i = r.nextInt(99)+1;
        int j = r.nextInt(9)+1;
        int posi = r.nextInt(4);
        int bposi = r.nextInt(4);

        mAnsbtn1 = (Button) findViewById(R.id.ansbtn1);
        mAnsbtn2 = (Button) findViewById(R.id.ansbtn2);
        mAnsbtn3 = (Button) findViewById(R.id.ansbtn3);
        mAnsbtn4 = (Button) findViewById(R.id.ansbtn4);

        TextView mNum1 = (TextView) findViewById(R.id.num1);
        TextView mEnzan = (TextView) findViewById(R.id.enzan);
        TextView mNum2 = (TextView) findViewById(R.id.num2);
        mNum1.setText(String.valueOf(i));
        mEnzan.setText(s[posi]);
        mNum2.setText(String.valueOf(j));

        mAnsbtn1.setText(String.valueOf(r.nextInt(100)+1));
        mAnsbtn2.setText(String.valueOf(r.nextInt(100)+1));
        mAnsbtn3.setText(String.valueOf(r.nextInt(100)+1));
        mAnsbtn4.setText(String.valueOf(r.nextInt(100)+1));

        mAnsbtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                set(-1);
            }
        });
        mAnsbtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                set(-1);
            }
        });
        mAnsbtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                set(-1);
            }
        });
        mAnsbtn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                set(-1);
            }
        });

        switch (posi){
            case 0:
                result = i + j;
                break;
            case 1:
                result = i - j;
                break;
            case 2:
                result = i * j;
                break;
            case 3:
                result = i / j;
                break;
        }

        switch (bposi){
            case 0:
                mAnsbtn1.setText(String.valueOf(result));
                mAnsbtn1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        set(1);
                    }
                });
                break;
            case 1:
                mAnsbtn2.setText(String.valueOf(result));
                mAnsbtn2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        set(1);
                    }
                });
                break;
            case 2:
                mAnsbtn3.setText(String.valueOf(result));
                mAnsbtn3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        set(1);
                    }
                });
                break;
            case 3:
                mAnsbtn4.setText(String.valueOf(result));
                mAnsbtn4.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        set(1);
                    }
                });
                break;
        }

        if(n == 0){
            correctCnt = 0;
            falseCnt = 0;
        }else if(n > 0){
            correctCnt += 1;
        }else if(n < 0){
            falseCnt += 1;
        }

        TextView mAnsTxt = (TextView) findViewById(R.id.anstxt);
        TextView mFlsTxt = (TextView) findViewById(R.id.faltxt);
        mAnsTxt.setText(String.valueOf(correctCnt));
        mFlsTxt.setText(String.valueOf(falseCnt));
    }

    @Override
    protected void onResume(){
        super.onResume();
        //noinspection deprecation
        mSoundPool = new SoundPool(2, AudioManager.STREAM_ALARM,0);
        mSoundResId = mSoundPool.load(this,R.raw.bellsound,1);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSoundPool.release();
    }

    public void gstart(){
        mAnsbtn1.setEnabled(true);
        mAnsbtn2.setEnabled(true);
        mAnsbtn3.setEnabled(true);
        mAnsbtn4.setEnabled(true);
    }

    public void gstop(){
        mAnsbtn1.setEnabled(false);
        mAnsbtn2.setEnabled(false);
        mAnsbtn3.setEnabled(false);
        mAnsbtn4.setEnabled(false);
    }

}
