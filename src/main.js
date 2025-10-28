
import { createApp } from "vue";
import ap from './注册页/App.vue'
import './style.css'
import router from "./roter/index";
createApp(ap).use(router)
.mount('#app')
