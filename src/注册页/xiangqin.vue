<template>
  <div class="detail-container">
    <nav class="blog-nav">
      <div class="nav-logo">
        <router-link to="/" class="logo-link">
          <span class="logo-text">萌芽论坛</span>
        </router-link>
      </div>
      <div class="nav-links">
        <router-link to="/" class="nav-link" :class="{ active: $route.path === '/' }">首页</router-link>
        <router-link to="/blog" class="nav-link">博客</router-link>
        <router-link to="/qa" class="nav-link">问答</router-link>
        <router-link to="/topics" class="nav-link">话题</router-link>
      </div>
    </nav>

    <div class="detail-content">
      <div class="detail-header">
        <h1 class="detail-title">{{ post.title }}</h1>
        <div class="detail-meta">
          <div class="author-info">
            <img src="../../public/图片 (27)(1).jpg" alt="用户头像" class="author-avatar">
            <span class="author-name">{{ post.author }}</span>
          </div>
          <span class="post-time">{{ post.time }}</span>
          <span class="post-category">{{ post.category }}</span>
        </div>
      </div>

      <div class="detail-body">
        <!-- 修正：将 contents 改为 content -->
        <p class="detail-content-text">{{ post.contents }}</p>
        
      </div>

      <div class="back-btn-container">
        <router-link :to="`/com?category=${$route.query.category || '最新'}`" class="back-btn">返回列表</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue';
import { useRoute } from 'vue-router';

// 初始化 post 响应式对象
const post = reactive({
  title: '',
  author: '',
  time: '',
  category: '',
  contents: ''
});

const route = useRoute(); // 声明 route 变量

onMounted(async () => {
  try {
    const response = await fetch(`http://localhost:8081/posts/${route.params.id}`); 
    if (!response.ok) {
      throw new Error(`请求失败: ${response.status}`);
    }
    const data = await response.json();
    
    // 赋值时确保字段名与接口返回一致
    post.title = data.title;
    post.author = data.author;
    post.time = data.time;
    post.category = data.category;
    post.contents = data.contents;
  } catch (error) {
    console.error("获取帖子详情失败:", error);
  }
});
</script>

<style scoped>
/* 整体容器 */
.detail-container {
  min-height: 100vh;
  background-color: #f9fff6; /* 主页同款浅黄绿背景 */
}

/* 导航栏（复用主页样式） */
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

.nav-logo .logo-text {
  font-size: 1.4rem;
  font-weight: bold;
  color: #7a9d27; /* 主色调：深绿黄 */
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
}

.nav-link.active {
  color: #9fc93c; /* 高亮色：亮黄绿 */
  border-bottom: 2px solid #9fc93c;
}

/* 详情内容区 */
.detail-content {
  max-width: 800px;
  margin: 2rem auto;
  padding: 0 1rem;
}

/* 标题区 */
.detail-header {
  background-color: #fff;
  border-radius: 8px;
  padding: 1.5rem;
  margin-bottom: 1.5rem;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.04);
  border-top: 3px solid #7a9d27; /* 顶部绿黄装饰线 */
}

.detail-title {
  color: #333;
  margin-top: 0;
  margin-bottom: 1rem;
  font-size: 1.8rem;
  line-height: 1.4;
}

.detail-meta {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 1rem;
  color: #666;
  font-size: 0.9rem;
}

.author-info {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.author-avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  border: 1px solid #eaf7d9;
}

.author-name {
  color: #7a9d27;
  font-weight: 500;
}

.post-category {
  background-color: #f0f9e2; /* 浅黄绿背景 */
  color: #7a9d27; /* 深绿黄文字 */
  padding: 0.2rem 0.6rem;
  border-radius: 12px;
  font-size: 0.8rem;
}

/* 内容区 */
.detail-body {
  background-color: #fff;
  border-radius: 8px;
  padding: 2rem;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.04);
  line-height: 1.8;
  color: #333;
}

.detail-content-text {
  margin: 0 0 1.5rem 0;
  font-size: 1rem;
}

/* 返回按钮 */
.back-btn-container {
  margin-top: 1.5rem;
  text-align: center;
}

.back-btn {
  display: inline-block;
  background-color: #7a9d27; /* 深绿黄背景 */
  color: white;
  text-decoration: none;
  padding: 0.6rem 1.2rem;
  border-radius: 4px;
  font-size: 0.9rem;
  transition: background-color 0.3s;
}

.back-btn:hover {
  background-color: #9fc93c; /* 亮黄绿 hover效果 */
}

/* 响应式调整 */
@media (max-width: 768px) {
  .detail-title {
    font-size: 1.5rem;
  }
  .detail-body {
    padding: 1.5rem;
  }
}
</style>