
import React, { Component } from 'react';
import Carousel from 'react-elastic-carousel';
import classes from './Carousel.module.css';
import img from '../../img/img1.jpg'
class carousel extends Component {
  state = {
    items: [
      {id: 1, title: 'item #1',src:'https://in.bmscdn.com/showcaseimage/eventimage/chennai-vs-punjab-15-09-2020-06-19-42-227.jpg'},
      {id: 2, title: 'item #2',src:'https://in.bmscdn.com/showcaseimage/eventimage/chennai-vs-punjab-15-09-2020-06-19-42-227.jpg'},
      {id: 3, title: 'item #3',src:'https://in.bmscdn.com/showcaseimage/eventimage/chennai-vs-punjab-15-09-2020-06-19-42-227.jpg'},
      {id: 4, title: 'item #4',src:'https://in.bmscdn.com/showcaseimage/eventimage/chennai-vs-punjab-15-09-2020-06-19-42-227.jpg'},
      {id: 5, title: 'item #5',src:'https://in.bmscdn.com/showcaseimage/eventimage/chennai-vs-punjab-15-09-2020-06-19-42-227.jpg'}
    ]
  }

  render () {
    const { items } = this.state;
    return (
      <Carousel itemsToShow={1} swipeable={false} draggable={false} >
        {items.map(item => <div className={classes.Carousel} key={item.id}><img height='350px' width='100%' src={item.src}></img></div>)}
      </Carousel>
    )
  }
}
export default carousel;