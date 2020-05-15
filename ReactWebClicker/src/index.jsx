import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import './style.scss';
import Ripples from 'react-ripples';
import {Router, Route} from "react-router";



let xhr = new XMLHttpRequest();
let currentUser;

  

// xhr.open('POST', '/api/users/');
// xhr.setRequestHeader("Content-Type", "application/json");
// xhr.send(JSON.stringify({user_name: "user3", password: "password3", score: 0}));

// xhr.open("PUT", '/api/user1')
// xhr.setRequestHeader("Content-Type", "application/json");
// xhr.send(JSON.stringify({score: 0}));



class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      LoginOpen: true,
      RegistrationOpen: false,
      
    };
  }
  

  showRegistrationBox(){
    this.setState({RegistrationOpen: true, LoginOpen: false});
  }

  showLoginBox(){
    this.setState({LoginOpen: true, RegistrationOpen: false});
  }

  showMainPage(){
    this.setState({LoginBox:false, RegistrationBox: false, MainPageOpen: true});
  }
  

   render() {

    

     return (
       
      <div className= "root-container">
        <div className = "box-controller">
        <div className = {"controller " + (this.state.LoginOpen ? "selected-controller": "")}  onClick={this.showLoginBox.bind(this)}>
          Login
          </div>
          <div name = "registerTab" className = {"controller " + (this.state.RegistrationOpen ? "selected-controller": "")} onClick={this.showRegistrationBox.bind(this)}>
          Register
          </div>
          {/* <div className = {"controller " + (this.state.MainPageOpen ? "selected-controller": "")} onClick={this.showMainPage.bind(this)}>
         Main Page
          </div> */}
        </div>

        <div className="box-container">
          {this.state.LoginOpen && <LoginBox/>}
          {this.state.RegistrationOpen && <RegistrationBox/>}
          
        </div>

     </div>
     )
  } 
}

class LoginBox extends React.Component {

  constructor(props){
    super(props);
    this.state={ 
      WarningOpen: false
    };
  }

  loginSubmit() {
    let set = this;
    xhr.open('GET', '/api/users/', true);
    xhr.responseType = 'json';
    let username;
    let password;
    let users = [];

    xhr.onload = function () {
      users = xhr.response;
      console.log({users})
      username = document.getElementsByName("username")[0].value;
      password = document.getElementsByName("password")[0].value;
      let correct = false;
      

      for(let x of users) {
        if(username === x.user_name && password === x.password) {
          currentUser = x;
          correct = true;
        }
      }

      if(correct) {
        ReactDOM.render(<MainPage/>, document.getElementById('root'));
      } else {
        set.setState({WarningOpen: true})
        setTimeout(function() {
          set.setState({WarningOpen: false})
        }, 1000);
      }
    }
    xhr.send(null);
  }

  render(){
    return(

    <div className="inner-container">
      <div className="header">
        Login
      </div>

      < div className="box">

           < div className="input-group">
            <label htmlFor="username"></label>
            <input type="text" name="username" className="login-input" placeholder="Username"/>
          </div>

          < div className="input-group">
            <label htmlFor="password"></label>
            <input type="password" name="password" className="login-input" placeholder="Password"/>
            {this.state.WarningOpen && <div style={{color:'rgb(255, 17, 0)'}}>Incorrect username/password</div>}
          </div>
          <button type="button" className="login-button" onClick={this.loginSubmit.bind(this)}>Login</button>
        </div>
    </div>);
  }
}


class RegistrationBox extends React.Component {

  constructor(props){
    super(props);
    this.state=this.state={ 
      WarningOpen: false,
      UserExists: false
    };
  }

