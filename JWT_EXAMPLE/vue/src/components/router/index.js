import { createRouter, createWebHistory } from 'vue-router';
import Login from '../views/Login.vue';
import UserSearch from '../views/UserSearch.vue';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
  },
  {
    path: '/user-search',
    name: 'UserSearch',
    component: UserSearch,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
