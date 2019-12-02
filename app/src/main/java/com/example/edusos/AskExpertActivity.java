package com.example.edusos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class AskExpertActivity extends AppCompatActivity {

    private EditText mSearchField;

    private Button mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_expert);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Experts");

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (Button) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });



    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(AskExpertActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<Expert> options =
                new FirebaseRecyclerOptions.Builder<Expert>()
                        .setQuery(firebaseSearchQuery, Expert.class)
                        .build();

        final FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Expert, ExpertsViewHolder>(options) {
            ArrayList<Expert> expertList;

            @NonNull
            @Override
            public ExpertsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.online_expert, parent, false);

                return new ExpertsViewHolder(view);            }


            @Override
            protected void onBindViewHolder(ExpertsViewHolder viewHolder, int position, Expert model) {
                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getOnline().toString(), model.getPhone(),
                        model.getRating().toString(), Integer.toString(model.getQuestionsAnswered()), model.getSubjects().toString());
            }

        };

        mResultList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }

    public static class ExpertsViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ExpertsViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String expertName, String expertOnline, String expertPhone,
                               String expertRate, String expertQA, String expertSubjects){

            TextView expert_name = (TextView) mView.findViewById(R.id.name_text);
            TextView expert_online = (TextView) mView.findViewById(R.id.online_text);
            TextView expert_phone = (TextView) mView.findViewById(R.id.phone_text);
            TextView expert_rate = (TextView) mView.findViewById(R.id.rate_text);
            TextView expert_question_answered = (TextView) mView.findViewById(R.id.question_answered_text);
            TextView expert_subjects = (TextView) mView.findViewById(R.id.subject_text);


            expert_name.setText(expertName);
            expert_online.setText(expertOnline);
            expert_phone.setText(expertPhone);
            expert_rate.setText(expertRate);
            expert_question_answered.setText(expertQA);
            expert_subjects.setText(expertSubjects);


        }




    }

}
