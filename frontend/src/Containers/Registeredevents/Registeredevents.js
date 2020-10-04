import React, { Component } from 'react'
import classes from './Registeredevents.module.css'
class registeredevents extends Component{

    render(){
        return(
            <div className={classes.Registeredcard}>
                <h4 style={{color:'black'}}>event name</h4>
                <div className={classes.Datetime}>
                  <i className="far fa-calendar-alt"></i>
                  <span>&nbsp;time </span>
                  <span>|</span>
                  <span>&nbsp;date</span>
                </div>
                <span>seats : 5</span>
                <span className={classes.Cancel}>Cancel and Refund</span>
            </div>
        );
    }
}
export default registeredevents