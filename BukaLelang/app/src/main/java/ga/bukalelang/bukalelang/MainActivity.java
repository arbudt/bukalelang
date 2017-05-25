package ga.bukalelang.bukalelang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ga.bukalelang.bukalelang.activity.LoginActivity;
import ga.bukalelang.bukalelang.activity.UserActivity;
import ga.bukalelang.bukalelang.helper.DBDataSource;

public class MainActivity extends AppCompatActivity {

    private Button btnAkun, btnLogin, btnRegister, btnGetUser;
    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);

        btnAkun = (Button) findViewById(R.id.btnShowAkun);
        btnAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        btnLogin = (Button) findViewById(R.id.btnShowLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        dataSource = new DBDataSource(this);


        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    dataSource.open();
                    long idUser = dataSource.createUser(
                            "123",
                            "arif",
                            "true",
                            "11111",
                            "ema@gma.com",
                            "null",
                            "11212121"
                    );
                    if(idUser > 0){
                        Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Failed insert", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Failed Register"+e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnGetUser = (Button) findViewById(R.id.btnGetUser);
        btnGetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    dataSource.open();
                    dataSource.getUser();
                    Toast.makeText(MainActivity.this, "Geted user", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Failed get user"+e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
