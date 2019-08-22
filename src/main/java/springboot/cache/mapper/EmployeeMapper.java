package springboot.cache.mapper;

import org.apache.ibatis.annotations.*;
import springboot.cache.bean.Employee;

@Mapper
public interface EmployeeMapper {

    @Select("select * from Employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Insert("insert into employee (lastName,email,gender,d_Id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmp(Employee employee);

    @Update("update Employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    public void updateEmp(Employee employee);

    @Delete("delete from Employee where id=#{id}")
    public void deleteEmp(Integer id);

    @Select("select * from Employee where lastName=#{lastName}")
    public Employee getEmpbyLastName(String lastName);
}
