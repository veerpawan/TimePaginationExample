package demo.com.times;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import demo.com.times.adapter.ItemAdapter;
import demo.com.times.adapter.RecyclerItemClickListener;
import demo.com.times.model.ResponseObjects;
import demo.com.times.viewmodel.ItemViewModel;

public class MainActivity extends AppCompatActivity {
    ProgressBar feed_loading;
    //getting recyclerview
    private RecyclerView recyclerView;
    PagedList<ResponseObjects> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setting up recyclerview
        recyclerView = findViewById(R.id.recyclerview);
        feed_loading = findViewById(R.id.feed_loading);
        feed_loading.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        //creating the Adapter
        final ItemAdapter adapter = new ItemAdapter(this);


        //observing the itemPagedList from view model
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<ResponseObjects>>() {
            @Override
            public void onChanged(@Nullable PagedList<ResponseObjects> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
                feed_loading.setVisibility(View.GONE);
            }
        });

        //setting the adapter
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i=new Intent(getApplicationContext(),DetailedActivity.class);
                        i.putExtra("slug", adapter.getCurrentList().get(position).slug);
                        i.putExtra("banner", adapter.getCurrentList().get(position).i);
                        i.putExtra("p", adapter.getCurrentList().get(position).p);
                        i.putExtra("id", adapter.getCurrentList().get(position).id);
                        startActivity(i);

                    }
                })
        );
    }
}

