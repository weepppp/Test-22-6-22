package com.tang.service.impl;

import com.tang.dao.DeptDao;
import com.tang.dao.impl.DeptDaoImpl;
import com.tang.entity.Dept;
import com.tang.service.DeptService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author weepppp 2022/6/18 6:10
 * @version V1.0
 * @since
 **/
public class DeptServiceImpl implements DeptService {
    private DeptDao deptDao = new DeptDaoImpl();
    @Override
    public List<Dept> selectAllDept() throws SQLException {
        return deptDao.selectAllDept();
    }
}
