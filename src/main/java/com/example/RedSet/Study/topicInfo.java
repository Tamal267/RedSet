package com.example.RedSet.Study;

public class topicInfo {

    private static final topicInfo instance = new topicInfo();
    public static topicInfo getInstance(){
        return instance;
    }
    String mainTopic, subTopic;
    public String getMainTopic() {
        return mainTopic;
    }

    public void setMainTopic(String mainTopic) {
        this.mainTopic = mainTopic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }
}
