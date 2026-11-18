import { createRouter, createWebHistory } from 'vue-router';
import StudentLogin from '../views/StudentLogin.vue';
import HelloWorld from '../views/HelloWorld.vue';
const routes = [
  {
    path: '/',
    name: 'Login',
    component: StudentLogin
  },
  {
    path: '/helloworld',
    name: 'HelloWorld',
    component: HelloWorld
  }
];
const router = createRouter({
  history: createWebHistory(),
  routes
});
export default router;
