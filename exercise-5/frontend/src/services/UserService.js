import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/user',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

export default {
    postNewUser(user) {
        return apiClient.post('/sign-up', user)
    },
    postSignIn(userInfo) {
        return apiClient.post('/login', userInfo)
    }
}
