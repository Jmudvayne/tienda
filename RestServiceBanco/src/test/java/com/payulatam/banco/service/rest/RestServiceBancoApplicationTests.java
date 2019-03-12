package com.payulatam.banco.service.rest;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.payulatam.bank.common.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServiceBancoApplicationTests {

	@Test
	public void contextLoads() {
		testGetAllEmployee();
		System.out.println("dsfdasf");
		
	
	}
	
	
	private static void testGetAllEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Employee> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<Cliente> clientes= restTemplate.getForObject("http://localhost:8085/clientes", List.class);
		System.out.println(clientes.size());
		
	}

}
