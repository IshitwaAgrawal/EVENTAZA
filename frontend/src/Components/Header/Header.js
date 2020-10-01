import React, { Component } from "react";
import classes from "./Header.module.css";
import { Link } from "react-router-dom";
import Modal from '../../Containers/ui/Modal/Modal'
import Sidebar from '../../Containers/ui/Sidebar/Sidebar'
import {withRouter} from 'react-router-dom'
class header extends Component {
  SelectedCityHandler = (city) =>{
    this.props.selectedcity(city);
  }

  LogoclickedHandler = () =>{
    this.props.history.push("/")
  }
  render() {
    let token = localStorage.getItem("jwt");
    let auth = true;
    if (token === null) {
      auth = false;
    }

    const logoutHandler = () => {
      localStorage.clear();
      auth = false;
    };
   
    return (
      <nav>
        <div className={classes.Header1}>
          <div className={classes.Logo}>
            <h1 style={{ display: "inline-block", color: "white", fontSize:'20px' }} onClick={this.LogoclickedHandler}>
              Eventaza
            </h1>
            {/* <h1>hello{props.name}</h1> */}
            <form>
            <input type='search' placeholder="search" ></input>
            </form>
            {/* <i className="fas fa-search"></i> */}
          </div>
          <div className={classes.Link}>
            <div >
              {/* <h3>Select city</h3>
              <i className="fas fa-caret-down"></i> */}
              <Modal selectedcity={this.SelectedCityHandler}></Modal>
            </div>
            <div className={classes.Login} style={{marginRight:'60px'}}>
              {auth === false ? (
                <Link to="/login">Login</Link>
              ) : (
                <Link to='' onClick={logoutHandler}>Logout</Link>
              )}
            </div>
            <div >
             <Sidebar></Sidebar>
            </div>
          </div>
          {/* <div  className={classes.Hlink}>
                    <Link  className={classes.a} to='/'>Home</Link>
                    <Link className={classes.a}  to='/register'>Register</Link>
                    <Link  className={classes.a} to='/login'>Login</Link>
                </div> */}
        </div>
        <div className={classes.Header2}>
          <div style={{display:'inline'}}>
            <Link to='/events'>dummy</Link>
            <Link to='/events'>dummy</Link>
            <Link to='/events'>dummy</Link>
            <Link to='/eventpage'>dummy</Link>
            
          </div>
          <div className={classes.CreateEvent}>
          {auth === true? (
                <Link to="/createevent">Create Event</Link>
              ) : (
                <Link to="/createevent" >Create Event</Link>
              )}
            
          </div>
        </div>
      </nav>
    );
  }
}

export default withRouter(header);
