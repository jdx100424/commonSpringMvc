<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>猫神</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
  content="Ethos Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<script type="application/x-javascript">
	
	


</script>

<script type="text/javascript"
  src="http://192.168.0.3:8080/commonSpringMvc-webapp/js/baseJs/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
  src="http://192.168.0.3:8080/commonSpringMvc-webapp/js/baseJs/json2.js"></script>
<script type="text/javascript"
  src="http://192.168.0.3:8080/commonSpringMvc-webapp/js/baseJs/ajaxupload.js"></script>
<script type="text/javascript"
  src="http://192.168.0.3:8080/commonSpringMvc-webapp/js/login/login.js"></script>

<link href="http://192.168.0.3:8080/commonSpringMvc-webapp/css/style.css"
  rel='stylesheet' type='text/css' />

</head>
<body>


  <div class="main">
    <div class="login">
      <h1>Ethos Login Form</h1>
      <div class="inset">
        <!--start-main-->
        <form id="loginForm" method="post"
          style="padding-left: 10px; padding-top: 10px">
          <input type='hidden' id='redirectSrc' name='redirectSrc'
            value='<%=request.getParameter("src")%>' />
          <div>
            <h2>Login Form</h2>
            <span><label>Username</label></span> <span><input
              type="text" class="textbox" id="userName"></span>
          </div>
          <div>
            <span><label>Password</label></span> <span><input
              type="password" class="password" id="password"></span>
          </div>

          <div>
            <span><label>kaptcha</label></span> <span><input
              type="text" maxlength="5" class="chknumber_input"
              id="kaptcha" name="kaptcha"></span> <img
              src="http://192.168.0.3:8080/commonSpringMvc-webapp/kaptcha/getKaptcha" width="120"
              height="30" id="kaptchaImage" style="margin-bottom: -3px" />
          </div>

          <div class="sign">
            <div class="submit" onclick="loginSubmit()">
              <img src="http://192.168.0.3:8080/commonSpringMvc-webapp/images/arrow.png" />
            </div>
          </div>
        </form>
      </div>
    </div>
    <!--//end-main-->
  </div>
  <!--start-copyright-->
  <div class="copy-right">
    <p>
      &copy; 2015 Ethos Login Form. All Rights Reserved | Design by <a
        href="http://www.moke8.com/" target="_blank">moke8</a>
    </p>

  </div>
  <!--//end-copyright-->
</body>
</html>