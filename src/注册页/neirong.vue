<template>
  <div class="blog-container">
    <!-- 导航栏 -->
    <nav class="blog-nav">
      <!-- 左侧Logo区域 -->
      <div class="nav-logo">
        <router-link to="/" class="logo-link">
          <span class="logo-text">萌芽论坛</span>
        </router-link>
      </div>

      <!-- 中间导航链接 -->
      <div class="nav-links">
        <router-link to="/" class="nav-link" :class="{ active: $route.path === '/com' }">首页</router-link>
        <router-link to="/blog" class="nav-link" :class="{ active: $route.path === '/blog' }">博客</router-link>
        <router-link to="/qa" class="nav-link" :class="{ active: $route.path === '/qa' }">问答</router-link>
        <router-link to="/topics" class="nav-link" :class="{ active: $route.path === '/topics' }">话题</router-link>
        <router-link to="/about" class="nav-link" :class="{ active: $route.path === '/about' }">关于</router-link>
      </div>

      <!-- 右侧功能区 -->
      <div class="nav-actions">
        <!-- 搜索框区域 -->
        <div class="search-container">
          <input 
            type="text" 
            class="search-input" 
            placeholder="搜索文章、话题..." 
            v-model="searchText"
            @keyup.enter="handleSearch"
          >
          <button class="search-btn" @click="handleSearch">🔍</button>
        </div>

        <router-link to="/" class="login-btn">登录</router-link>
        <router-link to="/" class="register-btn">注册</router-link>
      </div>
    </nav>

    <!-- 内容区（左中右布局） -->
    <div class="content-wrapper">
      <!-- 左侧：分类导航 -->
      <div class="content-left">
        <h3 class="category-title">论坛分类</h3>
        <ul class="category-list">
   <router-link to="/com?category=最新">
    <li class="category-item active">最新</li>
  </router-link>
  <router-link to="/com?category=生活">
    <li class="category-item" id="sh">生活</li>
  </router-link>
  <router-link to="/com?category=美食">
    <li class="category-item" id="ms">美食</li>
  </router-link>
  <router-link to="/com?category=技术">
    <li class="category-item" id="js">技术</li>
  </router-link>
  <router-link to="/com?category=旅游">
    <li class="category-item" id="ly">旅游</li>
  </router-link>
        </ul>
      </div>

      <!-- 中间：帖子内容区 -->
      <div class="content-middle">
        <div class="post-header">
          <h3 class="post-title">最新帖子</h3>
          <router-link to="/fabu" class="publish-btn">发布帖子</router-link>
        </div>

        <!-- 帖子列表 -->
        
        <div class="post-list">
          <div class="post-card" v-for="(post, index) in postList" :key="index">
            <div class="post-author">
              <img src="../../public/图片 (27)(1).jpg" alt="用户头像" class="author-avatar">
              <span class="author-name">{{ post.author }}</span>
              <span class="post-time">{{ post.time }}</span>
            </div>
              <router-link :to="`/xq/${post.id}`" style="text-decoration: none; color: inherit;"> <h4 class="post-card-title">{{ post.title }}</h4></router-link>
            <p class="post-card-content">{{ post.content }}</p>
            <div class="post-footer">
              <span class="post-category">{{ post.category }}</span>
              <div class="post-actions">
                <span class="action-item">👍 {{ post.likeCount }}</span>
                <span class="action-item">💬 {{ post.commentCount }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
>
      <!-- 右侧：辅助内容（热门标签+登录提示） -->
      <div class="content-right">
        <!-- 热门标签 -->
        <div class="right-card tag-card">
          <h3 class="right-card-title">热门标签</h3>
          <div class="tag-list">
            <span class="tag-item"># 日常分享</span>
            <span class="tag-item"># 前端开发</span>
            <span class="tag-item"># 美食探店</span>
            <span class="tag-item"># 旅行攻略</span>
            <span class="tag-item"># 生活技巧</span>
            <span class="tag-item"># Java学习</span>
          </div>
        </div>

        <!-- 登录提示 -->
        <div class="right-card login-tip-card">
          <h3 class="right-card-title">欢迎来到萌芽论坛</h3>
          <p class="tip-text">登录后可发布帖子、评论互动</p>
          <router-link to="/" class="tip-login-btn">立即登录</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, watch } from 'vue';
