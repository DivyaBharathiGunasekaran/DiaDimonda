<%@ page isELIgnored= "false" %>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<spring:url var="images" value="/resources/images"/>
<nav class="navbar navbar-inverse navbar-custom navbar-fixed-top">
		<div class="container-fluid">
		<button type="button" class="navbar-toggle collapsed" 
   data-toggle="collapse" data-target="#collapse-example" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
			<div class="navbar-header">
				<span class="navbar-brand" ><img src="${images}/logo.jpg" class="img-fluid"
					height="50px" width="200px"></span>
			</div>
			<div class="collapse navbar-collapse" id="collapse-example">
			<ul class="nav navbar-nav">
				<li ><a href="${contextRoot}/home">Home</a></li>
				<li ><a href="${contextRoot}/about">About</a></li>
				<li ><a href="${contextRoot}/contact">Contact</a></li>
				<li ><a href="${contextRoot}/admin/category">Category</a></li>
				<li ><a href="${contextRoot}/admin/product">Product</a></li>
				
				
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextRoot}/login"><span class="glyphicon glyphicon-log-in"></span> Sign In</a></li>
			</ul>
			</div>
		</div>
	</nav>