  registerSubmit(){

    let set = this;
    xhr.open('GET', '/api/users/', true);
    xhr.responseType = 'json';
    

    xhr.onload = function () {
      let users = [];
      users = xhr.response;
      let username = document.getElementsByName("username")[0].value;
      let password = document.getElementsByName("password")[0].value;
      let bool = false;
      console.log({users})
      for(let x of users) {
        if (x.user_name == username.trim()) {
          bool = true;
        }
      }

      if(bool) {
        set.setState({UserExists: true})
        setTimeout(function() {
          set.setState({UserExists: false})
        }, 1000);
      } else if(username.trim() == "" || password.trim() == "") {
        set.setState({WarningOpen: true});
        setTimeout(function() {
        set.setState({WarningOpen: false})
        }, 1000);
      } else {
        let xhr2 = new XMLHttpRequest;

        xhr2.open('POST', '/api/users/');
        xhr2.setRequestHeader("Content-Type", "application/json");
        xhr2.send(JSON.stringify({user_name: username, password: password, score: 0}))
      }

    }
    xhr.send(null);    
  }

  render(){
    return(

    <div className="inner-container">
      <div className="header">
        Register
      </div>

      < div className="box">

           < div className="input-group">
            <label htmlFor="username"></label>
            <input type="text" name="username" className="login-input" placeholder="Username"/>
          </div>

          < div className="input-group">
            <label htmlFor="password"></label>
            <input type="password" name="password" className="login-input" placeholder="Password"/>
          </div>
          {this.state.WarningOpen && <div style={{color:'rgb(255, 17, 0)'}}>Cannot submit blank</div>}
          {this.state.UserExists && <div style={{color:'rgb(255, 17, 0)'}}>User already exists</div>}
          <button type="button" className="login-button" onClick={this.registerSubmit.bind(this)}>Register</button>
        </div>
    </div>);
  }
 }


 class MainPage extends React.Component {

  users = [];

  constructor(props){
    super(props);
    this.state={list: [], score: currentUser.score};

    let users = this.users; 
    let set = this;

    xhr.open('GET', '/api/users/', true);
    xhr.responseType = 'json';

    xhr.onload = function () {
      users = xhr.response;
      users.sort((a, b) => {
        if(a.score > b.score) {
          return -1;
        } else return 1;
      })
      set.setState({list: users});
    }
    xhr.send(null);
  }

  updateScore() {
    let updated = this.state.score + 1;
    this.setState({score: updated});

    let xhr1 = new XMLHttpRequest;
    xhr1.open("PUT", '/api/' + currentUser.user_name + "/");
    xhr1.setRequestHeader("Content-Type", "application/json");
    xhr1.send(JSON.stringify({score: updated}));

    xhr.open('GET', '/api/users/', true);
    xhr.responseType = 'json';

    let users = this.users;
    let set = this;

    xhr.onload = function () {
      users = xhr.response;
      users.sort((a, b) => {
        if(a.score > b.score) {
          return -1;
        } else return 1;
      })
      set.setState({list: users});
    }
    xhr.send(null);
  }

  logOut() {
    ReactDOM.render(<App/>, document.getElementById('root'));
  }


  render(){
    return(
  <div> 
  <div className="root-container">
  <Ripples >
    <button type="button" name = "counter" style={{color:'rgb(232, 239, 243)', background:'rgb(37, 154, 243)', height:200, width:1200, fontSize:50}} className="btn btn-primary" onClick={this.updateScore.bind(this)}>
      {this.state.score}
      </button>
      </Ripples>
      <div className="box-item">
      <div>
      {this.state.list.map(function(element, idx){
        if(element.user_name === currentUser.user_name) {
          return (<li key={idx} style={{fontWeight: 'bold'}}>{element.user_name}: {element.score}</li>)
        }
        return (<li key={idx}>{element.user_name}: {element.score}</li>)
        })}
       </div>
       </div>
       <div><button type="button" name="logout" style={{color:'rgb(255, 8, 127)', background:'rgb(65, 75, 85)', height:50, width:100, fontSize:20}} className="btn btn-primary" onClick={this.logOut.bind(this)}>
         Log out
         </button>
       </div>
       </div>
       </div>);
  }
  }



  ReactDOM.render(<App/>, document.getElementById('root'));
