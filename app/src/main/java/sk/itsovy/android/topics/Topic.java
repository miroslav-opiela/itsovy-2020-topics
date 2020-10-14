package sk.itsovy.android.topics;

public class Topic {

    private String topic;
    private String student;

    public Topic(String topic) {
        this.topic = topic;
        this.student = "n/a";
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStudent() {
        return student;
    }

    public void appendStudent(String selected) {
        if (student.equals("n/a")) {
            student = selected;
        } else {
            student = student + ", " + selected;
        }
    }
}
