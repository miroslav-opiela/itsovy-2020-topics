package sk.itsovy.android.topics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainFragment extends Fragment implements OnTopicClickListener {


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewTopics);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TopicsAdapter adapter = new TopicsAdapter();
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTopicClick(Topic topic) {
        TopicsViewModel model = new ViewModelProvider(requireActivity())
                .get(TopicsViewModel.class);
        model.setSelectedTopic(topic);
    }
}