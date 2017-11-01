package com.example.benodonnell.a3rdyearproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private ProgressDialog mProgress;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener fireBaseAuthListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        fireBaseAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    mProgress.dismiss();
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                }
            }
        };
        login();
    }


    public User login() {
        Button btn;
        btn = (Button) findViewById(R.id.bLogin);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mProgress.setMessage("Signing in......");
                mProgress.show();
                EditText email = (EditText) findViewById(R.id.editText);
                EditText password = (EditText) findViewById(R.id.editText2);

                String emailtext = String.valueOf(email.getText()).trim();
                String passwordsText = String.valueOf(password.getText()).trim();

                boolean isEmail;
                //Seeing if it is a valid email
                if (emailtext.contains("@"))
                    isEmail = true;
                else {
                    isEmail = false;
                    Toast.makeText(getApplicationContext(), "Please Enter A Valid Email", Toast.LENGTH_SHORT).show();
                }

                if (emailtext.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
                } else if (passwordsText.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();

                } else {
                    firebaseAuth.signInWithEmailAndPassword(emailtext, passwordsText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return null;
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.bRegister) {
            Intent i = new Intent(MainActivity.this, Register.class);
            startActivity(i);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(fireBaseAuthListner);
    }

    public void onStop() {
        super.onStop();
        if (fireBaseAuthListner != null) {
            firebaseAuth.removeAuthStateListener(fireBaseAuthListner);
            firebaseAuth.signOut();

        }
    }

}

