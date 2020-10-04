
import React,{Component} from 'react'
import axios from '../axios'
import classes from './Eventpage.module.css'
import {withRouter} from 'react-router-dom'
import {FaStar} from "react-icons/fa";
class event extends Component {
    state = {
        eventdetails:{

        },
        rating:1,
        seats:1
    }
    componentDidMount(){
        var id=this.props.match.params.id
        // var idwd =id.replace('-','');
        axios.get('events/' + id)
        .then(response => {
            // console.log(response)
            this.setState({
            eventdetails:{...response.data}
            })
        })
    }
    onRegister = () =>{
        // var id=this.props.match.params.id
        // const Userdata={
        //     username : localStorage.getItem('Userdata')
        // }
        // axios.post('http://c98fcd3c130d.ngrok.io/' + id +'/register', Userdata)
        // .then(response => {
        //     console.log(response);
        // })
     
    }

    seatSelectHandler =(event)=>{
            this.setState({
                    seats:event.target.value
            })
    }

    setratingHandler =(givenrating) =>{
        this.setState({
            rating:givenrating
        })
    }
    render(){console.log(this.state.rating)
        // var encodeddata = this.state.image;
        return (
            // <div>
            //     <img alt ='not shown'src={`data:image/png;base64,${encodeddata}`}></img>
            // </div>
            <div>
                <div className={classes.Container}>
                <img alt='img' src='https://ts-production.imgix.net/images/0ef34210-5acc-4c53-8630-44cb5241aaa0.jpg?auto=compress,format&w=800&h=450'></img>
                
                <div className={classes.Heading}>
                <div className={classes.Eventname} >
                    <h1>{this.state.eventdetails.eventName}</h1> &nbsp; &nbsp;<i class="far fa-bookmark"></i>
                </div>
                <div>
                
                   {/* <span>&#9733;</span>
                   <span>&#9733;</span>
                   <span>&#9733;</span>
                   <span>&#9733;</span>
                   <span>&#9733;</span> */}
                   {
                       Array(this.state.eventdetails.avgRating)
                       .fill()
                       .map((_) =>(
                        <span>&#9733;</span>
                       ))
                   }
                   
                </div>
                </div>
                <div>
               <div className={classes.datetime}>
               <i className="far fa-calendar-alt"></i>
               <span>{this.state.eventdetails.startDate}</span>
               <span>|</span>
               <span>{this.state.eventdetails.startTime}</span>
               </div>
               <div className={classes.datetime}>
               <i className="fas fa-map-marker-alt"></i>
               <span>{this.state.eventdetails.eventLocation}</span>
               </div>
               <div className={classes.price}>
               <i className="fas fa-rupee-sign"></i>
        <span>{this.state.eventdetails.price}</span>

               </div>
                </div>
                <div className={classes.discription}>
                <h3>Discription</h3>
                <p>{this.state.eventdetails.eventDescription}</p>
                </div>
                <div className={classes.register}>
                    <div>
                 <h3>Organizer's name : {this.state.eventdetails.organiserName}</h3>
                 <div className={classes.rating}>
                     Rate&nbsp;:&nbsp;
                     {
                         Array(5)
                         .fill()
                         .map((_,i)=>{
                             const ratingvalue=i+1;
                             return(
                             <label>
                                 <input type='radio' name='rating' value={ratingvalue}  onClick={()=>this.setratingHandler(ratingvalue)}></input>
                           <FaStar className='classes.star' color={ratingvalue <= this.state.rating ? "goldenrod" : "grey"}></FaStar>
                            </label>
                             )
                         })
                     }
                 </div>
                 </div>
                    <div>
                    <form>
                        <label htmlFor='seats'>No. of Tickets : &nbsp;</label>
                        <select name='seats' id='seats' onChange={this.seatSelectHandler}> 
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        </select>
                    </form>
                <h6 style={{textAlign:'center'}}className={classes.registerbutton} onClick={this.onRegister}>Register</h6>
                </div>
                </div>
                </div>
            </div>
        );
    }

}
export default withRouter(event);