package sk.itsovy.android.topics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class StudentsAdapter
        extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {

    private String[] students;
    private int[] counts;

    public StudentsAdapter(String[] students) {
        this.students = students;
        counts = new int[students.length];
        Arrays.fill(counts, 1);
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new StudentsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, int position) {
        holder.bind(students[position], counts[position]);
    }

    @Override
    public int getItemCount() {
        return students.length;
    }

    public static class StudentsViewHolder extends RecyclerView.ViewHolder {

        private Spinner spinner;
        private TextView textView;

        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinner);
            textView = itemView.findViewById(R.id.singleStudentTextView);
        }

        public void bind(String student, int count) {
            textView.setText(student);
            spinner.setSelection(count);
        }
    }
}
