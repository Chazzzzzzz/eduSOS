package com.example.edusos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ValueEventListener valueEventListener;
    //DatabaseReference reference, ref1;
    DatabaseReference rootRef;
    DatabaseReference QuestionsRef;
    List<Question> allQuestions;
    List<Question> matchQuestionsWithAnswers;
    EditText et_main_searchTerm;
    Question question;
    TextView tv_main_searchResult;
    String searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference = FirebaseDatabase.getInstance().getReference().child("Tutor");
        rootRef = FirebaseDatabase.getInstance().getReference();
        QuestionsRef = rootRef.child("Questions");
        et_main_searchTerm = (EditText) findViewById(R.id.et_main_searchTerm);
        tv_main_searchResult = (TextView) findViewById(R.id.tv_main_searchResult);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_main_search:
                searchResult = "";
                final String searchTerm = et_main_searchTerm.getText().toString();
                allQuestions = new ArrayList<Question>();
                matchQuestionsWithAnswers = new ArrayList<Question>();

                valueEventListener = new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        //String str="";

                        for(DataSnapshot ds:dataSnapshot.getChildren()){

//                String name = ds.child("name").getValue(String.class);
//                Log.d("READ_", name);
                            question=ds.getValue(Question.class);
                            allQuestions.add(question);
                            Log.d("QUESTION__len",String.valueOf(allQuestions.size()));

                            if(question.answers != null && question.answers.size() >0) {
                                allQuestions.add(question);
                                for (int i = 0; i < question.answers.size(); i++) {
                                    if (question.answers.get(i).toLowerCase().contains(searchTerm.toLowerCase())) {
                                        matchQuestionsWithAnswers.add(question);
                                        searchResult+= "Question: " + question.questionStr + "\n" + "Answer: "+question.answers.get(i) + "\n";
                                        Log.d("QUESTION_match", question.answers.get(i));
                                        Log.d("QUESTION_match_QA: ", searchResult);
                                    }
                                }
                            }

                        }
                        tv_main_searchResult.setText(searchResult);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };
                QuestionsRef.addValueEventListener(valueEventListener);
                //tv_main_searchResult.setText(searchResult);
                et_main_searchTerm.setText("");

                break;
            case R.id.btn_main_QandA:
                Intent intent = new Intent(MainActivity.this, QandA_Activity.class);
                startActivity(intent);
                break;
        }

    }
}





