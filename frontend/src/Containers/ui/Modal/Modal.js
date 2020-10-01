import React from 'react'
// import Bootstrap from'react-bootstrap'
import Modal from 'react-bootstrap/Modal'
import classes from './Modal.module.css'
function MyVerticallyCenteredModal(props) {
  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Select your city
        </Modal.Title>
      </Modal.Header>
      <Modal.Body className={classes.modaldata}>
       <div style={{cursor:'pointer'}} onClick={() =>props.onHide('Delhi')}>delhi</div>
       <div style={{cursor:'pointer'}} onClick={()=>props.onHide('Ghaziabad')}>Ghaziabad</div>
       <div style={{cursor:'pointer'}} onClick={()=>props.onHide('Noida')}>Noida</div>
       <div style={{cursor:'pointer'}} onClick={()=>props.onHide('Bangalore')}>Bangalore</div>
       <div style={{cursor:'pointer'}} onClick={() =>props.onHide('Chennai')}>chennai</div>
       <div style={{cursor:'pointer'}} onClick={() => props.onHide('Mumbai')}>mumbai</div>
      </Modal.Body>
    </Modal>
  );
}

function App(props) {
  const [modalShow, setModalShow] = React.useState(false);
  const[showCity,setCity] = React.useState('Select City');
    const HideHandler = (city) =>{
      setModalShow(false);
      if(city!==undefined){
      setCity(city);
      props.selectedcity(city)
      }
    }
  return (
    <>
      <div  className={classes.Button} onClick={() => setModalShow(true)}>
  <p>{showCity}</p>
              <i className="fas fa-caret-down"></i>
      </div>

      <MyVerticallyCenteredModal
        show={modalShow}
        onHide={HideHandler}
      />
    </>
  );
}

  
  export default App