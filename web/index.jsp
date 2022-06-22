<%--
  Created by IntelliJ IDEA.
  User: zoeak
  Date: 2022/6/17
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<body>

<div style="margin-left: 200px">

<%--    下面容易忘记!!!!!--%>
    <form action="${pageContext.request.contextPath}/empServlet?mark=select" method="post">

        <%-- 下拉框回显--%>
        部门名称:<select name="did">
        <option value="">-----请选择----</option>
        <c:forEach items="${deptList}" var="dept">
            <option value="${dept.did}" <c:if test="${dept.did == did}">selected</c:if>>${dept.dname}</option>
        </c:forEach>
    </select>

        员工名称:<input type="text" name="name" value="${name}"> <input type="submit" value="查询">
    </form>
</div>


<table width="80%" align="center" cellspacing="0px" cellpadding="0px"
       border="1px">
    <tr>
        <th><input type="checkbox" id="checkall"  onchange="checkall()"></th>
        <th>编号</th>
        <th>照片</th>
        <th>部门名称</th>
        <th>名称</th>
        <th>年龄</th>
        <th>生日</th>
        <th>描述</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${pageUtils.ulist}" var="emp">
        <tr>
            <th><input type="checkbox" name="check" value="${emp.eid}"></th>
            <th>${emp.eid}</th>
            <th style="text-align: center;">
                <img src="/upload/${emp.photo}" width="80" height="50" alt="" />
            </th>
            <th>${emp.dname}</th>
            <th>${emp.ename}</th>
<%--      状态1，0显示--%>
<%--            <td>--%>
<%--                <c:if test="${parkingUsage.status==1}">出售中</c:if>--%>
<%--                <c:if test="${parkingUsage.status==0}">使用中</c:if>--%>
<%--            </td>--%>
            <th>${emp.eage}</th>
            <th>${emp.edate}</th>
            <th>${emp.edesc}</th>
            <th>
                <a href="${pageContext.request.contextPath}/empServlet?mark=gotoUpdate&eid=${emp.eid}">编辑</a>
                <a href="javaScript:void(0)" onclick="delectId('${emp.eid}')">删除</a>
            </th>
        </tr>
    </c:forEach>


</table>
<div style="margin-left: 200px">
    <a href="${pageContext.request.contextPath}/empServlet?mark=gotoAdd">新增</a>
    <h4 onclick="delAll()">批量删除</h4>
    <a href="${pageContext.request.contextPath}/empServlet?mark=select&currentPage=1&did=${did}&name=${name}">首页</a>
    <c:if test="${pageUtils.currentPage > 1}">
    <a href="${pageContext.request.contextPath}/empServlet?mark=select&currentPage=${pageUtils.currentPage - 1}&did=${did}&name=${name}">上一页</a>
    </c:if>
    <c:if test="${pageUtils.currentPage < pageUtils.totalPageSize}">
        <a href="${pageContext.request.contextPath}/empServlet?mark=select&currentPage=${pageUtils.currentPage + 1}&did=${did}&name=${name}">下一页</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/empServlet?mark=select&currentPage=${pageUtils.totalPageSize}&did=${did}&name=${name}">末页</a>
</div>

<script>
    function checkall(){
        //全选框被点击，下面所有的框都会变成checked
        var alls=document.getElementsByName("check");
        var ch=document.getElementById("checkall");
        if(ch.checked){
            for(var i=0;i<alls.length;i++){
                alls[i].checked=true;
            }
        }else{
            for(var i=0;i<alls.length;i++){
                alls[i].checked=false;
            }
        }
    }
    function delAll(){
        var alls=document.getElementsByName("check");
        var ids=new Array();
        for(var i=0;i<alls.length;i++){
            if(alls[i].checked){
                ids.push(alls[i].value);
            }
        }
        if(ids.length>0){
            if(confirm("确认操作?")){
                alert("成功!");
                var eidStr = ids + "";
                delectId(eidStr);
            }
        }else{
            alert("请选中要操作的项");
        }
    }

    function delectId(eid) {
        $.ajax({
            "url":"${pageContext.request.contextPath}/empServlet",
            "type":"post",
            "data":{"eid":eid,"mark":"delete"},
            "dataType":"text",
            "success":callBack,
            "error":function () {
                alert("请求失败！");
            }
        })
    }
    function callBack(data) {
        if (data == "true") {
            alert("删除成功");
            window.location.href="${pageContext.request.contextPath}/empServlet?mark=select"
        } else {
            alert("删除失败");
        }
    }
</script>
</body>
</html>