import {useRoute} from 'vue-router';
const route=useRoute();
const postList=reactive([])
const current=ref('最新')
const fetchs=async(category)=>{
  try{
const response = await fetch(`http://localhost:8081/posts/list?category=${category}`);
 if (response.ok) {
  return await response.json();
 }else if(!response.ok){
   throw new Error('后端请求失败');
 }
  }
  catch (error) {
    console.error('获取帖子数据错误:', error);
    return [];  
  }
}
watch(
  ()=>route.query.category,
  async (newval)=>{
    current.value=newval || '最新';
  
  try{
     const data=await fetchs(newval)
     postList.splice(0, postList.length, ...data); 
  }
  catch(error){
    console.error('更新帖子列表失败:', error);
      postList.splice(0, postList.length);  // 清空列表或处理错误
  }
},
{ immediate: true }
)
  
</script>

<style scoped>
/* 整体容器 */
.blog-container {
  min-height: 100vh;
  background-color: #f9fff6; /* 页面背景：极浅黄绿色 */
}

/* 导航栏样式（保留原样式） */
.blog-nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 2rem;
  height: 60px;
  background-color: #f8fff4;
  border-bottom: 1px solid #d4e6b5;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.nav-logo {
  display: flex;
  align-items: center;
}

.logo-link {
  text-decoration: none;
}

.logo-text {
  font-size: 1.4rem;
  font-weight: bold;
  color: #7a9d27;
  letter-spacing: 0.5px;
}

.nav-links {
  display: flex;
  gap: 2.5rem;
}

.nav-link {
  color: #5a751e;
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 500;
  padding: 0.5rem 0;
  position: relative;
  transition: color 0.3s;
}

.nav-link:hover {
  color: #9fc93c;
}

.nav-link.active {
  color: #9fc93c;
}

.nav-link.active::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 2px;
  background-color: #9fc93c;
}

.nav-actions {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.search-container {
  position: relative;
  display: flex;
  align-items: center;
}

.search-input {
  width: 200px;
  padding: 0.4rem 0.6rem 0.4rem 1.8rem;
  border: 1px solid #d4e6b5;
  border-radius: 20px;
  background-color: #fff;
  font-size: 0.9rem;
  color: #5a751e;
  transition: all 0.3s;
}

.search-input:focus {
  width: 250px;
  outline: none;
  border-color: #9fc93c;
  box-shadow: 0 0 0 2px rgba(159, 201, 60, 0.2);
}

.search-input::placeholder {
  color: #a9c571;
}

.search-btn {
  position: absolute;
  left: 0.5rem;
  background: transparent;
  border: none;
  color: #7a9d27;
  font-size: 1rem;
  cursor: pointer;
  padding: 0.2rem;
  transition: color 0.3s;
}

.search-btn:hover {
  color: #9fc93c;
}

.login-btn {
  background-color: transparent;
  color: #5a751e;
  border: 1px solid #7a9d27;
  padding: 0.4rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s;
  text-decoration: none;
}

.login-btn:hover {
  background-color: #f0f9e2;
}

.register-btn {
  background-color: #7a9d27;
  color: white;
  border: none;
  padding: 0.4rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  text-decoration: none;
}

.register-btn:hover {
  background-color: #9fc93c;
}

/* 内容区布局 */
.content-wrapper {
  display: flex;
  gap: 1.5rem;
  padding: 1.5rem 2rem;
  max-width: 1400px;
  margin: 0 auto;
}

/* 左侧分类导航 */
.content-left {
  width: 220px;
  background-color: #fff;
  border-radius: 8px;
  padding: 1rem;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.04);
}

.category-title {
  font-size: 1.1rem;
  color: #7a9d27;
  margin-bottom: 1rem;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid #eaf7d9;
}

.category-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.category-item {
  padding: 0.6rem 0.8rem;
  margin-bottom: 0.5rem;
  border-radius: 4px;
  color: #5a751e;
  cursor: pointer;
  transition: background-color 0.3s;
    
}
.category-list a {
  text-decoration: none !important; /* !important 确保覆盖默认样式 */
}


.category-item:hover {
  background-color: #f0f9e2;
}

.category-item.active {

  font-weight: 500;
}

