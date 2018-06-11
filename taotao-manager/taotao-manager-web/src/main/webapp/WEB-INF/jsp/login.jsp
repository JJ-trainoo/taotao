<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body style="background-color: #F3F3F3">
	<div class="easyui-dialog" title="管理员登录" id="loginForm" method="post">
		<table cellpadding="5">
			<tr>
				<td>UserName:</td>
				<td><input class="easyui-textbox" type="text" name="username" data-options="required:true" value="admin"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input class="easyui-textbox" type="password" name="password" data-options="required:true" value="admin"></td>
			</tr>
		</table>
		<div style="text-align:center;padding:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
		</div>
	</div>
	<script type="text/javascript">
		function submitForm(){
			var username = $("[name=username]").val();
			var password = $("[name=password]").val();
			$.ajax({
				type: 'POST',
				url: "/login/sign_in",
				data: {
					"username" : username,
					"password" : password
				},
				dataType: 'json',
				success: function (data) {
					if (data.status == 1)
						window.location.href="index";
					else{
						alert(data.msg)
					}
				}
			});
		}
		function clearForm(){
			$('#loginForm').form('clear');
		}
	</script>
</body>
</html>