package app;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mitocode.calculator.SimpleCalculatorApplication;
import com.mitocode.calculator.SimpleCalculatorController;

//@RunWith(SpringRunner.class)
//@WebMvcTest(SimpleCalculatorController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=SimpleCalculatorApplication.class)
@WebMvcTest(SimpleCalculatorController.class)
public class SimpleCalculatorControllerTest {

	@Autowired
	private MockMvc mvc;
		
	@Test
	public void test_01_Add_API() throws Exception {
		
		String cadena = new StringBuilder()
				.append("/api/add/")
				.append("{num1}")
				.append("/{num2}")
				.toString();	
		int num1 =2;
		int num2 =3;		
		//mvc.perform(get("/api/add/2/3") // también funciona así sin el String
		mvc.perform(get(cadena,num1,num2)
		.contentType(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("2 + 3 = 5")));
	}
	
	@Test
	public void test_02_Sub_API() throws Exception {
		
		String cadena = new StringBuilder()
				.append("/api/sub/")
				.append("{num1}")
				.append("/{num2}")
				.toString();	
		int num1 =7;
		int num2 =3;		
		//mvc.perform(get("/api/add/2/3") // también funciona así sin el String
		mvc.perform(get(cadena,num1,num2)
		.contentType(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("7 - 3 = 4")));
	}
	
	@Test
	public void test_03_Mul_API() throws Exception {
		
		String cadena = new StringBuilder()
				.append("/api/mul/")
				.append("{num1}")
				.append("/{num2}")
				.toString();	
		int num1 =5;
		int num2 =5;		
		//mvc.perform(get("/api/add/2/3") // también funciona así sin el String
		mvc.perform(get(cadena,num1,num2)
		.contentType(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("5 x 5 = 25")));
	}
	
	@Test
	public void test_04_Div_API() throws Exception {
		
		String cadena = new StringBuilder()
				.append("/api/div/")
				.append("{num1}")
				.append("/{num2}")
				.toString();	
		int num1 =6;
		int num2 =2;		
		//mvc.perform(get("/api/add/2/3") // también funciona así sin el String
		mvc.perform(get(cadena,num1,num2)
		.contentType(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("6 / 2 = 3")));
	}
	
	@Test(expected = Exception.class)
	public void test_05_DivByZero_API() throws Exception {
		
		String cadena = new StringBuilder()
				.append("/api/div/")
				.append("{num1}")
				.append("/{num2}")
				.toString();	
		int num1 =6;
		int num2 =0;
		
		mvc.perform(get(cadena,num1,num2)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		}
}
