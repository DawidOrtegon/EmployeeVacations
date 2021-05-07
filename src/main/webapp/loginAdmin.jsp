<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

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

    <!-- =======================================================
    * Template Name: Presento - v3.1.0
    * Template URL: https://bootstrapmade.com/presento-bootstrap-corporate-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>
<!-- ======= Header ======= -->
<header id="header" class="fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center">
        <h1 class="logo me-auto"><a href="index.html">Your Holidays Planner<span>.</span></a></h1>
        <nav id="navbar" class="navbar order-last order-lg-0">
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav><!-- .navbar -->
    </div>
</header><!-- End Header -->

<!-- ======= Breadcrumbs ======= -->
<section class="breadcrumbs">
    <div class="container">
        <ol>
            <li><a href="index.html">Home</a></li>
            <li>Admin Login</li>
        </ol>
    </div>
</section><!-- End Breadcrumbs -->

<!--<main id="main">-->
<section id="contact" class="contact">
    <div class="row form-group"></div>
    <div class="row form-group"></div>
    <div class="row form-group"></div>
    <div class="row form-group"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <form action="<%=request.getContextPath()%>/LoginADMINControllerServlet" method="post" role="form">
                    <div class="container">
                        <div class="form-group">
                            <label for="username">User Name:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>

                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                        <div class="row form-group"></div>

                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>

                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                        <div class="row form-group"></div>

                        <div class="form-group">
                            <label for="isManager">Are you a Manager:</label>
                            <input type="text" class="form-control" id="isManager" name="isManager" required>
                        </div>

                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                        <div class="row form-group"></div>
                    </div>

                    <div class="my-lg-3">
                        <div class="text-center">
                            <button type="submit" class="btn btn-info">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row form-group"></div>
    <div class="row form-group"></div>
    <div class="row form-group"></div>
    <div class="row form-group"></div>
    <div class="row form-group"></div>
</section><!-- End Contact Section -->

<!-- ======= About Section ======= -->
<section id="about" class="about section-bg">
    <div class="container" data-aos="fade-up">

        <div class="row no-gutters">
            <div class="content col-xl-5 d-flex align-items-stretch">
                <div class="content">
                    <h3>Submit the Request with us</h3>
                    <p>
                        Make and enjoy your holidays, ensuring that your manager received the request on time.
                    </p>
                </div>
            </div>
            <div class="col-xl-7 d-flex align-items-stretch">
                <div class="icon-boxes d-flex flex-column justify-content-center">
                    <div class="row">
                        <div class="col-md-6 icon-box" data-aos="fade-up" data-aos-delay="100">
                            <i class="bx bx-receipt"></i>
                            <h4>Easy to manage</h4>
                            <p>Never so easy to make a request, just insert that the data and immediately your manager
                                will receive the notification.</p>
                        </div>
                        <div class="col-md-6 icon-box" data-aos="fade-up" data-aos-delay="200">
                            <i class="bx bx-cube-alt"></i>
                            <h4>Intuitive</h4>
                            <p>Does not matter your age, with our design there is not possible to get confuse.</p>
                        </div>
                        <div class="col-md-6 icon-box" data-aos="fade-up" data-aos-delay="300">
                            <i class="bx bx-images"></i>
                            <h4>Fast</h4>
                            <p>Just sign up to be part of the employees who use the platform, no sensitive data
                                required, just the most basic info: Your start Date, name and last name. </p>
                        </div>
                        <div class="col-md-6 icon-box" data-aos="fade-up" data-aos-delay="400">
                            <i class="bx bx-shield"></i>
                            <h4>Secure</h4>
                            <p>Since there is an internal platform, only your colleagues can see all the request with
                                special id.</p>
                        </div>
                    </div>
                </div><!-- End .content-->
            </div>
        </div>

    </div>
</section><!-- End About Section -->

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