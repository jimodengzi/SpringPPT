# springboot-demo



1,pom.xm新增mybatis启动器和mysql驱动

```xml
 		<dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.48</version>
        </dependency>
```

2,application.properties中添加数据库连接信息(驱动,url,username,password),可能还要增加tomcat容器配置

```perl
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

server.port=8081

```

3,mysql数据库test新增表格

![test.t_user](C:\Users\lpd\Pictures\springboot\sql的uml.png)

```sql
create table t_user
(
    id    int auto_increment comment '主键id'
        primary key,
    name  varchar(50) not null comment '姓名',
    email varchar(50) null comment '邮箱'
);

INSERT INTO test.t_user (id, name, email) VALUES (1, '张三', '');
INSERT INTO test.t_user (id, name, email) VALUES (2, '李四', null);
INSERT INTO test.t_user (id, name, email) VALUES (3, 'wangwu', null);

```

4,创建实体类entity/User

```java
import java.io.Serializable;

public class User implements Serializable {
    private Integer id ;//int auto_increment comment '主键id' primary key,
    private String  name  ; //varchar(50) not null comment '姓名',
    private String  email  ;//varchar(50) null comment '邮箱'
  // 补充setter/getter方法
```

5,dao层UserDao

```java
@Mapper
public interface UserDao{
  @Select("SELECT * FROM t_user")
    List<Map<String,Object>> selectUser();
  @Select("SELECT * FROM t_user tu where tu.id = #{id}")
   Map<String,Object> selectUserById(Integer id);	
  @Insert("INSERT INTO t_user (name ,email) VALUES(#{name},#{email})")
  Integer insertUser(User user);
  @Update("UPDATE t_user SET name=#{name} ,email=#{email} WHERE id = #{id}")
  Integer updateUser(User user);
  @Delete("DELETE FROM t_user WHERE id = #{id}")
 Integer deleteUser(Integer id); 
  
}
```

6,service层UserService

```java
@Service
@Transactional
public class UserService{
  @Autowired
  private UserDao userDao;
  List<Map<String,Object>> selectUser();
  Map<String,Object> selectUserById(Integer id);	
  String insertUser(User user);
  String updateUser(User user);
  String deleteUser(Integer id); 
}
```

7,controller层UserController

```java
@RestController
/*@RequestMapping("/api/user")*/
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUser")
    public List<Map<String, Object>> findUser() {
        return userService.findUser();
    }

    @GetMapping("/findUserById")
    public Map<String, Object> findUserById(Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/addUser")
    public String addUser(User user) {
        return userService.addUser(user);
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/delUser")
    public String delUser(Integer id) {
        return userService.delUser(id);
    }

}

```

8,启动类

```java
@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudThymeleafApplication.class, args);
    }
}
```

9,测试url

```java
http://localhost:8081/findUser
http://localhost:8081/findUserById?id=2
http://localhost:8081/addUser?name=攀登&email=123@qq.com
http://localhost:8081/updateUser?name=肖战&id=4
http://localhost:8081/updateUser?name=肖战123&id=4

```

PPT开始

springboot


培训
主讲人:李攀登
时间:2020年5月7日

内容概要
一,SpringBoot入门
二,SpringBoot配置
三,SpringBoot与日志
四,SpringBoot之CRUD

一, SpringBoot基础

简介
Helloworld
原理分析

一,简介
         SpringBoot用来简化spring开发,约定大于配置,去繁从简,just run就可以创建一个独立的,产品级别的应用.
背景:
      J2EE笨重的开发\繁多的配置\低下的开发效率\复杂的部署流程\第三方技术集成难度大.
解决:
    “spring全家桶”时代.
     springboot →J2EE一站式解决方案 
     springcloud →分布式整体解决方案

优点:
  - 快速创建独立运行的spring项目以及与主流框架的集成
  - 使用嵌入式的servlet容器,应用无需打成war包
  - starter自动依赖与版本控制
  - 大量的自动配置,简化开发,也可修改默认值
  - 无需配置xml,无代码生成,开箱即用
  - 准生产环境的运行时应用监控
  - 与云计算的天然集成
    
单体应用
传统web应用框架
brower <--> apache <--> (tomcat{war{各种服务模块}}) <-->databases(mysql)

simple to develop/test/deploy/scale(水平扩展)

微服务和单体应用比较

