package com.example.edusos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class QandA_Activity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText et_question, et_topics, et_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qand_a_);
        et_question = (EditText) findViewById(R.id.et_QA_question);
        et_topics = (EditText) findViewById(R.id.et_QA_topics);
        et_answer = (EditText) findViewById(R.id.et_QA_answer);
        reference = FirebaseDatabase.getInstance().getReference().child("Questions");



//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("newMessage");
//
//        myRef.setValue("Hi there!");
    }
    public void onClick(View v) {
        Question obj = new Question();

        String question = et_question.getText().toString();
        String answer = et_answer.getText().toString();
        String topicsStr = et_topics.getText().toString();
        String[] arrOftopics = topicsStr.split(", ");
        List<String> topics = new ArrayList<String>();
        List<String> answers = new ArrayList<String>();
        answers.add(answer);
        for (int i = 0; i < arrOftopics.length; i++) {
            topics.add(arrOftopics[i]);
        }
        obj.setQuestionStr(question);
        obj.setTopics(topics);
        obj.setAnswers(answers);
        Log.d("MSG_", obj.toString());


        reference.push().setValue(obj);
        et_question.setText("");
        et_answer.setText("");
        et_topics.setText("");



    }









}
