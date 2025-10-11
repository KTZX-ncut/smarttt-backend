# SmartTT 智能教学管理系统后端

## 📖 项目简介

SmartTT 是一个基于 Spring Boot 的智能教学管理系统后端，采用微服务架构设计，提供完整的教学管理、课程管理、评价评估和考试管理功能。系统支持高校教学全流程管理，包括人员管理、课程资源、教学评价、成绩分析等核心功能。

## 🏗️ 项目架构

本项目采用 Maven 多模块架构，包含以下模块：

```
smarttt-backend/
├── smarttt-admin         # 管理模块 - 系统管理、人员管理、角色权限
├── smarttt-course        # 课程模块 - 课程管理、教学资源、课堂管理
├── smarttt-evaluation    # 评价模块 - 教学评价、能力评估、成绩分析
├── smarttt-exam          # 考试模块 - 试题库管理、作业管理
└── smarttt-common        # 公共模块 - 通用工具类、文件存储
```

### 模块说明

#### 1. smarttt-admin (管理模块)
**端口**: 8080

**主要功能**:
- 用户认证与授权（JWT Token）
- 角色权限管理（RBAC）
- 人员管理（教师、学生）
- 组织架构管理（学校、学院、专业、班级）
- 系统配置管理
- 教学目标管理
- Excel 数据导入导出

**核心技术**:
- Spring Boot 2.6.13
- Spring Data Redis（缓存）
- JWT（java-jwt 3.10.3）
- MyBatis Plus 3.4.2
- EasyExcel 3.1.3（Excel 处理）
- Hutool 5.3.7（工具库）
- MapStruct 1.5.0（对象映射）

#### 2. smarttt-course (课程模块)
**端口**: 8082

**主要功能**:
- 课程信息管理
- 教学日历管理
- 课堂管理（课堂菜单、作业信息、练习列表）
- 教学大纲管理
- 教案管理
- 课程资源管理
- 学生选课管理
- 文件上传下载（MinIO）

**核心技术**:
- Spring Boot 2.7.6
- MyBatis Plus 3.4.2
- Spring Data Redis
- MinIO 8.3.6（对象存储）
- 动态表切换（多学期支持）

#### 3. smarttt-evaluation (评价模块)
**端口**: 8083

**主要功能**:
- 达成度评价
- 能力评估体系
- 知识点评价
- 课程目标评价
- 学生画像分析
- 思政价值评价
- 外部评价管理
- AI 辅助评价
- 成绩分析报告

**核心技术**:
- Spring Boot 2.7.6
- MyBatis Plus 3.4.2
- Spring Data Redis
- Apache Tika 2.9.2（文档解析）
- SQLite JDBC 3.46.1（嵌入式数据库）
- Caffeine 2.9.0（本地缓存）
- 事件驱动架构（Spring Event）
- 线程池异步处理

#### 4. smarttt-exam (考试模块)
**端口**: 未启用（配置中 skip=true）

**主要功能**:
- 试题库管理
- 作业管理
- 题目分类管理

**核心技术**:
- Spring Boot 2.7.6
- MyBatis Plus 3.4.2
- Spring Data Redis

#### 5. smarttt-common (公共模块)

**主要功能**:
- MinIO 文件存储服务
- 通用工具类
- 公共实体类

**核心技术**:
- MinIO 8.3.6
- Hutool 5.8.16
- Lombok

## 🛠️ 技术栈

### 后端框架
- **Spring Boot**: 2.6.13 / 2.7.6
- **Spring Web**: RESTful API
- **Spring Data Redis**: 缓存支持
- **Spring AOP**: 切面编程

### 数据持久化
- **MySQL**: 主数据库（8.x）
- **MyBatis Plus**: 3.4.2（ORM 框架）
- **Druid**: 1.2.20（数据库连接池）

### 安全认证
- **JWT**: 3.10.3（Token 认证）
- **加密工具**: 自定义字段加密

### 文件存储
- **MinIO**: 8.3.6（对象存储服务）
- **Apache POI**: 5.2.4（Excel 处理）
- **EasyExcel**: 3.1.3（Excel 处理）
- **Apache Tika**: 2.9.2（文档解析）

