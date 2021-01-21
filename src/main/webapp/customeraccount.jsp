<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/18/2021
  Time: 2:43 PM
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
    <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
    <!-- style css -->
    <link type="text/css" rel="stylesheet" href="../css/style.css">
    <!-- Responsive-->
    <link type="text/css" rel="stylesheet" href="../css/responsive.css">
    <!-- fevicon -->
    <link rel="icon" href="" type="image/gif"/>
    <!-- Scrollbar Custom CSS -->
    <link type="text/css" rel="stylesheet" href="../css/jquery.mCustomScrollbar.min.css">
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
    <div class="loader"><img src="../images/loading.gif" alt=""/></div>
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
                            <a href="#">Account</a>
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
                                            <div class="logo"><a href="/"><img src="../images/logo.png" alt="#"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9">
                                    <div class="right_header_info">
                                        <ul>
                                            <li><img style="margin-right: 15px;" src="../images/phone_icon.png"
                                                     alt="#"/><a
                                                    href="#">987-654-3210</a></li>
                                            <li><img style="margin-right: 15px;" src="../images/mail_icon.png" alt="#"/><a
                                                    href="#">bangerco@gmail.com</a></li>
                                            <li><img src="../images/search_icon.png" alt="#"/></li>
                                            <li>
                                                <button type="button" id="sidebarCollapse">
                                                    <img src="../images/menu_icon.png" alt="#"/>
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
        <section style="margin-top:50px" id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="full center">
                            <h2 class="heading_main orange_heading">Your Account</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div style="width:35%" class="column">
                        <div class=" full margin_top_30">
                            <h3 style="font-size:24px;text-align:center" class="main_heading _left_side margin_top_30">
                                Account Details</h3>
                            <div class="contact_form">
                                <form action="/user/update" method="post" enctype="multipart/form-data">
                                    <fieldset class="row">
                                        <div class="col-md-12">
                                            <div class="full field">
                                                Full Name
                                                <input disabled type="text"
                                                       value="${profile.firstName} ${profile.lastName}" name="name"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="full field">
                                                Email
                                                <input disabled type="email" value="${profile.email}" name="email"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="full field">
                                                Contact Number
                                                <input type="text" value="${profile.contact}" name="mobile"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="full field">
                                                Date of Birth
                                                <input disabled value="${profile.dateOfBirth}" type="text" name=dob
                                                       id="dob" required="required">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="full field">
                                                Address
                                                <input type="text" value="${profile.address}" name="Address"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="half field">
                                                Username
                                                <input disabled type="text" value="${profile.userName}"
                                                       name="userName"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="half field">
                                                Password
                                                <input type="password" value="${profile.password}" name="password"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="full center">
                                                <input class="submit_bt btn btn-primary" value="Update Details"
                                                       type="submit" name="Update Details"/>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div style="width:65%"  class="column">
                        <div class="row">
                            <div class="margin_top_30">
                                <h3 style="font-size:24px;text-align:center;margin-left: 50px" class="main_heading _left_side margin_top_30">
                                    Bookings</h3>
                            </div>
                        </div>
                        <div class="row">
                            <c:forEach var="booking" items="${bookings}">
                                <c:url value = "/bookings/booking" var = "url">
                                    <c:param name = "bookingId" value = "${booking.bookingId}"/>
                                </c:url>
                                <div class="col-sm">
                                    <div class="card" style="width: 13rem;">
                                        <div class="card-body">
                                            <h5 class="card-title">Vehicle
                                                : ${booking.vehicleList.get(0).vehicleType.vehicleType}</h5>
                                            <p style="font-size:11px" class="card-text">Vehicle Plate Number
                                                : ${booking.vehicleList.get(0).plateNumber}</p>
                                            <p style="font-size:11px" class="card-text">Starting Date
                                                : ${booking.startDate}</p>
                                            <p style="font-size:11px" class="card-text">Ending Date
                                                : ${booking.endDate}</p>
                                            <a style="margin-top:10px" href="${url}" class="btn btn-primary">View
                                                Booking</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="col-sm">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
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
                                        <input type="email" name="mail" placeholder="Enter your email"/>
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
                                <p><strong>Banger&Co : Adderess</strong><br><br>Newyork 10012, USA<br>Phone: +987 654
                                    3210<br>demo@gmail.com</p>
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
                            <p>© 2019 All Rights Reserved. Design by <a href="https://html.design">Free Html
                                Templates</a></p>
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
<script src="../js/jquery.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
<!-- Scrollbar Js Files -->
<script src="../js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="../js/custom.js"></script>
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
</body>
</html>
