package com.tang.service;

import com.tang.entity.Dept;

import java.sql.SQLException;
import java.util.List;

/**
 * @author weepppp 2022/6/18 6:09
 * @version V1.0
 * @since
 **/
public interface DeptService {
    List<Dept> selectAllDept() throws SQLException;
}
