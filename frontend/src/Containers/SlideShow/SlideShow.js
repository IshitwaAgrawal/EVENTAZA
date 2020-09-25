import React, { Component } from "react";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import classes from "./SlideShow.module.css";

class slideShow extends Component {
  render() {
    var settings = {
      arrows: false,
      dots: false,
      infinite: true,
      speed: 500,
      slidesToShow: 4,
      slidesToScroll: 3,
    };
    return (
      <Slider {...settings}>
        <div>
          <div className={classes.cards}>
            <h3>1</h3>
          </div>
        </div>
        <div>
          <div className={classes.cards}>
            <h3>2</h3>
          </div>
        </div>
        <div>
          <div className={classes.cards}>
            <h3>3</h3>
          </div>
        </div>
        <div>
          <div className={classes.cards}>
            <h3>4</h3>
          </div>
        </div>
        <div>
          <div className={classes.cards}>
            <h3>5</h3>
          </div>
        </div>
        <div>
          <div className={classes.cards}>
            <h3>6</h3>
          </div>
        </div>
      </Slider>
    );
  }
}

export default slideShow;
