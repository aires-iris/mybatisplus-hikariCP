package com.yundui.hikaricp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yundui.hikaricp.domain.Article;

import java.util.List;
import java.util.Map;

public interface IArticleService extends IService<Article> {

    Page getArticleExtList(Page page, Map<String, Object> map);

    List<Article> getAll();
}
