
import React, { Component } from 'react'
import EventCard from '../../Containers/Card/EventCard'
import classes from './Allevent.module.css'
import axios from '../../Components/axios'
import {withRouter} from 'react-router-dom'
class allevent extends Component {
    state={
        events:[],
        loadevents:false,
        currentparams:''
    }
componentDidUpdate(){
    
    this.LoadEvent();
}

componentDidMount(){
    // console.log(this.props.match.params.name)
    this.LoadEvent();
}

    LoadEvent =()=>{
        if(!this.state.loadevents || (this.state.loadevents && this.state.currentparams !== this.props.match.params.name ))
        {
        // console.log(this.props.match.params.name)
    axios.get('categories/'+this.props.match.params.name+'/events').then(response => {
        // console.log(response)
        this.setState({
        events:response.data,
        loadevents:true,
        currentparams:this.props.match.params.name
    })
    });
}
    }

    render(){
        let event = this.state.events.map(event => <EventCard key={event.id} event={event}></EventCard>)
        return(
            <div className={classes.Show}>
                {event}
            </div>
        );
    }
}
export default withRouter(allevent)