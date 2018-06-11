<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="tb" style="padding:3px">
	<span>訂單 ID:</span>
	<input id="orderId" style="line-height:22px;border:1px solid #ccc">
	<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">查詢</a>
</div>

<table class="easyui-datagrid" id="orderShipList" title="物流列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/ship/list',method:'get',pageSize:30">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'orderId',width:60">订单ID</th>
            <th data-options="field:'receiverName',width:100,align:'right'">收货人全名</th>
            <th data-options="field:'receiverPhone',width:110,align:'right'">固定电话</th>
            <th data-options="field:'receiverMobile',width:110,align:'right'">移动电话</th>
            <th data-options="field:'receiverState',width:80,align:'center'">省份</th>
            <th data-options="field:'receiverCity',width:80,align:'center'">城市</th>
            <th data-options="field:'receiverDistrict',width:100,align:'center'">区/县</th>
            <th data-options="field:'receiverAddress',width:140,align:'center'">收货地址</th>
            <th data-options="field:'receiverZip',width:100,align:'center'">邮政编码</th>
            <th data-options="field:'created',width:140,align:'center',formatter:TAOTAO.formatDateTime">创建时间</th>
            <th data-options="field:'updated',width:140,align:'center',formatter:TAOTAO.formatDateTime">更新时间</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var orderShipList = $("#orderShipList");
    	var sels = orderShipList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    function doSearch(){
    	$('#orderShipList').datagrid('load',{
    		orderId: $('#orderId').val()
    	});
    }
</script>