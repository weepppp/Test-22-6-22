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

    <input type="hidden" name="eid" value="${emp.eid}">

    <p>名称:<input type="text" name="ename" value="${emp.ename}"></p>

    <p>年龄:<input type="text" name="eage" value="${emp.eage}"></p>

    <%--修改和回显照片--%>
    <img src="/upload/${emp.photo}" alt="">
    <input name="photo" type="file" value="${emp.photo}"/>

    <%-- radio多选框--%>
    <%--  <div class="form-group">--%>
    <%--      <label  class="col-md-2 control-label">使用状态</label>--%>
    <%--      <div class="col-md-5">--%>
    <%--          出售中<input type="radio" name="status" value="1" <c:if test="${parkingUsage.status==1}">checked="checked"</c:if>>--%>
    <%--          使用中<input type="radio" name="status" value="0" <c:if test="${parkingUsage.status==0}">checked="checked"</c:if>>--%>
    <%--      </div>--%>
    <%--  </div>--%>

    <p>生日:<input type="text" name="edate" value="${emp.edate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"></p>

    <p>描述:<textarea name="edesc">${emp.edesc}</textarea></p>

    <%-- 下拉框回显--%>
    <p>
        <select name="did">
            <option value="">-----请选择----</option>
            <c:forEach items="${deptList}" var="dept">
                <option value="${dept.did}" <c:if
                        test="${emp.did==dept.did}">selected</c:if>>${dept.dname}</option>
            </c:forEach>
        </select>
    </p>

    <p><input type="submit" value="提交"></p>
</form>
</body>
</html>
