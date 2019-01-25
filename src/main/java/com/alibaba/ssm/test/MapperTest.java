package com.alibaba.ssm.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.ssm.bean.Department;
import com.alibaba.ssm.bean.Employee;
import com.alibaba.ssm.dao.DepartmentMapper;
import com.alibaba.ssm.dao.EmployeeMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper mapper;
	
	@Autowired
	SqlSession sqlsession;
	
	@Test
	public void generatorDepartment() {
		Department department = new Department(null,"测试部");
		departmentMapper.insertSelective(department);
		System.out.println();
	}
	@Test
	public void generatorEmployee() {
		EmployeeMapper employeeMapper = sqlsession.getMapper(EmployeeMapper.class);
		for(int i=0;i<2000;i++) {
			String uid = UUID.randomUUID().toString().substring(0, 6);
			if(i%2==0)
				employeeMapper.insertSelective(new Employee(null, uid, "M", uid+"@163.com", 1));
			else
				employeeMapper.insertSelective(new Employee(null, uid, "F", uid+"@qq.com", 2));
				
		}
		
	}
	@Test
	public void selectData() {
		System.out.println(mapper.selectByPrimaryKeyWithDept(2));
	}
	

}
