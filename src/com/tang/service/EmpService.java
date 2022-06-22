package com.tang.service;

import com.tang.entity.Emp;
import com.tang.utils.PageUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * @author weepppp 2022/6/18 0:05
 * @version V1.0
 * @since
 **/
public interface EmpService {
    // 分页 模糊
    PageUtils<Emp> selectByPage(String name, Integer did, String currentPage, String eachPage) throws SQLException;
    // 去修改
    Emp gotoUpdate(Integer eid) throws SQLException;
    // 修改
    Integer update(Emp emp) throws SQLException;
    // 增加
    Integer insert(Emp emp) throws SQLException;
    // 删除
    Boolean delete(String eid, HttpServletRequest request) throws SQLException;
}
