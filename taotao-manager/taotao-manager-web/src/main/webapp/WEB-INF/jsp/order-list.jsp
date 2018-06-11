<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="tb" style="padding:3px">
	<span>用戶 ID:</span>
	<input id="userId" style="line-height:22px;border:1px solid #ccc">
	<span>訂單 ID:</span>
	<input id="orderId" style="line-height:22px;border:1px solid #ccc">
	<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">查詢</a>
</div>

<table class="easyui-datagrid" id="orderList" title="订单列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/list',method:'get',pageSize:30">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'orderId',width:60">订单ID</th>
            <th data-options="field:'payment',width:80,align:'right'">实付金额</th>
            <th data-options="field:'paymentType',width:80,align:'center',formatter:TAOTAO.formatPayStatus">支付类型</th>
            <th data-options="field:'postFee',width:80,align:'right'">邮费</th>
            <th data-options="field:'status',width:70,align:'center',formatter:TAOTAO.formatOrderStatus">状态</th>
            <th data-options="field:'createTime',width:100,align:'center',formatter:TAOTAO.formatDateTime">创建时间</th>
            <th data-options="field:'updateTime',width:100,align:'center',formatter:TAOTAO.formatDateTime">更新时间</th>
            <th data-options="field:'paymentTime',width:100,align:'center',formatter:TAOTAO.formatDateTime">付款时间</th>
            <th data-options="field:'consignTime',width:100,align:'center',formatter:TAOTAO.formatDateTime">发货时间</th>
            <th data-options="field:'endTime',width:100,align:'center',formatter:TAOTAO.formatDateTime">交易完成时间</th>
            <th data-options="field:'closeTime',width:100,align:'center',formatter:TAOTAO.formatDateTime">交易关闭时间</th>
            <th data-options="field:'shippingName',width:100,align:'center'">物流名称</th>
            <th data-options="field:'shippingCode',width:130,align:'center'">物流单号</th>
            <th data-options="field:'userId',width:70,align:'center'">用户id</th>
            <th data-options="field:'buyerMessage',width:100,align:'center'">买家留言</th>
            <th data-options="field:'buyerNick',width:70,align:'center'">买家昵称</th>
            <th data-options="field:'buyerRate',width:100,align:'center',formatter:TAOTAO.formatRate">买家是否评价</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var orderList = $("#orderList");
    	var sels = orderList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    function doSearch(){
    	$('#orderList').datagrid('load',{
    		userId: $('#userId').val(),
    		orderId: $('#orderId').val()
    	});
    }
</script>