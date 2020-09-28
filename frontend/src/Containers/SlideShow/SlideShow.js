import React, { Component } from "react";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import Card from "../Card/Card"
import classes from './SlideShow.module.css'
import axios from '../../Components/axios';
class slideShow extends Component {
  state={
    events:[]
  }

  componentDidMount(){
    axios.get('categories/' + this.props.eventtype).then(response => {
      this.setState({
        events:response.data
      })
      console.log(response);
      console.log(this.state.events)
    });
  }

  render() {
    let event = this.state.events.map( event => <Card event={event} ></Card>)
    var settings = {
      focusOnSelect:false,
      arrows: false,
      dots: false,
      infinite: true,
      speed: 500,
      slidesToShow: 2,
      slidesToScroll: 3,
    };
    return (
      <Slider className={classes.SlideShow} {...settings}>
          {event}
      </Slider>
    );
  }
}

export default slideShow;
