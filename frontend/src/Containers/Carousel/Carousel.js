
import React, { Component } from 'react';
import Carousel from 'react-elastic-carousel';
import classes from './Carousel.module.css';
import img from '../../img/img1.jpg'
class carousel extends Component {
  state = {
    items: [
      {id: 1, title: 'item #1'},
      {id: 2, title: 'item #2'},
      {id: 3, title: 'item #3'},
      {id: 4, title: 'item #4'},
      {id: 5, title: 'item #5'}
    ]
  }

  render () {
    const { items } = this.state;
    return (
      <Carousel itemsToShow={2}>
        {items.map(item => <div className={classes.Carousel} key={item.id}><img src={img}></img></div>)}
      </Carousel>
    )
  }
}
export default carousel;