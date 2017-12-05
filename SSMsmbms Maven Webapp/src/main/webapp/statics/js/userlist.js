	//validateUser数据验证
    function validateUser(){
       var userCode=$("#userCode").val();
       if(userCode==""){
           $("#userIdMsg").value('*请输入用户编码');
           return false;
       }
       return true;
    }
       
    
    
    
    	//table表格
       function init() {
           $('#test').datagrid({
               title:'用户管理',     //布局的标题名称
               iconCls:'icon-save',  //图标样式
               width:'100%', //宽度
               height:450, //高度
               nowrap: true,
               striped: true,
               url:'user/findUsersList?userName='+$("#userName").val(),
               sortName: 'code',
               sortOrder: 'desc',
               idField:'id',  //指示哪个字段是标识字段。
               pageList: [2, 5, 15, 20],
               frozenColumns:[[
	                   {field:'ck',checkbox:true},
	                   {field: 'id', width: 50, hidden: true },
	                   {title:'用户编码',field:'userCode',width:80,sortable:true}
                   ]],
               columns:[[ //Column是一个数组对象，它的每个元素也是一个数组。元素数组的元素是一个配置对象，它定义了每个列的字段。
                   {field:'userName',title:'用户名称',width:200}, //title 标题文本
                   {field:'gender',title:'性别',width:200,  //field：列的字段名
                       formatter:function(value){
                           if(value==1){
                               return "男";
                           }else{
                               return "女";
                           }
                       }
                   },
                   {field:'birthday',title:'年龄',width:200,
                        formatter:function(value){
                          return jsGetAge(value);
                       }
                   },
                   {field:'phone',title:'电话',width:200},
                   
                   {field:'userType',title:'用户类型',width:200,
                       formatter:function(value){
                           if(value==1){
                               return "管理员";
                           }else if(value==2){
                               return "员工";
                           }else{
                               return "经理";
                           }
                       }
                   }
               ]],
               
               pagination:true, //rows: 每页显示的数据量 page:第几页  显示分页
               rownumbers:true, //带有行号的列
               singleSelect:true,
               
              toolbar:[{
                   text:'添加',
                   iconCls:'icon-add',
                   handler:function(){
                       //open1();
                       openUserAddDialog();
                   }
               },{
            	   id: 'btnDelete',
                   text:'批量删除',
                   iconCls:'icon-remove',
                   disabled:false,
                   handler:function(){
                       doDelete();
                   }
               },{
                   text:'修改',
                   iconCls:'icon-edit',
                   disabled:false,
                   handler:function(){
                       openUserModifyDialog();
                   }
               }]
           });
       }
       
       
       
       
       
       
       $(function(){
           init();
           //模糊查询
           $("#btnSelect").click(function () {
        	   alert("123");
               init();
               
           });

           
         $('#dd').dialog({
               title:'添加用户',
               collapsible:false,
               resizable:true,
               //小弹层的OK
               buttons:[{
                   text:'保存',
                   iconCls:'icon-ok',
                   handler:function(){
                       saveUser();
                   }
               }, {
                   text:'取消',
                   handler:function(){
                       $('#dd').dialog('close');
                   }
               }],

         });
           
           $('#dd').dialog('close');
       }) ;
       
       
       
       
       
       
       
       
       //把日期转换成年龄
       function jsGetAge(strBirthday) {
           var returnAge;
           var strBirthdayArr=strBirthday.split("-");
           var birthYear = strBirthdayArr[0];
           var birthMonth = strBirthdayArr[1];
           var birthDay = strBirthdayArr[2];
           var d = new Date();
           var nowYear = d.getFullYear();
           var nowMonth = d.getMonth() + 1;
           var nowDay = d.getDate();
           if(nowYear == birthYear){
               returnAge = 0;//同年 则为0岁
           }else{
               var ageDiff = nowYear - birthYear ; //年之差
               if(ageDiff > 0){
                   if(nowMonth == birthMonth){
                       var dayDiff = nowDay - birthDay;//日之差
                       if(dayDiff < 0){
                           returnAge = ageDiff - 1;
                       }else{
                           returnAge = ageDiff ;
                       }
                   }else{
                       var monthDiff = nowMonth - birthMonth;//月之差
                       if(monthDiff < 0){
                           returnAge = ageDiff - 1;
                       } else{
                           returnAge = ageDiff ;
                       }
                   } }else{
                   returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
               }
           }
           return returnAge;//返回周岁年龄
       }
       
       
       
       
       
       
       //删除用户信息
       function  doDelete() {
    	   //选中要删除的行
           var selectRows = $("#test").datagrid("getSelections");
           
           if (selectRows!=0) {
               //提示用户是否真的删除数据
               $.messager.confirm("确认消息", "您确定要删除此信息么？", function (r) {
                   if (r) {
                       var strIds = "";
                       //将要删除的ids拼接成字符串  但是最后多了一个逗号
                       for (var i = 0; i < selectRows.length; i++) {
                           strIds += selectRows[i].id + ",";
                       }
                       //截取最后一个逗号之前的字符串
                       strIds = strIds.substr(0, strIds.length - 1);
                       //alert(strIds);
                       $.post("user/delUser", {strId: strIds}, function (data) {
                           if (data == true) {
                               $("#test").datagrid("reload");
                               $("#test").datagrid("clearSelections");
                           } else {
                               $.messager.alert("删除失败", data);
                           }
                       })
                   }
               })
           }else{
               $.messager.alert("系统提示","请选择一条要编辑的数据！");
               return;
           }
       }
       
       
       
       
       
       
       
       //修改用户信息
       var url;
       function openUserModifyDialog(){
    	   
    	   //获取要修改的行数
           var selectedRows = $("#test").datagrid("getSelections");
           if (selectedRows.length!=1){
               $.messager.alert("系统提示","请选择一条要编辑的数据！");
               return;
           }
           
           var row = selectedRows[0];
           $("#dd").dialog("open").dialog("setTitle","编辑用户信息");
           
           $("#form2").form("load",row);
           
           alert(row.id);
           url="user/addUser?id="+row.id;
       }
      
      
       
       
       
       
       
       //新增用户信息
       function openUserAddDialog() {
           //$("#dd").dialog("open").dialog("setTitle", "添加用户信息");
           $('#dd').dialog('open');//open显示新增页面小弹层
           url = "user/addUser";
       }
       
       
       
       
       
       
       
       //保存用户信息
       function saveUser() {
          alert(url);
           $.ajax({
               url: url,
               data:$("#form2").serialize(),
               type:"post",
               success: function (result) {
                   if (result == true) {
                       $.messager.alert("系统提示", "保存成功！");
                       resetValue();
                       //取消复选框的对号
                       $("#test").datagrid("clearSelections");
                       $("#test").datagrid("reload");
                       $("#dd").dialog("close");
                       
                   } else {
                       $.messager.alert("系统提示", "保存失败！");
                   }
               }
           })
           
       }
       
       
       
       
       
       
       
       //修改后清空输入框内的内容
       function resetValue() {
           $("#userCode").val("");
           $("#userName").val("");
           $("#userPassword").val("");
           $("#userRemi").val("");
           $("#gender").val("");
           $("#birthday").val("");
           $("#phone").val("");
           $("#address").val("");
           $("#roleName").val("");
       }