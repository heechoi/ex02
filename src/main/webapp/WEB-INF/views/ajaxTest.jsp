<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>
<style>
.pagination{
	width:100%;
}
.pagination li{
	list-style: none;
	float:left;
	padding:3px;
	border:1px solid blue;
	margin:3px;
}
.pagination a{
	text-decoration: none;
	color:black;
}
.pagination li.active a{
	color:red;
}
</style>
<script>
	$(function(){
		$("#newReplyBtn").click(function(){
			var bno = $("#bno").val();
			var replyer = $("#replyer").val();
			var replytext = $("#replytext").val();
			
			var sendData = {bno:bno,replyer:replyer,replytext:replytext};
			
			$.ajax({
				url:"/ex02/replies/",
				type:"post",
				headers:{"Content-Type":"application/json"},
				dataType:"text",
				data:JSON.stringify(sendData),//json객체를 json string으로 변경해줌
				success:function(result){
					console.log(result);
					alert(result);
				}
			})
		})
		
		$("#modifyReplyBtn").click(function(){
			
			var rno = $("#rno").val();
			var replytext = $("#replytext").val();
			
			var sendData = {replytext:replytext};
			$.ajax({
				url:"/ex02/replies/"+rno,
				type:"put",
				dataType:"text",
				data:JSON.stringify(sendData),
				headers:{"Content-Type":"application/json"},
				success:function(result){
					console.log(result);
				}
			})
		})
		
		$("#removeReplyBtn").click(function(){
			var rno = $("#rno").val();
			var sendData = {rno:rno};
			$.ajax({
				url:"/ex02/replies/"+rno,
				type:"delete",
				dataType:"text",
				headers:{"Content-Type":"application/json"},
				data:JSON.stringify(sendData),
				success:function(result){
					console.log(result);
				}
			})
		})
		var page = 1;
		$("#listReplyBtn").click(function(){
			var bno = $("#bno").val();
			
			$.ajax({
				url:"/ex02/replies/"+bno+"/"+page,
				type:"get",
				dataType:"json",
				success:function(result){
					console.log(result);
					var source = $("#template").html();
					var t_fn = Handlebars.compile(source);
					
					$("#list").html(t_fn(result));
					printPaging(result.pageMaker);
					/* $("#list").empty();
					var list = "";
					
					for(var i=0;i<result.length;i++){
						list+="<li>";
						list+="rno : <span class='rnoNum'>"+result[i].rno+"</span>, 작성자 : "+result[i].replyer+", 작성내용 : "+result[i].replytext;
						list+="<button class='deleteBtn'>삭제</button></li>";
					}
					
					$("#list").append(list); */
				}
			})
		})
		
		
		function printPaging(pageMaker){
			var str = "";
			if(pageMaker.prev){
				str+="<li><a href='"+pageMaker.startPage-1+"'> << </a></li>";
			}
			for(var i=pageMaker.startPage;i<=pageMaker.endPage;i++){
				if(pageMaker.cri.page == i){
					str+="<li class='active'><a href='"+i+"'>"+i+"</a></li>";
				}else{
					str+="<li><a href='"+i+"'>"+i+"</a></li>";
				}
				
			}
			if(pageMaker.next){
				str+="<li><a href='"+pageMaker.startPage+1+"'> >> </a></li>";
			}
			
			$(".pagination").html(str);
		}
		//li a 가 동적으로 추가 되는 경우 $(document).on으로 해도 되지만, 다른경우
		$(".pagination").on("click"," li a",function(e){
			e.preventDefault();
			page = $(this).attr("href");
			var bno = $("#bno").val();
			$.ajax({
				url:"/ex02/replies/"+bno+"/"+page,
				type:"get",
				dataType:"json",
				success:function(result){
					console.log(result);
					var source = $("#template").html();
					var t_fn = Handlebars.compile(source);
					
					$("#list").html(t_fn(result));
					printPaging(result.pageMaker);
					/* $("#list").empty();
					var list = "";
					
					for(var i=0;i<result.length;i++){
						list+="<li>";
						list+="rno : <span class='rnoNum'>"+result[i].rno+"</span>, 작성자 : "+result[i].replyer+", 작성내용 : "+result[i].replytext;
						list+="<button class='deleteBtn'>삭제</button></li>";
					}
					
					$("#list").append(list); */
				}
			})
			
		})
		
		$(document).on("click",".deleteBtn",function(){
			var rno = $(this).parent().find(".rnoNum").text();
			var sendData = {rno:rno};
		
			$.ajax({
				url:"/ex02/replies/"+rno,
				type:"delete",
				dataType:"text",
				headers:{"Content-Type":"application/json"},
				data:JSON.stringify(sendData),
				success:function(result){
					console.log(result);
					
					var bno = $("#bno").val();
					
					$.ajax({
						url:"/ex02/replies/all/"+bno,
						type:"get",
						dataType:"json",
						success:function(result){
							console.log(result);
							$("#list").empty();
							var list = "";
							
							for(var i=0;i<result.length;i++){
								list+="<li>";
								list+="rno : <span class='rnoNum'>"+result[i].rno+"</span>, 작성자 : "+result[i].replyer+", 작성내용 : "+result[i].replytext;
								list+="<button class='deleteBtn'>삭제</button></li>";
							}
							
							$("#list").append(list);
						}
					})
				}
			})
			
		})
	})
</script>

</head>
<body>
	<h1>Ajax Test Page</h1>
	<div>
		<div>
			게시글 번호
			<input type="text" name="bno" id="bno">	
		</div>
		<div>
			댓글 번호
			<input type="text" name="rno" id="rno">
		</div>
		<div>
			Replyer
			<input type="text" name="replyer" id="replyer">
		</div>
		<div>
			Reply Text
			<input type="text" name="replytext" id="replytext">
		</div>
		<button id="newReplyBtn">Add Reply</button>
		<button id="modifyReplyBtn">Modify Reply</button>
		<button id="removeReplyBtn">Remove Reply</button>
		<button id="listReplyBtn">List Reply</button>
	</div>
	<ul id="list">
	</ul>
	<script id="template" type="text/x-handlebars-template">
		{{#list}}
		<li>
			rno : <span class="rnoNum">{{rno}}</span>, 작성자 : {{replyer}},작성 내용:{{replytext}}
			<button class='deleteBtn'>삭제</button>
		</li>
			{{/list}}
	</script>
	
	<ul class="pagination">
		
	</ul>
</body>
</html>