package dev.uublabs.githubhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepositoriesActivity extends AppCompatActivity {

    private String username;
    private List<GithubRepository> repositoryList;
    private TextView tvRepoTitle;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repositories);

        username = getIntent().getStringExtra("username");

        tvRepoTitle = findViewById(R.id.tvRepoTitle);
        tvRepoTitle.setText(username + "'s Repositories");

        repositoryList = new ArrayList<>();
        recyclerView = findViewById(R.id.rvRepos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);

        RetrofitHelper.getRepositories(username)
                .enqueue(new Callback<List<GithubRepository>>()
                {
                    @Override
                    public void onResponse(Call<List<GithubRepository>> call, Response<List<GithubRepository>> response)
                    {
                        repositoryList = response.body();

                        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(repositoryList);

                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<GithubRepository>> call, Throwable t)
                    {

                    }
                });


    }
}
