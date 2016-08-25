package com.chenapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class HeWeatherBean {
    /**
     * aqi : {"city":{"aqi":"135","co":"1","no2":"21","o3":"191","pm10":"48","pm25":"31","qlty":"轻度污染","so2":"8"}}
     * basic : {"city":"杭州","cnty":"中国","id":"CN101210101","lat":"30.319000","lon":"120.165000","update":{"loc":"2016-08-24 19:52","utc":"2016-08-24 11:52"}}
     * daily_forecast : [{"astro":{"sr":"05:31","ss":"18:31"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-24","hum":"53","pcpn":"0.0","pop":"0","pres":"1004","tmp":{"max":"35","min":"26"},"vis":"10","wind":{"deg":"58","dir":"东北风","sc":"微风","spd":"0"}},{"astro":{"sr":"05:32","ss":"18:30"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-25","hum":"53","pcpn":"0.0","pop":"0","pres":"1002","tmp":{"max":"35","min":"26"},"vis":"10","wind":{"deg":"32","dir":"东北风","sc":"微风","spd":"0"}},{"astro":{"sr":"05:32","ss":"18:29"},"cond":{"code_d":"300","code_n":"300","txt_d":"阵雨","txt_n":"阵雨"},"date":"2016-08-26","hum":"66","pcpn":"0.4","pop":"80","pres":"1005","tmp":{"max":"33","min":"26"},"vis":"9","wind":{"deg":"356","dir":"北风","sc":"3-4","spd":"14"}},{"astro":{"sr":"05:33","ss":"18:28"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2016-08-27","hum":"53","pcpn":"0.0","pop":"0","pres":"1009","tmp":{"max":"30","min":"23"},"vis":"10","wind":{"deg":"330","dir":"北风","sc":"4-5","spd":"17"}},{"astro":{"sr":"05:33","ss":"18:26"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-28","hum":"55","pcpn":"0.4","pop":"5","pres":"1008","tmp":{"max":"31","min":"22"},"vis":"9","wind":{"deg":"342","dir":"北风","sc":"4-5","spd":"21"}},{"astro":{"sr":"05:34","ss":"18:25"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-29","hum":"44","pcpn":"0.0","pop":"0","pres":"1008","tmp":{"max":"32","min":"22"},"vis":"10","wind":{"deg":"351","dir":"北风","sc":"微风","spd":"2"}},{"astro":{"sr":"05:35","ss":"18:24"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-30","hum":"41","pcpn":"0.0","pop":"0","pres":"1006","tmp":{"max":"33","min":"23"},"vis":"10","wind":{"deg":"328","dir":"北风","sc":"微风","spd":"10"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"35","hum":"57","pcpn":"0","pres":"1003","tmp":"31","vis":"10","wind":{"deg":"100","dir":"东风","sc":"5-6","spd":"30"}}
     * status : ok
     * suggestion : {"comf":{"brf":"很不舒适","txt":"白天天气晴好，但烈日炎炎会使您会感到很热，很不舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},"sport":{"brf":"较适宜","txt":"天气较好，户外运动请注意防晒，推荐您在室内进行低强度运动。"},"trav":{"brf":"一般","txt":"天气较好，同时又有微风伴您一路同行，但是比较热，外出旅游请注意防晒，并注意防暑降温。"},"uv":{"brf":"强","txt":"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"}}
     */

    private List<HeWeatherdataserviceBean> HeWeatherdataservice;

    public List<HeWeatherdataserviceBean> getHeWeatherdataservice() {
        return HeWeatherdataservice;
    }

    public void setHeWeatherdataservice(List<HeWeatherdataserviceBean> HeWeatherdataservice) {
        this.HeWeatherdataservice = HeWeatherdataservice;
    }

    public static class HeWeatherdataserviceBean {
        /**
         * city : {"aqi":"135","co":"1","no2":"21","o3":"191","pm10":"48","pm25":"31","qlty":"轻度污染","so2":"8"}
         */

        private AqiBean aqi;
        /**
         * city : 杭州
         * cnty : 中国
         * id : CN101210101
         * lat : 30.319000
         * lon : 120.165000
         * update : {"loc":"2016-08-24 19:52","utc":"2016-08-24 11:52"}
         */

        private BasicBean basic;
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 35
         * hum : 57
         * pcpn : 0
         * pres : 1003
         * tmp : 31
         * vis : 10
         * wind : {"deg":"100","dir":"东风","sc":"5-6","spd":"30"}
         */

        private NowBean now;
        private String status;
        /**
         * comf : {"brf":"很不舒适","txt":"白天天气晴好，但烈日炎炎会使您会感到很热，很不舒适。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"}
         * sport : {"brf":"较适宜","txt":"天气较好，户外运动请注意防晒，推荐您在室内进行低强度运动。"}
         * trav : {"brf":"一般","txt":"天气较好，同时又有微风伴您一路同行，但是比较热，外出旅游请注意防晒，并注意防暑降温。"}
         * uv : {"brf":"强","txt":"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"}
         */

        private SuggestionBean suggestion;
        /**
         * astro : {"sr":"05:31","ss":"18:31"}
         * cond : {"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"}
         * date : 2016-08-24
         * hum : 53
         * pcpn : 0.0
         * pop : 0
         * pres : 1004
         * tmp : {"max":"35","min":"26"}
         * vis : 10
         * wind : {"deg":"58","dir":"东北风","sc":"微风","spd":"0"}
         */

        private List<DailyForecastBean> daily_forecast;

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class AqiBean {
            /**
             * aqi : 135
             * co : 1
             * no2 : 21
             * o3 : 191
             * pm10 : 48
             * pm25 : 31
             * qlty : 轻度污染
             * so2 : 8
             */

            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean {
                private String aqi;
                private String co;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String qlty;
                private String so2;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class BasicBean {
            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            /**
             * loc : 2016-08-24 19:52
             * utc : 2016-08-24 11:52
             */

            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowBean {
            /**
             * code : 101
             * txt : 多云
             */

            private CondBean cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            /**
             * deg : 100
             * dir : 东风
             * sc : 5-6
             * spd : 30
             */

            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionBean {
            /**
             * brf : 很不舒适
             * txt : 白天天气晴好，但烈日炎炎会使您会感到很热，很不舒适。
             */

            private ComfBean comf;
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            private CwBean cw;
            /**
             * brf : 炎热
             * txt : 天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。
             */

            private DrsgBean drsg;
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
             */

            private FluBean flu;
            /**
             * brf : 较适宜
             * txt : 天气较好，户外运动请注意防晒，推荐您在室内进行低强度运动。
             */

            private SportBean sport;
            /**
             * brf : 一般
             * txt : 天气较好，同时又有微风伴您一路同行，但是比较热，外出旅游请注意防晒，并注意防暑降温。
             */

            private TravBean trav;
            /**
             * brf : 强
             * txt : 紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。
             */

            private UvBean uv;

            public ComfBean getComf() {
                return comf;
            }

            public void setComf(ComfBean comf) {
                this.comf = comf;
            }

            public CwBean getCw() {
                return cw;
            }

            public void setCw(CwBean cw) {
                this.cw = cw;
            }

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public static class ComfBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * sr : 05:31
             * ss : 18:31
             */

            private AstroBean astro;
            /**
             * code_d : 100
             * code_n : 101
             * txt_d : 晴
             * txt_n : 多云
             */

            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 35
             * min : 26
             */

            private TmpBean tmp;
            private String vis;
            /**
             * deg : 58
             * dir : 东北风
             * sc : 微风
             * spd : 0
             */

            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                private String sr;
                private String ss;

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
