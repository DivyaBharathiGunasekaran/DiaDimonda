<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="CR" value="${pageContext.request.contextPath}" />
<c:set var="ContextRoot" value="${pageContext.request.contextPath}" />
<div class="container">
	<div class="row">
		<div class="col-sm-5">
			<c:url value='/setaddress' var="url"></c:url>
			<div class="row col-md-6 col-md-offset-2 custyle">
				<form:form class="form-horizontal" method="post"
					modelAttribute="address" action="${url}">
					<form:hidden path="id" />
					<form:hidden path="cartId" />
					<div class="form-group">
						<label class="control-label">Enter Address </label>
						<div class="controls">
							<form:input class="form-control input-xlarge"
								placeholder="Enter Address" path="addressline1" />
							<form:errors path="addressline1" cssStyle="color:red"></form:errors>
						</div>
					</div>

					<div class="form-group">
						<label>Enter City</label>
						<div class="controls">
							<form:input class="form-control input-xlarge"
								placeholder="Enter City" path="city" />
							<form:errors path="city" cssStyle="color:red"></form:errors>
						</div>
					</div>

					<div class="form-group">
						<label>Enter State</label>
						<div class="controls">
							<form:input class="form-control input-xlarge"
								placeholder="Enter State" path="state" />
							<form:errors path="state" cssStyle="color:red"></form:errors>
						</div>
					</div>
					<div class="form-group">
						<label>Enter Pincode</label>
						<div class="controls">
							<form:input class="form-control input-xlarge"
								placeholder="Enter Pincode" path="pincode" />
							<form:errors path="pincode" cssStyle="color:red"></form:errors>
						</div>
					</div>

					<div class="form-group">
						<input type="submit" class="btn button">
					</div>
				</form:form>
			</div>

		</div>
		<div class="col-sm-7">
			<div class="col-md-12">
				<c:forEach var="ad" items="${addresslist}">

					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
						
						<h4 class="text-center"><a href="${ContextRoot}/Invoice/${ad.id}"><span class="btn button">DELIVER HERE</span></a></h4>

							<div class="caption">
								<div class="row text-center">
									<p>${ad.addressline1}</p>

									<p>${ad.city}</p>
									<p>${ad.state}</p>

									<p>${ad.pincode}</p>
									
									</div>
									

									</div>
									<div class="row">
									<div class="col-md-4">
										<a href="${ContextRoot}/editadd/${ad.id}" class="btn button "><span
											class="	glyphicon glyphicon-pencil"></span> </a>

									</div>

									<div class="col-md-4">

										<a href="${ContextRoot}/deladd/${ad.id}" class="btn button "><span
											class="glyphicon glyphicon-trash"></span> </a>

									</div>
								</div>
							</div>
						</div>
				</c:forEach>
			</div>



		</div>

	</div>


</div>
