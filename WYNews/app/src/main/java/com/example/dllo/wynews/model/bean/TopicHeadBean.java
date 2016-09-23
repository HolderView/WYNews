package com.example.dllo.wynews.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 */
public class TopicHeadBean {

    /**
     * focusNum : 864
     * picUrl : http://dingyue.nosdn.127.net/WyAO4V9RxxrBWAvNnVmne8YzZLadiGaIfQmyjqBC5Y59K1462266097752.jpg
     * topicId : SJ03393238481963889505
     * topicName : 杨幂
     */

    private List<话题Bean> 话题;

    public List<话题Bean> get话题() {
        return 话题;
    }

    public void set话题(List<话题Bean> 话题) {
        this.话题 = 话题;
    }

    public static class 话题Bean {
        private int focusNum;
        private String picUrl;
        private String topicId;
        private String topicName;

        public int getFocusNum() {
            return focusNum;
        }

        public void setFocusNum(int focusNum) {
            this.focusNum = focusNum;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }
    }
}
