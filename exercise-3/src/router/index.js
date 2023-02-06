import { createRouter, createWebHistory } from 'vue-router'
import CalcPage from '@/views/CalcPage.vue'
import Contact from '@/views/ContactForm.vue'

const routes = [
  {
    path: '/',
    name: 'Calculator',
    component: CalcPage
  },
  {
    path: '/',
    name: 'Contact',
    component: Contact
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
