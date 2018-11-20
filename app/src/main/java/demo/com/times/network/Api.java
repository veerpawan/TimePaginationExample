package demo.com.times.network;

import demo.com.times.model.ServerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("product/")
    Call<ServerResponse> getResults(@Query("offset") int page, @Query("format") String pagesize);
}
