package happy.linhdn.nowplaying.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponse {
    @SerializedName("page")
    public int page;
    @SerializedName("total_pages")
    public int total_pages;
    @SerializedName("total_results")
    public int total_results;
    @SerializedName("results")
    public List<Movie> results;
}