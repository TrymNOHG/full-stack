import { createRouter, createWebHistory } from 'vue-router'
import CalcPage from '@/views/CalcPage.vue'

const routes = [
  {
    path: '/',
    name: 'Calculator',
    component: CalcPage
  },
  {
    // path: '/',
    // name: 'Contact',
    // component:
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
