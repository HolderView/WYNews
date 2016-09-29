package com.example.dllo.wynews.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class PictureBean {

    /**
     * desc : 9月28日，黑龙江牡丹江，一场九月雪将黑龙江省大海林业局二浪河林场装点得美如画，五花山变得银装素裹。据当地工作人员介绍，9月份从未出现过这样的景象。
     * pvnum :
     * createdate : 2016-09-29 11:21:38
     * scover : http://img3.cache.netease.com/photo/0096/2016-09-29/s_C24JMKJJ54GI0096.jpg
     * setname : 黑龙江九月降雪 装扮山区美景如画
     * cover : http://img3.cache.netease.com/photo/0096/2016-09-29/C24JMKJJ54GI0096.jpg
     * pics : ["http://img3.cache.netease.com/photo/0096/2016-09-29/C24JMKJJ54GI0096.jpg","http://img3.cache.netease.com/photo/0096/2016-09-29/C24JMKJK54GI0096.jpg","http://img3.cache.netease.com/photo/0096/2016-09-29/C24JMKJL54GI0096.jpg"]
     * clientcover1 : http://img3.cache.netease.com/photo/0096/2016-09-29/C24JMKJJ54GI0096.jpg
     * replynum : 388
     * topicname :
     * setid : 109800
     * seturl : http://help.3g.163.com/photoview/54GI0096/109800.html
     * datetime : 2016-09-29 11:22:42
     * clientcover :
     * imgsum : 9
     * tcover : http://img3.cache.netease.com/photo/0096/2016-09-29/t_C24JMKJJ54GI0096.jpg
     */

    private String desc;
    private String pvnum;
    private String createdate;
    private String scover;
    private String setname;
    private String cover;
    private String clientcover1;
    private String replynum;
    private String topicname;
    private String setid;
    private String seturl;
    private String datetime;
    private String clientcover;
    private String imgsum;
    private String tcover;
    private List<String> pics;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPvnum() {
        return pvnum;
    }

    public void setPvnum(String pvnum) {
        this.pvnum = pvnum;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getScover() {
        return scover;
    }

    public void setScover(String scover) {
        this.scover = scover;
    }

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getClientcover1() {
        return clientcover1;
    }

    public void setClientcover1(String clientcover1) {
        this.clientcover1 = clientcover1;
    }

    public String getReplynum() {
        return replynum;
    }

    public void setReplynum(String replynum) {
        this.replynum = replynum;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public String getSetid() {
        return setid;
    }

    public void setSetid(String setid) {
        this.setid = setid;
    }

    public String getSeturl() {
        return seturl;
    }

    public void setSeturl(String seturl) {
        this.seturl = seturl;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getClientcover() {
        return clientcover;
    }

    public void setClientcover(String clientcover) {
        this.clientcover = clientcover;
    }

    public String getImgsum() {
        return imgsum;
    }

    public void setImgsum(String imgsum) {
        this.imgsum = imgsum;
    }

    public String getTcover() {
        return tcover;
    }

    public void setTcover(String tcover) {
        this.tcover = tcover;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
