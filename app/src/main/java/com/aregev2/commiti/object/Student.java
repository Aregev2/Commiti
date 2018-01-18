package com.aregev2.commiti.object;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Aregev2 on 13/01/2018.
 */

public class Student {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private UserProfileChangeRequest userProfileChangeRequest;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private String username;
    private String email;
    private String id;
    private String key;
    private String password;

    private int accountType;

    public Student(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getAccountType() {
        return 0;
    }

    public void init(){
        String username = this.getUsername();
        String key = databaseReference.push().getKey();
        int accountType = this.getAccountType();
        databaseReference.child(key).setValue(accountType);

        userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
        firebaseUser.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
