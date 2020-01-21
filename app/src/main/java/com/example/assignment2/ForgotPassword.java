package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.assignment2.SignIn.isValidPassword;

public class ForgotPassword extends AppCompatActivity {
private Button btnresetpassword;
private EditText edtnewpassword,edtconfirmpassword,edtusername;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
private void init(){
    btnresetpassword=findViewById(R.id.bvresetpassword);
    edtnewpassword=findViewById(R.id.evnewpassword);
    edtconfirmpassword=findViewById(R.id.evconfirmpassword);
    edtusername=findViewById(R.id.evusername);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
       init();
        btnresetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    Toast.makeText(ForgotPassword.this,"password is reseted succsesfully",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void showErrorMessage(String title, String message, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
        builder.setTitle(title).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                view.requestFocus();
            }
        }).create().show();
    }

    private Boolean checkValid() {
        if (edtusername.getText().toString().isEmpty()) {
            showErrorMessage("please enter an email address", getString(R.string.app_name), edtusername);
            return false;
        } else if (!(edtusername.getText().toString().trim().matches(emailPattern))) {
            showErrorMessage("please enter valid email address", getString(R.string.app_name), edtusername);
            return false;


        } else if (edtnewpassword.getText().toString().length() < 8 && !isValidPassword(edtnewpassword.getText().toString())) {
            showErrorMessage("please enter password", getString(R.string.app_name), edtnewpassword);
            return false;

        } else if (edtconfirmpassword.getText().toString().isEmpty()||!edtnewpassword.getText().toString().equals(edtconfirmpassword.getText().toString())) {
            showErrorMessage("please enter password again", getString(R.string.app_name), edtconfirmpassword);
            return false;

        }

        return true;
    }
}
