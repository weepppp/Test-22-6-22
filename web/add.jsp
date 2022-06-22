
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js">
    </script>
    <script
            src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js">
    </script>
</head>
<body>

<form action="${pageContext.request.contextPath}/empServlet?mark=add"
      method="post" enctype="multipart/form-data">
    <%-- add没有eid--%>
    <p>名称:<input type="text" name="ename" value=""></p>

    <p>年龄:<input type="text" name="eage" value=""></p>

    照片<input name="photo" type="file" value=""/>

    <p>生日:<input type="text" name="edate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"></p>

    <p>描述:<textarea name="edesc"></textarea></p>

    <%-- 下拉框--%>
    <p>
        <select name="did">
            <option value="">-----请选择----</option>
            <c:forEach items="${deptList}" var="dept">
                <option value="${dept.did}">${dept.dname}</option>
            </c:forEach>
        </select>
    </p>

    <p><input type="submit" value="提交"></p>
</form>
</body>
</html>
