<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="images" value="/resources/images"/>
<div class="container-fluid">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
                <li data-target="#myCarousel" data-slide-to="4"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="first-slide home-image" src="${images}/ca1.jpg" alt="first slide" height="100%" width="100%">
                    <div class="container">
                        
                    </div>
                </div>
                <div class="item">
                    <img class="second-slide home-image" src="${images}/ca2.jpg" alt="second slide" height="100%" width="100%">
                    <div class="container">
                       
                    </div>
                </div>
                <div class="item">
                    <img class="third-slide home-image" src="${images}/ca3.jpg" alt="third slide" height="100%" width="100%">
                    <div class="container">
                       
                    </div>
                </div>
                <div class="item">
                    <img class="fourth-slide home-image" src="${images}/ca4.jpg" alt="fourth slide" height="100%" width="100%">
                    <div class="container">
                       
                    </div>
                </div>
                <div class="item">
                    <img class="fifth-slide home-image" src="${images}/ca5.jpg" alt="fifth slide" height="100%" width="100%">
                    <div class="container">
                       
                    </div>
                </div>
<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" ></span>
                <span class="sr-only">Next</span>
            </a>
        </div><!-- /.carousel -->
                </div>
                </div>