<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/14/2021
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <title>avalon</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- bootstrap css -->
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
    <!-- style css -->
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <!-- Responsive-->
    <link type="text/css" rel="stylesheet" href="css/responsive.css">
    <!-- fevicon -->
    <link rel="icon" href="" type="image/gif" />
    <!-- Scrollbar Custom CSS -->
    <link type="text/css" rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <!-- awesome fontfamily -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Tweaks for older IEs-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<!-- body -->
<body class="main-layout">
<!-- loader  -->
<div class="loader_bg">
    <div class="loader"><img src="images/loading.gif" alt="" /></div>
</div>
<!-- end loader -->

<div class="wrapper">

    <div class="sidebar">
        <!-- Sidebar  -->
        <nav id="sidebar">

            <div id="dismiss">
                <i class="fa fa-arrow-left"></i>
            </div>

            <ul class="list-unstyled components">

                <li class="active">
                    <a href="/">Home</a>
                </li>
                <li>
                    <a href="/#about">About</a>
                </li>
                <li>
                    <a href="/#why_choose_us">why Choose Us</a>
                </li>
                <c:set var="user" value="${user}"/>
                <c:choose>
                    <c:when test="${user != null}">
                        <li>
                            <a href="/user/account">Account</a>
                        </li>
                        <li>
                            <a href="/vehicles">Rent A Vehicle</a>
                        </li>
                        <li>
                            <a href="/logout">Logout</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="/login">Login</a>
                        </li>
                        <li>
                            <a href="/user-register">Sign Up</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>

        </nav>
    </div>


    <div id="content">

        <!-- section -->
        <section id="home" class="other_section">
            <div class="row">
                <div class="col-lg-12">
                    <!-- header -->
                    <header>
                        <!-- header inner -->
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 logo_section">
                                    <div class="full">
                                        <div class="center-desk">
                                            <div class="logo"> <a href="/"><img src="images/logo.png" alt="#"></a> </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9">
                                    <div class="right_header_info">
                                        <ul>
                                            <li><img style="margin-right: 15px;" src="images/phone_icon.png" alt="#" /><a href="#">987-654-3210</a></li>
                                            <li><img style="margin-right: 15px;" src="images/mail_icon.png" alt="#" /><a href="#">bangerco@gmail.com</a></li>
                                            <li><img src="images/search_icon.png" alt="#" /></li>
                                            <li>
                                                <button type="button" id="sidebarCollapse">
                                                    <img src="images/menu_icon.png" alt="#" />
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end header inner -->
                    </header>
                    <section>
                        <div class="container">

                        </div>
                    </section>
                    <!-- end header -->
                </div>
            </div>
        </section>
        <!-- end section -->
        <!-- section -->
        <div class="section layout_padding padding_top_0">
            <div class="container">
                <div style="padding-top:30px" class="row">
                    <h5 style="text-align:center;font-size:24px;font-weight: bold">Please note that according to company policy Customers who are below 25 years are only allowed to rent Small Town Cars</h5>
                </div>
                <div class="row">
                    <c:forEach var="vehicle" items="${vehicles}">
                        <c:url value="/bookings/new-booking" var="url">
                            <c:param name="vehicleId" value="${vehicle.vehicleId}"/>
                        </c:url>
                        <div class="col-sm">
                            <div style="margin-top:25px" class="card" style="width: 20rem;">
                                <img style="width:20rem; height:250px" class="card-img-top"
                                     src="./images/${vehicle.imageName}" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title">Licence Plate Number - ${vehicle.plateNumber}</h5>
                                    <p style="font-size:14px" class="card-text">Transmission
                                        - ${vehicle.transmission}</p>
                                    <p style="font-size:14px" class="card-text">Fuel Type - ${vehicle.fuelType}</p>
                                    <p style="font-size:14px" class="card-text">Price Per Day -
                                        £${vehicle.pricePerDay}</p>
                                    <a style="margin-top:10px;border-color:#161c34;background-color: #161c34" href="${url}" class="btn btn-primary">Book Now</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-sm">

                    </div>
                </div>
            </div>
        </div>
        <!-- end section -->

        <!-- footer -->
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="full">
                            <h3>Banger&Co</h3>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="full">
                            <ul class="social_links">
                                <li><a href="#"><i class="fa fa-facebook-f"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="full">
                            <h4 class="widget_heading">SUBSCRIBE</h4>
                        </div>
                        <div class="full subribe_form">
                            <form>
                                <fieldset>
                                    <div class="field">
                                        <input type="email" name="mail" placeholder="Enter your email" />
                                    </div>
                                    <div class="field">
                                        <button class="submit_bt">Sumbit</button>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="full">
                            <h4 class="widget_heading">Usefull Links</h4>
                        </div>
                        <div class="full f_menu">
                            <ul>
                                <li><a href="#">Home</a></li>
                                <li><a href="#">About</a></li>
                                <li><a href="#">Our Cars</a></li>
                                <li><a href="#">Services</a></li>
                                <li><a href="#">Testimonial</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="full">
                            <h4 class="widget_heading">Contact Details</h4>
                            <div class="full cont_footer">
                                <p><strong>Banger&Co : Adderess</strong><br><br>Newyork 10012, USA<br>Phone: +987 654 3210<br>demo@gmail.com</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- end footer -->

        <!-- copyright -->

        <div class="cpy_right">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="full">
                            <p>© 2019 All Rights Reserved. Design by <a href="https://html.design">Free Html Templates</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- right copyright -->

    </div>
</div>

<div class="overlay"></div>

<!-- Javascript files-->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<!-- Scrollbar Js Files -->
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/custom.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#sidebar").mCustomScrollbar({
            theme: "minimal"
        });

        $('#dismiss, .overlay').on('click', function () {
            $('#sidebar').removeClass('active');
            $('.overlay').removeClass('active');
        });

        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').addClass('active');
            $('.overlay').addClass('active');
            $('.collapse.in').toggleClass('in');
            $('a[aria-expanded=true]').attr('aria-expanded', 'false');
        });
    });
</script>

<script>
    // This example adds a marker to indicate the position of Bondi Beach in Sydney,
    // Australia.
    function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 11,
            center: {lat: 40.645037, lng: -73.880224},
        });

        var image = 'images/location_point.png';
        var beachMarker = new google.maps.Marker({
            position: {lat: 40.645037, lng: -73.880224},
            map: map,
            icon: image
        });
    }
</script>
<!-- google map js -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8eaHt9Dh5H57Zh0xVTqxVdBFCvFMqFjQ&callback=initMap"></script>
<!-- end google map js -->

</body>
</html>
