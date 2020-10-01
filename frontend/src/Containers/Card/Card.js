import React, { Component } from "react";
import classes from "./Card.module.css";
import HoverCard from "react-png-hovercard";
import {Link} from 'react-router-dom'
class card extends Component {

    clickedHandler =() =>{
        
    }


  render() {
    return (
      <Link to='/2'style={{padding:'20px'}}>
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
            <p> test discription</p>
          </div>
        }
        maxWidth={280}
        animationSpeed={1000}
        height={150}
        margin={20}
      />
      </Link>
    );
  }
}
export default card;
