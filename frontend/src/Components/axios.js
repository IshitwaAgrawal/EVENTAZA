import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://cd3afa361d0d.ngrok.io/'
});

export default instance;