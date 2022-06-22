package com.tang.dao.impl;

import com.tang.dao.DeptDao;
import com.tang.entity.Dept;
import com.tang.utils.JDBCutils;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author weepppp 2022/6/18 6:07
 * @version V1.0
 * @since
 **/
public class DeptDaoImpl implements DeptDao {
    @Override
    public List<Dept> selectAllDept() throws SQLException {
        String sql = "select * from dept";
        List<Dept> deptList = JDBCutils.qr.query(sql, new BeanListHandler<Dept>(Dept.class));
        return deptList;
    }
}
