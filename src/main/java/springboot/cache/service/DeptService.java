package springboot.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import springboot.cache.bean.Department;
import springboot.cache.mapper.DepartmentMapper;

@Service
//@CacheConfig(cacheNames = "dept",cacheManager = "deptRedisCacheManager")
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("redisCacheManager") //标记缓存管理器的id
    @Autowired
    RedisCacheManager redisCacheManager;

//    @Cacheable(cacheNames = "dept")
//    public Department getDeptId(Integer id) {
//        System.out.println("check successful"+id);
//        Department department = departmentMapper.getDeptById(id);
//        return department;
//    }

    //使用缓存管理器得到缓存操作
    public Department getDeptId(Integer id) {
        System.out.println("check successful"+id);
        Department department = departmentMapper.getDeptById(id);

        //获取缓存
        Cache dept = redisCacheManager.getCache("dept");
        //操作缓存
        dept.put("dept:1",department);
        return department;
    }

}
