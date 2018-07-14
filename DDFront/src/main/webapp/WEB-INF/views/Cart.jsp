<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />
	<c:set var="ContextRoot" value="${pageContext.request.contextPath}"/>
<div class="container">
    <div class="row">
    <c:if test="${empty(cartlist)}">
    <h2 class="jumbotron">Cart Is Empty</h2>
     <a href="${ContextRoot}/productgrid" type="button" class="btn btn-default">
                        
                        <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                        
    </a>
    </c:if>
    <c:if test="${!empty(cartlist)}">
    
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Total</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="totalPrice" value="0"></c:set>
                    <c:forEach var = "dd" items = "${cartlist}">
                    <tr>
                    
                        <td class="col-sm-7 col-md-6">
                        <div class="media">
                        <h4 class="media-heading">${dd.pname}</h4>
                            <a class="thumbnail pull-left" href="#"> <img src="${CR}/${dd.pid}.jpg" class="img-responsive" width="100" height="100"> </a>
                            
                        </div></td>
                         <td class="col-sm-1 col-md-1 text-center"><strong>${dd.qty}</strong></td>
                        <td class="col-sm-1 col-md-1 text-center"><strong>${dd.pprice }</strong></td>
                        <td class="col-sm-1 col-md-1 text-center"><strong>${dd.total }</strong></td>
                        <c:set var="totalPrice" value="${totalPrice + dd.total}"></c:set>
                        <td class="col-sm-1 col-md-1">
                        <a href="${contextRoot}/info/${dd.pid}" class="btn btn-primary"><i class="glyphicon glyphicon-edit"></i> Edit</a></td>
                        <td class="col-sm-1 col-md-1">
                        <a href="${ContextRoot}/cart/deletecart/${dd.id}"type="button" class="btn btn-danger">
                            <span class="glyphicon glyphicon-remove"></span> Remove
                        </a></td>
                   
                    </tr>
                   </c:forEach>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h4>Total</h4></td>
                        <td ><h4>&#8377 ${totalPrice }</h4></td>
                    </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td>
                        <a href="${ContextRoot}/productgrid" type="button" class="btn btn-default">
                        
                        <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                        </a></td>
                        <td>
                        <a href="${ContextRoot}/address" type="button" class="btn btn-success">
                            <span class="glyphicon glyphicon-play"></span>CheckOut 
                        </a></td>
                    </tr>
                </tbody>
            </table>
            </c:if>
        </div>
    </div>
</div>