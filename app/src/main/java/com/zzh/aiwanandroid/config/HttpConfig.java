package com.zzh.aiwanandroid.config;

public class HttpConfig {

    // base url
    public static final String BASE_URL = "https://www.wanandroid.com";

    // 个人信息接口
    public static final String PERSON_INFO_URL = "https://wanandroid.com//user/lg/userinfo/json";

    // 问答
    public static final String QUESTION_URL = "https://wanandroid.com/wenda/comments/问答id/json";


    /**
     * 1.首页相关
     * 1.1 首页文章列表
     * https://www.wanandroid.com/article/list/0/json
     * <p>
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    public static String HOME_ARTICLE_URL(int page) {
        return BASE_URL + "/article/list/" + page + "/json";
    }

    /**
     * .2 首页banner
     * https://www.wanandroid.com/banner/json
     *
     * 方法：GET
     * 参数：无
     */

    /**
     * 1.5 置顶文章
     *
     * https://www.wanandroid.com/article/top/json
     */

}
