package com.app.firebaseuse;

import android.app.ProgressDialog;
import android.app.Service;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEtUserName,mEtPassword;
    private Button mBtnRegister;
    private TextView mTvsignUp;
     ProgressDialog progressDialog;
     FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);
        mEtUserName=(EditText)findViewById(R.id.idEtUsername);
        mEtPassword=(EditText)findViewById(R.id.idEtPassword);
        mBtnRegister=(Button)findViewById(R.id.idBtnSubmitData);
        mTvsignUp=(TextView)findViewById(R.id.idTvARregisterSIhere);

        mBtnRegister.setOnClickListener(this);
        mTvsignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        if(view == mBtnRegister)
        {
            registerUser();

        }

        if(view == mTvsignUp)
        {

            //Later Login Here
        }



    }

    private void registerUser() {

        String eMail=mEtUserName.getText().toString().trim();
        String password=mEtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(eMail))
        {
            Toast.makeText(this,"Please Enter your EMail Id",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        //Further code

            progressDialog.setMessage("Registering user");

            progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(eMail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // here we register
                    Toast.makeText(MainActivity.this, "You Are registered", Toast.LENGTH_SHORT).show();

                }

                else
                {
                    Toast.makeText(MainActivity.this, "You Are NOT  registered", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
