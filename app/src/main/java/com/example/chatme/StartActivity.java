package com.example.chatme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {
    EditText email,password;
    Button login, register;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    TextView forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser != null){
            Intent intent= new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        auth= FirebaseAuth.getInstance();

        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        forgot_password=findViewById(R.id.fogot_password);

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,ResetPasswordActivity.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=findViewById(R.id.email);
                password=findViewById(R.id.password);
                login=findViewById(R.id.login);

                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt_email=email.getText().toString();
                        String txt_password=password.getText().toString();

                        if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                            Toast.makeText(StartActivity.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                        }else{
                            auth.signInWithEmailAndPassword(txt_email, txt_password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Intent intent=new Intent(StartActivity.this,MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                Toast.makeText(StartActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,RegisterActivity.class));
            }
        });
    }
}
