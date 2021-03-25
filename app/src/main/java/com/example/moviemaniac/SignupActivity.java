package com.example.moviemaniac;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviemaniac.DashboardActivity;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private EditText emailEt,passwordEt1,passwordEt2;
    private Button SignUpButton;
    private TextView SigninTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firebaseAuth = firebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passwordEt1 = findViewById(R.id.password);
        passwordEt2 = findViewById(R.id.confirmpass);
        SignUpButton = findViewById((R.id.login));
        progressDialog = new ProgressDialog(this);
        SigninTv = findViewById(R.id.signinTv);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
        SigninTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( SignupActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void Register(){
        String email =emailEt.getText().toString();
        String password1=passwordEt1.getText().toString();
        String password2=passwordEt2.getText().toString();
        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your email ");
            return;
        }
        else if(TextUtils.isEmpty(password1)){
            passwordEt1.setError("Enter your password ");
            return;
        }
        else if(TextUtils.isEmpty(password2)){
            passwordEt2.setError("confirm your password ");
            return;


    }
else if(!(password1.equals(password2)))
        {
            passwordEt2.setError("Different password");
        }
        else if(password1.length()<4){
            passwordEt1.setError("too short password! ");
            return;
        }
        else if(!isValidEmail(email))
        {
            emailEt.setError("Invalid email");
            return;
        }
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignupActivity.this,"successfully registered",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent( SignupActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(SignupActivity.this," failed"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
}
private boolean isValidEmail(String target)
{
    return (!TextUtils.isEmpty(target)&& Patterns.EMAIL_ADDRESS.matcher(target).matches());
}
}