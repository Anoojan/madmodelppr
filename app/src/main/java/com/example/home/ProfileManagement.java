package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ProfileManagement extends AppCompatActivity {

    EditText username;
    EditText dateofbirth;
    EditText password;
    RadioButton male;
    RadioButton female;
    Button updateprofile;
    DBHelper myDb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
        username=findViewById(R.id.editText3);
        dateofbirth=findViewById(R.id.editText4);
        password=findViewById(R.id.editText5);
        male=findViewById(R.id.radioButton);
        female=findViewById(R.id.radioButton2);
        updateprofile=findViewById(R.id.button3);
        myDb3 = new DBHelper(this);
        addInfo();

    }

    public void addInfo() {

        updateprofile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String textusername = username.getText().toString().trim();
                        String textdob = dateofbirth.getText().toString().trim();
                        String textpassword= password.getText().toString().trim();
                        // String amount = amount.getText().toString().trim();

                        if(textusername.isEmpty()){
                            username.setError("Enter the username");
                            username.requestFocus();
                            return;
                        }

                        if(textdob.isEmpty()){
                            dateofbirth.setError("Enter the Street");
                            dateofbirth.requestFocus();
                            return;
                        }
                        if(textpassword.isEmpty()){
                            password.setError("Enter the District");
                            password.requestFocus();
                            return;
                        }


                        boolean isInserted = myDb3.addInfo(textusername,textdob,textpassword);

                        if (isInserted == true)
                            Toast.makeText(ProfileManagement.this, "Details Are Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProfileManagement.this, "Details Are Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}
