import { createRouter, createWebHistory } from 'vue-router'
import CalcPage from '@/views/CalcPage.vue'
import Contact from '@/views/ContactForm.vue'

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
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
