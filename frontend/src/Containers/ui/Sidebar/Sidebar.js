
import React from 'react';
import { slide as Menu } from 'react-burger-menu';
import './Sidebar.css'
import {Link} from 'react-router-dom'
export default props => {
  let username = localStorage.getItem('Userdata')
  return (
    <Menu>
        <h3 className="Usernamedisplay">
        Hey! {username}
      </h3>
      <Link className="menu-item" to={"/dashboard/" + username} >
        Dashboard
      </Link>

      <Link className="menu-item" to={'/wishlist/' + username}>
        Wishlist
      </Link>
      <a className="menu-item" href="/">
        Newsletter
      </a>
      

      <a className="menu-item" href="/">
        Logout
      </a>

      
    </Menu>
  );
};