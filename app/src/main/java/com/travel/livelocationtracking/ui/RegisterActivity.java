package com.travel.livelocationtracking.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
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
import com.travel.livelocationtracking.db.UserEntity;
import com.travel.livelocationtracking.viewmodel.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private UserViewModel userViewModel;

    AwesomeValidation awesomeValidation;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirm_password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.createAccount)
    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        awesomeValidation = new AwesomeValidation(BASIC);
        awesomeValidation.addValidation(this, R.id.name,
                RegexTemplate.NOT_EMPTY, R.string.err_name);
        awesomeValidation.addValidation(this, R.id.email,
                Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.password,
                RegexTemplate.NOT_EMPTY, R.string.err_password);
        awesomeValidation.addValidation(this, R.id.confirm_password,
                R.id.password, R.string.invalid_confirm_password);

        login.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createAccount:
                if (awesomeValidation.validate())
                    insertRecord();
                break;

            case R.id.login:
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void insertRecord() {
        String names = name.getText().toString().trim();
        String emails = email.getText().toString().trim();
        String passwords = password.getText().toString().trim();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(emails);
        userEntity.setUserName(names);
        userEntity.setUserPassword(passwords);
        userViewModel.insert(userEntity);
        new Handler().postDelayed(() -> {
            Toast.makeText(RegisterActivity.this, "Successfully registered",
                    Toast.LENGTH_LONG).show();
          Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
          startActivity(intent);
          finish();
        }, 1000);

    }

}