一个单体应用程序把所有的功能都放在一个单一进程中,将整个应用复制到多个服务器上进行扩展;
一个微服务架构把每个功能元素放进一个独立的服务中,并且通过跨服务器将这些服务进行扩展,只有在需要时才复制.

你必须掌握以下内容:
  - spring框架的使用经验
  - 熟练使用maven进行项目构建和依赖管理
  - 熟练使用eclipse或者idea

环境约束
  - jdk1.8
  - maven3.x
  - IntelliJ IDEA 
  - SpringBoot 1.5.9.RELEASE以上
  
  二,HelloWorld

1,创建maven项目
2,引入starters
3,创建主程序
4,启动运行
 <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
    </parent>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>


@Controller
@EnableAutoConfiguration
Public class SampleController{
	
  @RequestMapping(“/”)
  @ResponseBody
  String home(){
    return “Hello World!”;
  } 
  public static void main(String[] args) throws Exception {
   SpringApplication.run(SampleController.class,args);
  }
}



三,Hello World探究


1,starters
  springboot为我们提供了简化企业及开发的绝大多数场景的starter pom(启动器),只要引入了相应场景的starter pom,相关技术的绝大部分配置将会消除(自动配置),从而简化我们开发.业务中我们就会用到springboot为我们自动配置的bean

  这些starters几乎涵盖了所有的常用场景,springboot对这些场景的依赖的jar也做了严格的测试与版本控制.我们不必担心jar的版本匹配度问题.
  
 2,入口类和@SpringBootApplication

1,程序从main方法开始运行
2,使用SpringApplication.run()加载 主程序类
3,主程序类需要标注@SpringBootApplication
4,@EnableAutoConfiguration是核心注解
5,@Import导入所有的自动配置场景
6,@AutoConfigurationPackage定义默认的包扫描规则
7,程序启动扫描加载主程序类所在的包以及下面的所有子包的组件.

 3,自动配置

1,xxxAutoConfiguration
  - SpringBoot中存在大量的这种类,这些类的作用就是帮我们进行自动配置
  - 它会将这个场景需要的所有组件都注册到容器中,并配置好
  - 它们在类路径下的META-INF/spring.factories文件中
  - spring-boot-autoConfigure-1.5.9.RELEASE.jar中包含了所有场景的自动配置类代码
  - 这些自动配置类是SpringBoot进行自动配置的精髓

二,SpringBoot配置

配置文件\加载顺序\配置原理


1,配置文件

-    SpringBoot使用一个全局的配置文件，配置文件名是固定的；
    •application.properties
    •application.yml

-    配置文件放在src/main/resources/目录或者类路径/config下

