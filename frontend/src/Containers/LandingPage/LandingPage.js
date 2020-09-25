import React, { Component } from "react";
import classes from "./LandingPage.module.css";
import Carousel from "../Carousel/Carousel";
import Slideshow from '../SlideShow/SlideShow'
class home extends Component {
  render() {
    return (
      <div>
        <Carousel></Carousel>
        <div className={classes.fade}></div>
        <Slideshow></Slideshow><br></br>
        <Slideshow></Slideshow><br></br>
        <Slideshow></Slideshow><br></br>
        <Slideshow></Slideshow><br></br>
      </div>
    );
  }
}

export default home;
