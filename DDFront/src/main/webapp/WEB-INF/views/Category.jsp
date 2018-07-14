    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    


	<c:url value='/admin/setcategory' var="url"></c:url>
	<div class="container">
    <div class="row col-md-6 col-md-offset-2 custyle">
    <form:form class="form-horizontal" action='${url}' method="POST" modelAttribute="category">
  
  <div class="form-group">
  <c:if test="${status }">
  <span class="alert alert-danger">Failed to process the data</span>
  </c:if>
  </div>
  <c:if test="${edit}">
		 			<div class="form-group"> 
		 				<label class="control-label" for="categoryId">Category Id</label> 
		 				<div class="controls"> 
		 					<form:input type="text" id="categoryId" name="categoryId" placeholder="" 
		 						class="form-control input-xlarge" path="categoryId" readonly="true"/>
		 				</div> 
		 			</div>
		</c:if>
    <div class="form-group">
      
      <label class="control-label"  for="categoryname">Category Name</label>
      <div class="controls">
        <form:input type="text" id="categoryname" name="categoryname" placeholder="" class="form-control input-xlarge" path="categoryname" />
        <form:errors path="categoryname" cssStyle="color:red"></form:errors>
        </div>
    </div>
 
 <div class="form-group">
      
      <label class="control-label"  for="categoryDescription">Category Description</label>
      <div class="controls">
        <form:input type="text" id="categoryDescription" name="categoryDescription" placeholder="" class="form-control input-xlarge" path="categoryDescription" />
        <form:errors path="categoryDescription" cssStyle="color:red"></form:errors>
        </div>
    </div>
 
    <div class="form-group">
      <label class="control-label"  for="submit"></label>
      <div class="controls">
        <input type="submit" id="submit" name="submit" class="btn btn-success">
       
      </div>
    </div>
  
</form:form>
    <table class="table table-striped custab">
    <thead>
       <tr>
            <th>Category Name</th>
            <th>Description</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <c:forEach var="cl" items="${categorylist}">
            <tr>
                <td>${cl.categoryname}</td>
                <td>${cl.categoryDescription}
                <td><a class='btn btn-info btn-xs' href="editcat/${cl.categoryId}"><span class="glyphicon glyphicon-edit"></span> Edit</a></td>
                <td><a href="delcat/${cl.categoryId}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a></td>
            </tr>
            </c:forEach>
            </table>
    </div>
</div>

