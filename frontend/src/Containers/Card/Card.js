import React, { Component } from "react";
import classes from "./Card.module.css";
import HoverCard from "react-png-hovercard";
import {NavLink} from 'react-router-dom'
class card extends Component {

    clickedHandler =() =>{
        
    }


  render() {
    return (
      <div style={{padding:'20px'}}>
      <NavLink to={'/' + this.props.event.id}>
      <HoverCard 
        front={
          <div className={classes.Front} >
          <div style={{zIndex:5 }}>
          <p style={{color:'white', margin:'10px'}}>{this.props.event.eventName}</p>
        </div>
        </div>
        }
        back={
          <div className={classes.Back} onClick={this.clickedHandler}>
            <p>Location : {this.props.event.eventLocation}</p>
            <p>Date : {this.props.event.startDate}</p>
            <p>Time : {this.props.event.startTime}</p>
            </div>
        }
        maxWidth={280}
        animationSpeed={1000}
        height={150}
        margin={20}
      />
      </NavLink>
      </div>
    );
  }
}
export default card;
