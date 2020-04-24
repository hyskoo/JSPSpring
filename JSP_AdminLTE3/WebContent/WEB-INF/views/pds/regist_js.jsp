<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
	$('#registBtn').on('click', function(e){
		var form = document.registForm
		
		if(form.title.value==""){
			alert("제목은 필수입니다.");
			return;
		}
		
		if(form.content.value.length>1000){
			alert("글자수가 1000자를 초과할 수 없습니다.");
			return;
		}
		
		form.submit();
	});
</script>