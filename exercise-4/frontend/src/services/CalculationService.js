import axios from 'axios'

const apiClient = axios.create({
    baseURL: 'http://localhost:8084',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

export default {
    postFeedback(expression) {
        return apiClient.post('/calculate', expression)
    }
}
