<template>

  <div class="a">
    <div class="b">
     <img src="./../../public/101305024c60175c0537d5e7f2f4d5ef.png" alt="">
    </div>
    <div class="q">
      <h1>ssd论坛</h1>
    </div>
    <div class="c">
      <form id="dd">
        <h2>注册新账号</h2>
        <p>用户名</p >
        <input type="text" placeholder="用户名" v-model="ks.a">
        <p>密码</p >
        <input type="password" placeholder="密码" v-model="ks.b">
        <p>邮箱</p >
        <input type="password" placeholder="" v-model="ks.c">
        <p>{{ ms }}</p >
        <button>点击注册</button>
      </form>
    </div>
    <div class="e"> <img src="../../public/3dc266a64a42fddbf4c007472f91cd0a.png" alt=""></div>
  </div>
</template>
<script>
import a from '../roter/index.js'; 
import b from '../roter/jiekou.js'; 
export default {
  data() {
    return {
  
      ks: {
        a: '', // 用户名
        b: '', // 密码
        c: ''  // 邮箱
      },
      k: '' 
    };
  },
  methods: {
 
    validateForm() {
      this.k = ''; // 清空之前的提示
      if (this.ks.b.length < 6) {
        this.k = "密码不能小于六位";
        return false; // 验证失败
      }
    
      
      if (this.ks.a.length<1) {
        this.k = "用户名为英文或者汉字";
        return false; // 验证失败
      }
      return true; // 验证通过
    },

    async login() {
      // 先执行表单验证，验证不通过则不发送请求
      if (!this.validateForm()) {
        return;
      }

      try {
        // 构造请求参数：将ks.a（用户名）、ks.b（密码）对应到后端User的username和password
        const requestData = {
          username: this.ks.a,
          password: this.ks.b
        };
        
        const res = await b.post('/hello', requestData);
        
        // 修正变量名：response -> res
        if (res.status == 200) {
          alert('登录成功');
          a.push('/p'); // 跳转页面
        } else {
          alert('登录失败：' + res.data);
        }
      } catch (error) {
        console.error('登录失败', error);
        // 错误状态码处理（如400）
        if (error.response) {
          alert('登录失败：' + error.response.data);
        } else {
          alert('请求出错，请重试');
        }
      }
    }
  }
};
</script>

<style>
body {
  line-height: 0;
  background-color: rgba(37, 137, 64, 0.721);
}
.a{
  position: relative;

}
.b{
  width: 10em;
  height: 10em;
  margin-left: 27em;
}
.b img{
  max-width: 100%;
  max-height: 100%;
}
.c {
  display: flex;
  display: inline-block;
  background-color:  rgba(58, 156, 84, 0.721);
  padding: 50px;
  margin-left: 18em;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
  border-radius: 8px;
  margin-top: 1em;
}
#dd{
  display: flex;
  flex-direction: column;
  gap: 2em;
}
#dd input{
  width: 25em;
  height: 2em;
  border-radius: 8px
}
#dd button{
  border-radius: 8px;
  width: 10em;
  height: 2em;
  align-self: center;
  background-color: rgb(39, 150, 94);
  box-shadow:0 4px 8px rgba(0, 0, 0, 0.3);
  color: rgb(183, 204, 223);
  cursor: pointer;
}
.q{
  margin-left: 27em;
}
.q h1{
  font-family: "Arial Black", sans-serif;
  color: aliceblue;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
.e{
  position: absolute;
  left: 62em;
  top: 12em;
}
.e img{
  width: 30em;
  height: 20em;
}
</style>