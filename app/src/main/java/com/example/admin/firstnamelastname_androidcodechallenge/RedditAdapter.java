package com.example.admin.firstnamelastname_androidcodechallenge;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.firstnamelastname_androidcodechallenge.model.Child;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.RedditHoldView> implements View.OnClickListener {

    ArrayList<Child> children;

    public RedditAdapter(ArrayList<Child> children) {
        this.children = children;
    }

    @Override
    public RedditHoldView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.post_row_layout, parent, false);
        return new RedditHoldView(view);
    }

    @Override
    public void onBindViewHolder(final RedditHoldView holder, int position) {

        if (children.get(position).getData().getTitle() != null)
            holder.title.setText("titulo hardcore");
        System.out.println(holder.title.getText());
        if (children.get(position).getData().getAuthor() != null)
            holder.title.setText(children.get(position).getData().getAuthor());
        if (children.get(position).getData().getUps() != null)
            holder.title.setText(children.get(position).getData().getUps().toString());
        if (children.get(position).getData().getDowns() != null)
            holder.title.setText(children.get(position).getData().getDowns().toString());
        if (children.get(position).getData().getThumbnail() != null)
            if (children.get(position).getData().getThumbnail().equals("self")) {
            //TODO self image
                holder.thumbail.setImageResource(R.drawable.avatar_bg);
            } else if (children.get(position).getData().getThumbnail().equals("default")) {
            holder.thumbail.setImageResource(R.drawable.avatar_bg);
            } else {
                Picasso.with((holder.itemView.getContext())).load(children.get(position).getData().getThumbnail()).into(holder.thumbail);
            }

            holder.itemView.setId(position);
       holder.itemView.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    @Override
    public void onClick(View view) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, children.get(view.getId()).getData().getTitle());
        sendIntent.setType("text/plain");
        view.getContext().startActivity(sendIntent);

    }

    class RedditHoldView extends RecyclerView.ViewHolder {

        TextView title, author, ups, downs, num_comments;
        ImageView thumbail;

        public RedditHoldView(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            author = itemView.findViewById(R.id.tv_author);
            ups = itemView.findViewById(R.id.tv_ups);
            downs = itemView.findViewById(R.id.tv_downs);
            num_comments = itemView.findViewById(R.id.tv_num_comments);
            thumbail = itemView.findViewById(R.id.iv_thumbnail);

        }
    }

}
