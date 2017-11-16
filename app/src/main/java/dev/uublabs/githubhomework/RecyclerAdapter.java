package dev.uublabs.githubhomework;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/16/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    List<GithubRepository> repositoryList = new ArrayList<>();

    static String TAG = "CelebrityRcAdapter";
    public RecyclerAdapter(List<GithubRepository> list)
    {
        repositoryList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        GithubRepository githubRepository = repositoryList.get(position);
        if(githubRepository != null)
        {
            holder.tvRepoName.setText("Name: "+githubRepository.getName());
            holder.tvRepoDesc.setText("Description: "+githubRepository.getDescription());
            holder.tvRepoDate.setText("Date Pushed: "+githubRepository.getPushedAt());
            holder.tvRepoOwner.setText("Owner: "+githubRepository.getOwner().getLogin());

        }
    }

    @Override
    public int getItemCount()
    {
        return repositoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView tvRepoName;
        private final TextView tvRepoDesc;
        private final TextView tvRepoDate;
        private final TextView tvRepoOwner;

        public ViewHolder(View itemView)
        {
            super(itemView);
            tvRepoName = itemView.findViewById(R.id.tvRepoName);
            tvRepoDesc = itemView.findViewById(R.id.tvRepoDesc);
            tvRepoDate = itemView.findViewById(R.id.tvRepoDate);
            tvRepoOwner = itemView.findViewById(R.id.tvRepoOwner);
        }
    }
}
