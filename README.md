Spring-cloud-demo
-----------------

这个是我自己学习使用的spring-cloud-demo工程,在学习阶段记录一些学习心得，也让自己有动力学习下去!

-----
现阶段已使用的
1. eureka
2. zuul
3. fegin的app1和app2
4. 日志跟踪zipkin
5. client模拟登录 2018.11.11
6. Oauth2鉴权 2018.11.11


### 项目结构
```
├─spring-cloud-demo
│  │
│  ├─app1--------------后台APP1,Feign示例
│  │
│  ├─app2--------------后台APP2,Feign示例
│  │
│  ├─client------------模拟客户端，登录接口
│  │
│  ├─eureka------------服务注册中心
│  │
│  ├─zipkin------------链路跟踪
│  │
│  └─zuul--------------Oauth2鉴权,路由
│
```
----

之后要做的事
------

~~1. 加入oauth~~
2. 加入hystrix
3. 集成docker