<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="tb" style="padding:3px">
	<span>商品 ID:</span>
	<input id="itemId" style="line-height:22px;border:1px solid #ccc">
	<span>訂單 ID:</span>
	<input id="orderId" style="line-height:22px;border:1px solid #ccc">
	<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">查詢</a>
</div>

<table class="easyui-datagrid" id="orderItemList" title="订单列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/item/list',method:'get',pageSize:30">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">ID</th>
            <th data-options="field:'itemId',width:80,align:'right'">商品ID</th>
            <th data-options="field:'orderId',width:80,align:'center'">订单ID</th>
            <th data-options="field:'num',width:80,align:'right'">商品购买数量</th>
            <th data-options="field:'title',width:240,align:'center'">商品标题</th>
            <th data-options="field:'price',width:100,align:'center',formatter:TAOTAO.formatPrice">商品单价</th>
            <th data-options="field:'totalFee',width:100,align:'center',formatter:TAOTAO.formatPrice">商品总金额</th>
            <th data-options="field:'picPath',width:300,align:'center'">商品图片地址</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var orderItemList = $("#orderItemList");
    	var sels = orderItemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    function doSearch(){
    	$('#orderItemList').datagrid('load',{
    		itemId: $('#itemId').val(),
    		orderId: $('#orderId').val()
    	});
    }
</script>