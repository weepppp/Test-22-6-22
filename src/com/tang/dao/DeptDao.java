package com.tang.dao;

import com.tang.entity.Dept;

import java.sql.SQLException;
import java.util.List;

/**
 * @author weepppp 2022/6/18 6:06
 * @version V1.0
 * @since
 **/
public interface DeptDao {
    List<Dept> selectAllDept() throws SQLException;
}
