package com.travel.livelocationtracking.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.travel.livelocationtracking.R;
import com.travel.livelocationtracking.db.UserDatabase;
import com.travel.livelocationtracking.db.UserEntity;
import com.travel.livelocationtracking.services.TrackerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    UserDatabase userDatabase;
    AwesomeValidation awesomeValidation;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.createAccount)
    Button createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        awesomeValidation = new AwesomeValidation(BASIC);
        awesomeValidation.addValidation(this, R.id.email,
                Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.password,
                RegexTemplate.NOT_EMPTY, R.string.err_password);
        userDatabase = UserDatabase.getUserDatabase(this);
        login.setOnClickListener(this);
        createAccount.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (awesomeValidation.validate())
                    loginAccount();
                break;
            case R.id.createAccount:
                Intent intentRegister = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(intentRegister);
                finish();
                break;
        }
    }

    private void loginAccount() {
        Runnable runnable = () -> {
            String emails = email.getText().toString().trim();
            String passwords = password.getText().toString().trim();
            UserEntity userEntity = userDatabase.userDao().userLogin(emails, passwords);
            if (userEntity != null) {
                Intent intent = new Intent(LoginActivity.this,
                        TrackerActivity.class);
                startActivity(intent);
                finish();
                runOnUiThread(() -> {
                    Toast.makeText(getBaseContext(),"Successfully login",
                            Toast.LENGTH_SHORT).show();
                });
            } else {
                runOnUiThread(() -> {
                    email.getText().clear();
                    password.getText().clear();
                    Toast.makeText(getBaseContext(),"Record Not Found",
                            Toast.LENGTH_SHORT).show();
                });
            }
        };
        new Thread(runnable).start();
    }

}
