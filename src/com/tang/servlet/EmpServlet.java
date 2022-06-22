package com.tang.servlet;

import com.tang.entity.Dept;
import com.tang.entity.Emp;
import com.tang.service.DeptService;
import com.tang.service.EmpService;
import com.tang.service.impl.DeptServiceImpl;
import com.tang.service.impl.EmpServiceImpl;
import com.tang.utils.MD5Utils;
import com.tang.utils.PageUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author weepppp 2022/6/17 23:25
 * @version V1.0
 * @since
 **/
@WebServlet(name = "empServlet",urlPatterns = "/empServlet")
public class EmpServlet extends BaseServlet {
    private EmpService empService = new EmpServiceImpl();
    private DeptService deptService = new DeptServiceImpl();

    public void select(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String didStr = request.getParameter("did");
        Integer did = (didStr == null || "".equals(didStr) ? null : Integer.parseInt(didStr));
        String name = request.getParameter("name");
        String currentPage = request.getParameter("currentPage");
        String eachPage = request.getParameter("eachPage");
        PageUtils<Emp> pageUtils = empService.selectByPage(name, did, currentPage, eachPage);
        List<Dept> deptList = deptService.selectAllDept();
        request.setAttribute("pageUtils", pageUtils);
        request.setAttribute("deptList", deptList);
        request.setAttribute("did", did);
        request.setAttribute("name", name);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void gotoUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String eid = request.getParameter("eid");
        Emp emp = empService.gotoUpdate(Integer.parseInt(eid));
        List<Dept> deptList = deptService.selectAllDept();
        request.setAttribute("deptList",deptList);
        request.setAttribute("emp", emp);
        // 去修改是转发
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String realPath = request.getServletContext().getRealPath("WEB-INF/upload");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Emp emp = new Emp();
        boolean b = ServletFileUpload.isMultipartContent(request);
        if (b) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);
            Iterator<FileItem> iterator = list.iterator();
            while (iterator.hasNext()) {
                FileItem next = iterator.next();
                if (next.isFormField()) {
                    String fieldName = next.getFieldName();
                    if ("eid".equals(fieldName)) {
                        emp.setEid(Integer.parseInt(next.getString("UTF-8")));
                    } else if ("ename".equals(fieldName)) {
                        emp.setEname(next.getString("UTF-8"));
                    } else if ("eage".equals(fieldName)) {
                        emp.setEage(Integer.parseInt(next.getString("UTF-8")));
                    } else if ("edate".equals(fieldName)) {
                        emp.setEdate(new SimpleDateFormat("yyyy-MM-dd").parse(next.getString("UTF-8")));
                    } else if ("edesc".equals(fieldName)) {
                        emp.setEdesc(next.getString("UTF-8"));
                    } else if ("did".equals(fieldName)) {
                        emp.setDid(Integer.parseInt(next.getString("UTF-8")));
                    }
                } else {
                        String name = next.getName();
                        if (!name.endsWith("jpg") || name.endsWith("png") || name.endsWith("gif")) {
                            request.setAttribute("msg", "图片格式不正确");
                        } else {
                            name = UUID.randomUUID() + name;
                            emp.setPhoto(name);
                            File file1 = new File(file, name);
                            next.write(file1);
                        }
                    }
                }
                Integer integer = empService.update(emp);
                if (integer > 0) {
                    response.sendRedirect("empServlet?mark=select");
                } else {
                    String eid = request.getParameter("eid");
                    response.sendRedirect("empServlet?mark=gotoUpdate&eid" + eid);
                }
            } else{
                request.setAttribute("msg", "请以图片的形式提交");
            }
        }

        public void gotoAdd(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
            List<Dept> deptList = deptService.selectAllDept();
            request.setAttribute("deptList",deptList);
            // 去增加是转发
            request.getRequestDispatcher("add.jsp").forward(request,response);
        }

        public  void add(HttpServletRequest request,HttpServletResponse response) throws Exception {
            String realPath = request.getServletContext().getRealPath("WEB-INF/upload");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            Emp emp = new Emp();
            boolean b = ServletFileUpload.isMultipartContent(request);
            if (b) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> list = upload.parseRequest(request);
                Iterator<FileItem> iterator = list.iterator();
                while (iterator.hasNext()) {
                    FileItem next = iterator.next();
                    if (next.isFormField()) {
                        String fieldName = next.getFieldName();
                         if ("ename".equals(fieldName)) {
                            emp.setEname(next.getString("UTF-8"));
                        } else if ("eage".equals(fieldName)) {
                            emp.setEage(Integer.parseInt(next.getString("UTF-8")));
                        } else if ("edate".equals(fieldName)) {
                            emp.setEdate(new SimpleDateFormat("yyyy-MM-dd").parse(next.getString("UTF-8")));
                        } else if ("edesc".equals(fieldName)) {
                            emp.setEdesc(next.getString("UTF-8"));
                        } else if ("did".equals(fieldName)) {
                            emp.setDid(Integer.parseInt(next.getString("UTF-8")));
                        }
                    } else {
                        String name = next.getName();
                        if (!name.endsWith("jpg") || name.endsWith("png") || name.endsWith("gif")) {
                            request.setAttribute("msg", "图片格式不正确");
                        } else {
                            name = UUID.randomUUID() + name;
                            emp.setPhoto(name);
                            File file1 = new File(file, name);
                            next.write(file1);
                        }
                    }
                }
                Integer integer = empService.insert(emp);
                if (integer > 0) {
                    response.sendRedirect("empServlet?mark=select");
                } else {
                    response.sendRedirect("add.jsp");
                }
            } else{
                request.setAttribute("msg", "请以图片的形式提交");
            }
        }

        public void delete(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
            PrintWriter writer = response.getWriter();
            String eid = request.getParameter("eid");
            if(empService.delete(eid,request)){
                writer.print("true");
            } else {
                writer.print("false");
            }
        }
    }

