package ga.bukalelang.bukalelang.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.Authenticator;

import ga.bukalelang.bukalelang.R;
import ga.bukalelang.bukalelang.api.ApiBukaLapak;
import ga.bukalelang.bukalelang.api.ApiService;
import ga.bukalelang.bukalelang.config.Config;
import ga.bukalelang.bukalelang.helper.DBDataSource;
import ga.bukalelang.bukalelang.model.LoginResponse;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnLogin;
    private TextView textLinkInfo;

    private ProgressDialog progressDialog;

    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);

        textLinkInfo = (TextView) findViewById(R.id.textLinkInfo);
        textLinkInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "bukalelang bukalapak", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        dataSource = new DBDataSource(this);
    }

    public void login(){
        if(!validate()){
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

//        getDataLogin();
//        postDataLogin();
//        postDataLoginJson();
        getToken();

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        onLoginSuccess();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 5000);
    }

    private void getToken(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                String userToken = inputEmail.getText()+":"+inputPassword.getText();
                String auth = Base64.encodeToString(userToken.getBytes(), Base64.NO_WRAP);

                Request.Builder builder = originalRequest.newBuilder().header("Authorization", auth);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit newRetrofit = new Retrofit.Builder().baseUrl(Config.BASE_API_BUKALAPAK).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();

        ApiService apiService = newRetrofit.create(ApiService.class);
        Call<LoginResponse> result = apiService.getApiToken();
        result.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try{
                    if(response.body().getUserId() != null){
                        progressDialog.setMessage("Login Success");

                        try{
                            long idUser = dataSource.createUser(
                                response.body().getUserId().toString(),
                                response.body().getUserName().toString(),
                                response.body().getConfirmed().toString(),
                                response.body().getToken().toString(),
                                response.body().getEmail().toString(),
                                response.body().getConfirmedPhone().toString(),
                                response.body().getOmnikey().toString()
                            );
                            progressDialog.setMessage("Register Local Account");
                        }catch (Exception e){
                            Toast.makeText(LoginActivity.this, "Failed Register"+e.getMessage(), Toast.LENGTH_LONG).show();
                        }

//                        try{
//                            dataSource.getUserById(idUser);
//                            progressDialog.setMessage("Account has Register");
//                            onLoginSuccess();
//                            //Intent intent = new Intent(LoginActivity.this, AkunActivity.class);
//                            //startActivity(intent);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
                    }else{
                        progressDialog.setMessage(response.body().getMessage());
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        onLoginFailed();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    onLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Terjadi masalah saat request data", Toast.LENGTH_LONG).show();
                onLoginFailed();
            }
        });
    }


    private void postDataLogin(){
        ApiService apiService = ApiBukaLapak.getClient().create(ApiService.class);


//        Request.Builder builder = orig
//        String authToken = Credentials.basic(inputEmail.getText().toString(), inputPassword.getText().toString());

        Call<LoginResponse> result = apiService.getApiToken();
        result.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Toast.makeText(LoginActivity.this, "Login Berhasil"+response.body().getMessage()+"basic ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Terjadi masalah saat request data", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void postDataLoginJson(){
        ApiService apiService = ApiBukaLapak.getClient().create(ApiService.class);

        String append = inputEmail.getText()+":"+inputPassword.getText();
        final String auth = "Basic "+Base64.encodeToString(append.getBytes(),Base64.NO_WRAP);
//        Request.Builder builder = orig
//        String authToken = Credentials.basic(inputEmail.getText().toString(), inputPassword.getText().toString());
        Call<ResponseBody> result = apiService.getApiTokenJson();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    if(response.body() != null){
                        Toast.makeText(LoginActivity.this, "Proses body "+response.body().string(), Toast.LENGTH_LONG).show();
                    }else if(response.errorBody() != null){
                        Toast.makeText(LoginActivity.this, "Proses error "+response.errorBody().string(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Hasil tidak diketahui", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Terjadi masalah saat request data", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getDataLogin(){
        ApiService apiService = ApiBukaLapak.getClient().create(ApiService.class);

        Call<ResponseBody> result = apiService.getCategory();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = response.message();
                    String str2 = response.errorBody().toString();
                    String str4 = response.toString();
                    String str3 = "";
                    if(response.isSuccessful()){
                        str3 = "sukses";
                    }else{
                        str3 = "gagal";
                    }
                    if(response.body() != null){
                        Toast.makeText(LoginActivity.this, "Proses body "+response.body().string(), Toast.LENGTH_LONG).show();
                    }else if(response.errorBody() != null){
                        Toast.makeText(LoginActivity.this, "Proses error "+response.errorBody().string(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Hasil tidak diketahui", Toast.LENGTH_LONG).show();
                    }
                    //textLinkInfo.setText(str4);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Terjadi masalah saat request data", Toast.LENGTH_LONG).show();
            }
        });
    }

/*
    private void getDataAsModel(){
        UserApiService userApiService = ApiClient.getClient().create(UserApiService.class);

        Call<Result> call = userApiService.getResultInfo();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Toast.makeText(NgeRet.this, "Response versi:"+response.body().getInfo().getVersion()+"\n seed:"+response.body().getInfo().getSeed(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(NgeRet.this, "Terjadi masalah saat request data", Toast.LENGTH_LONG).show();
            }
        });
    }
    */

    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        progressDialog.dismiss();
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
        progressDialog.dismiss();
    }

    public boolean validate() {
        boolean valid = true;

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("enter a valid email address");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            inputPassword.setError("minimal 4 alphanumeric characters");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }

}
