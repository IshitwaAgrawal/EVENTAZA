import React, { Component } from "react";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import Card from "../Card/Card"
import classes from './Cityslideshow.module.css'
import axios from '../../Components/axios';
class cityslideShow extends Component {
  state={
    events:[],
    city:''
  }
  componentDidUpdate(){
      if(this.state.city !== this.props.city){
      axios.get('search/'+this.props.city)
      .then(response =>{
          this.setState({
              events:[...response.data],
              city:this.props.city
          })
      })
    }
  }
  componentDidMount(){
    axios.get('search/'+this.props.city)
    .then(response =>{
        this.setState({
            events:[...response.data]
        })
    })
}
  render() {
    let event = this.state.events.map( event => <Card sstyle={{padding:'20px',width:'80%'}} key={event.id} event={event} ></Card>)
    var settings = {
      focusOnSelect:false,
      arrows: true,
      dots: false,
      infinite: true,
      speed: 500,
      centerMode:false,
      slidesToScroll: 2,
      responsive: [
        { breakpoint: 600, settings: { slidesToShow: 1,arrows:true} },
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

export default cityslideShow;
