package com.example.quizappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<Question> questions;
    int score;
    public static String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;
    public static String a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblQuestion);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);


        questions = new ArrayList<Question>(){
            {
                add(new Question("Which of the following is NOT a commonly used programming language?", "C", "Java", "Ruby","HTML", "Python"));
                add(new Question("What does the term HTTP stand for?", "A", "Hyper Text Transfer Protocol", "Hyper Transfer Text Protocol","High Transfer Text Protocol", "High Text Transfer Protocol"));
                add(new Question("Who is the father of computer? ", "C", "Charles Darwin", "Ada Lovelace","Charles Babbage", "Tobin Bell"));
                add(new Question("Which of the following is a type of malware that replicates itself and spreads to other computers on a network?", "B", "Trojan horse", "Worm", "Spyware", "Adware"));
                add(new Question("Which of the following is NOT an example of cloud storage service?", "A", "Microsoft Excel", "Dropbox", "Google Drive", "iCloud"));
                add(new Question("Which of the following is an example of a mobile operating system?","C", "Windows","macOS", "Android","Linux"));
                add(new Question("One of the main reasons for Android requiring more RAM is:","D","High performance","More Apps in the Background","Heavy Apps","Java's Garbage Collector"));
                add(new Question("WWhich of the following is a type of computer port used for connecting a keyboard and mouse?","B","HDMI"," USB","Ethernet","VGA"));
                add(new Question("Which of the following is a type of software used to protect against viruses and other malware?","A","Anti-virus","Firewall","VPN","Encryption"));
                add(new Question("Which of the following is a programming language used for building web applications?","C","C++","Swift","JavaScript","Python"));
            }
        };

        q1 =  questions.get(0).getQuestion();
        q2 =  questions.get(1).getQuestion();
        q3 =  questions.get(2).getQuestion();
        q4 =  questions.get(3).getQuestion();
        q5 =  questions.get(4).getQuestion();
        q6 =  questions.get(5).getQuestion();
        q7 =  questions.get(6).getQuestion();
        q8 =  questions.get(7).getQuestion();
        q9 =  questions.get(8).getQuestion();
        q10 = questions.get(9).getQuestion();
        a1 = questions.get(0).getAns();
        a2 = questions.get(1).getAns();
        a3 = questions.get(2).getAns();
        a4 = questions.get(3).getAns();
        a5 = questions.get(4).getAns();
        a6 = questions.get(5).getAns();
        a7 = questions.get(6).getAns();
        a8 = questions.get(7).getAns();
        a9 = questions.get(8).getAns();
        a10 = questions.get(9).getAns();
        loadQuestion();
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }


    private void loadQuestion(){
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.optionA:
                Answer="A";
                break;

            case R.id.optionB:
                Answer="B";
                break;

            case R.id.optionC:
                Answer="C";
                break;

            case R.id.optionD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }
}
