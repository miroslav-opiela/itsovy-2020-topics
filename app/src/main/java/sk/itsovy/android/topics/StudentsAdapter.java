package sk.itsovy.android.topics;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    // getter
    public String[] getStudents() {
        return students;
    }

    // getter
    public int[] getCounts() {
        return counts;
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
        // potrebujeme menit veci v instancnej premennej pole, preto posielame referenciu
        holder.bind(students[position], counts, position);
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

        public void bind(String student, int[] counts, int index) {
            textView.setText(student);
            spinner.setSelection(counts[index]);
            // ked sa klikne na spinner
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    // pozicia je v nasom pripade aj hodnota. Ak by to bolo inak, je potrebne to vytiahnut z toho string array
                    counts[index] = position;
                    Log.d("SPINNER", student + " " + position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
}
