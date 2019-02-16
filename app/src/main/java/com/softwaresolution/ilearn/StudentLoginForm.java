package com.softwaresolution.ilearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentLoginForm extends AppCompatActivity {
    public static String STUDENT_NAME;
    public static String TEACHER_ASSIGNED;
    private EditText editText_StudentUsername, editText_StudentPassword;
    private Button button_StudentLogin;
    private LinearLayout linearLayout_studentReg;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_form);
        button_StudentLogin = (Button) findViewById(R.id.button_StudentLogin);
        editText_StudentUsername = (EditText) findViewById(R.id.edittext_StudentUsername);
        editText_StudentPassword = (EditText) findViewById(R.id.edittext_StudentPassword);
        linearLayout_studentReg = (LinearLayout) findViewById(R.id.linearlayout_studentSignup);

        button_StudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainActivity();
            }
        });
        linearLayout_studentReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStudentReg();
            }
        });
    }
    List<LoginStudent> loginStudents;
    private void  showMainActivity(){
        final String username = editText_StudentUsername.getText().toString().trim();
        final String password = editText_StudentPassword.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please enter you username", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter you password", Toast.LENGTH_LONG).show();
            return;
        }
        loginStudents = new ArrayList<LoginStudent>();
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("StudentForm");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loginStudents.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    LoginStudent loginStudent = dataSnapshot1.getValue(LoginStudent.class);
                    loginStudents.add(loginStudent);
                }
                for (int i = 0;i < loginStudents.size();i++){
                    if(username.equals(loginStudents.get(i).studentName)
                            && password.equals(loginStudents.get(i).studentPassword)){
                        STUDENT_NAME = loginStudents.get(i).studentName;
                        TEACHER_ASSIGNED = loginStudents.get(i).studentTeacherAssigned;
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Database error",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void showStudentReg(){
        Intent intent = new Intent(this,StudentRegistration.class);
        startActivity(intent);
    }
}