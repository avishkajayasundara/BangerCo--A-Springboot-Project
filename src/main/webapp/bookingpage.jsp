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
    <link rel="icon" href="" type="image/gif" />
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
    <div class="loader"><img src="../images/loading.gif" alt="" /></div>
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
                                            <div class="logo"> <a href="/"><img src="../images/logo.png" alt="#"></a> </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9">
                                    <div class="right_header_info">
                                        <ul>
                                            <li><img style="margin-right: 15px;" src="../images/phone_icon.png" alt="#" /><a href="#">987-654-3210</a></li>
                                            <li><img style="margin-right: 15px;" src="../images/mail_icon.png" alt="#" /><a href="#">bangerco@gmail.com</a></li>
                                            <li><img src="../images/search_icon.png" alt="#" /></li>
                                            <li>
                                                <button type="button" id="sidebarCollapse">
                                                    <img src="../images/menu_icon.png" alt="#" />
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
        <div id="about" class="section layout_padding">
            <div class="container">
                <div style="horizontal-align:top" class="row">
                    <div class="col-lg-4 margin_top_30">
                        <div class="full margin_top_30">
                            <h3 style="font-size:48px" class="main_heading _left_side margin_top_30">Rent this Car</h3>
                            <c:set var="vehicleId" value="${vehicle.vehicleId}"/>
                            <div class="col-sm">
                                <div style="margin-top:15px" class="card" style="width: 18rem;">
                                    <img style="width:100%; height:250px" class="card-img-top"
                                         src="../images/${vehicle.imageName}" alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title">Licence Plate Number : ${vehicle.plateNumber}</h5>
                                        <p style="font-size:14px" class="card-text">Transmission
                                            - ${vehicle.transmission}</p>
                                        <p style="font-size:14px" class="card-text">Fuel Type - ${vehicle.fuelType}</p>
                                        <p style="font-size:14px" class="card-text">Price Per Day -
                                            $${vehicle.pricePerDay}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div style="border: 2px solid #161c34; border-radius:5px;margin-top: 60px" class="col-lg-8">
                        <div class="full">
                            <div class="contact_form">
                                <form action="/bookings/save" method="post" id="bookingform">
                                    <fieldset class="row">
                                        <div class="col-md-12">
                                            <div class="full">
                                                <div class="row">
                                                    <div  style="width:48%;margin-right: 2%" class="column field">
                                                        Date of Collection
                                                        <input type="hidden" name="vehicleId" id="vehicleId" value="${vehicle.vehicleId}" />
                                                        <input type="date" id="startDate" name="startDate"/>
                                                    </div>
                                                    <div style="width:48%;margin-left:2%" class="column field">
                                                        Date of Return
                                                        <input type="date" id="endDate" name="endDate"/>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="full">
                                                <div class="row">
                                                    <div style="width:30%;margin-right:2%" class="column field">
                                                        Baby Seat
                                                        <input type="checkbox" placeholder="Phone" name="number"/>
                                                    </div>
                                                    <div style="width:30%;margin-right:2%" class="column field">
                                                        Wine Chiller
                                                        <input type="checkbox" placeholder="Phone" name="number"/>
                                                    </div>
                                                    <div style="width:30%;margin-right:2%" class="column field">
                                                        Sat Nav
                                                        <input type="checkbox" placeholder="Phone" name="number"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="full center">
                                                <button onclick="submitForm()" class="submit_bt">Book Now</button>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
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

<script>
    $(function(){
        var dtToday = new Date();

        var month = dtToday.getMonth() + 1;
        var day = dtToday.getDate();
        var year = dtToday.getFullYear();

        if(month < 10)
            month = '0' + month.toString();
        if(day < 10)
            day = '0' + day.toString();

        var maxDate = year + '-' + month + '-' + day;
        $('#startDate').attr('min', maxDate);
        $('#endDate').attr('min', maxDate);
    });
</script>
<script>
    function submitForm()
    {
        const element = document.querySelector('form');
        element.addEventListener('submit', event => {
            let oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
            let sDate = new Date(document.getElementById('startDate').value);
            let eDate = new Date(document.getElementById('endDate').value);
            let diffDays = Math.round(Math.abs((sDate - eDate) / oneDay));
            let bookable = true;
            let message = "Sorry.This Vehicle is Booked on the following dates \n";
            var array=[];
            <c:forEach var = "it" items = "${bookings}">
                array.push("${it}");

            </c:forEach>
            event.preventDefault();
            if(diffDays>14){
                window.alert("The Maximum Period You Could Rent a Vehicle is 14 Days");
            }
            else{
                console.log("Executing else");
                let i = ${bookings.size()};
                console.log(i);
                var n=0;
                for(n;n<i;n++){
                    console.log(array[n]);
                    if((sDate >= new Date(array[n]) && sDate <= new Date(array[n+1])) && (eDate => new Date(array[n]) && eDate <= new Date(array[n+1]))){
                        console.log(bookable);
                        bookable=false;
                    }
                    message += array[n]+" to "+array[n+1]+"\n";
                    n=n+1;
                }
                if(sDate>eDate){
                    window.alert("Error: Returning Cannot be a past date than the booking date");
                }else{
                    if(bookable){
                        document.getElementById("bookingform").submit();
                    }else{
                        window.alert(message);
                    }
                }

            }
        });

    }

</script>
</body>
</html>
