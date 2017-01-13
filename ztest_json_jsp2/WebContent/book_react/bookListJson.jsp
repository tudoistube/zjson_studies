<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.1.1.js"
		    integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
		    crossorigin="anonymous"></script>
		    
    <!--...브라우저에서 이 jsp 페이지의 실행되는 주소가 
           http://localhost:8080/ztest_json_jsp2/ReactListJsonGET.yes?bookName=CSS 이므로
           /ztest_json_jsp2/WebContent 폴더를 http://localhost:8080/ztest_json_jsp2 와 같은 기준으로 잡고
           하위의 CSS 파일이 든 폴더를 참조하기 위한 경로를 생각하면 절대경로이든 상대경로이든 설정하기 쉽다.  
           절대경로 : <link rel="stylesheet" href="/ztest_json_jsp2/zcss/zstyles.css">
           상대경로 : <link rel="stylesheet" href="./zcss/zstyles.css"> 
     -->
	<link rel="stylesheet" href="/ztest_json_jsp2/zcss/zstyles.css">
			    
	<title>Book List from Daum Open API with JSON...</title>
	
	<script type="text/javascript">
		//var zjson = {"channel":{"result":"3","title":"Search Daum Open API","totalCount":"4007","description":"Daum Open API search result","item":[{"author_t":"고경희","sale_price":"25200","cover_s_url":"http://t1.daumcdn.net/thumb/R72x100/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00031821542YE%3Fmoddttm=20170104090711","sale_yn":"Y","pub_date":"20170103","link":"http://book.daum.net/detail/book.do?bookid=BOK00031821542YE","barcode":"BOK00031821542YE","etc_author":"","status_des":"정상판매","author":"고경희","title":"&lt;b&gt;HTML5&lt;/b&gt;+CSS3 웹 표준의 정석(2017)","category":"컴퓨터/IT ","translator":"","pub_nm":"이지스퍼블리싱","description":"웹 분야 1위 도서! HTML 5.1 최종 표준안으로 전면 개정! 대학, 학원 강의 인기 교재! 문과생도, 중학생도 쉽게 배우는 책! 웹 분야 1위 도서인 『Do it! HTML5+CSS3 웹 표준의 정석...","isbn":"1187370681","ebook_barcode":"DGT00031918527IN","isbn13":"9791187370680","cover_l_url":"https://t1.search.daumcdn.net/thumb/R110x160/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00031821542YE%3Fmoddttm=20170104090711","list_price":"28000"},{"author_t":"윤인성","sale_price":"27000","cover_s_url":"http://t1.daumcdn.net/thumb/R72x100/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00024379015YE%3Fmoddttm=20170104060412","sale_yn":"Y","pub_date":"20150121","link":"http://book.daum.net/detail/book.do?bookid=BOK00024379015YE","barcode":"BOK00024379015YE","etc_author":"","status_des":"정상판매","author":"윤인성","title":"&lt;b&gt;HTML5&lt;/b&gt;+CSS3 입문","category":"컴퓨터/IT ","translator":"","pub_nm":"한빛미디어","description":"2014년 10월 확정된 HTML5 표준안과 최신 웹 브라우저 환경에 맞춘 최신 개정판이다. 웹 디자인을 처음 배우는 독자에게 HTML5와 CSS3를 단계별로 학습하는 방법을 설명해준 후, 웹...","isbn":"896848161X","ebook_barcode":"DGT4808968481611","isbn13":"9788968481611","cover_l_url":"https://t1.search.daumcdn.net/thumb/R110x160/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00024379015YE%3Fmoddttm=20170104060412","list_price":"30000"},{"author_t":"천인국","sale_price":"26320","cover_s_url":"http://t1.daumcdn.net/thumb/R72x100/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788992649209%3Fmoddttm=20170104060412","sale_yn":"Y","pub_date":"20131219","link":"http://book.daum.net/detail/book.do?bookid=KOR9788992649209","barcode":"KOR9788992649209","etc_author":"","status_des":"정상판매","author":"천인국","title":"&lt;b&gt;HTML5&lt;/b&gt; + CSS3 + JavaScript로 배우는 웹프로그래밍...","category":"컴퓨터/IT ","translator":"","pub_nm":"인피니티북스","description":"『HTML5 + CSS3 + JavaScript로 배우는 웹프로그래밍 기초』는 웹프로그래밍을 공부한다면 반드시 짚고 넘어가야 할 내용을 친절하게 설명한다. 가장 기초가 되는 HTML5와 CSS3, J...","isbn":"8992649207","ebook_barcode":"","isbn13":"9788992649209","cover_l_url":"https://t1.search.daumcdn.net/thumb/R110x160/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788992649209%3Fmoddttm=20170104060412","list_price":"28000"}],"lastBuildDate":"Mon, 09 Jan 2017 18:17:39 +0900","link":"http://dna.daum.net/apis","generator":"Daum Open API"}};
		//var jsonArr = [{"author":"고경희","translator":"","isbn":"1187370681","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=BOK00031821542YE","status_des":"정상판매","description":"웹 분야 1위 도서! HTML 5.1 최종 표준안으로 전면 개정! 대학, 학원 강의 인기 교재! 문과생도, 중학생도 쉽게 배우는 책! 웹 분야 1위 도서인 『Do it! HTML5+CSS3 웹 표준의 정석...","author_t":"고경희","list_price":"28000","title":"&lt;b&gt;HTML5&lt;\/b&gt;+CSS3 웹 표준의 정석(2017)","sale_price":"25200","pub_date":"20170103","ebook_barcode":"DGT00031918527IN","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00031821542YE%3Fmoddttm=20170104090711","isbn13":"9791187370680","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00031821542YE%3Fmoddttm=20170104090711","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"이지스퍼블리싱","barcode":"BOK00031821542YE","sale_yn":"Y"},{"author":"윤인성","translator":"","isbn":"896848161X","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=BOK00024379015YE","status_des":"정상판매","description":"2014년 10월 확정된 HTML5 표준안과 최신 웹 브라우저 환경에 맞춘 최신 개정판이다. 웹 디자인을 처음 배우는 독자에게 HTML5와 CSS3를 단계별로 학습하는 방법을 설명해준 후, 웹...","author_t":"윤인성","list_price":"30000","title":"&lt;b&gt;HTML5&lt;\/b&gt;+CSS3 입문","sale_price":"27000","pub_date":"20150121","ebook_barcode":"DGT4808968481611","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00024379015YE%3Fmoddttm=20170104060412","isbn13":"9788968481611","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00024379015YE%3Fmoddttm=20170104060412","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"한빛미디어","barcode":"BOK00024379015YE","sale_yn":"Y"},{"author":"천인국","translator":"","isbn":"8992649207","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=KOR9788992649209","status_des":"정상판매","description":"『HTML5 + CSS3 + JavaScript로 배우는 웹프로그래밍 기초』는 웹프로그래밍을 공부한다면 반드시 짚고 넘어가야 할 내용을 친절하게 설명한다. 가장 기초가 되는 HTML5와 CSS3, J...","author_t":"천인국","list_price":"28000","title":"&lt;b&gt;HTML5&lt;\/b&gt; + CSS3 + JavaScript로 배우는 웹프로그래밍...","sale_price":"26320","pub_date":"20131219","ebook_barcode":"","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788992649209%3Fmoddttm=20170104060412","isbn13":"9788992649209","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788992649209%3Fmoddttm=20170104060412","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"인피니티북스","barcode":"KOR9788992649209","sale_yn":"Y"}];
		var zjson = '<%= request.getAttribute("zjson") %>';    /* retrieve json from request attribute */
		var zjsonArr = '<%= request.getAttribute("zjsonArr") %>';    /* retrieve json from request attribute */
		/*
		var zjsonArr = [
			           {"author":"고경희","translator":"","isbn":"1187370681","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=BOK00031821542YE","status_des":"정상판매","description":"웹 분야 1위 도서! HTML 5.1 최종 표준안으로 전면 개정! 대학, 학원 강의 인기 교재! 문과생도, 중학생도 쉽게 배우는 책! 웹 분야 1위 도서인 『Do it! HTML5+CSS3 웹 표준의 정석...","author_t":"고경희","list_price":"28000","title":"&lt;b&gt;HTML5&lt;\/b&gt;+CSS3 웹 표준의 정석(2017)","sale_price":"25200","pub_date":"20170103","ebook_barcode":"DGT00031918527IN","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00031821542YE%3Fmoddttm=20170104090711","isbn13":"9791187370680","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00031821542YE%3Fmoddttm=20170104090711","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"이지스퍼블리싱","barcode":"BOK00031821542YE","sale_yn":"Y"}
		              ,{"author":"윤인성","translator":"","isbn":"896848161X","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=BOK00024379015YE","status_des":"정상판매","description":"2014년 10월 확정된 HTML5 표준안과 최신 웹 브라우저 환경에 맞춘 최신 개정판이다. 웹 디자인을 처음 배우는 독자에게 HTML5와 CSS3를 단계별로 학습하는 방법을 설명해준 후, 웹...","author_t":"윤인성","list_price":"30000","title":"&lt;b&gt;HTML5&lt;\/b&gt;+CSS3 입문","sale_price":"27000","pub_date":"20150121","ebook_barcode":"DGT4808968481611","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00024379015YE%3Fmoddttm=20170104060412","isbn13":"9788968481611","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00024379015YE%3Fmoddttm=20170104060412","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"한빛미디어","barcode":"BOK00024379015YE","sale_yn":"Y"}
		              ,{"author":"천인국","translator":"","isbn":"8992649207","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=KOR9788992649209","status_des":"정상판매","description":"『HTML5 + CSS3 + JavaScript로 배우는 웹프로그래밍 기초』는 웹프로그래밍을 공부한다면 반드시 짚고 넘어가야 할 내용을 친절하게 설명한다. 가장 기초가 되는 HTML5와 CSS3, J...","author_t":"천인국","list_price":"28000","title":"&lt;b&gt;HTML5&lt;\/b&gt; + CSS3 + JavaScript로 배우는 웹프로그래밍...","sale_price":"26320","pub_date":"20131219","ebook_barcode":"","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788992649209%3Fmoddttm=20170104060412","isbn13":"9788992649209","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788992649209%3Fmoddttm=20170104060412","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"인피니티북스","barcode":"KOR9788992649209","sale_yn":"Y"}
		              ];
		*/
		//let zjsonArr = '${zjsonArr}';    /* retrieve json from request attribute */
		let zresult = '${zresult}';    /* retrieve json from request attribute */
		//var mytest = eval('(' + myVar + ')');
		//var jsonObj = JSON.parse(jsonArr);
		//console.log("zjson : " + zjson); 
		//console.log("zjsonArr : " + zjsonArr);
		
		/*...여기서 JSON.parse(zjson) 처리를 해주지 않으면 App.js :: List ::
			 this.props.books.map((book) 부분에서 "Uncaught TypeError: this.props.books.map is not a function"
			 오류가 발생함.
		     json결과를 던져주는 쪽에서 header를 application/json이라고 주지 않아서 
		     그냥 String 형식으로 받아져서 그렇습니다. 
		     화면에서도 시작과 끝에 쌍따옴표가 있는 String 형식이죠. 
		     일반적으로 ajax형태로 받은 json은 쓰기전에 JSON.Parse를 한번 거칩니다. 어찌 올지 모르니까요.
		*/
		var zjsonParse = JSON.parse(zjson);
		var zjsonArrParse = JSON.parse(zjsonArr);

		/*
		console.log("zjsonParse : " + zjsonParse); //zjsonParse : [object Object]
		console.log("zjsonArrParse : " + zjsonArrParse); //zjsonArrParse : [object Object],[object Object],[object Object]
		console.log("------------------------");		
		console.log("zjsonParse : " + zjsonParse.channel.item[0].author);
		console.log("zjsonArrParse : " + zjsonArrParse[0].author);		
		console.log("------------------------");
		
        for (var i in zjsonArrParse) {
        	console.log();
            console.log(zjsonArrParse[i].author);
            console.log();
        }//for.
        */
		console.log("------------------------");
		console.log("zjsonArr : " + zjsonArr);
		console.log("------------------------");
		
        /*
		var zjsonStringify = JSON.stringify(zjson);
		var zjsonArrStringify = JSON.stringify(zjsonArr);

		console.log("zjsonStringify : " + zjsonStringify); 
		console.log("zjsonArrStringify : " + zjsonArrStringify);		
		console.log("------------------------");
		*/
		   
	</script>
</head>
<body>

	<h1>ready to render JSON data to React Component...</h1>
	
	
	<c:if test="${not empty requestScope.zresult }">
		${zresult }
	</c:if>

	<div id="zroot"></div>
	
    <script src="/ztest_json_jsp2/resources/built/bundle.js" type="text/javascript"></script>

</body>
</html>