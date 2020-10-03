
import React,{Component} from 'react'
import axios from '../axios'
import classes from './Eventpage.module.css'
import {withRouter} from 'react-router-dom'
class event extends Component {
    state = {
        eventdetails:{

        }
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
    render(){
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
                    <h1>{this.state.eventdetails.eventName}</h1>
                </div>
                <div>
                   <span>&#9733;</span>
                   <span>&#9733;</span>
                   <span>&#9733;</span>
                   <span>&#9733;</span>
                   <span>&#9733;</span>
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
                 <h3>Organizer's name : {this.state.eventdetails.organiserName}</h3>
                <h6 className={classes.registerbutton} onClick={this.onRegister}>Register</h6>
                </div>
                </div>
            </div>
        );
    }

}
export default withRouter(event);