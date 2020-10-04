import React, { Component } from "react";
import Carousel from "../Carousel/Carousel";
import Slideshow from '../SlideShow/SlideShow'
import {withRouter} from 'react-router-dom'
import Cityslideshow from '../Cityslideshow/Cityslideshow'
class home extends Component {
  state={
    city:null,
    cityslideshow:null
  }
   
  // componentDidUpdate(){
  //   if(this.state.city !== this.props.city)
  //   {
        
  //       this.setState({
  //         cityslideshow:<div> <h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Events in {this.props.city}</h1>
  //         <Slideshow eventtype={'search/' + this.props.city}  ></Slideshow></div>,
  //         city:this.props.city
  //       })
  //   }
  // }

  render() {
    let cityslideshow= true
    if(this.props.city === null)
    {
      cityslideshow=false;
    }
    return (
      <div>
        <Carousel></Carousel>
        {/* <div className={classes.fade}></div> */}
        <h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Trending</h1>
        <Slideshow eventtype='recommendedEvents'></Slideshow><br></br><br></br>
        
        
        { 
        cityslideshow?
        <div><h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Events in {this.props.city}</h1>
        <Cityslideshow city={this.props.city}></Cityslideshow> <br></br><br></br> </div>:
        <div></div>
      
        }
        <h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Past events</h1>
        <Slideshow eventtype='getPastEvents' ></Slideshow><br></br><br></br>
        <h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Upcoming events</h1>
        <Slideshow eventtype='upcomingEvents'></Slideshow><br></br><br></br>
        <h1 style={{color:'white',margin:'20px',fontSize:'30px'}}>Ongoing events</h1>
        <Slideshow eventtype='ongoingEvents'></Slideshow><br></br><br></br>
      </div>
    );
  }
}

export default withRouter(home);
