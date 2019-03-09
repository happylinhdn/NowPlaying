package happy.linhdn.nowplaying.data.model;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Movie {
    @SerializedName("poster_path")
    public String poster_path;

    @SerializedName("adult")
    public boolean adult;

    @SerializedName("overview")
    public String overview;

    @SerializedName("id")
    public long id;

    @SerializedName("title")
    public String title;

    @SerializedName("backdrop_path")
    public String backdrop_path;

    @SerializedName("vote_average")
    public double vote_average;

    public String getVoteFormat() {
        Locale locale = Locale.US;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern("#,##0.00");

        return decimalFormat.format(vote_average);
    }
}