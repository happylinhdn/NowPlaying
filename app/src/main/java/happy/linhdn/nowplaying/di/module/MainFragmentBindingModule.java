package happy.linhdn.nowplaying.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import happy.linhdn.nowplaying.ui.list.ListFragment;

//import happy.linhdn.nowplaying.ui.detail.DetailsFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();
//
//    @ContributesAndroidInjector
//    abstract DetailsFragment provideDetailsFragment();
}
