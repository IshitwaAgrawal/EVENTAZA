import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://aabefbcceae8.ngrok.io/'
});

export default instance;