<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<title>자료등록</title>
<body>
  <!-- Content Wrapper. Contains page content -->
  <div>
    <jsp:include page="content_header.jsp">
    	<jsp:param value="자료실" name="subject"/>
		<jsp:param value="수정" name="item"/>
		<jsp:param value="modifyForm.do" name="url"/>    	
    </jsp:include>
    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h4>글수정</h4>
					</div><!--end card-header  -->
					<div class="card-body">
					<!-- enctype="multipart/form-data"  -->
						<form role="form" method="post" action="modify.do" name="modifyForm"> 
						
							<input type="hidden" name="pno" value="${pds.pno}" />
							<input type='hidden' name='page' value="${param.page}">
							<input type='hidden' name='perPageNum' value="${param.perPageNum}">
							<input type='hidden' name='searchType' value="${param.searchType}">
							<input type='hidden' name='keyword' value="${param.keyword}">
							
						
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" id="writer" readonly
									name="writer" class="form-control" value="${pds.writer }">
							</div>
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title"
									name='title' class="form-control" placeholder="제목을 쓰세요" value="${pds.title}">
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="form-control" name="content" id="content" rows="5"
									placeholder="1000자 내외로 작성하세요.">${pds.content}</textarea>
							</div>
							<div class="form-group">								
								<div class="card card-outline card-success">
									<div class="card-header">
										<h5 style="display:inline;line-height:40px;">첨부파일 : </h5>
										&nbsp;&nbsp;<button class="btn btn-xs btn-primary" 
										type="button" id="addFileBtn">Add File</button>
									</div>									
									<div class="card-footer fileInput">
									</div>
								</div>
							</div>
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-primary" id="modifyBtn">수 정</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn" id="cancelBtn" onclick="Cancel();">취 소</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="/WEB-INF/views/commons/summernote.jsp" %>

<jsp:include page="./modify_js.jsp" />
<%-- <jsp:include page="./attach_js.jsp" /> --%>


</body>






  
  