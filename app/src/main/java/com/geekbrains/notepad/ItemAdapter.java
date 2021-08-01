package com.geekbrains.notepad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHoldrer> {


    private CardSource dataSource;
    private static OnItemClickListener listener;

    public ItemAdapter(CardSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setListener(@Nullable OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHoldrer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ItemViewHoldrer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHoldrer holder, int position) {

        holder.bind(dataSource.getCardNote(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public static class ItemViewHoldrer extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView description;
        private final TextView date;

        public ItemViewHoldrer(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);

//            itemView.setOnClickListener(v -> {
//                listener.onItemClick(getAdapterPosition());
//            });

        }



        public void bind(CardNote cardNote) {
            title.setText(cardNote.getTitle());
            description.setText(cardNote.getDescription());
            date.setText(cardNote.getDate());

            description.setOnClickListener(v -> listener.onItemClick(description, getLayoutPosition()));
        }
    }

    interface OnItemClickListener{
        void onItemClick (View view, int position);
    }
}
