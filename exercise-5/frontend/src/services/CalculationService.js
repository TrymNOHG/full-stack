import axios from 'axios'
import SessionToken from "@/features/SessionToken.js"

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/calculator',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

export default {
    async postExpression(expression) {
        return apiClient.post('/calculate', expression, {
            headers: {
                Authorization: `Bearer ${await SessionToken()}`
            }
        })
    },
    async getLog(user) {
        return apiClient.post('/log/load', user, {
            headers: {
                Authorization: `Bearer ${await SessionToken()}`
            }
        });
    }
}
