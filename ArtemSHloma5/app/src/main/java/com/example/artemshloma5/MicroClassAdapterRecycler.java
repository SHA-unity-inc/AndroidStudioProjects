package com.example.artemshloma5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MicroClassAdapterRecycler extends RecyclerView.Adapter<MicroClassAdapterRecycler.ItemViewHolder> {

    private List<MicroClass> itemList;

    public MicroClassAdapterRecycler(List<MicroClass> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MicroClassAdapterRecycler.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mega_class_button, parent, false);
        return new MicroClassAdapterRecycler.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MicroClassAdapterRecycler.ItemViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.button_img);
            textView = itemView.findViewById(R.id.button_text);
        }

        public void bind(MicroClass item) {
            textView.setText(item.name);
            imageView.setImageResource(item.image);
        }
    }
}