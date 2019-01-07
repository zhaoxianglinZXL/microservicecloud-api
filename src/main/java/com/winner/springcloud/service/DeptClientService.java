package com.winner.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winner.springcloud.model.DeptInfo;


/**
 * @描述 修改microservicecloud-api工程，根据已有的DeptClientService 接口 
 * 新建
 * 一个实现了FallbackFactory接口类DeptClientServiceFallbackFactory
 * @描述 这个实现了接口的方式获取数据  从feign客户端 发起接口请求 到本类  本类再从注册到微服务中的服务  去查找数据
*/
//本地使用feign的时候使用他
//@FeignClient(value="MICROSERVICECLOUD-DEPT")//通过微服务名去轮询查询 服务者 从而调用相应的方法

@FeignClient(value="MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
//现在测试hystrix 所以要将上面的 注解注释掉  调用api接口时  他的hystrix的所有服务降级指向 DeptClientService.class
//此时我们已经做了服务降级处理，让客户端在服务端不可用时也会获得提示信息
public interface DeptClientService {
	
	/**
	 * 
	 * @描述：教程中人家的controller定义的时restcontroller 不需要加controller的实例路径  而我自己作死没弄那个 弄得时正常的 所以你必须在前面加dept
	 * 他是在微服务根路径下面  在加我们的访问路径
	 */
	//添加
	@RequestMapping(value = "dept/addDept",method = RequestMethod.POST)
	public boolean addDept(DeptInfo dept);
	
	//根据id查询一条部门信息
	@RequestMapping(value="dept/queryDeptById",method = RequestMethod.GET)
	public DeptInfo queryDeptById(int id);

	//查询全部部门信息
	@RequestMapping(value="dept/queryAll",method = RequestMethod.GET)
	public List<DeptInfo> queryAll();
}
