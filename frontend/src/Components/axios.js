import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://2c2598567302.ngrok.io/'
});

export default instance;