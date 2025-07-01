import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../pages/HomePage.vue';
// 기능별 라우터 import
import authRoutes from './auth';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },
    ...authRoutes, // 인증 관련 라우트 추가
  ],
});

export default router;
