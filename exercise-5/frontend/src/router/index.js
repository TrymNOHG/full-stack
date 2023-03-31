import { createRouter, createWebHistory } from 'vue-router'
import CalcPage from '@/views/CalcPage.vue'
import Contact from '@/views/ContactForm.vue'
import SignUp from "@/views/SignUp.vue";
import Login from "@/views/Login.vue";

const routes = [
  {
    path: '/Calculator',
    name: 'Calculator',
    component: CalcPage
  },
  {
    path: '/Contact',
    name: 'Contact',
    component: Contact
  },
  {
    path: '/sign-up',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
