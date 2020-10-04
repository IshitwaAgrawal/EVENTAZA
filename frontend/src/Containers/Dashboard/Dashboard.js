import React, { Component } from "react";
import classes from "./Dashboard.module.css";
import Registercard from '../Registeredevents/Registeredevents'
import Organizedcard from '../Organizedevents/Organizedevents'
class dashboard extends Component {
  state = {
    user: {},
    registeredevents: [],
    organizedevents: [],
  };
  render() {
    return (
      <div>
        <div className={classes.Userdetails}>
          <h2>
            <i className="far fa-user"></i>&nbsp;&nbsp;Manas Saxena
          </h2>
          <p>
            &nbsp;&nbsp;<i className="far fa-envelope"></i>
            &nbsp;&nbsp;manassaxena160601@gmail.com
          </p>
          <button className={classes.Deleteaccount}>Delete account</button>
        </div>
        <div className={classes.Registeredevents}>
            <h3>Registered events</h3>
            <div className={classes.Showevents}>
              <Registercard></Registercard>
              </div>
            </div>
            <div className={classes.Registeredevents}>
            <h3>Organized events</h3>
            <div className={classes.Showevents}>
              <Organizedcard></Organizedcard>
              </div>
            </div>
      </div>
    );
  }
}

export default dashboard;
