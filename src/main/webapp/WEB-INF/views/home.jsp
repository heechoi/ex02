<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		$(function(){
			$("#btn1").click(function(){
				$.ajax({
					url:"/ex02/json/map",
					type:"get",
					dataType:"json",
					success:function(result){
						console.log(result);
					}
				})
			})
			
			$("#btn2").click(function(){
				$.ajax({
					url:"/ex02/json/sendList",
					type:"get",
					dataType:"json",
					success:function(result){
						console.log(result);
					}
				})
			})
			
				$("#btn3").click(function(){
				$.ajax({
					url:"/ex02/json/sendMapAuth",
			 	data:{"test":"test",
					  "test2":123}, 
					type:"get",
					dataType:"json",
					success:function(result){
						console.log(result);
					}
				})
			})
		})
	</script>
</head>
<body>
	<button id="btn1">map test</button>
	<button id="btn2">list test</button>
	<button id="btn3">list test</button>
</body>
</html>
