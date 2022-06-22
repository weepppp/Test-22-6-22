package com.tang.service.impl;

import com.tang.dao.EmpDao;
import com.tang.dao.impl.EmpDaoImpl;
import com.tang.entity.Emp;
import com.tang.service.EmpService;
import com.tang.utils.JDBCutils;
import com.tang.utils.PageUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * @author weepppp 2022/6/18 0:06
 * @version V1.0
 * @since
 **/
public class EmpServiceImpl implements EmpService {
private EmpDao empDao = new EmpDaoImpl();

    /**
     * 有模糊条件
     */
    @Override
    public PageUtils<Emp> selectByPage(String name, Integer did, String currentPage, String eachPage) throws SQLException {
        PageUtils<Emp> pageUtils = new PageUtils<>();
        Integer currentPage1;
        if (currentPage == null || "".equals(currentPage)) {
                currentPage1 = 1;
        } else {
                currentPage1 = Integer.parseInt(currentPage);
        }
        Integer eachPage1;
        if (eachPage == null || "".equals(eachPage)) {
            eachPage1 = 2;
        } else {
            eachPage1 = Integer.parseInt(eachPage);
        }
        Integer totalPageSum = empDao.selectCount(name, did);
        Integer totalPageSize = totalPageSum % eachPage1 == 0 ? totalPageSum/eachPage1 :totalPageSum/eachPage1+1;
        List<Emp> empList = empDao.selectByPage(name, did, currentPage1, eachPage1);
        pageUtils.setTotalPageSum(totalPageSum);
        pageUtils.setTotalPageSize(totalPageSize);
        pageUtils.setCurrentPage(currentPage1);
        pageUtils.setEachPage(eachPage1);
        pageUtils.setUlist(empList);
        return pageUtils;
    }

    @Override
    public Emp gotoUpdate(Integer eid) throws SQLException {
        return empDao.gotoUpdate(eid);
    }

    @Override
    public Integer update(Emp emp) throws SQLException {
        return empDao.update(emp);
    }

    @Override
    public Integer insert(Emp emp) throws SQLException {
        return empDao.insert(emp);
    }

    /**
     * 要删除图片的删除
     */
    @Override
    public Boolean delete(String eid, HttpServletRequest request) throws SQLException {
        // 如果要用这个方法  删除有图片的记录才会成功
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload/");
        if (eid != null && !"".equals(eid)) {
            String[] split = eid.split(",");
            for(String s : split) {
                Emp emp = empDao.gotoUpdate(Integer.parseInt(eid));
                String photo = emp.getPhoto();
                File file = new File(realPath, photo);
                if (!file.exists()) {
                    throw new RuntimeException("图片不存在");
                } else {
                    boolean flag = file.delete();
                    if (flag) {
                        empDao.delete(Integer.parseInt(s));
                    }
                }
            }
            return  true;
        }
        return false;
    }


    /**
     * 无模糊条件
     */
//    @Override
//    public PageUtils<User> selectAdmin(String currentPage, String eachPage) throws SQLException {
//        Integer currentPage1 = null;
//        Integer eachPage1 = null;
//        if (currentPage == null || "".equals(currentPage)) {
//            currentPage1 = 1;
//        } else {
//            currentPage1 = Integer.parseInt(currentPage);
//        }
//        if (eachPage == null || "".equals(eachPage)) {
//            eachPage1 = 2;
//        } else {
//            eachPage1 = Integer.parseInt(eachPage);
//        }
//        Integer totalPageSum = userDao.selectCount();
//        Integer totalPageSize = totalPageSum % 2 == 0 ? totalPageSum / eachPage1 : totalPageSum / eachPage1 + 1;
//        List<User> userList = userDao.selectPage(currentPage1, eachPage1);
//        PageUtils<User> pageUtils = new PageUtils<>();
//        pageUtils.setUlist(userList);
//        pageUtils.setEachPage(eachPage1);
//        pageUtils.setCurrentPage(currentPage1);
//        pageUtils.setTotalPageSize(totalPageSize);
//        pageUtils.setTotalPageSum(totalPageSum);
//        return pageUtils;
//    }

    /**
     * 不需要删除图片的删除
     */
//    @Override
//    public Integer deleteById(String sid) throws SQLException {
//        String sql = "delete from pro where sid in (" + sid + ")";
//        int i = JDBCutils.qr.update(sql);
//        return i;
//    }
}
