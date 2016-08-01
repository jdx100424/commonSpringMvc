$(function() {
	$('#kaptchaImage').click(
			function() {// 生成验证码
				$(this).hide().attr(
						'src',
						'http://192.168.0.3:8080/commonSpringMvc-webapp/kaptcha/getKaptcha?'
								+ Math.floor(Math.random() * 100)).fadeIn();
			});
});

function loginSubmit() {
	var effectRow = new Object();
	effectRow["userName"] = document.getElementById("userName").value;
	effectRow["password"] = document.getElementById("password").value;
	effectRow["kaptcha"] = document.getElementById("kaptcha").value;
	var src = document.getElementById("redirectSrc").value;
	effectRow["src"] = src;
	$.post("http://192.168.0.3:8080/commonSpringMvc-webapp/login/submit", effectRow,
			function(rsp) {
				var json = eval('(' + rsp + ')');
				if (json.code == 200) {
					if(src==null || src=="null" || src==""){
						location.href = "http://admin.maoshen.com:8080/admin/index";
					}else{
						location.href = src;
					}
				} else {
					alert(json.message);
				}
			}, "text").error(function(rsp) {
		alert("exception");
	});
}