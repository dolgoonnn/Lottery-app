package com.example.a5onoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mfirstnumber, msecondnumber, mthirdnumber, mfourthnumber, mfifthnumber;
    private EditText gfirstnumber, gsecondnumber ,gthirdnumber, gfourthnumber, gfifthnumber;
    int result = 0;
    private Button mgenerateButton;
    private String[] lotteryBalls = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
            "17", "18", "19", "20", "21", "22", "23"
    };
    private String[] guess = new String[5];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        mgenerateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(Validate()){
                    generateRandomNumber();
                    if (result == 5){
                        Intent intent=new Intent(view.getContext(), MainActivity2.class);
                        startActivity(intent);
                    }else{
                        gfirstnumber.setText("");
                        gsecondnumber.setText("");
                        gthirdnumber.setText("");
                        gfourthnumber.setText("");
                        gfifthnumber.setText("");
                        Toast.makeText(getApplicationContext(),"Try again next time",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void generateRandomNumber() {
        Collections.shuffle(Arrays.asList(lotteryBalls));
        mfirstnumber.setText(lotteryBalls[0]);
        msecondnumber.setText(lotteryBalls[1]);
        mthirdnumber.setText(lotteryBalls[2]);
        mfourthnumber.setText(lotteryBalls[3]);
        mfifthnumber.setText(lotteryBalls[4]);
    }

    private void bindViews() {
        mfirstnumber = (TextView) findViewById(R.id.firstnumber);
        msecondnumber = (TextView) findViewById(R.id.secondnumber);
        mthirdnumber = (TextView) findViewById(R.id.thirdnumber);
        mfourthnumber = (TextView) findViewById(R.id.fourthnumber);
        mfifthnumber = (TextView) findViewById(R.id.fifthnumber);
        mgenerateButton = (Button) findViewById(R.id.generateButton);

        gfirstnumber = (EditText) findViewById(R.id.firstguess);
        gsecondnumber = (EditText) findViewById(R.id.secondguess);
        gthirdnumber = (EditText) findViewById(R.id.thirdguess);
        gfourthnumber = (EditText) findViewById(R.id.fourthguess);
        gfifthnumber = (EditText) findViewById(R.id.fifthguess);

        gfirstnumber.setText(guess[0]);
        gsecondnumber.setText(guess[1]);
        gthirdnumber.setText(guess[2]);
        gfourthnumber.setText(guess[3]);
        gfifthnumber.setText(guess[4]);
    }

    public int numbersMatching(ArrayList<Integer> guess) {
        for (Integer i : guess) {
            for (String j : lotteryBalls) {
                if (i.equals(j)) result++;
            }
        }

        return result;
    }
    private boolean Validate() {
        if (!hasText(gfirstnumber)) return false;
        if (!hasText(gsecondnumber)) return false;
        if (!hasText(gthirdnumber)) return false;
        return true;
    }

    private boolean hasText(EditText gfirstnumber) {
        String text = gfirstnumber.getText().toString().trim();
        gfirstnumber.setError(null);

        if (text.length() == 0) {
            Toast.makeText(getApplicationContext(),"No empty area",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}