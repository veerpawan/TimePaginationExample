package demo.com.times.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.content.Context;

import demo.com.times.adapter.ItemDataSource;
import demo.com.times.adapter.ItemDataSourceFactory;
import demo.com.times.model.ResponseObjects;

public class ItemViewModel extends ViewModel {



    //creating livedata for PagedList  and PagedKeyedDataSource
    public LiveData<PagedList<ResponseObjects>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, ResponseObjects>> liveDataSource;

    //constructor
    public ItemViewModel() {

        //getting our data source factory
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }
}
