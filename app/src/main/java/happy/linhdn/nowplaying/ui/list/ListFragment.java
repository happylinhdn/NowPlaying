package happy.linhdn.nowplaying.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import happy.linhdn.nowplaying.R;
import happy.linhdn.nowplaying.base.BaseFragment;
import happy.linhdn.nowplaying.util.ViewModelFactory;

public class ListFragment extends BaseFragment {

    @BindView(R.id.grid_view)
    GridView gridView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;
    private ListViewModel viewModel;
    private MovieListAdapter adapter;
    private boolean loading = false;

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);
        adapter = new MovieListAdapter(this, viewModel);
        gridView.setAdapter(adapter);

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount !=0) {
                    if(viewModel.isCanLoadMore() && !loading) {
                        viewModel.fetchMovies(true);
                    }
                }
            }
        });

        observableViewModel();
    }

    private void observableViewModel() {
        viewModel.getMoviePlayingNow().observe(this, repos -> {
            if(repos != null) {
                gridView.setVisibility(View.VISIBLE);
                adapter.addData(repos.results);
            }
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loading = isLoading;
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
//                    gridView.setVisibility(View.GONE);
                }
            }
        });
    }
}
