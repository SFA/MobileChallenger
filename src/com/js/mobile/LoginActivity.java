package com.js.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);

        //load up the layout
        setContentView(R.layout.login);

        //get the button resource in the xml file and assign it to a local variable of type Button
        Button launch = (Button)findViewById(R.id.login_button);

        //this is the action listener
        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This gets the resources in the xml file and assigns it to a local variable of type EditText
                EditText usernameEditText = (EditText) findViewById(R.id.txt_username);
                EditText passwordEditText = (EditText) findViewById(R.id.txt_password);

                //The getText() gets the current value of the text box
                //The toString() converts the value to String data type then assigns it to a variable of type String
                String sUserName = usernameEditText.getText().toString();
                String sPassword = passwordEditText.getText().toString();

                //this just catches the error if the program can't locate the GUI stuff
                if (usernameEditText == null || passwordEditText == null) {
                    PopUp("Crap!", "Couldn't find the 'txt_username' or 'txt_password' EditView in main.xml");
                } else {
                    //Display the username and the password in string format
                    PopUp("Logging in", "Username: " + sUserName + "\nPassword: " + sPassword);
                    DataHandler.doLogin(sUserName, sPassword);
                    Intent myIntent = new Intent(getBaseContext(), LeaderBoardActivity.class);
                    myIntent.putExtra("userName", sUserName);
                    startActivity(myIntent);
//                    setContentView(R.layout.main);
                }
            }
        }); //end of launch.setOnclickListener
    }

    public void PopUp(String title, String message){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do stuff onclick of YES
//                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do stuff onclick of CANCEL
                        Toast.makeText(getBaseContext(), "You clicked CANCEL", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
