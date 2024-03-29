<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<style type="text/css">
label.error {
	BORDER-RIGHT: #f34956 1px solid;
	PADDING-RIGHT: 10px;
	BORDER-TOP: #f34956 1px solid;
	PADDING-LEFT: 20px;
	FONT-SIZE: 12px;
	BACKGROUND: #ffecec;
	PADDING-BOTTOM: 1px;
	MARGIN: 5px auto 0px;
	BORDER-LEFT: #f34956 1px solid;
	COLOR: #c00;
	PADDING-TOP: 5px;
	BORDER-BOTTOM: #f34956 1px solid;
	TEXT-ALIGN: right
}

input.error {
	border: 1px solid red;
}

textarea.error {
	border: 1px solid red;
}

label.valid {
	border-color: white;
	background: white;
	display: none;
}

input.focus {
	border: 2px solid green;
}

textarea.focus {
	border: 2px solid green;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		jQuery("#regForm").validate({
			rules : {
				"userName" : {
					required : true,
					checkUsername : true
				},
				"password" : {
					required : true,
					rangelength : [ 6, 12 ]
				},

				"confirmpassword" : {
					required : true,
					equalTo : "#password"
				},
				"email" : {
					"required" : true

				}
			},
			messages : {
				"userName" : {
					required : "用户名不能为空",
					checkUsername : "用户名已存在"
				},
				"password" : {
					required : "密码不能为空"
				},
				"confirmpassword" : {
					required : "确认密码不能为空"
				},
				"email" : {
					"required" : "电子邮箱不能为空!"

				}
			},
			errorElement : "label",
			highlight : function(element, errorClass)//针对验证的表单添加高亮显示
			{
				$(element).addClass(errorClass);
			},
			success : function(label) {
				label.html("").addClass("valid");
			}

		})

	})

	//自定义校验规则
	$.validator.addMethod(
	//规则的名称
	"checkUsername",
	//校验的函数
	function(value, element, params) {

		//定义一个标志
		var flag = false;

		//value:输入的内容
		//element:被校验的元素对象
		//params：规则对应的参数值
		//目的：对输入的username进行ajax校验
		$.ajax({
			"async" : false,
			"url" : "${pageContext.request.contextPath}/checkUsername",
			"data" : {
				"username" : value
			},
			"type" : "POST",
			"dataType" : "json",
			"success" : function(data) {
				flag = data.isExit;
			}
		});

		//返回false代表该校验器不通过
		return !flag;
	}

	);
</script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form class="form-horizontal" style="margin-top: 5px;"
					action="${pageContext.request.contextPath }/reg" method="post"
					id="regForm">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username"
								placeholder="请输入用户名" name="username">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password"
								placeholder="请输入密码" name="password">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd"
								placeholder="请输入确认密码" name="confirmpassword">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="inputEmail3"
								placeholder="Email" name="email">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption"
								placeholder="请输入姓名" name="name">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								id="sex1" name="sex" checked="checked" value="男"> 男
							</label> <label class="radio-inline"> <input type="radio"
								id="sex2" name="sex" value="女"> 女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="birthday">
						</div>
					</div>

					<%-- <div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="code">

						</div>
						<div class="col-sm-2">
							<img src="checkCode" alt="" id="codeImg"
								style="width: 80px; height: 30px; border: 1px solid black;" /> <a
								href="javascript:;"
								onclick="document.getElementById('codeImg').src = 'checkCode?'+(new Date()).getTime()">换一张</a>
							<span id="codemsg" style="color: red; font-size: 20px;">${code_message }</span>
						</div>

					</div> --%>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>




