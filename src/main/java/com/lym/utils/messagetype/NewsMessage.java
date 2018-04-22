package com.lym.utils.messagetype;

import com.lym.utils.messagetype.base.BaseMessage;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public class NewsMessage extends BaseMessage {
    private Integer ArticleCount;
    private Article[] Articles;

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Arrays.asList(Articles);
    }

    public void setArticles(Article... articles) {
        if(articles!=null)
        {
            Articles = articles;
            setArticleCount(Articles.length);
        }else{
            setArticleCount(0);
        }
    }
}
