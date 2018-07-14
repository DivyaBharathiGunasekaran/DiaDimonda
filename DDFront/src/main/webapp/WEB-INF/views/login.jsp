<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<spring:url var="images" value="/resources/images"/>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
            <c:if test="${loginerror==true}">
            ${error}
            </c:if>
            <c:if test="${logout==true}">
            ${msg}
            </c:if>
                <form class="form-signin" action='<c:url value='/j_spring_security_check'></c:url>' method="POST">
                <input type="text" id="j_username" name="j_username" class="form-control" placeholder="Email" required autofocus>
                <input type="password" id="j_password" name="j_password"  class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Sign in</button>
                    <label class="control-label" >New User?</label>
                    <a href="${contextRoot}/register" class="btn btn-lg btn-primary btn-block">Create an account </a>
                </form>
            </div>
            
        </div>
    </div>
</div>
