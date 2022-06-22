package com.tang.dao.impl;

import com.tang.dao.EmpDao;
import com.tang.entity.Emp;
import com.tang.utils.JDBCutils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weepppp 2022/6/17 23:16
 * @version V1.0
 * @since
 **/
public class EmpDaoImpl implements EmpDao {

    /**
     * 有模糊条件
     */
    @Override
    public Integer selectCount(String name, Integer did) throws SQLException {
        Long sum ;
        StringBuffer sb = new StringBuffer("select count(*) from emp where 1 = 1");
        List<Object> list = new ArrayList<>();
        if (name != null && !"".equals(name)) {
            sb.append(" and ename like ? ");
            list.add("%" + name + "%");
        }
        if (did != null && !"".equals(did)) {
            sb.append(" and did = ? ");
            list.add(did);
        }
        if (list == null || list.size() <= 0) {
            sum = (Long)JDBCutils.qr.query(sb.toString(), new ScalarHandler());
        } else {
            sum = (Long)JDBCutils.qr.query(sb.toString(),new ScalarHandler(),list.toArray());
        }
        return sum == null ? 0:Integer.parseInt(sum +"") ;
    }

    @Override
    public List<Emp> selectByPage(String name, Integer did, Integer currentPage, Integer eachPage) throws SQLException {
        StringBuffer sb = new StringBuffer("select emp.*,dept.dname dname from emp,dept where emp.did = dept.did");
        List<Object> list = new ArrayList<>();
        if (name != null && !"".equals(name)) {
            sb.append(" and emp.ename like ? ");
            list.add("%" + name + "%");
        }
        if (did != null && !"".equals(did)) {
            sb.append(" and dept.did = ? ");
            list.add(did);
        }
        sb.append(" limit ?,?");
        list.add((currentPage-1)*eachPage);
        list.add(eachPage);
        List<Emp> list1 = JDBCutils.qr.query(sb.toString(),new BeanListHandler<Emp>(Emp.class),list.toArray());
        return list1;
    }

    @Override
    public Emp gotoUpdate(Integer eid) throws SQLException {
        String sql = "select * from emp where eid = ?";
        Emp emp = JDBCutils.qr.query(sql, new BeanHandler<Emp>(Emp.class), eid);
        return emp;
    }

    @Override
    public Integer update(Emp emp) throws SQLException {
        String sql = "update emp set photo = ?,ename = ?,eage = ?,edesc = ?,edate = ?,did = ? where eid = ?";
        Object[] objects = {emp.getPhoto(),emp.getEname(),emp.getEage(),emp.getEdesc(),emp.getEdate()
        ,emp.getDid(),emp.getEid()};
        int i = JDBCutils.qr.update(sql, objects);
        return i;
    }

    @Override
    public Integer insert(Emp emp) throws SQLException {
        String sql = "insert into emp(photo,ename,eage,edesc,edate,did) values(?,?,?,?,?,?)";
        Object[] objects = {emp.getPhoto(),emp.getEname(),emp.getEage(),emp.getEdesc(),emp.getEdate()
                ,emp.getDid()};
        int i = JDBCutils.qr.update(sql, objects);
        return i;
    }

    @Override
    public Integer delete(Integer eid) throws SQLException {
        String sql = "delete from emp where eid = ?";
        int i = JDBCutils.qr.update(sql, eid);
        return i;
    }

/**
 * 无模糊条件
 */
//    @Override
//    public List<User> selectPage(Integer currentPage, Integer eachPage) throws SQLException {
//        String sql = "select * from user limit ?,?";
//        Object[] objects = {(currentPage - 1)*eachPage,eachPage};
//        List<User> userList = JDBCutils.qr.query(sql, new BeanListHandler<User>(User.class), objects);
//        return userList;
//    }
//
//    @Override
//    public Integer selectCount() throws SQLException {
//        String sql = "select count(1) from user";
//        Long i = (Long) JDBCutils.qr.query(sql,new ScalarHandler());
//        return i == null ? 0:Integer.parseInt(i+"");
//    }
}
