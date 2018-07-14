<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />
	<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<div class="container">
	<div class="row">
	

		<div class="col-md-12">
			<c:forEach var="pl" items="${productlist}">

				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<h4 class="text-center">
							<span class="label label-info">${pl.name}</span>
						</h4>
						<img src="${CR}/${pl.id}.jpg" class="img-responsive">
						
						<div class="caption">
							<div class="row">
								<div class="price">
									
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
													<h3 class="text-center">
										&#8377 ${pl.price} /-
									</h3>				</div>
								<div class="col-md-6">
								<c:if test="${pl.quantity==0}">
								<span class="alert alert-danger">Out of Stock</span>
								</c:if>
								<c:if test="${pl.quantity!=0}">
								
									<a href='${contextRoot}/info/${pl.id}' class="btn btn-primary btn-product"><span
										class="glyphicon glyphicon-info-sign"></span> Info</a>
										</c:if>
									
								</div>
							</div>

							<p></p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>

</div>
