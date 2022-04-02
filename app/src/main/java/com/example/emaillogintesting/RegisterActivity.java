package com.example.emaillogintesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText ET_email,ET_password,ET_confirmPassword;
    private Button Btn_registration;
    String email,password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ET_email=findViewById(R.id.ET_R_email);
        ET_password=findViewById(R.id.ET_R_password);
        ET_confirmPassword=findViewById(R.id.ET_R_confirmpassword);
        Btn_registration=findViewById(R.id.Btn_R_register);
        mAuth = FirebaseAuth.getInstance();



        Btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=ET_email.getText().toString();
                password=ET_password.getText().toString();
                confirmPassword=ET_confirmPassword.getText().toString();
                Log.i("Password",password);
                Log.i("C Password",confirmPassword);
                if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Cradentials", Toast.LENGTH_SHORT).show();
                }else if(!(password.contentEquals(confirmPassword))){
                    Toast.makeText(getApplicationContext(),"Password does not matches",Toast.LENGTH_SHORT).show();
                }else if(password.length() < 6 && confirmPassword.length() <6 ){
                    Toast.makeText(getApplicationContext(),"Password must be of 6 length",Toast.LENGTH_SHORT).show();
                }else{
                    //code for Firebase login
                    login_into_firebase(email,password);
                }

            }
        });




    }

    private void login_into_firebase(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegisterActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}