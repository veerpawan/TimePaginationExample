package demo.com.times.model;

import com.google.gson.annotations.SerializedName;


import java.util.List;


public class ServerResponse {

    @SerializedName("objects")
    public List<ResponseObjects> responseObjects;
    @SerializedName("meta")
    public Meta meta;
}

