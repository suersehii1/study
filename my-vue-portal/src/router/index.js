// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import Login from "../views/Login.vue";
import MainLayout from "../layouts/MainLayout.vue";
import SelectGrid from "../components/SelectGrid.vue";
import AddGridData from "../components/AddGridData.vue";
import Home from "../components/Home.vue";
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
      { path: "addGridData", name: "AddGridData", component: AddGridData },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
