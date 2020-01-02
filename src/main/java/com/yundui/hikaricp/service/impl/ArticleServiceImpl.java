package com.yundui.hikaricp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yundui.hikaricp.domain.Article;
import com.yundui.hikaricp.mapper.ArticleMapper;
import com.yundui.hikaricp.mapper.CommonMapper;
import com.yundui.hikaricp.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author fzx
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CommonMapper commonMapper;

    @Override
    @Transactional(readOnly = true)
    public Page getArticleExtList(Page page, Map<String, Object> map) {
        page.setRecords(articleMapper.getArticleExtList(map));
        page.setTotal(commonMapper.getCount());
        return page;
    }

    @Override
    public List<Article> getAll() {
        return articleMapper.selectList(new LambdaQueryWrapper<Article>().last("limit 3"));
    }

}
