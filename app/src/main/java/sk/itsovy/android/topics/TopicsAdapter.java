package sk.itsovy.android.topics;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TopicsAdapter extends ListAdapter<Topic, TopicsAdapter.TopicsViewHolder> {

    private OnTopicClickListener listener;

    protected TopicsAdapter() {
        super(new DiffUtil.ItemCallback<Topic>() {
            @Override
            public boolean areItemsTheSame(@NonNull Topic oldItem, @NonNull Topic newItem) {
                return Objects.equals(oldItem, newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull Topic oldItem, @NonNull Topic newItem) {
                return this.areItemsTheSame(oldItem, newItem);
            }
        });

        List<Topic> list = new ArrayList<>();
        list.add(new Topic("Kotlin"));
        list.add(new Topic("Security"));
        list.add(new Topic("Performance"));
        list.add(new Topic("Android 11"));
        submitList(list);
    }

    public void setListener(OnTopicClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.activity_list_item, parent, false);
        return new TopicsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    public static class TopicsViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public TopicsViewHolder(@NonNull View layout) {
            super(layout);
            textView = layout.findViewById(android.R.id.text1);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                textView.setTextAppearance(android.R.style.TextAppearance_Large);
            }
        }

        public void bind(final Topic item, final OnTopicClickListener listener) {
            textView.setText(item.getTopic());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTopicClick(item);
                }
            });
        }
    }
}