### 工具库
- **Lombok**: 简化 Java 代码
- **Hutool**: 5.3.7 / 5.8.16（Java 工具类库）
- **FastJSON**: 1.2.76（JSON 处理）
- **MapStruct**: 1.5.0（Bean 映射）

### 接口文档
- **Swagger2**: 2.9.2（API 文档）

### 容器化
- **Docker**: 容器化部署
- **Docker Compose**: 服务编排

## 📋 环境要求

- **JDK**: 1.8+
- **Maven**: 3.6+
- **MySQL**: 8.0+
- **Redis**: 6.0+
- **MinIO**: 最新版本（可选）

## 🚀 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd smarttt-backend
```

### 2. 配置数据库

创建 MySQL 数据库：

```sql
CREATE DATABASE smarttt_new CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. 修改配置文件

根据实际环境修改各模块的配置文件：

**smarttt-admin/src/main/resources/application.properties**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smarttt_new?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**smarttt-course/src/main/resources/application.properties**
```properties
# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/smarttt_new
spring.datasource.username=your_username
spring.datasource.password=your_password

# MinIO 配置（可选）
minio.url=http://your-minio-server:9000
minio.accessKey=your_access_key
minio.secretKey=your_secret_key
```

**smarttt-evaluation/src/main/resources/application.properties**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smarttt_new
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. 编译项目

```bash
# 根目录下执行
mvn clean install -DskipTests
```

### 5. 启动服务

#### 方式一：本地启动

分别启动各个模块：

```bash
# 启动管理模块
cd smarttt-admin
mvn spring-boot:run

# 启动课程模块（新终端）
cd smarttt-course
mvn spring-boot:run

# 启动评价模块（新终端）
cd smarttt-evaluation
mvn spring-boot:run
```

#### 方式二：Docker Compose 启动

```bash
# 确保已安装 Docker 和 Docker Compose
docker-compose -f docker-compose.service.yml up -d
```

### 6. 访问服务

- **管理模块**: http://localhost:8080
- **课程模块**: http://localhost:8082
- **评价模块**: http://localhost:8083

### 7. API 文档

启动服务后访问 Swagger 文档：

- **管理模块**: http://localhost:8080/swagger-ui.html
- **课程模块**: http://localhost:8082/swagger-ui.html
- **评价模块**: http://localhost:8083/swagger-ui.html

## 📁 项目结构

```
smarttt-backend/
├── smarttt-admin/                 # 管理模块
│   ├── src/main/java/
│   │   └── com/example/smartttadmin/
│   │       ├── config/            # 配置类（CORS、Swagger、Redis等）
│   │       ├── controller/        # 控制器层
│   │       ├── service/           # 服务层
│   │       ├── mapper/            # MyBatis Mapper
│   │       ├── pojo/              # 实体类
│   │       ├── dto/               # 数据传输对象
│   │       └── Utils/             # 工具类（JWT、加密等）
│   └── src/main/resources/
│       ├── application.properties
│       └── com/example/smartttadmin/mapper/*.xml
├── smarttt-course/                # 课程模块
│   ├── src/main/java/
│   │   └── com/example/smartttcourse/
│   │       ├── config/            # 配置类
│   │       ├── controller/        # 控制器层
│   │       ├── service/           # 服务层
│   │       ├── mapper/            # MyBatis Mapper
│   │       ├── pojo/              # 实体类
│   │       ├── dto/               # 数据传输对象
│   │       ├── factory/           # 工厂模式（文件处理）
│   │       └── Utils/             # 工具类
│   └── src/main/resources/
├── smarttt-evaluation/            # 评价模块
│   ├── src/main/java/
│   │   └── com/example/smartttevaluation/
│   │       ├── config/            # 配置类
│   │       ├── controller/        # 控制器层
│   │       ├── service/           # 服务层
│   │       ├── mapper/            # MyBatis Mapper
│   │       ├── pojo/              # 实体类
│   │       ├── dto/               # 数据传输对象
│   │       ├── vo/                # 视图对象
│   │       ├── event/             # 事件定义
│   │       ├── listener/          # 事件监听器
│   │       ├── schedule/          # 定时任务/调度
│   │       └── Utils/             # 工具类
│   └── src/main/resources/
├── smarttt-exam/                  # 考试模块
│   └── src/main/java/
├── smarttt-common/                # 公共模块
│   └── src/main/java/
│       └── com/example/smartttcommon/
├── docker-compose.service.yml     # Docker Compose 配置
└── pom.xml                        # 父 POM 文件
```

