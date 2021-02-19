package com.example.passwordgenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Switch SwitchUpperCase,SwitchLowerCase,SwitchNumber,SwitchSpecialCharacter;
    EditText EditTextMaxLength;
    TextView EditTextPassword;
    Button ButtonGeneratePassword,ButtonCopy;
    int PasswordLength = 0;
    String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String Small_chars = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String symbols = "!@#$%^&*_=+-/.?<>(){}[]";
    String Final_Password ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwitchUpperCase = findViewById(R.id.switchUpperCase);
        SwitchLowerCase = findViewById(R.id.switchLowerCase);
        SwitchNumber = findViewById(R.id.switchNumber);
        SwitchSpecialCharacter = findViewById(R.id.switchSpecialCharacter);
        EditTextMaxLength = findViewById(R.id.EditTextMaxLength);
        EditTextPassword = findViewById(R.id.EditTextPassword);
        ButtonGeneratePassword = findViewById(R.id.btnGenerate);
        ButtonCopy = findViewById(R.id.btnCopy);

        ButtonGeneratePassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(EditTextMaxLength.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Maximum Length of Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (!SwitchUpperCase.isChecked() && !SwitchLowerCase.isChecked() && !SwitchNumber.isChecked() && !SwitchSpecialCharacter.isChecked())
                    {
                        Toast.makeText(MainActivity.this, "Please On Switch of Types of Password Character", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //to get Edit Text Value in form of int
                        int PasswordLength = Integer.parseInt(EditTextMaxLength.getText().toString());

                        //create char array of password used for generated character store
                        //char[] password = new char[PasswordLength];
                        StringBuilder password = new StringBuilder(PasswordLength);
                        String combinedChars = "";

                        Random random = new Random();
                        if (SwitchUpperCase.isChecked()) {
                            combinedChars += Capital_chars;
                        }

                        if (SwitchLowerCase.isChecked()) {
                            combinedChars += Small_chars;
                        }

                        if (SwitchNumber.isChecked()) {
                            combinedChars += numbers;
                        }

                        if (SwitchSpecialCharacter.isChecked()) {
                            combinedChars += symbols;
                        }

                        if(PasswordLength > 100)
                        {
                            Toast.makeText(MainActivity.this, "Please Enter Password Lenth between 1 to 100 ", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                                for (int i = 0 ; i < PasswordLength ; i++ )
                                {
                                    //password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
                                    password.append(combinedChars.charAt(random.nextInt(combinedChars.length())));
                                }

                                EditTextPassword.setText("");
                                EditTextPassword.setText(password.toString());
                                Final_Password = password.toString();
                                combinedChars="";
                                password = null;
                        }
                    }
                }
            }
        });

        ButtonCopy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Password Copied", Final_Password);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MainActivity.this, "Password Copied", Toast.LENGTH_SHORT).show();
                    Final_Password="";
            }
        });
    }
}