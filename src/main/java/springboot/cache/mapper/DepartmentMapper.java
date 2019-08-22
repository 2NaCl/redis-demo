package springboot.cache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import springboot.cache.bean.Department;

@Mapper
public interface DepartmentMapper {

    @Select("Select * from department where id = #{id}")
    Department getDeptById(Integer id);
}
