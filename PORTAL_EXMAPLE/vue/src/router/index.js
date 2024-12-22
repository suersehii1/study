// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import Login from "../views/Login.vue";
import MainLayout from "../layouts/MainLayout.vue";
import SelectGrid from "../components/biz/grid/SelectGrid.vue";
import SelectGridCombo from "../components/biz/grid/SelectGridCombo.vue";
import Home from "../components/Home.vue";
import UserModify from "@/components/biz/adm/UserModify.vue";
import About from "../components/About.vue";
import Dashboard from "../components/Dashboard.vue";

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/main",
    name: "Main",
    component: MainLayout,
    redirect: "/main/home",
    children: [
      { path: "home", name: "Home", component: Home },
    ],
  },
  {
    path: "/grid",
    name: "Grid",
    component: MainLayout,
    children: [
      { path: "selectGrid", name: "SelectGrid", component: SelectGrid },
      { path: "selectGridCombo", name: "SelectGridCombo", component: SelectGridCombo },
    ],
  },
  {
    path: "/admin",
    component: MainLayout,
    children: [
      { path: "userModify", name: "User수정", component: UserModify },
    ],
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
