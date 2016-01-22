<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创业服务器年会项目投票</title>

<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript">
	function jumpone(uid) {
/* 		var finalurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb8d027c2d3cae32b&redirect_uri=http%3A%2F%2Fshanyuanjie.cn%2Fwechat%2FCYFWQVote?rurl=OneShow?id=" + uid +"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		window.location.href = finalurl; */
		alert("投票已经结束了，谢谢您对我们对支持！");
	}
</script>

</head>

<body>
	<div class="backg">
		<div class="top">
			<img src="img/创业服务器年会投票界面_01.gif" />
		</div>
		<div class="jianj"></div>
		<div class="unback">
			<div style="height: 5px; background-color: #F3F3F3;"></div>
			<table style="border-color: #FFFFFF;">
				<c:forEach items="${list}" var="candidate" varStatus="status">
					<tr class="tab-1">
						<td onclick="jumpone(${candidate.id })"><img class="logo"
							src="${candidate.logoUrl }" /></td>

						<td onclick="jumpone(${candidate.id })">
							<p class="cy">${candidate.name }</p>
							<p class="jianjie">${candidate.productUrl }</p>
							<p class="xiangqing">>详情</p>
						</td>
						<td class="youce"><p>
								<span class="piaoshu">票数<br />&nbsp;${candidate.vote }
								</span>
							</p></td>
					</tr>
				</c:forEach>


			</table>
			<div style="height: 30px; background-color: #F3F3F3;"></div>
		</div>

	</div>
</body>
</html>