package com.example.admin.firstnamelastname_androidcodechallenge;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.example.admin.firstnamelastname_androidcodechallenge.model.RedditResponse;
import com.example.admin.firstnamelastname_androidcodechallenge.network.RedditSearch;
import com.example.admin.firstnamelastname_androidcodechallenge.network.RetrofitInstanse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final String DEFAULT_CATEGORY = "funny";
    private static final int LIMIT = 50;
    RedditResponse responsedata;
    RecyclerView recyclerView;
    RedditAdapter adapter;

     Intent searchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = findViewById(R.id.sw_search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getPosts(query);
        return true;
    }



    @Override
    public boolean onQueryTextChange(String query) {
      //  getPosts(query);
        return true;
    }


    private void getPosts(String query) {
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        RedditSearch search = RetrofitInstanse.getRetrofitInstance().create(RedditSearch.class);
        if (query == null || query.equals("")) {
            query = DEFAULT_CATEGORY;
        }

        Call<RedditResponse> call = search.getRedditPosts(query, LIMIT);
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<RedditResponse>() {
            @Override
            public void onResponse(Call<RedditResponse> call, Response<RedditResponse> response) {
                responsedata = response.body();
                System.out.println("recibi data");
                System.out.println(responsedata == null);
            }

            @Override
            public void onFailure(Call<RedditResponse> call, Throwable t) {
            }
        });
        if (responsedata != null) {
            generateRedditList(responsedata);
        }

    }

    private void generateRedditList(RedditResponse responsedata) {

        recyclerView = findViewById(R.id.recycler_view_post_list);
        adapter = new RedditAdapter(new ArrayList(responsedata.getData().getChildren()));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


    }


    public void SharePost(View view) {

        Toast.makeText(this, String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();

    }



}
