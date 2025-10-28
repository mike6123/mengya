<template>
  <div class="publish-container">
    <!-- 简化导航：只保留回到首页 -->
    <div class="simple-nav">
      <router-link to="/com" class="home-link">
        🏠 回到首页
      </router-link>
    </div>

    <!-- 发布帖子表单区 -->
    <div class="publish-form-wrapper">
      <h2 class="publish-title">发布新帖子</h2>
      <form class="publish-form" @submit.prevent="handlePublish">
        <!-- 作者字段（可以是登录用户，这里简化为输入框） -->
        <div class="form-item">
          <label class="form-label">作者</label>
          <input 
            type="text" 
            class="form-input" 
            placeholder="请输入你的昵称" 
            v-model="post.author"
            required
          >
          <p class="error-tip" v-if="authorError">{{ authorError }}</p>
        </div>

        <!-- 帖子标题 -->
        <div class="form-item">
          <label class="form-label">帖子标题</label>
          <input 
            type="text" 
            class="form-input title-input" 
            placeholder="请输入帖子标题(不少于5个字)" 
            v-model="post.title"
            required
          >
          <p class="error-tip" v-if="titleError">{{ titleError }}</p>
        </div>

        <!-- 分类选择 -->
        <div class="form-item">
          <label class="form-label">选择分类</label>
          <select class="form-select" v-model="post.category" required>
            <option value="">-- 请选择分类 --</option>
            <option value="生活">生活</option>
            <option value="美食">美食</option>
            <option value="技术">技术</option>
            <option value="旅游">旅游</option>
          </select>
          <p class="error-tip" v-if="categoryError">{{ categoryError }}</p>
        </div>

        <!-- 帖子内容 -->
        <div class="form-item">
          <label class="form-label">帖子内容</label>
          <textarea 
            class="form-textarea" 
            placeholder="请分享你的内容概述(不少于20个字)" 
            v-model="post.content"
            rows="10"
            required
          ></textarea>
              <textarea 
            class="form-textarea" 
            placeholder="请分享你的内容(不少于20个字)" 
            v-model="post.contents"
            rows="20"
            required
          ></textarea>
          <p class="error-tip" v-if="contentError">{{ contentError }}</p>
          <p class="word-count">{{ post.content.length }} / 5000 字</p>
        </div>

        <!-- 提交按钮 -->
        <div class="form-submit">
          <button type="submit" class="publish-btn">发布帖子</button>
          <router-link to="/com" class="cancel-btn">取消</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

// 初始化路由实例（用于发布后跳转）
const router = useRouter();

// 定义帖子对象，与后端实体类 usershiti 字段对应
const post = reactive({
  author: '',    // 作者（与数据库 author 字段对应）
  title: '',     // 标题（与数据库 title 字段对应）
  content: '',   // 内容（与数据库 content 字段对应）
  category: '',
  contents: '' 
    // 分类（与数据库 category 字段对应）
  // time 字段由后端生成，前端无需传递
  // id 是主键，由数据库自增或后端生成，前端无需传递
});

// 错误提示信息
const authorError = ref('');
const titleError = ref('');
const contentError = ref('');
const categoryError = ref('');

// 表单验证
const validateForm = () => {
  let isValid = true;
  
  // 验证作者
  if (!post.author.trim()) {
    authorError.value = '请输入作者昵称';
    isValid = false;
  } else {
    authorError.value = '';
  }

  // 验证标题
  if (!post.title.trim()) {
    titleError.value = '请输入帖子标题';
    isValid = false;
  } else if (post.title.length < 5) {
    titleError.value = '标题长度不少于5个字';
    isValid = false;
  } else {
    titleError.value = '';
  }

  // 验证分类
  if (!post.category) {
    categoryError.value = '请选择帖子分类';
    isValid = false;
  } else {
    categoryError.value = '';
  }

  // 验证内容
  if (!post.content.trim()) {
    contentError.value = '请输入帖子内容';
    isValid = false;
  } else if (post.content.length < 20) {
    contentError.value = '内容长度不少于20个字';
    isValid = false;
  } else if (post.content.length > 5000) {
    contentError.value = '内容长度不能超过5000个字';
    isValid = false;
  } else {
    contentError.value = '';
  }

  return isValid;
};

