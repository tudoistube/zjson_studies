import React from 'react';
//import {render} from 'react-dom'; //...방법1.
import ReactDOM from 'react-dom'; //...방법2.

/*...javaJson_step09
     /ReactIndexGET.yes
     ↓
     /ztest_json_jsp2/src/yes/book/controller/BookFrontController.java
     ↓
     /ztest_json_jsp2/src/yes/book/controller/BookReactIndexGetAction.java
     ↓
     /ztest_json_jsp2/WebContent/book_react/index.jsp
*/

// Parent Component
class HiWorld extends React.Component { //...by12p.ref434p.
//class HiWorld extends Component {
  render() {
    return (
      <div>
        <ul>
          <h1>^^Hi, {this.props.zmessage}</h1>
          <ListItem quantity="6">Bread</ListItem>
          <ListItem quantity="30">Eggs : 0 EA.</ListItem>
          <ListItem quantity="2">Milk</ListItem>
  	     </ul>
      </div>
    );
  }
}

// Child Component
class ListItem extends React.Component {
  render() {
    return (
      <li>
        {/*...props.childern 속성을 이용해 열기 태그와 닫기 태그 사이에
              내용을 참조할 수 있음(Bread, Eggs, Milk 등이 출력됨).*/}
        {this.props.quantity} × {this.props.children}
      </li>
    );
  }
}

//render(<HiWorld />, document.getElementById('zroot')); //...방법1.
ReactDOM.render(<HiWorld zmessage={zmessage}/>, document.getElementById('zroot')); //...방법2.
