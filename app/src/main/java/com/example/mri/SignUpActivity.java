package com.example.mri;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mri.DataModel.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignUp;
    EditText txtUserName,txtUserId,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //btnSignUp = findViewById(R.id.btnSignUp);

        txtUserName = findViewById(R.id.txtUserName);
        txtUserId = findViewById(R.id.txtUserId);
        txtPassword = findViewById(R.id.txtPassword);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(txtUserId.getText().toString()).exists())
                        {
                            Toast.makeText(SignUpActivity.this, "The Id is exist already", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            User user = new User(txtUserName.getText().toString(), txtPassword.getText().toString());
                            table_user.child(txtUserId.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this, "Register Success", Toast.LENGTH_SHORT).show();

                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    /*
    public void SignIn(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }*/
}
