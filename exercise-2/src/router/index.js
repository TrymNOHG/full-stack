import { createRouter, createWebHistory } from 'vue-router'
import CalcPage from "@/views/CalcPage.vue";

const routes = [
    {
        path: '/',
        name: 'Calculator',
        component: CalcPage
    },
    // {
    //     path: '/event/:id',
    //     name: 'EventDetails',
    //     component: EventDetails
    // },
    // {
    //     path: '/about',
    //     name: 'About',
    //     component: About
    // }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
