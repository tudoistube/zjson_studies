import React from 'react';
//import {render} from 'react-dom'; //...방법1.
import ReactDOM from 'react-dom'; //...방법2.

/*...javaJson_step11
 * 
var jsonArr = [
	           {"author":"고경희","translator":"","isbn":"1187370681","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=BOK00031821542YE","status_des":"정상판매","description":"웹 분야 1위 도서! HTML 5.1 최종 표준안으로 전면 개정! 대학, 학원 강의 인기 교재! 문과생도, 중학생도 쉽게 배우는 책! 웹 분야 1위 도서인 『Do it! HTML5+CSS3 웹 표준의 정석...","author_t":"고경희","list_price":"28000","title":"&lt;b&gt;HTML5&lt;\/b&gt;+CSS3 웹 표준의 정석(2017)","sale_price":"25200","pub_date":"20170103","ebook_barcode":"DGT00031918527IN","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00031821542YE%3Fmoddttm=20170104090711","isbn13":"9791187370680","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00031821542YE%3Fmoddttm=20170104090711","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"이지스퍼블리싱","barcode":"BOK00031821542YE","sale_yn":"Y"}
              ,{"author":"윤인성","translator":"","isbn":"896848161X","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=BOK00024379015YE","status_des":"정상판매","description":"2014년 10월 확정된 HTML5 표준안과 최신 웹 브라우저 환경에 맞춘 최신 개정판이다. 웹 디자인을 처음 배우는 독자에게 HTML5와 CSS3를 단계별로 학습하는 방법을 설명해준 후, 웹...","author_t":"윤인성","list_price":"30000","title":"&lt;b&gt;HTML5&lt;\/b&gt;+CSS3 입문","sale_price":"27000","pub_date":"20150121","ebook_barcode":"DGT4808968481611","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00024379015YE%3Fmoddttm=20170104060412","isbn13":"9788968481611","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FBOK00024379015YE%3Fmoddttm=20170104060412","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"한빛미디어","barcode":"BOK00024379015YE","sale_yn":"Y"}
              ,{"author":"천인국","translator":"","isbn":"8992649207","link":"http:\/\/book.daum.net\/detail\/book.do?bookid=KOR9788992649209","status_des":"정상판매","description":"『HTML5 + CSS3 + JavaScript로 배우는 웹프로그래밍 기초』는 웹프로그래밍을 공부한다면 반드시 짚고 넘어가야 할 내용을 친절하게 설명한다. 가장 기초가 되는 HTML5와 CSS3, J...","author_t":"천인국","list_price":"28000","title":"&lt;b&gt;HTML5&lt;\/b&gt; + CSS3 + JavaScript로 배우는 웹프로그래밍...","sale_price":"26320","pub_date":"20131219","ebook_barcode":"","cover_s_url":"http:\/\/t1.daumcdn.net\/thumb\/R72x100\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788992649209%3Fmoddttm=20170104060412","isbn13":"9788992649209","cover_l_url":"https:\/\/t1.search.daumcdn.net\/thumb\/R110x160\/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fbook%2FKOR9788992649209%3Fmoddttm=20170104060412","etc_author":"","category":"컴퓨터\/IT ","pub_nm":"인피니티북스","barcode":"KOR9788992649209","sale_yn":"Y"}
              ];
*/

// Parent Component
class HiWorld extends React.Component { //...by12p.ref434p.
//class HiWorld extends Component {
  render() {
    return (
      <div>
        <ul>
          <List books={this.props.zjsonArrParse} />
  	     </ul>
      </div>
    );
  }
}

// Child Component
class List extends React.Component {
  render() {
    var books = this.props.books.map((book) => {
      return <Book key={book.title}
                   author={book.author}
                   title={book.title}
                   isbn={book.isbn} />
    });

    return (
      <div className="list">
        <h3>== Book List ==</h3>
        {books}
      </div>
    );
  }
};

class Book extends React.Component {

	  render() {
	    return (
	      /*...Card 컴포넌트에 className 특성이 사용된 것에 주의할 것.
	      JSX는 자바스크립트이므로 class 같은 XML 특성이 있는 식별자와 구분하기 위함.*/
	      <div className="book">
	      	<div className="book__title">Title : {this.props.title}</div>
	        <div className="book__author">Author : {this.props.author}</div>
	        <div className="book__isbn">ISBN : {this.props.isbn}</div>
	        <br/><br/><br/>
	      </div>
	    );
	  }
	}


//render(<HiWorld />, document.getElementById('zroot')); //...방법1.
ReactDOM.render(<HiWorld zjsonArrParse={zjsonArrParse}/>, document.getElementById('zroot')); //...방법2.
