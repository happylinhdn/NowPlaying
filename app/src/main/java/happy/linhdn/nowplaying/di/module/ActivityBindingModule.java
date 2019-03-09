package happy.linhdn.nowplaying.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import happy.linhdn.nowplaying.ui.main.MainActivity;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
