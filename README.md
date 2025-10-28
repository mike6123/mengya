# 萌芽论坛（Mengya Forum）

## 项目简介
一个基于 Vue（前端）和 [你的后端技术，如 Spring Boot/Node.js]（后端）开发的轻量级论坛系统，支持用户发帖、浏览内容、查看详情等核心功能，适合学习或小型社区使用。

## 技术栈
### 前端
- 框架：Vue 3（或 Vue 2，根据你的实际版本填写）
- 构建工具：Vite（从你之前的文件看是 Vite，可确认后保留）
- 路由：Vue Router（你之前有 `src/roter` 目录，推测用到了）
- 其他：CSS（或你用的样式库，如 Element Plus）

### 后端
- 框架：[例如 Spring Boot（从 demo 目录看像 Java 项目）/Express/Koa 等]
- 语言：[Java/JavaScript/其他]
- 依赖管理：[Maven/Gradle/npm 等，demo 里有 build.gradle，可填 Gradle]

## 功能列表
- ✅ 前端：首页展示、帖子列表、帖子详情、发布帖子页面（从你之前的 `fabuye.vue` 推测）
- ✅ 后端：[例如 接口开发（jiekou.js）、数据交互、请求处理等，根据你的实际功能补充]
- ⚠️ 待开发：[例如 用户登录、评论功能、数据存储（数据库）等，可选填]

## 环境要求
- 前端：Node.js 14+（运行 Vue 项目需要）、npm/yarn
- 后端：[例如 JDK 11+（Spring Boot 常用）、MySQL 8.0（如果用了数据库）]
- 开发工具：VS Code（前端）、[后端工具，如 IntelliJ IDEA/Eclipse]

## 使用步骤
### 1. 克隆项目到本地
```bash
git clone https://github.com/mike6123/mengya.git
cd mengya  # 进入项目根目录
cd 前端目录名（如 frontend，若在根目录则省略这步）

# 安装依赖
npm install

# 启动开发环境（默认端口通常是 3000 或 8080）
npm run dev
# 进入后端 demo 目录
cd demo

# 方式1：用 Gradle 启动（如果是 Spring Boot 项目）
./gradlew bootRun  # Linux/Mac
gradlew.bat bootRun  # Windows

# 方式2：或用 IDE 打开项目，直接运行 DemoApplication.java
项目目录说明
mengya/
├── 前端文件（根目录或 frontend 目录）
│   ├── src/          # 前端源码（组件、路由、样式等）
│   ├── index.html    # 前端入口页面
│   └── package.json  # 前端依赖配置
├── demo/             # 后端项目目录
│   ├── src/          # 后端源码（接口、启动类等）
│   └── build.gradle  # 后端依赖配置
└── README.md         # 项目说明（当前文件）
若后端需要连接数据库，需先在 demo/src/main/resources/application.properties 中配置数据库地址、账号密码。
项目目前为初版，后续可扩展用户认证、评论、分页等功能。
如有问题，可提交 GitHub Issues 或联系开发者。
```bash
git clone https://github.com/mike6123/mengya.git
cd mengya  # 进入项目根目录
