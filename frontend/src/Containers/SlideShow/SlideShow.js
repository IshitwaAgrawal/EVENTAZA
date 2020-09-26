import React, { Component } from "react";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import classes from './SlideShow.module.css'
import Slider from "react-slick";
import Card from "../Card/Card"
class slideShow extends Component {
  render() {
    var settings = {
      focusOnSelect:false,
      arrows: false,
      dots: false,
      infinite: true,
      speed: 500,
      slidesToShow: 3,
      slidesToScroll: 3,
    };
    return (
      <Slider {...settings}>
        <div>
            <Card></Card>
        </div>
        <div>
          <Card></Card>
        </div>
        <div>
          <Card></Card>
        </div>
        <div>
          <Card></Card>
        </div>
        <div>
          <Card></Card>
        </div>
        <div>
          <Card></Card>
        </div>
      </Slider>
    );
  }
}

export default slideShow;
