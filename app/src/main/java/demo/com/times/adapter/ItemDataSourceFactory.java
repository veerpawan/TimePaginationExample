package demo.com.times.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import demo.com.times.model.ResponseObjects;

public class ItemDataSourceFactory extends DataSource.Factory {

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, ResponseObjects>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, ResponseObjects> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource();

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, ResponseObjects>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
