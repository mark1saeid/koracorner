package com.example.koracorner;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgetAndChangePasswordActivity extends AppCompatActivity {

    private EditText edtMode;
    private TextView txtMode;
    private Button submit;
    private FirebaseAuth auth;

    private TextInputLayout labelMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_and_change_password);



        //Get FireBase auth instance
        auth = FirebaseAuth.getInstance();

        edtMode = (EditText) findViewById(R.id.mode);
        txtMode = (TextView) findViewById(R.id.title);
        submit = (Button) findViewById(R.id.submit_button);
        labelMode = (TextInputLayout) findViewById(R.id.label);

        final int mode = getIntent().getIntExtra("Mode", 0);
        if (mode == 0) {
            txtMode.setText("Forget Password");
            edtMode.setHint("Enter Registered Email");
            labelMode.setHint("Enter Registered Email");
        } else if (mode == 1) {
            txtMode.setText("Change Password");
            edtMode.setHint("Enter New Password");
            labelMode.setHint("Enter New Password");
        } else if (mode == 2) {
            txtMode.setText("Change Email");
            edtMode.setHint("Enter New Email");
            labelMode.setHint("Enter New Email");
        } else {
            txtMode.setText("Delete User");
            edtMode.setVisibility(View.GONE);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change method option based on the value
                callFunction(mode);
            }
        });

    }

    private void callFunction(int mode) {

        FirebaseUser user = auth.getCurrentUser();
        final String modeStr = edtMode.getText().toString();
        if (mode == 0) {
            if (TextUtils.isEmpty(modeStr)) {
                edtMode.setError("Value Required");
            } else {

                // Method to Reset Password or Forget Password Option
                auth.sendPasswordResetEmail(modeStr).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetAndChangePasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgetAndChangePasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        } else if (mode == 1) {
            if (TextUtils.isEmpty(modeStr)) {
                edtMode.setError("Value Required");
            } else {

                // Method to change Password Option
                user.updatePassword(modeStr)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgetAndChangePasswordActivity.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgetAndChangePasswordActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
            }
        } else if (mode == 2) {
            if (TextUtils.isEmpty(modeStr)) {
                edtMode.setError("Value Required");
            } else {

                // Method to Change Email or Username Option
                user.updateEmail(modeStr)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgetAndChangePasswordActivity.this, "Email address is updated.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ForgetAndChangePasswordActivity.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        } else {
            if (user != null) {

                // Method to Remove Account Option
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgetAndChangePasswordActivity.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgetAndChangePasswordActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        }

    }

}
