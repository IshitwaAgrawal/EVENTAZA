import classes from './Footer.module.css';
import React from 'react';
import {Link} from 'react-router-dom'
const footer = () =>{
     return(
        <div className={classes.Footer}>
            <div>
                <h1>LOGO</h1>
            </div>
            <div className={classes.Links}>
                <Link href='#' to=''><i style={{color:'white', fontSize:'20px', padding:'7px'}} className="fab fa-instagram"></i>Instagram</Link>
                <Link href='#' to=''><i style={{color:'white', fontSize:'20px', padding:'7px'}} className="fab fa-facebook-square"></i>facebook</Link>
                <Link href='#' to=''><i style={{color:'white', fontSize:'20px', padding:'7px'}} className="fab fa-twitter"></i>twitter</Link>
            </div>
        </div>
     ) ;

}
export default footer;