## 🔑 核心功能模块

### 权限认证系统
- 基于 JWT 的 Token 认证
- 自定义注解 `@AuthRequired` 实现接口权限控制
- AOP 切面实现统一权限验证
- 支持多角色、多权限管理

### 多学期支持
- 动态表切换机制
- 基于 MyBatis 拦截器实现表名动态替换
- 支持跨学期数据查询和管理

### 文件管理
- 集成 MinIO 对象存储
- 支持大文件上传（最大 500MB）
- 工厂模式实现多种文件类型处理
- 支持 Excel、PDF、Word 等格式解析

### 数据加密
- 敏感字段自动加密
- 自定义序列化器
- 支持数据库存储加密

### 评价体系
- 多维度评价指标
- 自动计算达成度
- 学生画像分析
- 异步事件驱动的评价计算

## 🔧 开发指南

### 代码规范
- 使用 Lombok 简化代码
- 统一使用 RESTful API 风格
- 统一异常处理
- 统一返回结果封装（Result 类）

### 数据库规范
- 表名采用下划线命名
- 主键统一使用 `id`
- 创建时间、更新时间字段统一命名

### API 规范
- 使用 Swagger 注解标注接口
- 统一使用 `/api/模块名/功能` 路径格式
- 请求参数使用 DTO 封装
- 响应数据使用 VO 封装

## 📝 API 接口示例

### 登录接口

**请求**:
```http
POST /login
Content-Type: application/json

{
  "username": "admin",
  "password": "password"
}
```

**响应**:
```json
{
  "code": 200,
  "msg": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "userInfo": {
      "userId": 1,
      "username": "admin",
      "roles": ["ADMIN"]
    }
  }
}
```

## 🐳 Docker 部署

### 构建镜像

各模块的 Dockerfile 已配置完成，可直接使用 Docker Compose 构建：

```bash
docker-compose -f docker-compose.service.yml build
```

### 启动服务

```bash
docker-compose -f docker-compose.service.yml up -d
```

### 查看日志

```bash
docker-compose -f docker-compose.service.yml logs -f [服务名]
```

### 停止服务

```bash
docker-compose -f docker-compose.service.yml down
```

## 🔍 故障排查

### 常见问题

1. **数据库连接失败**
   - 检查 MySQL 服务是否启动
   - 确认数据库配置信息是否正确
   - 检查数据库是否已创建

2. **Redis 连接失败**
   - 确认 Redis 服务是否启动
   - 检查 Redis 配置信息

3. **MinIO 连接失败**
   - 确认 MinIO 服务是否启动
   - 检查访问密钥配置是否正确

4. **端口占用**
   - 确认 8080、8082、8083 端口未被占用
   - 可在配置文件中修改端口号

## 📊 性能优化

- Redis 缓存热点数据
- Caffeine 本地缓存
- 数据库连接池优化（Druid）
- 异步事件处理
- 线程池配置优化

## 🔐 安全特性

- JWT Token 认证
- 密码加密存储
- 敏感字段加密
- CORS 跨域配置
- SQL 注入防护（MyBatis Plus）
- XSS 攻击防护

## 📄 许可证

本项目仅供学习和研究使用。

## 👥 开发团队

研究生项目 - SmartTT 智能教学管理系统

## 📮 联系方式

如有问题，请提交 Issue 或联系开发团队。

---

**注意**: 
- 请勿将包含敏感信息的配置文件提交到版本控制系统
- 生产环境部署时请修改默认密码和密钥
- 建议使用 HTTPS 协议保证数据传输安全

