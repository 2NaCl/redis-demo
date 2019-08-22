package springboot.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.cache.bean.Employee;
import springboot.cache.mapper.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {



    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate; //操作string

    @Autowired
    RedisTemplate redisTemplate; //操作kv都是对象的

    @Autowired
    RedisTemplate<Object,Employee> empRedisTemplate;

    @Test
    public void contextLoads() {

        Employee employee = employeeMapper.getEmpById(1001);
        System.out.println(employee);

    }

    /**
     * String(字符串) List(列表) Set(集合) Hash(散列) ZSet(有序集合)
     *
     * stringRedisTemplate.opsForValue()[String]
     * stringRedisTemplate.opsForHash()[List]
     * stringRedisTemplate.opsForSet()[Set]
     * stringRedisTemplate.opsForHash()[Hash]
     * stringRedisTemplate.opsForZSet()[ZSet]
     */
    @Test
    public void test1(){
        //给redis中保存一个数据
        stringRedisTemplate.opsForValue().append("msg", "Hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }
    @Test
    public void test2(){
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存在redis中
//        redisTemplate.opsForValue().set("emp01",empById);
        //1.将数据以json方式进行保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则
        empRedisTemplate.opsForValue().set("emp01",empById);

    }

}
