package com.yundui.hikaricp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yundui.hikaricp.annotation.AutoLog;
import com.yundui.hikaricp.domain.Article;
import com.yundui.hikaricp.domain.Result;
import com.yundui.hikaricp.domain.ext.ArticleExt;
import com.yundui.hikaricp.service.IArticleService;
import com.yundui.hikaricp.util.YdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 文章
 * @Author: jeecg-boot
 * @Date: 2019-12-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "文章")
@RestController
@RequestMapping("/biz/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    /**
     * 分页列表查询
     *
     * @param articleExt
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "文章-分页列表查询", operateType = 99)
    @ApiOperation(value = "文章-分页列表查询", notes = "文章-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(ArticleExt articleExt,
                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                HttpServletRequest req) {
        Result result = new Result();
        IPage<ArticleExt> pageList = articleService.getArticleExtList(new Page(pageNo, pageSize), YdUtil.sqlMap(req.getParameterMap()));
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param article
     * @return
     */
    @AutoLog(value = "文章-添加")
    @ApiOperation(value = "文章-添加", notes = "文章-添加")
    @PostMapping(value = "/add")
    public Result<Article> add(@RequestBody Article article) {
        Result<Article> result = new Result<Article>();
        try {
            articleService.save(article);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param article
     * @return
     */
    @AutoLog(value = "文章-编辑")
    @ApiOperation(value = "文章-编辑", notes = "文章-编辑")
    @PutMapping(value = "/edit")
    public Result<Article> edit(@RequestBody Article article) {
        Result<Article> result = new Result<Article>();
        Article articleEntity = articleService.getById(article.getId());
        if (articleEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = articleService.updateById(article);
            result.success("修改成功!");
        }

        return result;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "文章-通过id删除")
    @ApiOperation(value = "文章-通过id删除", notes = "文章-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<Article> delete(@RequestParam(name = "id", required = true) String id) {
        Result<Article> result = new Result<Article>();
        Article article = articleService.getById(id);
        if (article == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = articleService.removeById(id);
            if (ok) {
                result.success("删除成功!");
            }
        }

        return result;
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "文章-通过id查询")
    @ApiOperation(value = "文章-通过id查询", notes = "文章-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<Article> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<Article> result = new Result<Article>();
        Article article = articleService.getById(id);
        if (article == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(article);
            result.setSuccess(true);
        }
        return result;
    }


    @GetMapping("/testConnectionPool")
    public Result testConnectionPool() {

        boolean flag = false;
        while (true) {
            if (flag) {
                Article article = articleService.getOne(new LambdaQueryWrapper<Article>().eq(Article::getId, 6532934).last("limit 1"));
                System.out.println(article);
                flag = false;
            } else {
                List<Article> list = articleService.getAll();
                list.stream().forEach(System.out::println);
                flag = true;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
