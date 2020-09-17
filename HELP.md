# Getting Started

# 刷新依赖
gradle build --refresh-dependencies

# 启动项目
gradle bootRun
./gradlew bootRun --args='--server.port=8888 --server.ip=192.168.1.1'
./gradlew bootRun --args='--spring.profiles.active=prod'

#打包
gradle bootJar
java -jar build/libs/springboot-demo-0.0.1-SNAPSHOT.jar --server.port=8888 --server.ip=192.168.1.1

#发布
./gradlew publish

#列举任务
gradle tasks --all

#列举依赖
gradle dependencies
