<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MYBURY</title>
	<link rel="shortcut icon" href="/static/img/mobile/app-icon.png">
	<link rel="icon" href="/static/img/mobile/app-icon.png">
    <link rel="stylesheet" href="/static/css/main.css" />
</head>
<body>
    <div class="contents">
        <div class="icon-wrap">
            <img class="app-icon" title="로고"/>
        </div>
        <div class="mybury-wrap">
            <img class="mybury-icon" title="mybury"/>
        </div>
        
        <div class="title-wrap-web">
            <div class="text-wrap">
                <span>달성한</span>
                <span>버킷리스트들이</span>
                <span>당신의</span>
                <span>성공 발자취가 됩니다.</span>
            </div>
        </div>
        
        <div class="btn-wrap">
        	<a href="https://play.google.com/store/apps/details?id=womenproject.com.mybury&hl=ko"><img class="google-icon" title="구글 다운로드"/></a>
        	<a href="https://apps.apple.com/kr/app/%EB%A7%88%EC%9D%B4%EB%B2%84%EB%A6%AC/id1569205454"><img class="apple-icon" title="애플 다운로드"/></a>
        </div>
        
        <div class="title-wrap">
            <div class="text-wrap">
                <span>달성한 버킷리스트들이,</span><br>
                <span>당신의 성공 발자취가 됩니다.</span>
            </div>
        </div>
        <div class="list-img-wrap">
            <img class="list-img" title="힐링여행가기"/>
        </div>
        <div class="emoji-wrap">
            <img class="emoji-img" title="^^"/>
        </div>
        <div class="cs-wrap">
        	<div class="cs-1">
            	<span class="orange">언제든지 문의부탁드립니다.</span>
            </div>
            <div class="cs-2">
            	<span>이메일</span><span>mybury.info@gamil.com</span>
            </div>
        </div>
        
        <a id="os"></a>

        <div class="web-list">
            <img class="web-list-img" title="힐링여행가기"/>
        </div>

        <div class="down-wrap center">
            <span class="down"><a class="down-link">앱다운로드하기</a></span>
        </div>
    </div>
</body>

<script type="text/javascript">
	var uanaVigatorOs = navigator.userAgent;
	var agentUserOs = uanaVigatorOs.replace(/ /g,'');
	
	var downHref = 'https://play.google.com/store/apps/details?id=womenproject.com.mybury&hl=ko';
	
	if( agentUserOs.indexOf('Android') > -1) {
		downHref = 'https://play.google.com/store/apps/details?id=womenproject.com.mybury&hl=ko';
	} else if( agentUserOs.indexOf('iPhone') > -1) {
		downHref = 'https://apps.apple.com/kr/app/%EB%A7%88%EC%9D%B4%EB%B2%84%EB%A6%AC/id1569205454';
	} else {
		downHref = 'https://play.google.com/store/apps/details?id=womenproject.com.mybury&hl=ko';
	}
	
	document.getElementsByClassName('down-link')[0].href = downHref;
</script>
</html>