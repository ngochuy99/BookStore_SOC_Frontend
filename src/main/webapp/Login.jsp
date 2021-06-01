<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                        </div>
                                        <form class="user" method = "post" action="Login">
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-user"
                                                    name="username" aria-describedby="emailHelp"
                                                    placeholder="Enter Username...">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-user"
                                                    name = "password" placeholder="Password">
                                            </div>
                                            <div class="form-group">
                                                <div class="custom-control custom-checkbox small">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck">
                                                    <label class="custom-control-label" for="customCheck">Remember
                                                        Me</label>
                                                </div>
                                            </div>
                                            <button type = "submit" class="btn btn-primary btn-user btn-block">
                                                Login
                                            </button>
                                            <hr>
                                            <a href="#" id="btnGoogle" class="btn btn-google btn-user btn-block">
                                                <i class="fab fa-google fa-fw"></i> Login with Google
                                            </a>
                                            <a href="#" id="btnFacebook" class="btn btn-facebook btn-user btn-block">
                                                <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                                            </a>
                                        </form>
                                        <hr>
                                        <div class="text-center">
                                            <a class="small" href="Register.jsp">Create an Account!</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.6.2/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.6.2/firebase-analytics.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.6.2/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.6.2/firebase-firestore.js"></script>
    <script>
        (function() {
            console.log('Start file login with firebase');

            // Initialize Firebase
            var firebaseConfig = {
                apiKey: "AIzaSyDpLF3e-_lajNn3rIicslmShq9n8dWHCNg",
                authDomain: "soc-demo-login.firebaseapp.com",
                projectId: "soc-demo-login",
                storageBucket: "soc-demo-login.appspot.com",
                messagingSenderId: "654879518408",
                appId: "1:654879518408:web:31d4c387b94dc766877ffd",
                measurementId: "G-3W2P9F35RW"
            };
            firebase.initializeApp(firebaseConfig);
            //Google singin provider
            var ggprovider = new firebase.auth.GoogleAuthProvider();
            //Login in variables
            const btnGoogle = document.getElementById('btnGoogle');
            //GoogleOauth
            btnGoogle.addEventListener('click', e => {
                firebase.auth().signInWithPopup(ggprovider).then(function(result) {
                    var credential = result.credential;
                    var token = credential.accessToken;
                    var user = result.user;
                    window.location.replace("http://localhost:8080/Bookstore_war_exploded/BookAdmin?username="+user.displayName);
                }).catch(function(error) {
                    console.error('Error: hande error here>>>', error.code)
                })
            }, false)



            // FaceBook Oauth
            const btnFaceBook = document.getElementById('btnFacebook');
            var fbprovider = new firebase.auth.FacebookAuthProvider();

            btnFaceBook.addEventListener('click', e => {
                firebase.auth().signInWithPopup(fbprovider).then(function(result) {
                    // This gives you a Facebook Access Token. You can use it to access the Facebook API.
                    var token = result.credential.accessToken;
                    // The signed-in user info.
                    var user = result.user;
                    window.location.replace("http://localhost:8080/Bookstore_war_exploded/BookAdmin?username="+user.displayName);

                }).catch(function(error) {
                    var errorCode = error.code;
                    var errorMessage = error.message;
                    var email = error.email;
                    var credential = error.credential;
                    // ...
                    console.error('Error: hande error here>Facebook>>', error.code)
                });
            }, false)
        }())
    </script>
</body>
</html>