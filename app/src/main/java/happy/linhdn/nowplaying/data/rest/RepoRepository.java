package happy.linhdn.nowplaying.data.rest;

import javax.inject.Inject;

import happy.linhdn.nowplaying.data.model.NowPlayingResponse;
import io.reactivex.Single;
import retrofit2.Call;

public class RepoRepository {

    private final RepoService repoService;

    @Inject
    public RepoRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<NowPlayingResponse> getMoviesNowPlayingSingle(String apiKey, int page) {
        return repoService.getNowPlayingDataSingle(apiKey, page);
    }

    public Call<NowPlayingResponse> getMoviesNowPlaying(String apiKey, int page) {
        return repoService.getNowPlayingData(apiKey, page);
    }
}
