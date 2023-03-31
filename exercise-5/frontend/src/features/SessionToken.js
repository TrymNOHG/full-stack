import router from "@/router/index";
import store from '@/store'


export default async function sessionToken () {
    const sessionToken = store.state.sessionToken
    if (sessionToken === null) {
        alert("Log in to access your profile!")
        await router.push("/login")
        throw new Error("Session token cannot be null. Login in again.")
    }

    return sessionToken
}
