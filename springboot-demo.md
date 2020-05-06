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

