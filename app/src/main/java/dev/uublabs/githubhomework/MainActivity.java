package dev.uublabs.githubhomework;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{

    private EditText etUsername;
    private String profile;
    private TextView tvProfileName;
    private ImageView ivProfilePic;
    private TextView tvAliasName;
    private TextView tvCompany;
    private TextView tvLocation;
    private TextView tvRepositories;
    private TextView tvFollowing;
    private TextView tvFollowers;
    private String username;
    private Button btnViewRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        btnViewRepos = findViewById(R.id.btnGoToRepo);
        btnViewRepos.setVisibility(View.INVISIBLE);
    }

    private void bindViews()
    {
        etUsername = findViewById(R.id.etUsername);
        tvProfileName = findViewById(R.id.tvProfileName);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        tvAliasName = findViewById(R.id.tvAliasName);
        tvCompany = findViewById(R.id.tvCompanyName);
        tvLocation = findViewById(R.id.tvLocation);
        tvRepositories = findViewById(R.id.tvRepositories);
        tvFollowing = findViewById(R.id.tvFollowing);
        tvFollowers = findViewById(R.id.tvFollowers);
    }

    public void getProfile(View view)
    {
        username = etUsername.getText().toString();

        if (!username.equals(""))
        {

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            btnViewRepos.setVisibility(View.VISIBLE);
            RetrofitHelper.getMyProfile(username)
                    .enqueue(new Callback<GithubProfile>() {
                        @Override
                        public void onResponse(Call<GithubProfile> call, Response<GithubProfile> response) {
                            new ProfilePicAsync(ivProfilePic).execute(response.body().getAvatarUrl());
                            Log.d("MainActivity", "onResponse: " + response.toString());
                            tvProfileName.setText(response.body().getName());
                            tvAliasName.setText(username);
                            tvCompany.setText("Company: " + response.body().getCompany());
                            tvLocation.setText("Location: " + response.body().getLocation());
                            tvRepositories.setText("Repositories: " + response.body().getPublicRepos());
                            tvFollowing.setText("Following: " + response.body().getFollowing());
                            tvFollowers.setText("Followers: " + response.body().getFollowers());
                            Log.d("MainActivity", "onResponse: " + response.body().getReposUrl());
                        }

                        @Override
                        public void onFailure(Call<GithubProfile> call, Throwable t) {

                        }
                    });
        }
    }

    public void goToRepo(View view)
    {
        Intent intent = new Intent(this, GithubRepositoriesActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
