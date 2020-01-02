package com.yundui.hikaricp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yundui.hikaricp.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 文章
 * @Author: jeecg-boot
 * @Date:   2019/12/26
 * @Version: V1.0
 */
@Data
@TableName("article")
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
@ApiModel(value="article对象", description="文章")
public class Article {
    
	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
	private Integer id;
	/**几条，新版从1开始*/
	@Excel(name = "几条，新版从1开始", width = 15)
    @ApiModelProperty(value = "几条，新版从1开始")
	private Integer seq;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
	private String title;
	/**sn*/
	@Excel(name = "sn", width = 15)
    @ApiModelProperty(value = "sn")
	private String sn;
	/**文章链接*/
	@Excel(name = "文章链接", width = 15)
    @ApiModelProperty(value = "文章链接")
	private String contentUrl;
	/**阅读数*/
	@Excel(name = "阅读数", width = 15)
    @ApiModelProperty(value = "阅读数")
	private Integer readNum;
	/**点赞*/
	@Excel(name = "点赞", width = 15)
    @ApiModelProperty(value = "点赞")
	private Integer likeNum;
	/**是否删除*/
	@Excel(name = "是否删除", width = 15)
    @ApiModelProperty(value = "是否删除")
	@Dict(dicCode = "biz_plugin_doc_deleted")
	private Integer isDeleted;
	/**评论数*/
	@Excel(name = "评论数", width = 15)
    @ApiModelProperty(value = "评论数")
	private Integer commentNum;
	/**封面链接*/
	@Excel(name = "封面链接", width = 15)
    @ApiModelProperty(value = "封面链接")
	private String coverUrl;
	/**本地方面链接*/
	@Excel(name = "本地方面链接", width = 15)
    @ApiModelProperty(value = "本地方面链接")
	private String localCoverUrl;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy/MM/dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**发文时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy/MM/dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    @ApiModelProperty(value = "发文时间")
	private java.util.Date publishTime;
	/**送达人数*/
	@Excel(name = "送达人数", width = 15)
    @ApiModelProperty(value = "送达人数")
	private Integer sendUser;
	/**原创信息*/
	@Excel(name = "原创信息", width = 15)
    @ApiModelProperty(value = "原创信息")
	@Dict(dicCode = "biz_plugin_doc_copyright")
	private Integer copyrightStatus;

	private String biz;
}
