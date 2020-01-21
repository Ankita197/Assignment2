package com.example.assignment2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private String[] gender = {"select gender", "Male", "Female", "Other"};
    private EditText edtemail, edtpassword;
    private static EditText edtdateofbirth;
    private Button btn_signup, btndatepicker;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private TextView txtlogin;
    private Calendar cal;
    private int day;
    private int month;
    private int year;


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private void init() {
        spinner = findViewById(R.id.gender_spinner);
        edtemail = findViewById(R.id.evemail);
        edtpassword = findViewById(R.id.evpassword);
        btn_signup = findViewById(R.id.btnsignup);
        edtdateofbirth = findViewById(R.id.evdateofbirth);
        txtlogin = findViewById(R.id.tvlogin);
        btndatepicker = findViewById(R.id.datepicker);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

    }

    private void goLogin() {
        Intent i = new Intent(MainActivity.this, SignIn.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValid()) {
                    goLogin();
                }




            }


        });

        btndatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getSupportFragmentManager(), "DatePicker");
            }
        });
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        Log.e("=====TAG====", "Selected item: " + position + " = " + spinner.getSelectedItem());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLogin();
            }
        });

    }


    private void showErrorMessage(String title, String message, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                view.requestFocus();
            }
        }).create().show();
    }

    private Boolean checkValid() {
        if (edtemail.getText().toString().isEmpty()) {
            showErrorMessage("please enter an email address", getString(R.string.app_name), edtemail);
            return false;
        } else if (!(edtemail.getText().toString().trim().matches(emailPattern))) {
            showErrorMessage("please enter valid email address", getString(R.string.app_name), edtemail);
            return false;


        } else if (edtpassword.getText().toString().length() < 8 && !isValidPassword(edtpassword.getText().toString())) {
            showErrorMessage("please enter password", getString(R.string.app_name), edtpassword);
            return false;

        } else if (edtdateofbirth.getText().toString().isEmpty()) {
            showErrorMessage("please enter Bithdate", getString(R.string.app_name), edtdateofbirth);
            return false;

        } else if (spinner.getSelectedItemPosition() < 1) {
            showErrorMessage("please select gender", getString(R.string.app_name), spinner);
            return false;

        }

        return true;
    }

    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public String populateSetDate(int year, int month, int day) {
            edtdateofbirth.setText(month + "/" + day + "/" + year);
            return (month + "/" + day + "/" + year);
        }


    }
}





