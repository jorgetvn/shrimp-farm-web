package ec.jorgetorres.shrimpfarmweb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ShrimpFarmWebApplicationTests {
  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @BeforeTestExecution
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  void givenWac_whenServletContext_thenItProvidesShrimpFarmController() {
    ServletContext servletContext = wac.getServletContext();
    Assertions.assertThat(servletContext).isNotNull();
    Assertions.assertThat(servletContext).isInstanceOf(MockServletContext.class);
    Assertions.assertThat(wac.getBean("shrimpFarmController")).isNotNull();
  }

  @Test
  public void shirmpFarmShouldReturnJSONListfromService() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    this.mockMvc.perform(get("/api/shrimpfarms")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("James Blue")));
  }

}
