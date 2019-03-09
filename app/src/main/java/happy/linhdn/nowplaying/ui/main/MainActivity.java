package happy.linhdn.nowplaying.ui.main;

import android.os.Bundle;

import happy.linhdn.nowplaying.R;
import happy.linhdn.nowplaying.base.BaseActivity;
import happy.linhdn.nowplaying.ui.list.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();
    }
}
