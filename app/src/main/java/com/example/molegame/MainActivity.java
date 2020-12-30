package com.example.molegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {


    ImageView Mole1,Mole2,Mole3;
    TextView tv_left, tv_score;
    Button button;
    Random r;
    MediaPlayer mp;


    int score =0, fps=1000, left=5, notMissed=0;
    int select=0;
    int hitted = 0;

    AnimationDrawable an;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r= new Random();

        button = (Button)findViewById(R.id.button);
        tv_left=(TextView)findViewById(R.id.tv_left);
        tv_score=(TextView)findViewById(R.id.tv_score);

        Mole1 = (ImageView)findViewById(R.id.mole1);
        Mole2 = (ImageView)findViewById(R.id.mole2);
        Mole3 = (ImageView)findViewById(R.id.mole3);

        Mole1.setVisibility(View.INVISIBLE);
        Mole2.setVisibility(View.INVISIBLE);
        Mole3.setVisibility(View.INVISIBLE);

        button.setOnClickListener( new  View.OnClickListener()
       {
           @Override
           public void onClick(View view)
           {
               left=5;
               tv_left.setText("LEFT: "+ left);
               score=0;
               tv_score.setText("SCORE: "+ score);
               Handler handler = new Handler();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       game();
                   }
               }, 1000);
               button.setEnabled(false);
           }
       });

       Mole1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               notMissed = 1;
               Mole1.setImageResource(R.drawable.hit_mole);
               score=score+1;
               tv_score.setText("SCORE: " + score);
               Mole1.setEnabled(false);
               mp = MediaPlayer.create(MainActivity.this,R.raw.bump);
               mp.start();
           }
       });


        Mole2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notMissed = 1;
                Mole2.setImageResource(R.drawable.hit_mole);
                score=score+1;
                tv_score.setText("SCORE: " + score);
                Mole2.setEnabled(false);
                mp = MediaPlayer.create(MainActivity.this,R.raw.bump);
                mp.start();
            }
        });


        Mole3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notMissed = 1;
                Mole3.setImageResource(R.drawable.hit_mole);
                score=score+1;
                tv_score.setText("SCORE: " + score);
                Mole3.setEnabled(false);
                mp = MediaPlayer.create(MainActivity.this,R.raw.bump);
                mp.start();
            }
        });
    }



    private void game()
    {

        setFps(score);

        an = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.anim);

         do {
             select = r.nextInt(3) +1;
         }  while(select==hitted);
         hitted = select;

         switch(select)
         {
             case 1:
                 enableMole(Mole1);
                 break;
             case 2:
                 enableMole(Mole2);
                 break;
             case 3:
                 enableMole(Mole3);
                 break;
         }

        an.start();

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                disableMole(Mole1);
                disableMole(Mole2);
                disableMole(Mole3);

                if (notMissed == 0) {
                    left = left - 1;
                    tv_left.setText("LEFT: " + left);
                }
                    else if (notMissed==1)
                {
                    notMissed=0;
                }

                    if(left==0)
                    {
                        Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                        button.setEnabled(true);
                    }
                    else if(left>0)
                    {
                        game();
                    }
               }
        }, fps);



    }

    void disableMole(ImageView mole)
    {

        mole.setVisibility(View.INVISIBLE);
        mole.setEnabled(false);

    }

    void enableMole(ImageView mole)
    {
        mole.setImageDrawable(an);
        mole.setEnabled(true);
        mole.setVisibility(View.VISIBLE);
    }

    public void setFps(int score) {
        if (score<10)
        {
            fps=1000;
        }
        else if(score>=10 && score<15)
            fps=950;
        else if(score>=15 && score<20)
            fps=900;
        else if(score>=20 && score<25)
            fps=850;
        else if(score>=25 && score<30)
            fps=800;
        else if(score>=30 && score<35)
            fps=750;
        else fps=700;
    }
}
