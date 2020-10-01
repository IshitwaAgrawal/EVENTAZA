
import React from 'react';
import { slide as Menu } from 'react-burger-menu';
import './Sidebar.css'
export default props => {
  return (
    <Menu>
        <h3 className="Usernamedisplay">
        Hey! Name 
      </h3>
      <a className="menu-item" href="/">
        Dashboard
      </a>

      <a className="menu-item" href="/">
        Wishlist
      </a>
      <a className="menu-item" href="/">
        Newsletter
      </a>
      

      <a className="menu-item" href="/">
        Logout
      </a>

      
    </Menu>
  );
};