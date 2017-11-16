package dev.uublabs.githubhomework;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Admin on 11/16/2017.
 */

public class RetrofitHelper
{
    public static final String BASE_URL = "https://api.github.com/";

    public static Retrofit create()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    //call interface to get response
    public static Call<GithubProfile> getMyProfile(String user)
    {
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getProfile(user);
    }

    public static Call<List<GithubRepository>> getRepositories(String user)
    {
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRepositories(user);
    }

    //create an interface for http verbs
    public interface RetrofitService
    {
        @GET("users/{user}")
        Call<GithubProfile> getProfile(@Path("user") String user);
        @GET("users/{user}/repos")
        Call<List<GithubRepository>> getRepositories(@Path("user") String user);
    }
}