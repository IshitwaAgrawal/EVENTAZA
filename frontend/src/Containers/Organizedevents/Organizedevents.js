import React, { Component } from 'react'
import classes from './Organizedevents.module.css'
class registeredevents extends Component{

    render(){
        return(
            <div className={classes.Organizedcard}>
                <h4 style={{color:'black'}}>event name</h4>
                <div className={classes.Datetime}>
                  <i className="far fa-calendar-alt"></i>
                  <span>&nbsp;time </span>
                  <span>|</span>
                  <span>&nbsp;date</span>
                </div>
                <span> Total seats : 5 | Seats Booked : 2</span><br></br>
                <span className={classes.Price}>Price : Rs1500</span> <br></br><br></br>
                <span className={classes.Cancel}>Cancel Event</span>
            </div>
        );
    }
}
export default registeredevents