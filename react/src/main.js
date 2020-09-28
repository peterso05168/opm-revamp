import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueRouter from 'vue-router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.use(VueRouter)

// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

import Home from "./components/Home";
import AboutUs from "./components/AboutUs";
import Products from "./components/Products";
import Services from "./components/Services";
import Solutions from "./components/Solutions";
import JoinUs from "./components/JoinUs";
import ContactUs from "./components/ContactUs";
// 0. If using a module system (e.g. via vue-cli), import Vue and VueRouter
// and then call `Vue.use(VueRouter)`.

// 1. Define route components.
// These can be imported from other files
const Foo = { template: '<div>foo</div>' }
const Bar = { template: '<div>bar</div>' }

// 2. Define some routes
// Each route should map to a component. The "component" can
// either be an actual component constructor created via
// `Vue.extend()`, or just a component options object.
// We'll talk about nested routes later.
const routes = [
  { path: '', redirect: '/Home' },
  { path: '/Home', component: Home },
  { path: '/AboutUs', component: AboutUs },
  { path: '/Products', component: Products },
  { path: '/Services', component: Services },
  { path: '/Solutions', component: Solutions },
  { path: '/JoinUs', component: JoinUs },
  { path: '/ContactUs', component: ContactUs },
]

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = new VueRouter({
  mode: 'history',
  scrollBehavior(to, from, savedPosition) {
    return { x: 0, y: 0 }
  },
  routes// short for `routes: routes`
})

// 4. Create and mount the root instance.
// Make sure to inject the router with the router option to make the
// whole app router-aware.


new Vue({
  render: h => h(App),
  router
}).$mount('#app')
