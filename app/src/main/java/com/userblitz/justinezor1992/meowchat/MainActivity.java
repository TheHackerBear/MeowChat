package com.userblitz.justinezor1992.meowchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;


public class MainActivity extends Activity implements View.OnClickListener {
    Button mLoginButton;
    Button mCreateAccountButton;

    private String TAG = "com.userblitz.MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(this);
        mCreateAccountButton = (Button) findViewById(R.id.createAccountButton);
        mCreateAccountButton.setOnClickListener(this);


        Parse.initialize(this, "Q6Au8ayww5E5jyBKINYZ3TPvqi9MTe7b5P8o1PBr", "JcBmG8kKygtl8180yOqYh0FBTaTdYnFiN9TYrW1Y");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            Log.d(TAG, "user found");
            Intent intent = new Intent(this, TabActivity.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "user not found");
            // show the signup or login screen
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        final EditText password = (EditText) findViewById(R.id.passwordEditText);


        switch(view.getId()) {
            case R.id.loginButton:
            // handle button A click;
                ParseUser.logInInBackground(userName.getText().toString(), password.getText().toString(), new LogInCallback() {
                    public void done(com.parse.ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Intent intent = new Intent(MainActivity.this, TabActivity.class);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();

                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                break;
            case R.id.createAccountButton:
            // handle button B click;
                Intent createAccount = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(createAccount);
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }


    }



    }
