package happy.linhdn.nowplaying.data.rest;

import happy.linhdn.nowplaying.data.model.NowPlayingResponse;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepoService {

    @GET("now_playing/")
    Call<NowPlayingResponse> getNowPlayingData(@Query("api_key") String api_key, @Query("page") int page);

    @GET("now_playing/")
    Single<NowPlayingResponse> getNowPlayingDataSingle(@Query("api_key") String api_key, @Query("page") int page);

}
