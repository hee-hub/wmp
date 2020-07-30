<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 스크립트 분리, dispather servelet 으로 변경 (시간 남으면 )
function makeQueryString(param){	/*param의 val은 num아니면string이라고 치고*/
	
	 //byte[] bytes = url.getBytes("utf8");
	// String newString = new String(bytes, "utf8");
	// url = newString;
	
	//url = new OutputStreamWriter(url, "UTF-8");
	var keys = Object.keys(param);
	for(var i=0;i<keys.length;i++){
		keys[i]+='='+encodeURIComponent(param[keys[i]])
	}
	return keys.join('&'); 
}
function sendRequest(url, param){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log(xhr.status)
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				var resObj = JSON.parse(xhr.response);
				document.querySelector("#portion").innerHTML = resObj[0];
				document.querySelector("#rest").innerHTML = resObj[1];
			}else{
				document.querySelector("#portion").innerHTML = "ERROR " + xhr.status;
				document.querySelector("#rest").innerHTML = ''
			}
		}
	};
	xhr.open("POST", url);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
	xhr.send(makeQueryString(param));
}

function request(type){
	var param = {
		url : document.querySelector("#url").value,
		divisor : parseInt(document.querySelector("#divisor").value), //밸리데이션 체크 추가해야
		type : type,
	};
	sendRequest('test.do', param);
}
</script>
</head>
<body>
	URL : <input type="text" id="url"><br>
	<br>
	나누는 수 : <input type="number" id="divisor"></input><br>
	<br>
	<input type="radio" name="tag" onchange="request('excludeTag')"></input> 테그 제외<br>
	<input type="radio" name="tag" onchange="request('includeTag')"></input> 전체 텍스트<br>
	<br>
	<br>
	 몫 : <span id="portion" style="width:100%;word-wrap:break-word"></span><br>
	 나머지 : <span id="rest" style="width:100%;word-wrap:break-word"></span><br>
</body>
</html>