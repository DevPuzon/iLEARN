package com.softwaresolution.ilearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegistration extends AppCompatActivity {
    private EditText editText_StudentUsernameReg, editText_StudentPasswordReg,edittext_StudentTeacherName;
    private Button button_StudentRegister;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);
        editText_StudentUsernameReg = (EditText) findViewById(R.id.edittext_StudentUsernameReg);
        editText_StudentPasswordReg = (EditText) findViewById(R.id.edittext_StudentPasswordReg);
        edittext_StudentTeacherName = (EditText) findViewById(R.id.edittext_StudentTeacherName);
        button_StudentRegister = (Button) findViewById(R.id.button_StudentRegister);
        button_StudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentReg();
            }
        });
    }

    private void studentReg(){
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("StudentForm");

        String studentName = editText_StudentUsernameReg.getText().toString().trim();
        String studentPassword = editText_StudentPasswordReg.getText().toString().trim();
        String studentTeacherName = editText_StudentPasswordReg.getText().toString().trim();
        if(TextUtils.isEmpty(studentName)){
            Toast.makeText(this,"Please enter your username", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(studentPassword)){
            Toast.makeText(this,"Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(studentTeacherName)){
            Toast.makeText(this,"Please enter your teacher name assigned", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            LoginStudent studentData = new LoginStudent(studentName,studentPassword,studentTeacherName);
            databaseReference.child(studentName).setValue(studentData);
            Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
