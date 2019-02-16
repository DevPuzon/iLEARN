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

public class TeacherLoginForm extends AppCompatActivity {
    private EditText editText_TeacherUsername, editText_TeacherPassword;
    private Button button_TeacherLogin;
    private LinearLayout linearLayout_teacherSignup;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_form);
        button_TeacherLogin = (Button) findViewById(R.id.button_TeacherLogin);
        editText_TeacherUsername = (EditText) findViewById(R.id.edittext_TeacherUsername);
        editText_TeacherPassword = (EditText) findViewById(R.id.edittext_TeacherPassword);
        linearLayout_teacherSignup = (LinearLayout) findViewById(R.id.linearlayout_teacherSignup);

        button_TeacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authTeacher();
            }
        });
        linearLayout_teacherSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTeacherReg();
            }
        });
    }
    List<LoginTeacher> loginTeacherList;
    private  void authTeacher(){
        final String username = editText_TeacherUsername.getText().toString().trim();
        final String password = editText_TeacherPassword.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please enter you username", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter you password", Toast.LENGTH_LONG).show();
            return;
        }


        loginTeacherList = new ArrayList<LoginTeacher>();
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("TeacherForm");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loginTeacherList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    LoginTeacher loginTeacher1 = dataSnapshot1.getValue(LoginTeacher.class);
                    loginTeacherList.add(loginTeacher1);
                }
                for (int i = 0;i < loginTeacherList.size();++i){

                    if(username.equals(loginTeacherList.get(i).teacherName)
                            && password.equals(loginTeacherList.get(i).teacherPassword)){
                        for (int ie = 1; ie <= 10; ++ie) {
                            TeacherGetSetData.getnumber.add(String.valueOf(ie));
                        }
                        TeacherGetSetData.teacherName =loginTeacherList.get(i).teacherName;
                        Intent intent = new Intent(getApplicationContext(), TeacherBottomNavigation.class);
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
    private void showTeacherReg(){
        Intent intent = new Intent(this, TeacherRegistration.class);
        startActivity(intent);
    }
}
