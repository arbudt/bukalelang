package ga.bukalelang.bukalelang.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ga.bukalelang.bukalelang.R;
import ga.bukalelang.bukalelang.model.User;

public class UserActivity extends AppCompatActivity {

    private TextView textUserId, textUserName, textUserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textUserId = (TextView) findViewById(R.id.textUserId);
        textUserId.setText("User Id : "+User.getUser_id());

        textUserName = (TextView) findViewById(R.id.textUserName);
        textUserName.setText("User Name :"+User.getUser_name());

        textUserEmail = (TextView) findViewById(R.id.textUserEmail);
        textUserEmail.setText("User Email :"+User.getEmail());
    }

}
