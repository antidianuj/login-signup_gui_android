package com.example.login_register_ews_x;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Signin extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname,textInputEditTextUsername, textInputEditTextEmail, textInputEditTextPassword;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        textInputEditTextFullname=findViewById(R.id.fullname);
        textInputEditTextUsername =findViewById(R.id.username);
        textInputEditTextPassword=findViewById(R.id.password);
        textInputEditTextEmail=findViewById(R.id.email);
        buttonSignUp=findViewById(R.id.buttonSignUp);
        textViewLogin=findViewById(R.id.loginText);
        progressBar=findViewById(R.id.progress);


        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname,username,email,password;
                fullname=String.valueOf(textInputEditTextFullname.getText());
                username=String.valueOf(textInputEditTextUsername.getText());
                password=String.valueOf(textInputEditTextPassword.getText());
                email=String.valueOf(textInputEditTextEmail.getText());

                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "email";
                            field[3] = "password";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = email;
                            data[3] = password;
                            PutData putData = new PutData("http://192.168.1.100/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success"))
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
            }
        });



    }



}