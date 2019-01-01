package com.paopao.newservlet.domain;

import java.util.List;

/**
 * 作者：paopao on 2018/12/5 20:42
 * <p>
 * 作用:
 */
public class NewBean {

    /**
     * reason : 成功返回
     * data : [{"author_name":"乐哈科普","category":"头条","date":"2019-01-02 18:30","id":0,"thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20190102/20190102183023_14c1345703567b1ac0a0790c54e932e2_1_mwpm_03200403.jpg","title":"深蹲一天做多少个好","url":"http://mini.eastday.com/mobile/190102183023786.html"},{"author_name":"沙尔克04足球俱乐部","category":"头条","date":"2018-12-04 16:23","id":1,"thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20181204/20181204162306_10603f5177bbc11db32d20b64248cdbf_4_mwpm_03200403.jpg","title":"球迷已经拭目以待，球队也在摩拳擦掌","url":"http://mini.eastday.com/mobile/181204162306575.html"},{"author_name":"风月史纪","category":"头条","date":"2018-12-04 16:05","id":2,"thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20181204/20181204160500_e61d1cacc5d3863c4290de2832a13b77_3_mwpm_03200403.jpg","title":"乾隆为什么不传位给文武双全的永琪，反而让平庸的嘉庆当皇帝？","url":"http://mini.eastday.com/mobile/181204160500827.html"},{"author_name":"带你吃美食","category":"头条","date":"2018-12-04 16:04","id":3,"thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20181204/20181204160455_58fca5b0568649e84922c69df8d9a451_1_mwpm_03200403.jpg","title":"揭秘\u201c牛肉\u201d造假全过程，简直不忍直视，网友：我可能吃的是假的","url":"http://mini.eastday.com/mobile/181204160455906.html"},{"author_name":"董老板说体育","category":"头条","date":"2018-12-04 15:56","id":4,"thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20181204/20181204155644_9ef19ffcb731f45217df8484c399974b_2_mwpm_03200403.jpg","title":"德帅赛后说出输球真相：也许是我们4天三战的原因，他们太累了","url":"http://mini.eastday.com/mobile/181204155644534.html"},{"author_name":"军武观察","category":"头条","date":"2018-12-04 15:54","id":5,"thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20181204/20181204155405_49901c2c3bcc2635742bae63ad024759_2_mwpm_03200403.jpg","title":"美国不愿看到的事还是发生！沙特俄罗斯达成协议，伊朗或逃过一劫","url":"http://mini.eastday.com/mobile/181204155405778.html"},{"author_name":"剁手公主","category":"头条","date":"2018-12-04 15:52","id":6,"thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20181204/20181204155242_a6a16392a96ee6c6ab3575e5c99e7b7d_7_mwpm_03200403.jpg","title":"全自动计时机芯，菲利普马萨限量版腕表","url":"http://mini.eastday.com/mobile/181204155242642.html"},{"author_name":"小兵秦启","category":"头条","date":"2018-12-04 15:48","id":7,"thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20181204/20181204154858_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg","title":"刺激战场：难言教你设置合适的灵敏度，学会之后压枪稳如无后座!","url":"http://mini.eastday.com/mobile/181204154858738.html"},{"author_name":"中新经纬","category":"头条","date":"2018-12-04 15:45","id":8,"thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20181204/20181204154543_f70849bfbbb014d4567d3453f4997310_1_mwpm_03200403.jpg","title":"一线城市房价普遍下跌，上海跌幅最大！","url":"http://mini.eastday.com/mobile/181204154543327.html"},{"author_name":"头排客","category":"头条","date":"2018-12-04 15:42","id":9,"thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20181204/20181204154204_8acc3c91a944e6cdfd8f69f5d88376ef_24_mwpm_03200403.jpg","title":"这才是名媛的正确打开方式！华为总裁之女贝聿铭孙女都来参加的真实公主舞会","url":"http://mini.eastday.com/mobile/181204154204268.html"}]
     */

    private String reason;
    private List<DataBean> data;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * author_name : 乐哈科普
         * category : 头条
         * date : 2019-01-02 18:30
         * id : 0
         * thumbnail_pic_s : http://07imgmini.eastday.com/mobile/20190102/20190102183023_14c1345703567b1ac0a0790c54e932e2_1_mwpm_03200403.jpg
         * title : 深蹲一天做多少个好
         * url : http://mini.eastday.com/mobile/190102183023786.html
         */

        private String author_name;
        private String category;
        private String date;
        private int id;
        private String thumbnail_pic_s;
        private String title;
        private String url;

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
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
}
