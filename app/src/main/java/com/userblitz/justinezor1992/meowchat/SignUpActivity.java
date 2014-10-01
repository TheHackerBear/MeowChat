package com.userblitz.justinezor1992.meowchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;


public class SignUpActivity extends Activity implements View.OnClickListener {

    Button mCreateAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mCreateAccountButton = (Button) findViewById(R.id.createAccountButton);
        mCreateAccountButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View view) {

        final EditText userName = (EditText) findViewById(R.id.usernameEditText);
        final EditText passWord = (EditText) findViewById(R.id.passwordEditText);


        ParseUser user = new ParseUser();
        user.setUsername(userName.getText().toString());
        user.setPassword(passWord.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    //Hooray! Let them use the app now.
                    ParseUser.logInInBackground(userName.getText().toString(), passWord.getText().toString(), new LogInCallback() {
                        public void done(com.parse.ParseUser user, com.parse.ParseException e) {
                            if (user != null) {
                                // Hooray! The user is logged in.
                                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();

                            } else {
                                // Signup failed. Look at the ParseException to see what happened.
                                Toast.makeText(getApplicationContext(), "Sign-in failed!  Try again.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(getApplicationContext(), "Sorry that username is already taken!  Try another.", Toast.LENGTH_LONG).show();
                }

            }

        });



    }
}


