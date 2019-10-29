package com.example.koracorner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class Channel extends Fragment {

    private RecyclerView mPeopleRV;

    private FirebaseRecyclerAdapter<News, NewsViewHolder> mPeopleRVAdapter;


    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_channel, container, false);
        FirebaseApp.initializeApp(getActivity());















        mPeopleRV = rootView.findViewById(R.id.myRecycleView1);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference("channel");
        Query personsQuery = personsRef.orderByKey();


        mPeopleRV.setLayoutManager(new LinearLayoutManager(getActivity()));





        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<News>().setQuery(personsQuery, News.class).build();

        mPeopleRVAdapter = new FirebaseRecyclerAdapter<News, Channel.NewsViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(Channel.NewsViewHolder holder, final int position, final News model) {


                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDesc());
                holder.setImage(model.getImage());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(getActivity(), NewsWebView.class);
                        intent.putExtra("id", url);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public Channel.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_row, parent, false);

                return new Channel.NewsViewHolder(view);
            }
        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        mPeopleRVAdapter.stopListening();


    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_image);
        }
    }
}