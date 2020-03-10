package com.dhanifudin.cashflow.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhanifudin.cashflow.R;
import com.dhanifudin.cashflow.models.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    public interface OnItemTransactionListener {

        void onTransactionClicked(int index, Transaction item);
    }
    private List<Transaction> items;
    private OnItemTransactionListener listener;

    public TransactionAdapter(List<Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Transaction item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionText;
        TextView amountText;
        TextView typeText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionText = itemView.findViewById(R.id.text_description);
            amountText = itemView.findViewById(R.id.text_amount);
            typeText = itemView.findViewById(R.id.text_type);
        }
        public void bind(final int index, final Transaction item) {
            descriptionText.setText(item.getDescription());
            amountText.setText(String.valueOf(item.getAmount()));
            typeText.setText(item.getType().toString());
            // TODO: Tambahkan interaksi click di sini
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index, item);
                }
            });
        }
    }


}
