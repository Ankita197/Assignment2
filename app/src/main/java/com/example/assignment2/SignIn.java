package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    private TextView txtforgotpassword;
    private Button btnlogin;
    private EditText edtusername,edtpassword;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private AlertDialog.Builder builder;

    private void init(){
        txtforgotpassword=findViewById(R.id.tvforgotpassword);
        btnlogin=findViewById(R.id.btnsignin);
        edtusername=findViewById(R.id.evusername);
        edtpassword=findViewById(R.id.evpassword);
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
        builder = new AlertDialog.Builder(SignIn.this);
        txtforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this, ForgotPassword.class);
                startActivity(intent);

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtusername.getText().toString().isEmpty())
                {
                    builder.setTitle("please enter an email address").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            edtusername.requestFocus();
                        }
                    }).create().show();

                } else if (!(edtusername.getText().toString().trim().matches(emailPattern)))
                {
                    builder.setTitle("please enter valid email address").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            edtusername.requestFocus();
                        }
                    }).create().show();



                }
                else if (edtpassword.getText().toString().length() < 8 && !isValidPassword(edtpassword.getText().toString()))
                {
                    builder.setTitle("password is follow rules which is given below");
                    builder.setMessage("password should contain atleast on uppercase letter one number and one special character").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            edtpassword.requestFocus();
                        }
                    }).create().show();

                }

            }
        });
    }
}
