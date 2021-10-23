package com.example.braintester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button tryAgainButton;
    ConstraintLayout quizLayout;
    TextView question;
    TextView result;
    Button answer0;
    Button answer1;
    Button answer2;
    Button answer3;
    TextView timer;
    TextView scoreTextView;
    boolean isCounterRunning;
    int score;
    int totalQuestions;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctAnswerIndex;

    public void startTimer(){
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                isCounterRunning = true;
                timer.setText((millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                isCounterRunning = false;
                result.setText("Done! ^_^");
                tryAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void tryAgain(View view){
        tryAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setText("0/0");
        score=0;
        totalQuestions=0;
        startTimer();
        quizBody();
    }
    public void startQuiz(View view){
        goButton.setVisibility(View.INVISIBLE);
        quizLayout.setVisibility(View.VISIBLE);
        startTimer();
        quizBody();
    }

    public void chooseAnswer(View view){
        String chosenAnswer = view.getTag().toString();
        int ans = Integer.parseInt(chosenAnswer);
        if(ans == correctAnswerIndex && isCounterRunning){
            result.setText("Correct! :D");
            score++;
            totalQuestions++;
            scoreTextView.setText(score + "/" + totalQuestions);
            quizBody();
        }
        else if (ans != correctAnswerIndex && isCounterRunning){
            result.setText("Wrong! :(");
            totalQuestions++;
            scoreTextView.setText(score + "/" + totalQuestions);
            quizBody();
        }
    }

    public void quizBody(){
        int a,b;
        answers.clear();
        Random rand = new Random();
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        correctAnswerIndex = rand.nextInt(4);
        for(int i = 0; i < 4 ; i++){
            if(i == correctAnswerIndex){
                answers.add(a+b);
            }
            else {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == (a+b)){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        question.setText(a + "+" + b);
        answer0.setText(answers.get(0).toString());
        answer1.setText(answers.get(1).toString());
        answer2.setText(answers.get(2).toString());
        answer3.setText(answers.get(3).toString());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        quizLayout = findViewById(R.id.quizLayout);
        question = findViewById(R.id.questionTextView);
        result = findViewById(R.id.resultTextView);
        answer0 = findViewById(R.id.button0);
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);
        answer3 = findViewById(R.id.button3);
        timer = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        tryAgainButton =findViewById(R.id.tryAgainButton);

    }
}