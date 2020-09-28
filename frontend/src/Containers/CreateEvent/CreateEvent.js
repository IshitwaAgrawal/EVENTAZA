import React, { Component } from "react";
import classes from "./CreateEvent.module.css";
import { withRouter } from "react-router-dom";
class createevent extends Component {
  state = {
    username: "",
    eventname: "",
    organizername: "",
    eventlocation: "",
    Price: undefined,
    eventdiscription: "",
    eventdate: "",
    eventtime: "",
    totaltickets:0,
    note: "",
    image: undefined,
    brochure: undefined,
  };

  onChangeHandler = (event) => {
    let name = event.target.name;
    let value = event.target.value;
    this.setState({
      [name]: value,
    });
    console.log(name + ' ' + value)
  };
  submit =(e) =>{
    console.log(this.state);
    e.preventDefault();
    

  }

  render() {
    return (
      <div className={classes.Form}>
        <h4 className={classes.Heading}>Event Details</h4>
        <main>
          <form  onSubmit={this.submit} method='post'>
         <div className={classes.Left}>
         
            <label>Username</label><br></br>
            <input type='text' name='username' value={this.state.username} placeholder='Username'  onChange={this.onChangeHandler} /><br></br>
            <label>Eventname</label><br></br>
            <input type='text' name='eventname' value={this.state.eventname} placeholder='Eventname'  onChange={this.onChangeHandler} /><br></br>
            <label>organizername</label><br></br>
            <input type='text' name='organizername' value={this.state.organizername} placeholder='Organizername'  onChange={this.onChangeHandler} /><br></br>
            <label>Event Location</label><br></br>
            <input type='address' name='eventlocation' value={this.state.eventlocation} placeholder='Event location'   onChange={this.onChangeHandler}/><br></br>
            <label>Event Discription</label><br></br>
            <textarea name="eventdiscription" rows="5" cols="30" value={this.state.eventdiscription} placeholder='Event discription' onChange={this.onChangeHandler}></textarea><br></br>
            <label>Note</label><br></br>
            <input type='text' name='note' value={this.state.note} placeholder='Note'  onChange={this.onChangeHandler} /><br></br>
         </div>
         <div className={classes.Right} >
             <label>Image</label><br></br>
             <input type= 'file'  value={this.state.image} placeholder='Image' name='image' onChange={this.onChangeHandler}/><br></br>
             <label>Brochure</label><br></br>
             <input type= 'file' value={this.state.brochure} placeholder='Brochure' name='brochure' onChange={this.onChangeHandler}/><br></br>
             <label  >Date of event </label><br></br>
             <input type= 'date' value={this.state.eventdate} placeholder='Date' name='eventdate' onChange={this.onChangeHandler}/><br></br>
             <label>Time</label><br></br>
             <input type= 'time' value={this.state.eventtime} placeholder='Time' name='eventtime' onChange={this.onChangeHandler}/><br></br>
             <label>Price</label><br></br>
             <input type= 'number' value={this.state.Price} placeholder='Price' name='price' onChange={this.onChangeHandler}/><br></br>
         <input type='submit' className={classes.Submit} ></input>
         </div>
         </form>
        </main>
      </div>
    );
  }
}
export default withRouter(createevent);
