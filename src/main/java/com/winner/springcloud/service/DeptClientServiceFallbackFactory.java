package com.winner.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.winner.springcloud.model.DeptInfo;

import feign.hystrix.FallbackFactory;


@Component  //不要忘记添加！不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	//针对于 接口的 公共的错误返回  避免了 hystrix在每个方法上都要写一个fallback
	//将所有的熔断机制放在这里面
	@Override
	public DeptClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		
		return new DeptClientService() {
			
			@Override
			public DeptInfo queryDeptById(int id) {
				// TODO Auto-generated method stub
				System.out.println("进来没有全局的配置Hystrix");
				DeptInfo dept = new DeptInfo();
				dept.setDeptno(id);
				dept.setDname("该ID:"+id+"没有对应的信息，Consumer客户吨提供的降级信息！！此刻Provider已经关闭在api中测试的结果");
				dept.setDb_source("no this database in MySQL");
				return dept;
			}
			
			@Override
			public List<DeptInfo> queryAll() {
				// TODO Auto-generated method stub
				
				return null;
			}
			
			@Override
			public boolean addDept(DeptInfo dept) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
