package ga.bukalelang.bukalelang.api;

import ga.bukalelang.bukalelang.model.LoginResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by arbudt on 5/23/2017.
 */

public interface ApiService {
    @GET("users/info.json")
    Call<ResponseBody> getCategory();

    @POST("authenticate.json")
    Call<LoginResponse> getApiToken();

    @POST("authenticate.json")
    Call<ResponseBody> getApiTokenJson();
}
