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

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtId,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnSignIn);
        txtId = findViewById(R.id.txtUserId);
        txtPassword = findViewById(R.id.txtPassword);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(txtId.getText().toString()).exists())
                        {
                            User user = dataSnapshot.child(txtId.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(txtPassword.getText().toString()))
                            {
                                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                SignIn();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void SignIn(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
