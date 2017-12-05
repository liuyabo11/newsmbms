//初始化方法
function init(){
	$('#test').datagrid({
		title:'供应商模块',
		iconCls:'icon-save',
		width:'100%',
		height:400,
		//nowrap: false,
		//striped: true,
		collapsible:true,//是否可以折叠
		url:'datagrid_data.json',//ajax请求url
		//sortName: 'code',
		//sortOrder: 'desc',
		//remoteSort: false,
		//idField:'code',
		frozenColumns:[[//是否冻结
            {field:'ck',checkbox:true},
            {title:'用户编码',field:'userCode',width:80,sortable:true}
		]],
		columns:[[
	        {title:'用户名称',field:'userName'},
	        {title:'出生日期',field:'birthday'}
		]],
		
		//pagination:true,
		//rownumbers:true,
		pageNumber:1,
		pageSize:2,
		pageList:[2,5,8,10],
		pagination:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('add')
			}
		},{
			id:'btncut',
			text:'删除',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			id:'btnsave',
			text:'修改',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	
	var p = $('#test').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
}

//模糊查询
$(function(){
	init();
	
})
		