<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	label{
		float:left;
		width:100px;
	}
	table{
		margin-top:10px;
		width:700px;
		border-collapse: collapse;
	}
	table td{
		padding:5px;
	}
</style>
<script>
	function listAll(){
		$.ajax({
			url:"/ex02/member/",
			type:"get",
			dataType:"json",
			success:function(result){
				console.log(result);
				var source = $("#template").html();
				var t_fn = Handlebars.compile(source);
				$("#t").html(t_fn(result));
			}
		})
	}
	$(function(){
		$("#listBtn").click(function(){
			listAll();
		})
		
		$("#addBtn").click(function(){
			var userid = $("#userid").val();
			var username =$("#username").val();
			var userpw = $("#userpw").val();
			var email = $("#email").val();
			
			var sendData = {userid:userid,username:username,userpw:userpw,email:email};
			
			$.ajax({
				url:"/ex02/member/",
				type:"post",
				dataType:"text",
				data:JSON.stringify(sendData),
				headers:{"Content-Type":"application/json"},
				success:function(result){
					console.log(result);
					listAll();
				}
			})
		})
		
		$(document).on("click",".removeBtn",function(){
			var userid = $(this).val();
			var sendData = {userid:userid};
			$.ajax({
				url:"/ex02/member/"+userid,
				type:"delete",
				dataType:"text",
				data:JSON.stringify(sendData),
				headers:{"Content-Type":"application/json"},
				success:function(result){
					console.log(result);
					alert("삭제 되었습니다.");
					listAll();
				}
			}) 
		})
		
		$(document).on("click",".modifyBtnStep1",function(){
			var userid = $(this).val();
			var sendData ={userid:userid};
			$.ajax({
				url:"/ex02/member/"+userid,
				type:"put",
				dataType:"json",
				data:JSON.stringify(sendData),
				headers:{"Content-Type":"application/json"},
				success:function(result){
					console.log(result);
					$("#modifyId").val(result.userid);
					$("#modifyName").val(result.username);
					$("#modifyPw").val(result.userpw);
					$("#modifyEmail").val(result.email);
				}
			}) 
		})
		
		$(document).on("click","#modifyBtnStep2",function(){
			var userid = $("#modifyId").val();
			var username = $("#modifyName").val();
			var userpw = $("#modifyPw").val();
			var email = $("#modifyEmail").val();
			
			var sendData = {userid:userid,username:username,userpw:userpw,email:email};
			
			$.ajax({
				url:"/ex02/member/"+userid,
				type:"patch",
				dataType:"text",
				data:JSON.stringify(sendData),
				headers:{"Content-Type":"application/json"},
				success:function(result){
					alert("수정되었습니다");
					listAll();
				}
			}) 
		})
	})
	
	
</script>
</head>
<body>
	<form>
		<p>
			<label>아이디</label>
			<input type="text" name="userid" id="userid">
		</p>
		<p>
			<label>이름</label>
			<input type="text" name="username" id="username">
		</p>
		<p>
			<label>비밀번호</label>
			<input type="text" name="userpw" id="userpw">
		</p>
		<p>
			<label>이메일</label>
			<input type="text" name="email" id="email">
		</p>
		<button id="addBtn" type='button'>추가</button>
		<button id="listBtn" type='button'>리스트 가져오기</button>
	</form>
	<table border="1" id="t">
	</table>
	<script id="template" type="text/x-handlebars-template">
		{{#each.}}
			<tr>
				<td>{{userid}}</td>
				<td>{{username}}</td>
				<td>{{userpw}}</td>
				<td>{{email}}</td>
				<td><button class='modifyBtnStep1' value='{{userid}}' data-toggle='modal' data-target='#myModal'>수정</button><button class='removeBtn' value='{{userid}}'>삭제</button></td>
			</tr>
		{{/each}}
	</script>
	
	<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
       <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
        	<form id="f">
        		<p>
					<label>아이디</label>
					<input type='text' id='modifyId' readonly="readonly">
				</p>
				<p>
					<label>이름</label>
					<input type='text' id='modifyName'>
				</p>
				<p>
					<label>비밀번호</label>
					<input type='text' id='modifyPw'>
				</p>
				<p>
					<label>이메일</label>
					<input type='text' id='modifyEmail'>
				</p>
        	</form>
        </div>
        <div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal" id="modifyBtnStep2">수정</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>
	
</body>
</html>