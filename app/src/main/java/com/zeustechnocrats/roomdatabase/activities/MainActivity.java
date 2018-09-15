package com.zeustechnocrats.roomdatabase.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zeustechnocrats.roomdatabase.R;
import com.zeustechnocrats.roomdatabase.model.User;
import com.zeustechnocrats.roomdatabase.database.UserDatabase;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private EditText etFullName, etEmail;
    private Button btnSave, btnShowData;

    public UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();

        userDatabase = UserDatabase.getInstance(this);

    }

    private void initUI() {
        etFullName = (EditText) findViewById(R.id.etname);
        etEmail = (EditText) findViewById(R.id.etemail);
        btnSave = (Button) findViewById(R.id.btnsave);
        btnShowData = (Button) findViewById(R.id.btnshowdata);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strName = etFullName.getText().toString().trim();
                String strEmail = etEmail.getText().toString().trim();

                if (strName.length() == 0) {
                    etFullName.setError("Please enter name");
                    etFullName.requestFocus();
                } else if (strName.length() == 0) {
                    etFullName.setError("Please enter name");
                    etFullName.requestFocus();
                } else {
                    User user = new User();
                    user.setName(strName);
                    user.setEmail(strEmail);
                    new InsertUser(MainActivity.this, user).execute();
                }

            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
            }
        });

    }

    private static class InsertUser extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<MainActivity> activityWeakReference;
        private User user;

        public InsertUser(MainActivity activity, User user) {
            activityWeakReference = new WeakReference<>(activity);
            this.user = user;
        }

        @Override
        protected Boolean doInBackground(Void... objs) {
            activityWeakReference.get().userDatabase.getUserDao().insert(user);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean isInserted) {
            if (isInserted) {
                Toast.makeText(activityWeakReference.get(), "User inserted successfully...", Toast.LENGTH_SHORT).show();
                activityWeakReference.get().etFullName.setText("");
                activityWeakReference.get().etEmail.setText("");
            }
        }

    }

}
