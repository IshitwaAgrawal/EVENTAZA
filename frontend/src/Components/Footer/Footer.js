import classes from './Footer.module.css';
import React from 'react';

const footer = () =>{
     return(
        <div className={classes.Footer}>
            <div>
                <h1>LOGO</h1>
            </div>
            <div className={classes.Links}>
                <a href='#'><i style={{color:'white', fontSize:'20px', padding:'7px'}} class="fab fa-instagram"></i>Instagram</a>
                <a href='#'><i style={{color:'white', fontSize:'20px', padding:'7px'}} class="fab fa-facebook-square"></i>facebook</a>
                <a href='#'><i style={{color:'white', fontSize:'20px', padding:'7px'}} class="fab fa-twitter"></i>twitter</a>
            </div>
        </div>
     ) ;

}
export default footer;