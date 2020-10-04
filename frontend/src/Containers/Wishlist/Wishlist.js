import React, { Component } from 'react'
import classes from './Wishlist.module.css'
import {Link} from 'react-router-dom'

class wishlist extends Component{

    render(){
        return(
            <div className ={classes.Wishlist}>
                <h1>Wishlist</h1>
                <div className={classes.showitem}>
                <Link to='/' className={classes.Link} >
                    <div className={classes.Registeredcard}>
                <h4 style={{color:'black'}}>event name</h4>
                <div className={classes.Datetime}>
                  <i className="far fa-calendar-alt"></i>
                  <span>&nbsp;time </span>
                  <span>|</span>
                  <span>&nbsp;date</span>
                </div>
                <span >Price : Rs1400</span><br></br><br></br>
                <span className={classes.Cancel}>Remove from wishlist</span>
            </div>
            </Link>
                </div>
            </div>
        );
    }
}

export default wishlist