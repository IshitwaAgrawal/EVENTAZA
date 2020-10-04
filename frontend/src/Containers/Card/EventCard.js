import React, { Component } from "react";
import Card from 'react-bootstrap/Card'
import classes from './EventCard.module.css'
import {Link} from 'react-router-dom'
class eventcard extends Component {
   
  render() {
    const space=' ' 
    return (
      <Link to={'/'+ this.props.event.id} className={classes.link}>
      <Card className={classes.Card} >
        <Card.Img height='200px' variant="top" src="https://images.unsplash.com/photo-1498910265115-9fb541931cd1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1089&q=80" />
        <Card.Body style={{color:'grey'}}>
          <Card.Title className={classes.Title}>{this.props.event.eventName}</Card.Title>
          <div >
          <div >
          <div>
          <Card.Text>
    <i style={{color:'#333545'}} className="fas fa-calendar-alt"></i>{space}{this.props.event.startDate} 
          </Card.Text>
          </div>
          <div>
          <Card.Text>
          <i  style={{color:'#333545'}} className="fas fa-map-marker-alt"></i>{space} {this.props.event.eventLocation}
          </Card.Text>
          </div>
          </div>
          <div>
          <Card.Text>
          <i style={{color:'#333545'}}className="fas fa-rupee-sign"></i>{space} {this.props.event.price}
              
          </Card.Text>
          </div>
          <div className={classes.star}>
          {
                       Array(this.props.event.avgRating)
                       .fill()
                       .map((_) =>(
                        <span>&#9733;</span>
                       ))
                   }
          </div>
          </div>
        </Card.Body>
      </Card>
      </Link>
    );
  }
}

export default eventcard
