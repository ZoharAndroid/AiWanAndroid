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
    public static String QUERY_URL(int page) {
        return "https://www.wanandroid.com/article/query/" + page + "/json";
    }

    /**
     * 广场列表数据
     * <p>
     * https://wanandroid.com/user_article/list/页码/json
     * GET请求
     * 页码拼接在url上从0开始
     */
    public static String SQUARE_URL(int page) {
        return "https://wanandroid.com/user_article/list/" + page + "/json";
    }

    /**
     * 获取公众号列表
     * <p>
     * https://wanandroid.com/wxarticle/chapters/json
     * 方法： GET
     */
    public static String GET_WECHAT_URL = "https://wanandroid.com/wxarticle/chapters/json";

    /**
     * 查看某个公众号历史数据
     * <p>
     * https://wanandroid.com/wxarticle/list/408/1/json
     * 方法：GET
     * 参数：
     * 公众号 ID：拼接在 url 中，eg:405
     * 公众号页码：拼接在url 中，eg:1
     * <p>
     * 该接口支持传入 page_size 控制分页数量，取值为[1-40]，不传则使用默认值，一旦传入了 page_size，后续该接口分页都需要带上，否则会造成分页读取错误。
     */
    public static String QUERY_WECHAT_ARTICLE_URL(int userId, int currentPage) {
        return "https://wanandroid.com/wxarticle/list/" + userId + "/" + currentPage + "/json";
    }

    /**
     * 体系数据
     * https://www.wanandroid.com/tree/json
     *
     * 方法：GET
     * 参数：无
     */
    public static String STRUCTURE_URL = "https://www.wanandroid.com/tree/json";
}
