package com.yundui.hikaricp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yundui.hikaricp.domain.Article;
import com.yundui.hikaricp.domain.ext.ArticleExt;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleExt> getArticleExtList(Map<String, Object> map);
}
