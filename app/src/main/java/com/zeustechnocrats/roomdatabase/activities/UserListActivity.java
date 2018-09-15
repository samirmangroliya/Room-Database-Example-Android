package com.zeustechnocrats.roomdatabase.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zeustechnocrats.roomdatabase.R;
import com.zeustechnocrats.roomdatabase.model.User;
import com.zeustechnocrats.roomdatabase.database.UserDatabase;

import java.lang.ref.WeakReference;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private TextView tvData;
    public UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();

        userDatabase = UserDatabase.getInstance(this);

        new GetAllUsers(UserListActivity.this).execute();
    }

    private void initUI() {
        tvData = (TextView) findViewById(R.id.tvdata);

    }

    private static class GetAllUsers extends AsyncTask<Void, Void, List<User>> {
        private WeakReference<UserListActivity> activityWeakReference;

        public GetAllUsers(UserListActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected List<User> doInBackground(Void... objs) {
            return activityWeakReference.get().userDatabase.getUserDao().getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            Log.d("UserListActivity",users.size()+"");
            if (users.size() == 0) {
                activityWeakReference.get().tvData.setText("No one user inserted...");
                return;
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Total Users: " + users.size());
            stringBuilder.append("\n\n");

            for (User user : users) {

                stringBuilder.append("Name: " + user.getName());
                stringBuilder.append("\n");
                stringBuilder.append("Email: " + user.getEmail());
                stringBuilder.append("\n");
                stringBuilder.append("-----------------------------------------");
                stringBuilder.append("\n");
            }
            activityWeakReference.get().tvData.setText(stringBuilder.toString());
        }

    }


}
