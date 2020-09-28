import React, { Component } from "react";
import classes from "./Card.module.css";
import HoverCard from "react-png-hovercard";
class card extends Component {

    clickedHandler =() =>{
        
    }


  render() {
    return (
      <HoverCard 
        front={
          <div className={classes.Front} >
            <div style={{zIndex:2}}>
            {/* <img
              src="https://images.unsplash.com/photo-1498910265115-9fb541931cd1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1089&q=80"
              alt=""
              style={{ objectFit: "cover" }}
            ></img> */}
          </div>
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
        maxWidth={400}
        width={100}
        animationSpeed={1000}
        height={200}
        margin={20}
      />
    );
  }
}
export default card;
