import React from 'react';
//import {render} from 'react-dom'; //...방법1.
import ReactDOM from 'react-dom'; //...방법2.

/*...javaJson_step09
*/

// Parent Component
class HiWorld extends React.Component { //...by12p.ref434p.
//class HiWorld extends Component {
  render() {
    return (
      <div>
        <ul>
          <List books={this.props.zjsonArr} />
  	     </ul>
      </div>
    );
  }
}

// Child Component
class List extends React.Component {
  render() {
    var books = this.props.books.map((book) => {
      return <Book author={book.author}
                   title={book.title}
                   isbn={book.isbn} />
    });

    return (
      <div className="list">
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
	        <div className="book__author">{this.props.author}</div>
	        <div className="book__isbn">{this.props.isbn}</div>
	        <div className="book__title">{this.props.title}</div>
	      </div>
	    );
	  }
	}


//render(<HiWorld />, document.getElementById('zroot')); //...방법1.
ReactDOM.render(<HiWorld zjsonArr={zjsonArr}/>, document.getElementById('zroot')); //...방법2.
