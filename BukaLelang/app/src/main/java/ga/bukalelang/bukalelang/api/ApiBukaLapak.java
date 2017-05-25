package ga.bukalelang.bukalelang.api;

import android.util.Base64;

import java.io.IOException;

import ga.bukalelang.bukalelang.config.Config;
import ga.bukalelang.bukalelang.model.User;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arbudt on 5/23/2017.
 */

public class ApiBukaLapak {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();

                    String userToken = User.getUser_id()+":"+User.getToken();
                    String auth = Base64.encodeToString(userToken.getBytes(), Base64.NO_WRAP);

                    Request.Builder builder = originalRequest.newBuilder().header("Authorization", auth);

                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                }
            }).build();
            retrofit = new Retrofit.Builder().baseUrl(Config.BASE_API_BUKALAPAK).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        }
        return retrofit;
    }
}
