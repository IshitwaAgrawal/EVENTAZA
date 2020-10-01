import React, { Component } from "react";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import Card from "../Card/Card"
import classes from './SlideShow.module.css'
import axios from 'axios';
class slideShow extends Component {
  state={
    events:[1,2,3,4,5,6]
  }

  // componentDidMount(){
  //   axios.get(' http://a29e202a4e49.ngrok.io/categories/' + this.props.eventtype).then(response => {
  //     this.setState({
        
  //       events:[...response.data]
  //     })
  //     console.log(response.data);
  //     console.log(this.state.events)
  //   });
  // }

  render() {
    let event = this.state.events.map( event => <Card style={{padding:'20px',width:'80%'}} key={event} event={event} ></Card>)
    var settings = {
      focusOnSelect:false,
      arrows: true,
      dots: false,
      infinite: true,
      speed: 500,
      centerMode:true,
      slidesToScroll: 1,
      responsive: [
        { breakpoint: 600, settings: { slidesToShow: 1,arrows:false } },
        { breakpoint: 965, settings: { slidesToShow: 2 } },
        { breakpoint: 1056, settings: { slidesToShow: 3 } },
        { breakpoint: 1440, settings: { slidesToShow: 4 } },
      ]
   };
    return (
      <div style={{width:'90%',margin:'auto'}}>
       <Slider className={classes.SlideShow} {...settings}>

           {event}

       </Slider>
      </div>
    );
  }
}

export default slideShow;
