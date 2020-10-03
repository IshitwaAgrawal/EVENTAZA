import React, { Component } from 'react';
import classes from './Layout.module.css';
import Header from '../Components/Header/Header'
import { Route, Switch,withRouter} from 'react-router-dom';
import Login from '../Containers/Login/Login'
import Signup from '../Containers/Signup/Signup'
import Home from '../Containers/LandingPage/LandingPage'
import Footer from '../Components/Footer/Footer'
import CreateEvent from '../Containers/CreateEvent/CreateEvent' 
import AllEvent from '../Containers/Allevents/Allevent'
import Eventpage from '../Components/Eventpage/Eventpage'
// import Toolbar from '../../components/Navigation/Toolbar/Toolbar';
// import SideDrawer from '../../components/Navigation/SideDrawer/SideDrawer';

class Layout extends Component {
    state={
            name:'',
            showSideDrawer: false,
            loginstatus:false,
            selectedcity:null
          };
      loginnameHandler = (newname)=>{
        this.setState({
            name:newname,
            loginstatus:true
        })
        console.log(this.state.name);
      }
      SelectedCityHandler = (city) =>{
          this.setState({
            selectedcity:city
          })
          
      }
    toggle =()=>{
        this.props.history.push('/login');
    }
    render () {
        // console.log(this.state.selectedcity)
        let token = localStorage.getItem('jwt');
        return (
           <div className={classes.Layout}>
               <div style={{position:'sticky',top:0,zIndex:5}}>
               <Header name={this.state.name} selectedcity={this.SelectedCityHandler}></Header>
               </div>
               {/* <Signup loginname={this.loginnameHandler}/> */}
               {this.state.name};
               <Switch>
              <Route path='/' exact render={() => <Home city={this.state.selectedcity}></Home>}></Route>
               { token ===null ?
                <Route path='/register'exact render={() => (<Signup />)} ></Route>:
                <Route path='/register' exact render ={ () => (<Home></Home>)}></Route>
               }
                { token ===null ?
                    <Route path='/login' exact render ={ () => (<Login loginname={this.loginnameHandler} ></Login>)}></Route>:<Route path='/login' exact render ={ () => (<Home></Home>)}></Route>}
                <Route path='/createevent' exact render = {() => (<CreateEvent></CreateEvent>)}></Route>
                <Route path='/events/:name' exact render={()=>(<AllEvent></AllEvent>)} ></Route>
                <Route path='/:id' exact render= {() => (<Eventpage></Eventpage>)}></Route>
                </Switch>
                <Footer />
           </div>
        )
    }
}

export default withRouter(Layout);