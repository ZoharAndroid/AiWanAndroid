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
     * 2 首页banner
     * https://www.wanandroid.com/banner/json
     * <p>
     * 方法：GET
     * 参数：无
     */
    public static String HOME_BANNER_URL = "https://www.wanandroid.com/banner/json";


    /**
     * 3 置顶文章
     * <p>
     * https://www.wanandroid.com/article/top/json
     */
    public static String HOME_TOP_URL = "https://www.wanandroid.com/article/top/json";

    /**
     * 搜索热词
     * <p>
     * https://www.wanandroid.com//hotkey/json
     * <p>
     * 方法：GET
     * 参数：无
     */
    public static String HOT_KEY_URL = "https://www.wanandroid.com//hotkey/json";

    /**
     * 搜索
     * <p>
     * https://www.wanandroid.com/article/query/0/json
     * <p>
     * 方法：POST
     * 参数：
     * 页码：拼接在链接上，从0开始。
     * k ： 搜索关键词
     * <p>
     * 注：该接口支持传入 page_size 控制分页数量，取值为[1-40]，不传则使用默认值，一旦传入了 page_size，
     * 后续该接口分页都需要带上，否则会造成分页读取错误。
     * <p>
     * 注意：支持多个关键词，用空格隔开
     */
    public static final String QUERY_URL(int page) {
        return "https://www.wanandroid.com/article/query/" + page + "/json";
    }
}
