package happy.linhdn.nowplaying.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import happy.linhdn.nowplaying.data.model.NowPlayingResponse;
import happy.linhdn.nowplaying.data.rest.RepoRepository;
import happy.linhdn.nowplaying.util.Constant;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private final RepoRepository repoRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<NowPlayingResponse> nowPlayingMovies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private int pPageNumber;
    private int pMaxPage;

    @Inject
    public ListViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
        pPageNumber = 1;
        pMaxPage = 1;
        fetchMovies(false);
    }

    LiveData<NowPlayingResponse> getMoviePlayingNow() {
        return nowPlayingMovies;
    }
    LiveData<Boolean> getError() {
        return repoLoadError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }

    public void fetchMovies(boolean isLoadMore) {
        loading.setValue(true);
        if (isLoadMore) {
            if (pMaxPage != 1 && pPageNumber < pMaxPage) {
                pPageNumber++;
            }
        }
//        repoRepository.getMoviesNowPlaying(Constant.API_KEY, 1).enqueue(new Callback<NowPlayingResponse>() {
//            @Override
//            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
//                repoLoadError.setValue(false);
//                nowPlayingMovies.setValue(response.body());
//                loading.setValue(false);
//            }
//
//            @Override
//            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
//                repoLoadError.setValue(true);
//                loading.setValue(false);
//            }
//        });

        disposable.add(repoRepository.getMoviesNowPlayingSingle(Constant.API_KEY, pPageNumber).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<NowPlayingResponse>() {
                    @Override
                    public void onSuccess(NowPlayingResponse value) {
                        repoLoadError.setValue(false);
                        if (!isLoadMore) {
                            nowPlayingMovies.setValue(value);
                        } else {
                            nowPlayingMovies.postValue(value);
                        }

                        pMaxPage = value.total_pages;
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    public boolean isCanLoadMore() {
        return pPageNumber < pMaxPage;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
