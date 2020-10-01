
import React,{Component} from 'react'
import axios from 'axios'
import classes from './Eventpage.module.css'
import {withRouter} from 'react-router-dom'
class event extends Component {
    state = {
        
    }
    componentDidMount(){
        // axios.get('')
        // .then(response => {
        //     console.log(response);
        //     this.setState({
        //                     })
        // })
    }
    render(){
        console.log(this.props.match.params.id);
        // var encodeddata = this.state.image;
        return (
            // <div>
            //     <img alt ='not shown'src={`data:image/png;base64,${encodeddata}`}></img>
            // </div>
            <div>
                <div className={classes.Container}>
                <img  src='https://ts-production.imgix.net/images/0ef34210-5acc-4c53-8630-44cb5241aaa0.jpg?auto=compress,format&w=800&h=450'></img>
                
                <div className={classes.Heading}>
                <div className={classes.Eventname} >
                    <h1>Event name</h1>
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
               <i class="far fa-calendar-alt"></i>
               <span>date</span>
               <span>|</span>
               <span>time</span>
               </div>
               <div className={classes.datetime}>
               <i class="fas fa-map-marker-alt"></i>
               <span>venue</span>
               </div>
               <div className={classes.price}>
               <i class="fas fa-rupee-sign"></i>
               <span>Price</span>

               </div>
                </div>
                <div className={classes.discription}>
                <h3>Discription</h3>
                <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metusPellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metusPellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus</p>
                </div>
                <div className={classes.register}>
                 <h3>Organizer's name</h3>
                <h6 className={classes.registerbutton}>Register</h6>
                </div>
                </div>
            </div>
        );
    }

}
export default withRouter(event);