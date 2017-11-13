<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>login or register</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="resource/css/disk.css"/>
		<link rel="stylesheet" href="resource/css/base.css"/>
		<link rel="stylesheet" href="resource/css/skin.css"/>
		<link rel="stylesheet" href="resource/checkInput/skin.css"/>
		<script type="text/javascript" src="resource/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="resource/checkInput/checkInput.js"></script>
		<script type="text/javascript" src="resource/js/tabs.js"></script>
		<style>
			body{background:white;}
			input{width:200px;padding:2px 3px;font-size:20px;}
			form dt{width:80px;float:left;font-size:18px;text-align:right;}
			form dt,form dd{padding:8px 3px;}
			
			#vip{
				width:1000px;
				border-bottom:#DDD 1px solid;
				padding-bottom:300px;
				height:400px;
				margin:0 auto;
			}
			#example{padding-right:40px;}
			#login_register,#example{float:left;margin-top:60px;}
			#panel{height:300px;width:400px;padding:20px 6px;border:1px solid #44a0fe;border-top:none;}
			#tab{
				background:#44a0fe;width:392px;height:30px;margin:0;
				padding:0 10px;position:relative;border:1px solid #44a0fe;
				padding-top:5px;
			}
			#tab li{
				text-align:center;
				position:relative;
				padding:0 10px;
				list-style:none;
				float:left;
				width:60px;
				height:30px;
				line-height:30px;
				bottom:0px;
				border:1px #44a0fe solid;
				border-bottom:none;
				cursor:pointer;
			}
			.tab_selected{background:white!important;}
		</style>
	</head>
	<body>
		<div id="wrap">
    		<div id="sky">
    			<a id="logo" href="/home/disk" title="SunDive"><img src="resource/img/logo.png"/></a>
			</div>
	        <div id="main">
	        	<div id="vip">
	        		<div id="example">
		        		<img src="resource/img/me.png"/>
		        	</div>
	        		<div id="login_register">
		        		<ul id="tab">
		        			<li tar="login_form" class="tab_selected">login</li>
		        			<li tar="register_form">register</li>
		        		</ul>
		        		<div id="panel">
			        		<form id="login_form" action="login/welcome" method="POST">
				        		<dl>
				        			<dt>Email：</dt><dd><input name="email" type="text"/></dd>
				        			<dt>password：</dt><dd><input name="password" type="password"/></dd>
				        			<dt></dt><dd><button type="submit" id="login">login</button></dd>
				        		</dl>
				        	</form>
				        	<form id="register_form" action="register/welcome" method="POST" style="display:none;">
					        	<dl>
					        		<dt>Email：</dt><dd><input name="email"/></dd>
					        		<dt>username：</dt><dd><input name="username"/></dd>
					        		<dt>password：</dt><dd><input name="password" type="password"/></dd>
					        		<dt>verify：</dt><dd><input name="confirm" type="password"/></dd>
					        		<dt>gender：</dt><dd><select name="gender"><option value="0">female</option><option value="1">male</option></select></dd>
					        		<dt></dt><dd><button id="register">register</button></dd>
					        	</dl>
				        	</form>		  
		        		</div> 	
	        		</div>
	        	</div>
	        </div>
    	</div>
	</body>
	<script type="text/javascript">
		$(function(){
			tab("#tab li","tab_selected");

			var rules = {
					"len4" : [/^\s*[\s\S]{4,50}\s*$/,"this field must have more than 3 digits"],
					"len1" : [/^\s*[\s\S]{1,20}\s*$/,"this field must not be empty"],
				};
			var tips1 = [{
					rightMsg:"",
					focusMsg:"login email",
					name : "email",
					type:"email"
				},{
					focusMsg:"login password",
					name:"password",
					type:"len4",
					errorMsg:"this field must not be empty"
				}];

			var tips2 = [{
					name:"email",
					focusMsg:"login email",
					type:"email",
					ajax:{
						successMsg:"this email is available to use",
						errorMsg:"this email has been used",
						url:"/register/confirm_email"
					}
				},{
					name:"username",
					focusMsg:"your nickname",
					type:"username",
					ajax:{
						successMsg:"this name is available to use",
						errorMsg:"this name has been used",
						url:"/register/confirm_username"
					}
				},{
					name:"password",
					type:"len4",
					focusMsg:"this field must have more than 3 digits",
					errorMsg:"this field must have more than 3 digits",
				},{
					name:"password2",
					type:"eq",
					focusMsg:"type in password again",
					errorMsg:"passwords are not matching",
					eqto:"password"
				}];

			$("#login_form").checkInput({
					items : tips1,
					rules : rules,
					beforeSubmit:function(e,result,form){
						var check = false;
						if(!result) {e.preventDefault();return;}

						$.ajax({
							url:"/login/login_confirm",
							data:form.serialize(),
							type:"post",
							dataType:"text",
							async:false,
							success:function(data){data == 1 ? check = true:check = false;},
							error:function(){check = false;}
						});
						if(!check){
							e.preventDefault();
							alert("this user doesn't exist");
						}
					}
			});
			$("#register_form").checkInput({
				items : tips2,
				beforeSubmit:function(e,result,form){
					if(!result) {e.preventDefault();return;}
				}
			});
		});
	</script>
</html>
