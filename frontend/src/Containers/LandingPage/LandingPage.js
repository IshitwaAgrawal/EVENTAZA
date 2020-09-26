import React, { Component } from "react";
import Carousel from "../Carousel/Carousel";
import Slideshow from '../SlideShow/SlideShow'

class home extends Component {
  render() {
    return (
      <div>
        <Carousel></Carousel>
        {/* <div className={classes.fade}></div> */}
        <h1 style={{color:'white'}}>Trending</h1>
        <Slideshow></Slideshow><br></br><br></br>
        <h1 style={{color:'white'}}>Past events</h1>
        <Slideshow></Slideshow><br></br><br></br>
        <h1 style={{color:'white'}}>Upcoming events</h1>
        <Slideshow></Slideshow><br></br><br></br>
      </div>
    );
  }
}

export default home;
