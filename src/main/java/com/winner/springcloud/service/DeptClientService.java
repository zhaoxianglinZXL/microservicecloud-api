package com.winner.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winner.springcloud.model.DeptInfo;

@FeignClient(value="MICROSERVICECLOUD-DEPT")//通过微服务名去轮询查询 服务者 从而调用相应的方法
public interface DeptClientService {
	
	//添加
	@RequestMapping(value = "/addDept",method = RequestMethod.POST)
	public boolean addDept(DeptInfo dept);
	
	//根据id查询一条部门信息
	@RequestMapping(value="/queryDeptById",method = RequestMethod.GET)
	public DeptInfo queryDeptById(int id);

	//查询全部部门信息
	@RequestMapping(value="/queryAll",method = RequestMethod.GET)
	public List<DeptInfo> queryAll();
}
