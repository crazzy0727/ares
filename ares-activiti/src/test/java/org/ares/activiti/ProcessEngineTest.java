package org.ares.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import junit.framework.TestCase;

public class ProcessEngineTest extends TestCase {
	@Test
	public void test() {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
		ProcessEngine processEngine  = processEngineConfiguration.buildProcessEngine();
		
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("bpmn/leave/leave-001.bpmn20.xml");
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave-001");
		
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery().singleResult();
		
		System.out.println(task.getName());
		taskService.complete(task.getId());
		
		System.out.println(task.getName());
	}
}
