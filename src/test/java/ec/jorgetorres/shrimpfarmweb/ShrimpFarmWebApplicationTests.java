package ec.jorgetorres.shrimpfarmweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.jorgetorres.shrimpfarmweb.domain.Pond;
import ec.jorgetorres.shrimpfarmweb.domain.ShrimpFarm;
import ec.jorgetorres.shrimpfarmweb.repository.ShrimpFarmRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.validation.Validation;
import javax.validation.Validator;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShrimpFarmWebApplicationTests {
  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private ShrimpFarmRepository shrimpFarmRepository;
  @Autowired
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;
  private Validator validator;

  @BeforeAll
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    this.validator = validator = Validation.buildDefaultValidatorFactory().getValidator();
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
    this.mockMvc.perform(get("/api/shrimpfarms")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("James Blue")));
  }

  @Test
  void whenPondIsInvalid_thenReturnsStatus400() throws Exception {
    ShrimpFarm shrimpFarm = shrimpFarmRepository.findByName("La Victoria");

    Pond pond = Pond.builder().name("San Fernandino")
        .size(new BigDecimal(-1))
        .shrimpFarm(shrimpFarm)
        .build();
    /*Set<ConstraintViolation<Pond>> violations = validator.validate(pond);
    Assertions.assertThat(violations.size()).isEqualTo(1);*/

    String body = objectMapper.writeValueAsString(pond);

    this.mockMvc.perform(post("/api/pond")
        .contentType("application/json")
        .content(body))
        .andExpect(status().isBadRequest());
  }

}
