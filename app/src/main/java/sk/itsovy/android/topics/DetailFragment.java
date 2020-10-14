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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DetailFragment extends Fragment {

    private TextView textViewTopic;
    private TextView textViewStudent;
    private StudentsAdapter adapter;

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
        adapter = new StudentsAdapter(students);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

        TopicsViewModel model = new ViewModelProvider(requireActivity()).get(TopicsViewModel.class);
        model.getSelectedTopic().observe(this, this::setTopic);

        Button button = view.findViewById(R.id.buttonAssign);
        // definovanie onClick v layoute vyzaduje implementaciu metody v aktivite
        // tu sa viac hodi implementacia vo fragmente, preto cez listener
        button.setOnClickListener(view1 -> assignStudent());

    }

    private void setTopic(Topic topic) {
        textViewTopic.setText(topic.getTopic());
        textViewStudent.setText(topic.getStudent());
    }

    public void assignStudent() {
        String[] students = adapter.getStudents();
        int[] counts = adapter.getCounts();

        // listocky na zrebovanie
        List<String> tickets = new ArrayList<>();
        for (int i = 0; i < students.length; i++) {
            // prida studenta zadany pocet krat
            for (int j = 0; j < counts[i]; j++) {
                tickets.add(students[i]);
            }
        }

        Random r = new Random();
        int idx = r.nextInt(tickets.size());
        String selected = tickets.get(idx);

        TopicsViewModel model = new ViewModelProvider(requireActivity()).get(TopicsViewModel.class);
        // topic sa vytiahne z modelu, modifikuje a vlozi naspat - to zaruci refresh (... lebo observer)
        Topic topic = model.getSelectedTopic().getValue();
        topic.appendStudent(selected);
        model.setSelectedTopic(topic);

    }
}