package sk.itsovy.android.topics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (smallDevice()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_activity, new MainFragment())
                    .commit();

            TopicsViewModel model = new ViewModelProvider(this).get(TopicsViewModel.class);
            model.getSelectedTopic().observe(this, this::showDetailActivity);
        }
    }

    private boolean smallDevice() {
        return findViewById(R.id.main_activity) != null;
    }

    private void showDetailActivity(Topic topic) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity, new DetailFragment())
                .addToBackStack(null)
                .commit();

    }
}