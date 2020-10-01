
import React, { Component } from 'react';
import Carousel from 'react-elastic-carousel';
import classes from './Carousel.module.css';
class carousel extends Component {
  state = {
    items: [
      {id: 1, title: 'item #1',src:'https://in.bmscdn.com/showcaseimage/eventimage/chennai-vs-punjab-15-09-2020-06-19-42-227.jpg'},
      {id: 2, title: 'item #2',src:'https://in.bmscdn.com/showcaseimage/eventimage/hindustan-times-codeathon-13-09-2020-06-56-08-573.jpg'},
      {id: 3, title: 'item #3',src:'https://in.bmscdn.com/showcaseimage/eventimage/ekam-satt-academy--djembe-05-09-2020-09-26-53-702.jpg'},
      {id: 4, title: 'item #4',src:'https://in.bmscdn.com/showcaseimage/eventimage/7-life-principles-for-success-by-himanshu-21-09-2020-11-46-21-350.jpg'},
      {id: 5, title: 'item #5',src:'https://in.bmscdn.com/showcaseimage/eventimage/life-is-a-circus-23-09-2020-11-27-15-859.jpg'}
    ]
  }

  render () {
    const { items } = this.state;
    return (
      <div  >
      <Carousel  itemsToShow={1} enableAutoPlay= {true} autoPlaySpeed={5500}  >
        {items.map(item => <div className={classes.Carousel} key={item.id}><img className={classes.brcpxa} height='100%' width='100%' alt='' src={item.src}></img></div>)}
      </Carousel>
      </div>
    )
  }
}
export default carousel;