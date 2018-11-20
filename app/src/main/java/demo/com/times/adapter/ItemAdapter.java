package demo.com.times.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import demo.com.times.R;
import demo.com.times.model.ResponseObjects;

public class ItemAdapter extends PagedListAdapter<ResponseObjects, ItemAdapter.ItemViewHolder> {

    private static DiffUtil.ItemCallback<ResponseObjects> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ResponseObjects>() {
                @Override
                public boolean areItemsTheSame(ResponseObjects oldItem, ResponseObjects newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(ResponseObjects oldItem, ResponseObjects newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private Context mCtx;

    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ResponseObjects item = getItem(position);

        if (item != null) {
            holder.textView.setText(item.slug);
            Glide.with(mCtx)
                    .load(item.i)
                    .into(holder.imageView);
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
