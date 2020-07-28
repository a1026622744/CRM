
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
//ajax模版
$.ajax({
    url:"",
    data:{},
    type:"",
    dataType:"json",
    success:function (data) {

    }
})

String createTime = DateTimeUtil.getSysTime();
String createBy = ((User) request.getSession().getAttribute("user")).getName();

</body>
</html>
