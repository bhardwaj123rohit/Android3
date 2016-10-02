package com.example.rohit.tttfrag;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rohit.tttfrag.detailsFragment.OnListFragmentInteractionListener;
import com.example.rohit.tttfrag.friends.FriendContent.FriendData;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link FriendData} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MydetailsRecyclerViewAdapter extends RecyclerView.Adapter<MydetailsRecyclerViewAdapter.ViewHolder> {

    private final List<FriendData> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MydetailsRecyclerViewAdapter(List<FriendData> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //Log.d(TAG, "onBindViewHolder: "+holder);
        //holder.mIdView.set(mValues.get(position).id);
        //holder.mIdView.setImageURI(mValues.get(position).content);


        holder.mContentView.setText(mValues.get(position).id);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIdView;
        public final TextView mContentView;
        public FriendData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (ImageView) view.findViewById(R.id.image);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
