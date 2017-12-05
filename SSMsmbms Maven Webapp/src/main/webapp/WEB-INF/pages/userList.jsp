<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head lang="en">
   
   <base href="<%=basePath%>">
   <meta charset="UTF-8">
   <title>超市账单管理系统</title>
   <link rel="stylesheet" href="statics/css/public.css"/>
   <link rel="stylesheet" href="statics/css/style.css"/>
   
   <link rel="stylesheet" type="text/css" href="statics/easyui/themes/default/easyui.css">
   <link rel="stylesheet" type="text/css" href="statics/easyui/themes/icon.css">
   <style>
       #dd form div{
           margin-top: 7px;
       }
   </style>
   
</head>
<body>
<!--头部-->
   <header class="publicHeader">
       <h1>超市账单管理系统</h1>
       <div class="publicHeaderR">
           <p><span>下午好！</span><span style="color: #fff21b">${sessionScope.user.userName}</span> , 欢迎你！</p>
           <a href="user/exit.html">退出</a>
       </div>
   </header>
<!--时间-->
   <section class="publicTime">
       <span id="time">2015年1月1日 11:11  星期一</span>
       <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
   </section>
<!--主体内容-->
<section class="publicMian ">
   <div class="left">
       <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
       <nav>
           <ul class="list">
               <li ><a href="bill/list.html">账单管理</a></li>
                <li><a href="provider/list.html">供应商管理</a></li>
                <li><a href="user/list.html">用户管理</a></li>
                <li><a href="password/update.html">密码修改</a></li>
                <li><a href="user/exit.html">退出系统</a></li>
           </ul>
       </nav>
   </div>
   <div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>用户管理页面</span>
       </div>
       
       <!-- 模糊查询 -->
       <div class="search">
           <form id="form1"  method="post">
               <span>用户名：</span>
               <input type="text" name="userName" id="userName"  placeholder="请输入用户名"/>
               <input type="button" id="btnSelect" value="查询"/>
           </form>
       </div>
       
       
       <!--用户-->
       <table id="test">
       
       </table>

   </div>
   
</section>

   <footer class="footer">
   </footer>



<div  id="dd" style="width:700px;padding:30px 60px;font-size: 17px;">
   <form id="form2" onsubmit="return validateUser()">
       <div>
           <label for="userId">用户编码：</label>
           <input type="text"  name="userCode" id="userCode"/>
           <span id="userIdMsg"></span>
       </div>
       <div>
           <label for="username">用户名称：</label>
           <input type="text" class="easyui-textbox" name="userName" id="userName"/>
           <span >*请输入用户名称</span>
       </div>
       <div>
           <label for="userpassword">用户密码：</label>
           <input type="text" class="easyui-textbox" name="userPassword" id="userPassword"/>
           <span>*密码长度必须大于6位小于20位</span>

       </div>
       <div>
           <label for="userRemi">确认密码：</label>
           <input type="text" class="easyui-textbox"  id="userRemi"/>
           <span>*请输入确认密码</span>
       </div>
       <div>
           <label >用户性别：</label>

           <select name="gender" id="gender">
               <option value="1">男</option>
               <option value="0">女</option>
           </select>
           <span></span>
       </div>
       <div>
           <label for="data">出生日期：</label>
           <input type="text" name="birthday" id="birthday"/>
           <span >*</span>
       </div>
       <div>
           <label for="userphone">用户电话：</label>
           <input type="text" class="easyui-textbox" name="phone" id="phone"/>
           <span >*</span>
       </div>
       
       <div>
           <label for="address">用户地址：</label>
           <input type="text" name="address" id="address"/>
       </div>
       
       <div>
           <label id="roleName">用户类别：</label>
           
           <input type="radio" name="userType"  value="0"/>管理员
           <input type="radio" name="userType"  value="1"/>经理
           <input type="radio" name="userType"  value="2"/>普通用户
           
       </div>
   </form>
</div>


<script src="statics/js/jquery.js"></script>
<script src="statics/js/js.js"></script>
<script src="statics/js/time.js"></script>
<script type="text/javascript" src="statics/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="statics/easyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="statics/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="statics/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="statics/js/userlist.js"></script>
</body>

</html>