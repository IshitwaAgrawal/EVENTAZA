import React, { Component } from "react";
import classes from './Card.module.css'
import HoverCard from 'react-png-hovercard';
class card extends Component {
  render() {
    return (
        <HoverCard
        front={
          <div className={classes.Front}>
            <img
              src="https://images.unsplash.com/photo-1498910265115-9fb541931cd1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1089&q=80"
              alt=""
              style={{ objectFit: 'cover' }}
            />
          </div>
        }
        back={
          <div className={classes.Back}>
            <p> I would do anything to be there</p>
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
export default card
