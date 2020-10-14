package sk.itsovy.android.topics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TopicsViewModel extends ViewModel {

    private MutableLiveData<Topic> selectedTopic = new MutableLiveData<>();

    public LiveData<Topic> getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic.postValue(selectedTopic);
    }
}
