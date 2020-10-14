package sk.itsovy.android.topics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    private TextView textViewTopic;
    private TextView textViewStudent;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewTopic = view.findViewById(R.id.textViewDetail);
        textViewStudent = view.findViewById(R.id.textViewSelectedStudent);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
        String[] students = getResources().getStringArray(R.array.names);
        StudentsAdapter adapter = new StudentsAdapter(students);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

        TopicsViewModel model = new ViewModelProvider(requireActivity()).get(TopicsViewModel.class);
        model.getSelectedTopic().observe(this, this::setTopic);

    }

    private void setTopic(Topic topic) {
        textViewTopic.setText(topic.getTopic());
        textViewStudent.setText(topic.getStudent());
    }
}