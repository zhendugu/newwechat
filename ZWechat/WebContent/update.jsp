<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="Update" method="post">
	
	项目名称:<input type="text" name='name' /><br/>
	详细介绍: <input type="text" name='introduce' /><br/>
	 项目LOGOURL地址（把手头的logo文件名填写上去，包括后缀名）：<input type="text" name='logoUrl' /><br/>
	 简短介绍（首页）:<input type="text" name='productUrl' /><br/>
	 官网地址：<input type="text" name='prospectusUrl' /><br/>
	 票数:<input type="text" name='vote' value='0' /><br/>
	 <input type='submit' value='提交' />
	 
	</form>
	<br/>
	<br/>
	<form action="ShuaVote" method="post">
	项目名称:<input type="text" name='name' /><br/>
	加<input type="text" name="shuapiao">票
	 <input type='submit' value='提交' />
	
	</form>
</body>
</html>