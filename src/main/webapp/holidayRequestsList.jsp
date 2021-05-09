<%--
  Created by IntelliJ IDEA.
  User: davidortegon
  Date: 02/05/2021
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Your Holidays Planner</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">

</head>

<body>
<header id="header" class="fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center">
        <h1 class="logo me-auto"><a href="index.html">Your Holidays Planner<span>.</span></a></h1>
        <nav id="navbar" class="navbar order-last order-lg-0">
            <ul>
                <li><a class="nav-link scrollto " href="submitHolidayRequest.jsp">Submit Request</a></li>
            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav><!-- .navbar -->

        <a href="index.html" class="get-started-btn scrollto">Admin Log In</a>
    </div>
</header><!-- End Header -->

<section class="breadcrumbs">
    <div class="container">
        <ol>
            <li><a href="index.html">Home</a></li>
            <li>Current Requests</li>
        </ol>
    </div>
</section><!-- End Breadcrumbs -->


<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>


<h1 class="section-title">Current Requests</h1>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<table class="table table-bordered align-content-center">

    <thead>
    <tr>
        <th scope="col">Request ID</th>
        <th scope="col">Employee Who Applied</th>
        <th scope="col">Login Employee</th>
        <th scope="col">From When</th>
        <th scope="col">Until</th>
        <th scope="col">Status</th>
        <th scope="col">Action</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="tmpRequest" items="${HolidaysRequestsList}">

    <c:url var="updateLink" value="HolidayRequestController">
    <c:param name="command" value="LOAD_B"></c:param>
    <c:param name="id" value="${tmpRequest.id}"></c:param>
    </c:url>

    <c:url var="deleteLink" value="HolidayRequestController">
    <c:param name="command" value="DELETE"></c:param>
    <c:param name="id" value="${tmpRequest.id}"></c:param>
    </c:url>

    <thead>
        <tr>
            <th scope="row">${tmpRequest.id}</th>
            <td>${tmpRequest.idEmployeeApplicant}</td>
            <td>${tmpRequest.loginEmployeeApplicant}</td>
            <td>${tmpRequest.startDateHol}</td>
            <td>${tmpRequest.endDateHol}</td>
            <td>${tmpRequest.status}</td>
            <td>
                <a href="${updateLink}">
                <button type="button" class="btn btn-success align-content-center">Edit</button>
            </a>
                <a href="${deleteLink}"
                   onclick="if(!(confirm('Confirm Delete ?? :)'))) return false">
                    <button type="button" class="btn btn-danger align-content-center">Delete</button>
                </a>
            </td>
        </tr>
    </thead>
    </c:forEach>

</table>

<%--        <div class="row form-group"></div>--%>
<%--        <div class="row form-group"></div>--%>
<%--        <div class="row form-group"></div>--%>

<!-- ======= Footer ======= -->
<footer id="footer">

    <div class="footer-top">
        <div class="container">
            <div class="row">

                <div class="col-lg-3 col-md-6 footer-contact">
                    <h3>Your Holidays Planner<span>.</span></h3>
                    <p>
                        Macedonska 35/19 <br>
                        Wroclaw, 51-112<br>
                        Poland(Polska) <br><br>
                        <strong>Phone:</strong> +48 886 407 714<br>
                        <strong>Email:</strong> 251596@student.pwr.edu.pl<br>
                    </p>
                </div>

            </div>
        </div>
    </div>

    <div class="container d-md-flex py-4">

        <div class="me-md-auto text-center text-md-start">
            <div class="copyright">
                &copy; Copyright <strong><span>Your Holidays Planner</span></strong>. All Rights Reserved
            </div>
            <div class="credits">
                <!-- All the links in the footer should remain intact. -->
                <!-- You can delete the links only if you purchased the pro version. -->
                <!-- Licensing information: https://bootstrapmade.com/license/ -->
                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/presento-bootstrap-corporate-template/ -->
                Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>
        </div>
        <div class="social-links text-center text-md-end pt-3 pt-md-0">
            <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
            <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
            <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
            <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
            <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
        </div>
    </div>
</footer><!-- End Footer -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="assets/vendor/aos/aos.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
<script src="assets/vendor/purecounter/purecounter.js"></script>
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>

<!--</main>&lt;!&ndash; End #main &ndash;&gt;-->

</body>
</html>
