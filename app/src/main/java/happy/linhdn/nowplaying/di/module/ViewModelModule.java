package happy.linhdn.nowplaying.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import happy.linhdn.nowplaying.di.util.ViewModelKey;
import happy.linhdn.nowplaying.ui.list.ListViewModel;
import happy.linhdn.nowplaying.util.ViewModelFactory;

//import happy.linhdn.nowplaying.ui.detail.DetailsViewModel;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailsViewModel.class)
//    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