.yml是YAML(YAML Ain`t Markup Language)语言的文件,以数据为中心,比json,xml等更适合做配置文件

全局配置文件可以对一些默认配置值进行修改

 

1、 YAML基本语法

– 使用缩进表示层级关系

– 缩进时不允许使用Tab键，只允许使用空格。

– 缩进的空格数目不重要，只要相同层级的元素左侧对齐即可

– 大小写敏感

2、 YAML 支持的三种数据结构

– 对象：键值对的集合

– 数组：一组按次序排列的值

– 字面量：单个的、不可再分的


OYAML常用写法

– 对象（Map）

• 对象的一组键值对，使用冒号分隔。如：username:

admin

• 冒号后面跟空格来分开键值；

• {k: v}是行内写法

YAML

hero:

  hp:

88

  sp:

8

  level: 4

guy:

  hp:

22

  sp:

2

  level: 2

行内写法

  {'hero':

{'hp':

88,'sp': 8,'level': 4},'guy': {'hp':

22,'sp': 2,'level': 2}}  



数组– 一组连词线（-）开头的行，构成一个数组，[]为行内写法– 数组，对象可以组合使用

- 复合结构。以上写法的任意组合都是可以– 字面量	• 数字、字符串、布尔、日期	• 字符串		– 默认不使用引号		– 可以使用单引号或者双引号，单引号会转义特殊字符,双引号相当于(JSON.stringify(str))		– 字符串可以写成多行，从第二行开始，必须有一个单空格缩进。换行符会被转为空格。– 文档• 多个文档用 - - - 隔开
参考url: http://www.ruanyifeng.com/blog/2016/07/yaml.html


Pets{name='zhangsan \n', username='张三', age=18, pet={name=小狗, gender=male}, animal=[dog, cat, fish],interests=[足球, 篮球], friends=[[zhangsan is my best friend, lisi]], childs=[{age=18, name=xiaozhang}, {pets={1=b, 0=a}, name=xiaoli}, {age=18, name=lisi}]}

3,配置文件值注入
@Value和@ConfigurationProperties为属性注值对比

@Value和@ConfigurationProperties对比项:功能/松散绑定（松散语法）/SpEL/JSR303数据校验/复杂类型封装
配置文件yml还是properties他们都能获取到值；
如果说，我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value；
如果说，我们专门编写了一个javaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties；


属性名匹配规则（Relaxed binding）– person.firstName：使用标准方式– person.first-name：大写用-– person.first_name：大写用_– PERSON_FIRST_NAME：推荐系统属性使用这种写法


@ConfigurationProperties– 与@Bean结合为属性赋值– 与@PropertySource（只能用于properties文件）结合读取指定文件• @ConfigurationProperties Validation– 支持JSR303进行配置文件值校验；


@ImportResource读取外部配置文件

四、配置文件占位符• RandomValuePropertySource：配置文件中可以使用随机数${random.value}、 ${random.int}、 ${random.long}${random.int(10)}、 ${random.int[1024,65536]}• 属性配置占位符– 可以在配置文件中引用前面配置过的属性（优先级前面配置过的这里都能用）。– ${app.name:默认值}来指定找不到属性时的默认值


4,配置文件占位符

RandomValuePropertySource：配置文件中可以使用随机数${random.value}、 ${random.int}、 ${random.long}${random.int(10)}、 ${random.int[1024,65536]}• 属性配置占位符


– 可以在配置文件中引用前面配置过的属性（优先级前面配置过的这里都能用）。– ${app.name:默认值}来指定找不到属性时的默认值
5, Profile

Profile是Spring对不同环境提供不同配置功能的支持，可以通过激活、指定参数等方式快速切换环境1、多profile文件形式：– 格式：application-{profile}.properties/yml：• application-dev.properties、 application-prod.properties2、多profile文档块模式：下一页PPT有展示3、激活方式：– 命令行 --spring.profiles.active=dev– 配置文件 spring.profiles.active=dev– jvm参数 –Dspring.profiles.active=dev


多profile文档块模式

---

6,配置文件加载位置

spring boot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文件– file:./config/– file:./– classpath:/config/– classpath:/– 以上是按照优先级从高到低的顺序，所有位置的文件都会被加载，高优先级配置内容会覆盖低优先级配置内容。– 我们也可以通过配置spring.config.location来改变默认配置


7,外部配置加载顺序

Spring Boot 支持多种外部配置方式这些方式优先级如下：https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-external-config1. 命令行参数2. 来自java:comp/env的JNDI属性3. Java系统属性（System.getProperties()）4. 操作系统环境变量5. RandomValuePropertySource配置的random.*属性值6. jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件7. jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件8. jar包外部的application.properties或application.yml(不带spring.profile)配置文件9. jar包内部的application.properties或application.yml(不带spring.profile)配置文件10. @Configuration注解类上的@PropertySource11. 通过SpringApplication.setDefaultProperties指定的默认属性



8,自动配置原理

1、可以查看HttpEncodingAutoConfiguration2、通用模式– xxxAutoConfiguration：自动配置类– xxxProperties：属性配置类– yml/properties文件中能配置的值就来源于[属性配置类]3、几个重要注解– @Bean– @Conditional4、 --debug=true查看详细的自动配置报告


@Conditional总结
Spring注解版原生的@Conditional作用
作用：必须是@Conditional指定的条件成立，才给容器中添加组件，配置类里面的所有内容才生效；自动配置类必须在一定的条件下才能生效；
我们怎么知道哪些自动配置类生效?
方法:
        我们可以通过启用 debug=true属性；来让控制台打印自动配置报告


这样我们就可以很方便的知道哪些自动配置类生效



三、 Spring Boot与日志


日志框架、日志配置


1,日志框架

市场上存在非常多的日志框架。 JUL（java.util.logging），JCL（Apache Commons Logging），Log4j，Log4j2，Logback、 SLF4j、 jboss-logging等。Spring Boot在框架内容部使用JCL，spring-boot-starter-logging采用了slf4j+logback的形式，Spring Boot也能自动适配（jul、 log4j2、 logback） 并简化配置


2,默认配置

1、全局常规设置（格式、路径、级别）2、指定日志配置文件位置



