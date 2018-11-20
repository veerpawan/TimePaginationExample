package demo.com.times.adapter;

import android.arch.paging.PageKeyedDataSource;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import demo.com.times.model.ResponseObjects;
import demo.com.times.model.ServerResponse;
import demo.com.times.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, ResponseObjects> {

    //the size of a page that we want
    public static final String PAGE_TITLE = "json";
    public static final int PAGE_SIZE = 20;

    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;


    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, ResponseObjects> callback) {
        RetrofitClient.getInstance()
                .getApi().getResults(FIRST_PAGE, PAGE_TITLE)
                .enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                        if (response.body() != null) {
                            callback.onResult(response.body().responseObjects, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {


                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ResponseObjects> callback) {
        RetrofitClient.getInstance()
                .getApi().getResults(params.key, PAGE_TITLE)
                .enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                            //passing the loaded data
                            //and the previous page key
                            callback.onResult(response.body().responseObjects, adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {

                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ResponseObjects> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getResults(params.key, PAGE_TITLE)
                .enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        Log.e("FGHTY", response.body().meta.next+"");
                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            //Integer key = (response.body().meta.next != null) ? params.key + 1 : null;
                            Integer key=params.key + 1;
                            //passing the loaded data and next page value
                            callback.onResult(response.body().responseObjects, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Log.e("FGHTY", t.toString());
                    }
                });
    }
}