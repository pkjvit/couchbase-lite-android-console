package com.couchbase.todo1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.couchbase.lite.Manager;

public class LoginActivity extends AppCompatActivity {

    EditText nameInput;
    EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameInput = (EditText) findViewById(R.id.nameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
    }

    public void login(View view) {
        Application application = (Application) getApplication();

        String name = nameInput.getText().toString();
        String pass = passwordInput.getText().toString();

        // Since we are naming the database as the user name, make sure it is a valid db name
        if(!Manager.isValidDatabaseName(name)) {
            showToast("Make sure user name is not empty and is a valid database name");
            return;
        }

        if(pass.length() == 0) {
            showToast("Make sure the password is not empty");
            return;
        }

        application.login(name, pass);
    }

    private void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

