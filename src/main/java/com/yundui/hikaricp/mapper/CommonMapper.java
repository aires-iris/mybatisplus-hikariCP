package com.yundui.hikaricp.mapper;

public interface CommonMapper {
    /**
     * 通用查询记录行数,性能优于select count(*) from ...
     * 需要配合SQL_CALC_FOUND_ROWS使用
     * 例如
     * SELECT SQL_CALC_FOUND_ROWS * FROM `table` WHERE ......  limit M, N;
     * @return
     */
    Long getCount();
}