/* 中间帖子内容区 */
.content-middle {
  flex: 1; /* 占满剩余宽度 */
}

.post-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.post-title {
  font-size: 1.2rem;
  color: #7a9d27;
  margin: 0;
}

.publish-btn {
  background-color: #7a9d27;
  color: white;
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  font-size: 0.9rem;
  transition: background-color 0.3s;
}

.publish-btn:hover {
  background-color: #9fc93c;
}

.post-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.post-card {
  background-color: #fff;
  border-radius: 8px;
  padding: 1.2rem;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.04);
  transition: box-shadow 0.3s;
}

.post-card:hover {
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.06);
}

.post-author {
  display: flex;
  align-items: center;
  gap: 0.8rem;
  margin-bottom: 1rem;
}

.author-avatar {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  object-fit: cover;
  border: 1px solid #eaf7d9;
}

.author-name {
  color: #5a751e;
  font-weight: 500;
  font-size: 0.9rem;
}

.post-time {
  color: #a9c571;
  font-size: 0.8rem;
  margin-left: auto;
}

.post-card-title {
  font-size: 1.1rem;
  color: #333;
  margin: 0 0 0.8rem 0;
  transition: color 0.3s;
}

.post-card-title:hover {
  color: #7a9d27;
}

.post-card-content {
  color: #666;
  font-size: 0.95rem;
  line-height: 1.5;
  margin: 0 0 1rem 0;
  display: -webkit-box;
  -webkit-line-clamp: 2; /* 最多显示2行 */
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.post-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 0.8rem;
  border-top: 1px solid #f9fff6;
}

.post-category {
  background-color: #f0f9e2;
  color: #7a9d27;
  font-size: 0.8rem;
  padding: 0.3rem 0.6rem;
  border-radius: 12px;
}

.post-actions {
  display: flex;
  gap: 1.2rem;
}

.action-item {
  color: #a9c571;
  font-size: 0.85rem;
  cursor: pointer;
  transition: color 0.3s;
}

.action-item:hover {
  color: #7a9d27;
}

/* 右侧辅助内容区 */
.content-right {
  width: 280px;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.right-card {
  background-color: #fff;
  border-radius: 8px;
  padding: 1rem;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.04);
}

.right-card-title {
  font-size: 1rem;
  color: #7a9d27;
  margin-top: 0;
  margin-bottom: 1rem;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid #eaf7d9;
}

/* 热门标签 */
.tag-list {
  display: flex;
  flex-wrap: wrap;
  gap: 0.6rem;
}

.tag-item {
  background-color: #f9fff6;
  color: #7a9d27;
  font-size: 0.85rem;
  padding: 0.4rem 0.8rem;
  border-radius: 16px;
  cursor: pointer;
  transition: all 0.3s;
  border: 1px solid #eaf7d9;
}

.tag-item:hover {
  background-color: #eaf7d9;
  color: #5a751e;
}

/* 登录提示 */
.login-tip-card {
  text-align: center;
  padding: 1.5rem 1rem;
}

.tip-text {
  color: #666;
  font-size: 0.9rem;
  margin: 0 0 1.2rem 0;
  line-height: 1.5;
}

.tip-login-btn {
  display: inline-block;
  background-color: #9fc93c;
  color: white;
  text-decoration: none;
  padding: 0.6rem 1.2rem;
  border-radius: 4px;
  font-size: 0.9rem;
  transition: background-color 0.3s;
}

.tip-login-btn:hover {
  background-color: #7a9d27;
}


/* 响应式调整 */
@media (max-width: 992px) {
  .content-wrapper {
    padding: 1rem;
  }

  .content-right {
    width: 240px;
  }

  .search-input {
    width: 150px;
  }

  .search-input:focus {
    width: 180px;
  }
}

@media (max-width: 768px) {
  .blog-nav {
    padding: 0 1rem;
  }

  .nav-links {
    gap: 1rem;
  }

  .logo-text {
    font-size: 1.2rem;
  }

  .search-container {
    display: none;
  }

  /* 小屏幕隐藏右侧，左侧变窄 */
  .content-wrapper {
    flex-direction: column;
  }

  .content-left {
    width: 100%;
  }

  .content-right {
    width: 100%;
  }

  .category-list {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
  }

  .category-item {
    margin-bottom: 0;
  }
}
</style>