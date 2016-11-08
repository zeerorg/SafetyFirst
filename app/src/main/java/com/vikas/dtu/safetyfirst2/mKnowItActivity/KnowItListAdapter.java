package com.vikas.dtu.safetyfirst2.mKnowItActivity;

/**
 * Created by Vikas on 07-11-2016.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst2.R;

import java.util.List;

public class KnowItListAdapter extends RecyclerView.Adapter<KnowItListAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    List<KnowItComponent> knowItComponents;

    public KnowItListAdapter(List<KnowItComponent> knowItComponents){
        this.knowItComponents = knowItComponents;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_knowit, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(knowItComponents.get(i).name);
        personViewHolder.personPhoto.setImageResource(knowItComponents.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return knowItComponents.size();
    }
}