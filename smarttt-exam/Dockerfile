# 基础镜像: Java 8 JRE + Python 3
FROM openjdk:8-jre

# 安装 Python3 和 pip（已优化镜像源）
RUN sed -i 's/deb.debian.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apt/sources.list && \
    sed -i 's/security.debian.org/mirrors.tuna.tsinghua.edu.cn/g' /etc/apt/sources.list && \
    apt-get update && \
    apt-get install -y --no-install-recommends python3 python3-pip && \
    ln -s /usr/bin/python3 /usr/bin/python && \
    # 核心优化：pip 下载加入清华源，加快 pandas 和 openai 的安装速度
    pip3 install --no-cache-dir -i https://pypi.tuna.tsinghua.edu.cn/simple openai pandas openpyxl && \
    rm -rf /var/lib/apt/lists/*

# 指定工作目录
WORKDIR /app

# 复制 jar 包
ADD target/smarttt-exam-0.0.1-SNAPSHOT.jar .

# 复制 Python 脚本和种子题目
COPY src/main/resources/python/ ./src/main/resources/python/

# 暴露端口
EXPOSE 8085

# 启动命令
ENTRYPOINT ["java","-jar","/app/smarttt-exam-0.0.1-SNAPSHOT.jar"]