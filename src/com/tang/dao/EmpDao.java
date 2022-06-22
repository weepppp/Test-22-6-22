package com.tang.dao;

import com.tang.entity.Emp;

import java.sql.SQLException;
import java.util.List;

/**
 * @author weepppp 2022/6/17 23:10
 * @version V1.0
 * @since
 **/
public interface EmpDao {
    // 分页 模糊
    Integer selectCount(String name,Integer did) throws SQLException;
    List<Emp> selectByPage(String name,Integer did,Integer currentPage,Integer eachPage) throws SQLException;
    // 去修改
    Emp gotoUpdate(Integer eid) throws SQLException;
    // 修改
    Integer update(Emp emp) throws SQLException;
    // 增加
    Integer insert(Emp emp) throws SQLException;
    // 删除
    Integer delete(Integer eid) throws SQLException;
}
