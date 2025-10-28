
import { createRouter, createWebHistory } from "vue-router";

const routes=[
   
       {
        path:'/',
        component:()=>import('../注册页/b.vue')
    },
          {
        path:'/com',
        component:()=>import('../注册页/neirong.vue')
    },
                

                 {
        path:'/fabu',
        component:()=>import('../注册页/fabuye.vue')
    },
                  {
        path:'/xq/:id',
        component:()=>import('../注册页/xiangqin.vue')
    }

]
const router=createRouter({
    history:createWebHistory(),
    routes: routes 
})




export default router;