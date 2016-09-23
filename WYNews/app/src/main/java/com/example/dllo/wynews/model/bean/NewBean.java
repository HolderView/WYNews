package com.example.dllo.wynews.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 */
public class NewBean {

    /**
     * ads : [{"imgsrc":"http://cms-bucket.nosdn.127.net/92fdb07b0494490bad81caa534cf145b20160922105437.jpeg","subtitle":"","tag":"doc","title":"谈恋爱不如养狗!这只狗比男友更实用","url":"C1IH5P0A05169QC9"},{"imgsrc":"http://cms-bucket.nosdn.127.net/f23f7da7b0584e41b57266bb97242d5720160922110837.jpeg","subtitle":"","tag":"photoset","title":"看客:泄洪地别样风景 大鱼被水冲断头","url":"3R710001|2199682"},{"imgsrc":"http://cms-bucket.nosdn.127.net/5d17f71265cf4720a74df5465356717e20160922104151.png","subtitle":"","tag":"doc","title":"他爬出集中营,70年只为做一身完美衣服","url":"C1IGE96905169QC9"},{"imgsrc":"http://cms-bucket.nosdn.127.net/12ebd9e0f50e41859d1dd6672775608620160922131255.jpeg","subtitle":"","tag":"doc","title":"25个小常识带你了解国际空间站","url":"C18N19GU05169CRR"}]
     * adtype : 0
     * clkNum : 0
     * docid : C1IC62S305169QC9
     * downTimes : 1
     * hasAD : 1
     * hasHead : 1
     * id : C1IC62S305169QC9
     * img : http://cms-bucket.nosdn.127.net/c02df120eb92457a83b6cb890bee09b920160922101517.jpeg
     * imgType : 0
     * imgsrc : http://cms-bucket.nosdn.127.net/c02df120eb92457a83b6cb890bee09b920160922101517.jpeg
     * lmodify : 2016-09-22 17:28:29
     * picCount : 0
     * program : LMA1
     * prompt : 成功为您推荐10条新内容
     * ptime : 2016-09-22 09:22:44
     * recType : 0
     * recprog : base
     * replyCount : 26564
     * source : 新电图
     * template : normal1
     * title : 名媛林黛玉穿的那些中国高定,完胜LV们
     * imgnewextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/86a793f71eac4417a945524381c500fb20160922065217.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/16572a4a01424c3cb9a901abe8e29f8b20160922065217.jpeg"}]
     * upTimes : 140
     */

    private List<T1348647909107Bean> T1348647909107;

    public List<T1348647909107Bean> getT1348647909107() {
        return T1348647909107;
    }

    public void setT1348647909107(List<T1348647909107Bean> T1348647909107) {
        this.T1348647909107 = T1348647909107;
    }

    public static class T1348647909107Bean {
        private int adtype;
        private int clkNum;
        private String docid;
        private int downTimes;
        private int hasAD;
        private int hasHead;
        private String id;
        private String img;
        private int imgType;
        private String imgsrc;
        private String lmodify;
        private int picCount;
        private String program;
        private String prompt;
        private String ptime;
        private int recType;
        private String recprog;
        private int replyCount;
        private String source;
        private String template;
        private String title;
        private int upTimes;
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/92fdb07b0494490bad81caa534cf145b20160922105437.jpeg
         * subtitle :
         * tag : doc
         * title : 谈恋爱不如养狗!这只狗比男友更实用
         * url : C1IH5P0A05169QC9
         */

        private List<AdsBean> ads;
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/86a793f71eac4417a945524381c500fb20160922065217.jpeg
         */

        private List<ImgnewextraBean> imgnewextra;

        public int getAdtype() {
            return adtype;
        }

        public void setAdtype(int adtype) {
            this.adtype = adtype;
        }

        public int getClkNum() {
            return clkNum;
        }

        public void setClkNum(int clkNum) {
            this.clkNum = clkNum;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public int getDownTimes() {
            return downTimes;
        }

        public void setDownTimes(int downTimes) {
            this.downTimes = downTimes;
        }

        public int getHasAD() {
            return hasAD;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
        }

        public int getHasHead() {
            return hasHead;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getImgType() {
            return imgType;
        }

        public void setImgType(int imgType) {
            this.imgType = imgType;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getLmodify() {
            return lmodify;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public int getPicCount() {
            return picCount;
        }

        public void setPicCount(int picCount) {
            this.picCount = picCount;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public int getRecType() {
            return recType;
        }

        public void setRecType(int recType) {
            this.recType = recType;
        }

        public String getRecprog() {
            return recprog;
        }

        public void setRecprog(String recprog) {
            this.recprog = recprog;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUpTimes() {
            return upTimes;
        }

        public void setUpTimes(int upTimes) {
            this.upTimes = upTimes;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public List<ImgnewextraBean> getImgnewextra() {
            return imgnewextra;
        }

        public void setImgnewextra(List<ImgnewextraBean> imgnewextra) {
            this.imgnewextra = imgnewextra;
        }

        public static class AdsBean {
            private String imgsrc;
            private String subtitle;
            private String tag;
            private String title;
            private String url;

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ImgnewextraBean {
            private String imgsrc;

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }
        }
    }
}
