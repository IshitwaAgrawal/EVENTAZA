import React, { Component } from "react";
import Carousel from "../Carousel/Carousel";
import Slideshow from '../SlideShow/SlideShow'
import {withRouter} from 'react-router-dom'
class home extends Component {
  render() {
    return (
      <div>
        <Carousel></Carousel>
        {/* <div className={classes.fade}></div> */}
        <h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Trending</h1>
        <Slideshow eventtype='getAllEvents'></Slideshow><br></br><br></br>
        <h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Past events</h1>
        <Slideshow eventtype='getPastEvents' ></Slideshow><br></br><br></br>
        <h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Upcoming events</h1>
        <Slideshow eventtype='getAllEvents'></Slideshow><br></br><br></br>
      </div>
    );
  }
}

export default withRouter(home);
