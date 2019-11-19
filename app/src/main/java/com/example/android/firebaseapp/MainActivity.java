package com.example.android.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;




public class MainActivity extends AppCompatActivity {
    EditText txt_name, txt_age, txt_phone, txt_height, txt_subjects, txt_experience;
    Button btn_save;
    DatabaseReference reference, ref1;

    Tutor tutor;
    FirebaseDatabase database;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 007;
    TextView txt_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_welcome = (TextView) findViewById(R.id.tv_welcome);


        // Following lines are for input member info for Firebase testing
        txt_name = (EditText) findViewById(R.id.ETname);
        txt_age = (EditText) findViewById(R.id.ETage);
        txt_phone = (EditText) findViewById(R.id.ETphone);
        txt_subjects = (EditText) findViewById(R.id.ETsubjects);
        txt_experience = (EditText) findViewById(R.id.ETexperience);

        btn_save = (Button) findViewById(R.id.BTsave);


        tutor = new Tutor();


        reference = FirebaseDatabase.getInstance().getReference().child("Tutor");



        //database = FirebaseDatabase.getInstance();


//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");



//        btn_save.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            int age = Integer.parseInt(txt_age.getText().toString().trim());
//                                            Float ht = Float.parseFloat(txt_height.getText().toString().trim());
//                                            Long ph = Long.parseLong(txt_phone.getText().toString().trim());
//                                            member.setName(txt_name.getText().toString().trim());
//                                            member.setAge(age);
//                                            member.setPh(ph);
//                                            member.setHt(ht);
//                                            reff.setValue(member);
//
//                                            Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_LONG).show();
//                                        }
//                                    }
//
//            );

        //Toast.makeText(this,"Firebase Connection Success", Toast.LENGTH_LONG).show();

        //===========================

//===============Start of Sign in=========================================================
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }


    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }


        public void signin_btnOnClick(View view) {
        signIn();

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //startActivityForResult(signInIntent,1);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String personName = account.getDisplayName();
            //String personPhotoUrl = account.getPhotoUrl().toString();
            String email = account.getEmail();

            txt_welcome.setText("Welcome "+personName+"\n"+ email);


            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("SIGNIN_", "signInResult:failed code=" + e.getStatusCode());
            Log.d("SIGNIN_", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
    private void updateUI(GoogleSignInAccount account) {

    }

    //===============End of Sign in=========================================================

    // This is an example of input data and save in firebase Member object
    public void btnOnClick(View view) {
        String name = txt_name.getText().toString().trim();
        int age = Integer.parseInt(txt_age.getText().toString().trim());
        String phone = txt_phone.getText().toString().trim();
        String subjects = txt_subjects.getText().toString().trim();
        Float experience = Float.parseFloat(txt_experience.getText().toString().trim());
        tutor.setName(name);
        tutor.setAge(age);
        tutor.setPhone(phone);
        tutor.setSubjects(subjects);
        tutor.setExperience(experience);

        reference.push().setValue(tutor);

        txt_name.setText("");
        txt_age.setText("");
        txt_phone.setText("");
        txt_subjects.setText("");
        txt_experience.setText("");

    }

}
