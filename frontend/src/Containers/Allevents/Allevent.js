
import React, { Component } from 'react'
import EventCard from '../../Containers/Card/EventCard'
import classes from './Allevent.module.css'
import axios from '../../Components/axios'
class allevent extends Component {
    state={
        events:[1,2,3,4,5,6,7,8]
    }
// componentDidMount(){
//     axios.get('').then(response => {
//         this.setState({events:response.data})
//     });
// }


    render(){
        let event = this.state.events.map(event => <EventCard event={event}></EventCard>)
        return(
            <div className={classes.Show}>
                {event}
            </div>
        );
    }
}
export default allevent