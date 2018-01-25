package com.aregev2.commiti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser;
    private UserProfileChangeRequest userProfileChangeRequest;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private String username;
    private String email;
    private String password;
    private String userKey;
    private int accountType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textInputLayoutUsername = findViewById(R.id.activity_register_layout_username);
        textInputLayoutEmail = findViewById(R.id.activity_register_layout_email);
        textInputLayoutPassword = findViewById(R.id.activity_register_layout_password);
        editTextUsername = findViewById(R.id.activity_register_editText_username);
        editTextEmail = findViewById(R.id.activity_register_editText_email);
        editTextPassword = findViewById(R.id.activity_register_editText_password);
        buttonRegister = findViewById(R.id.activity_register_button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editTextUsername.getText().toString();
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if(!username.isEmpty()){
                    if(!email.isEmpty()){
                        if(!password.isEmpty()){
                            accountType = 0;
                            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        initUserCreation();
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Account Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            textInputLayoutPassword.setError("Field is required");
                        }
                    }else{
                        textInputLayoutEmail.setError("Field is required");
                    }
                }else{
                    textInputLayoutUsername.setError("Field is required");
                }
            }
        });
        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayoutUsername.setError(null);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayoutEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayoutPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void initUserCreation(){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
        firebaseUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                userKey = firebaseUser.getUid();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference("users");
                databaseReference.child(userKey).child("type").setValue(accountType);

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}