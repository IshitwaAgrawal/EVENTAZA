import React, { Component } from "react";
import classes from "./LandingPage.module.css";
import Carousel from "../Carousel/Carousel";
class home extends Component {
  render() {
    return (
      <div>
        <Carousel></Carousel>
        <div  className={classes.brcpxa}></div>
      </div>
    );
  }
}

export default home;
