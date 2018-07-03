package com.example.admin.firstnamelastname_androidcodechallenge.network;

import com.example.admin.firstnamelastname_androidcodechallenge.model.RedditResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RedditSearch {

    @GET("/r/{category}/.json")
    Call<RedditResponse> getRedditPosts(@Path("category") String category, @Query("limit") int limit);


}
