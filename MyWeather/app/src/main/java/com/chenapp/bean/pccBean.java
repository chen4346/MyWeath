package com.chenapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/27 0027.
 */
public class pccBean {
    /**
     * city : [{"city":"北京","district":[{"district":"北京"},{"district":"平谷"},{"district":"门头沟"},{"district":"密云"},{"district":"房山"},{"district":"大兴"},{"district":"石景山"},{"district":"丰台"},{"district":"延庆"},{"district":"昌平"},{"district":"通州"},{"district":"怀柔"},{"district":"顺义"},{"district":"朝阳"},{"district":"海淀"}]}]
     * province : 北京
     */

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String province;
        /**
         * city : 北京
         * district : [{"district":"北京"},{"district":"平谷"},{"district":"门头沟"},{"district":"密云"},{"district":"房山"},{"district":"大兴"},{"district":"石景山"},{"district":"丰台"},{"district":"延庆"},{"district":"昌平"},{"district":"通州"},{"district":"怀柔"},{"district":"顺义"},{"district":"朝阳"},{"district":"海淀"}]
         */

        private List<CityBean> city;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            private String city;
            /**
             * district : 北京
             */

            private List<DistrictBean> district;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public List<DistrictBean> getDistrict() {
                return district;
            }

            public void setDistrict(List<DistrictBean> district) {
                this.district = district;
            }

            public static class DistrictBean {
                private String district;

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }
            }
        }
    }
}