// 处理发布逻辑
const handlePublish = async () => {
  // 先验证表单
  if (!validateForm()) {
    return;
  }

  try {
    // 发送POST请求到后端（保存到数据库）
    const response = await fetch('http://localhost:8081/posts/save', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(post)  // 将帖子对象转为JSON发送
    });

    if (response.ok) {
      // 发布成功，跳转到首页
      alert('发布成功！');
      router.push('/com');  // 跳转到首页（根据你的实际首页路由调整）
    } else {
      throw new Error('发布失败，请稍后再试');
    }
  } catch (error) {
    console.error('发布错误:', error);
    alert('发布失败：' + error.message);
  }
};
</script>

<style scoped>
/* 保持原有CSS不变 */
/* 整体容器 */
.publish-container {
  min-height: 100vh;
  background-color: #f9fff6; /* 极浅黄绿色背景 */
}

/* 简化导航 */
.simple-nav {
  padding: 1rem 2rem;
  background-color: #f8fff4; /* 浅黄绿导航背景 */
  border-bottom: 1px solid #d4e6b5; /* 淡黄绿边框 */
}

.home-link {
  color: #7a9d27; /* 主黄绿色文字 */
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 500;
  transition: color 0.3s;
}

.home-link:hover {
  color: #9fc93c; /* 亮黄绿色hover效果 */
}

/* 发布表单区 */
.publish-form-wrapper {
  max-width: 800px;
  margin: 2rem auto;
  padding: 0 1rem;
}

.publish-title {
  font-size: 1.5rem;
  color: #7a9d27; /* 主黄绿色标题 */
  margin-bottom: 1.5rem;
  padding-bottom: 0.8rem;
  border-bottom: 1px solid #eaf7d9; /* 淡黄绿分隔线 */
}

.publish-form {
  background-color: #fff;
  border-radius: 8px;
  padding: 2rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.form-item {
  margin-bottom: 1.5rem;
}

.form-label {
  display: block;
  margin-bottom: 0.6rem;
  font-size: 0.95rem;
  color: #5a751e; /* 深一点的黄绿色 */
  font-weight: 500;
}

.form-input, .form-select {
  width: 100%;
  padding: 0.8rem 1rem;
  border: 1px solid #d4e6b5; /* 淡黄绿边框 */
  border-radius: 4px;
  font-size: 0.95rem;
  color: #333;
  transition: border-color 0.3s;
}

.form-input:focus, .form-select:focus {
  outline: none;
  border-color: #9fc93c; /* 亮黄绿色聚焦边框 */
  box-shadow: 0 0 0 2px rgba(159, 201, 60, 0.2);
}

.title-input {
  font-size: 1.1rem;
}

.form-textarea {
  width: 100%;
  padding: 1rem;
  border: 1px solid #d4e6b5;
  border-radius: 4px;
  font-size: 0.95rem;
  color: #333;
  resize: vertical;
  transition: border-color 0.3s;
}

.form-textarea:focus {
  outline: none;
  border-color: #9fc93c;
  box-shadow: 0 0 0 2px rgba(159, 201, 60, 0.2);
}

.error-tip {
  margin-top: 0.5rem;
  font-size: 0.85rem;
  color: #ff4d4f; /* 错误提示红色 */
  margin-bottom: 0;
}

.word-count {
  margin-top: 0.5rem;
  font-size: 0.85rem;
  color: #a9c571; /* 淡黄绿色字数统计 */
  text-align: right;
  margin-bottom: 0;
}

.form-submit {
  display: flex;
  gap: 1rem;
  justify-content: flex-end;
  margin-top: 2rem;
}

.publish-btn {
  background-color: #7a9d27; /* 主黄绿色按钮 */
  color: white;
  border: none;
  padding: 0.8rem 2rem;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

.publish-btn:hover {
  background-color: #9fc93c; /* 亮黄绿色hover */
}

.cancel-btn {
  background-color: transparent;
  color: #5a751e;
  border: 1px solid #d4e6b5;
  padding: 0.8rem 2rem;
  border-radius: 4px;
  font-size: 1rem;
  text-decoration: none;
  transition: background-color 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.cancel-btn:hover {
  background-color: #f0f9e2; /* 浅黄绿色背景hover */
}

/* 响应式调整 */
@media (max-width: 768px) {
  .simple-nav {
    padding: 1rem;
  }

  .publish-form {
    padding: 1.5rem 1rem;
  }

  .form-submit {
    flex-direction: column;
  }

  .publish-btn, .cancel-btn {
    width: 100%;
  }
}
</style>