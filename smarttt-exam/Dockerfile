# 基础镜像: Java 8 JRE + Python 3
FROM openjdk:8-jre

# 安装 Python3 和 pip
RUN apt-get update && \
    apt-get install -y --no-install-recommends python3 python3-pip && \
    ln -s /usr/bin/python3 /usr/bin/python && \
    pip3 install --no-cache-dir openai pandas openpyxl && \
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
