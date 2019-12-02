package com.example.edusos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ExpertProfileActivity extends AppCompatActivity {
    DatabaseReference dbExpert;
    Expert expertObj;
//    EditText answerInput;
    String key;
    String name, email, phone;
    Double rating, ratePerQuestion;
    int questionAnswered;
    Boolean online;
    GoogleSignInAccount googleAccount;
    ArrayList<String> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_profile);



        TextView textViewName= (TextView) findViewById(R.id.cardview_name);
        TextView textViewOnline = (TextView) findViewById(R.id.cardview_online);
        TextView textViewEmail = (TextView) findViewById(R.id.cardview_email);
        TextView textViewRating = (TextView) findViewById(R.id.cardview_rating);
        TextView textViewRatePerQuestion = (TextView) findViewById(R.id.cardview_ratePerQuestion);

        TextView textViewQuestionsAnswered = (TextView) findViewById(R.id.cardview_questionAnswered);
        TextView textViewSubjects = (TextView) findViewById(R.id.cardview_subjects);

        Intent intent = getIntent();

        key = intent.getStringExtra("chosenKey");
        Log.d("KEY_", key);

        name = intent.getStringExtra("name");
        email  = intent.getStringExtra("googleAcc")+"@gmail.com";
        phone = intent.getStringExtra("phone");
        rating = intent.getDoubleExtra("rating", 4.0);
        ratePerQuestion = intent.getDoubleExtra("ratePerQuestion", 0.0);
        subjects = intent.getStringArrayListExtra("subjects");
        online = intent.getBooleanExtra("online", true);
        questionAnswered = intent.getIntExtra("questionAnswered", 10);

                String subjectStr = "Subjects: \n";
        if (subjects != null && subjects.size() >0) {
            for (String subject: subjects) {
                subjectStr += subject + "\n";

            }
        }

        textViewName.setText(name);
        textViewRatePerQuestion.setText("Rate per question: $"+ ratePerQuestion.toString());
        textViewSubjects.setText(subjectStr);
        if (!online) {
            textViewOnline.setText("Currently not online");
        }
        textViewRating.setText("Rating: "+rating);
        textViewQuestionsAnswered.setText("Questions Answered: "+questionAnswered);
        textViewEmail.setText("Email: "+ email);

//        googleAccount = ((EduSOSApplication) this.getApplication()).getAccount();
//
//        if (googleAccount != null) {
//            Log.d("SIGNIN_POST_", googleAccount.getDisplayName() + ",   " + googleAccount.getEmail());
//            welcome.setText("Welcome " + googleAccount.getDisplayName().split(" ")[0] + "!");
//
//        }
    }



}


