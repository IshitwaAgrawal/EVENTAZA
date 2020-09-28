import React, { Component } from "react";
import Card from 'react-bootstrap/Card'
import classes from './EventCard.module.css'
class eventcard extends Component {
    ClickHandler=() => {
        alert('clicked');
    }
  render() {
    return (
      <Card className={classes.Card} onClick={this.ClickHandler}>
        <Card.Img variant="top" src="https://images.unsplash.com/photo-1498910265115-9fb541931cd1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1089&q=80" />
        <Card.Body>
          <Card.Title>Card Title</Card.Title>
          <Card.Text>
            Some quick example text to build on the card title and make up the
            bulk of the card's content.
          </Card.Text>
        </Card.Body>
      </Card>
    );
  }
}

export default eventcard
