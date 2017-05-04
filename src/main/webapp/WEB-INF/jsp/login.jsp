<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2017/4/28
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"	import="java.util.* " %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
</head>
<body>
<c:if test="${error!=null }">
    <div class="alert alert-error">
        <a class="close" data-dismiss="alert">×</a>
        密码错误
    </div>
</c:if>

<form action="/backend/login" method="post" align="center">
    用户名：<input type="text"  name="account"/><br>
    密码：   <input type="password"   name="password"/><br>
    <input type="submit"  value="登录"/>
</form>
<br>
<br>
</body>
</html>