package com.codenation.desafio.desafio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Calendar;

import com.codenation.desafio.desafio.entity.EventLog;
import com.codenation.desafio.desafio.enuns.Level;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = DesafioApplication.class)
@Sql("/pre-sql.sql")
public class EventLogControllerTests {

  private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  @Value("${security.oauth2.client.client-id}")
  private String clientId;

  @Value("${security.oauth2.client.client-secret}")
  private String cliendSecret;

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private FilterChainProxy springSecurityFilterChain;

  private MockMvc mockMvc;
  Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
  }

  @Test
  public void testGetLogEventById() throws Exception {
    String token = obtainAccessToken("bruno@gmail.com", "123456");

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/eventlog/6001")
        .header("Authorization", "Bearer " + token).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    String resultString = result.getResponse().getContentAsString();

    EventLog event = this.gson.fromJson(resultString, EventLog.class);

    assertNotNull(event);
  }

  @Test
  public void whenAddNewEventLog() throws Exception {
    String token = obtainAccessToken("bruno@gmail.com", "123456");
    EventLog event = new EventLog();
    event.setDescription("description");
    event.setEventDate(Calendar.getInstance().getTime());
    event.setEventLog("Evento Log");
    event.setLevel(Level.error);
    event.setOrigin("origin");
    event.setQuantity(Long.valueOf(1));

    String eventJosn = this.gson.toJson(event);
    MvcResult result = mockMvc.perform(post("/eventlog").header("Authorization", "Bearer " + token)
        .contentType(APPLICATION_JSON_UTF8).content(eventJosn)).andReturn();
    int status = result.getResponse().getStatus();
    assertEquals(201, status);
  }

  @Test
  public void whenUpdateEventLog() throws Exception {
    String token = obtainAccessToken("bruno@gmail.com", "123456");
    EventLog event = new EventLog();
    event.setQuantity(Long.valueOf(2));
    event.setLevel(Level.info);

    String eventJson = this.gson.toJson(event);

    MvcResult result = mockMvc.perform(put("/eventlog/6001").header("Authorization", "Bearer " + token)
        .contentType(APPLICATION_JSON_UTF8).content(eventJson)).andReturn();
    int status = result.getResponse().getStatus();
    assertEquals(200, status);
  }

  @Test
  public void testDeleteUser() throws Exception {
    String token = obtainAccessToken("bruno@gmail.com", "123456");

    MvcResult result = mockMvc.perform(delete("/user/6001").header("Authorization", "Bearer " + token)).andReturn();
    int status = result.getResponse().getStatus();
    assertEquals(200, status);
  }

  private String obtainAccessToken(String username, String password) throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "password");
    params.add("username", username);
    params.add("password", password);

    ResultActions result = mockMvc
        .perform(post("/oauth/token").params(params).with(httpBasic(clientId, cliendSecret))
            .accept("application/json;charset=UTF-8"))
        .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

    String resultString = result.andReturn().getResponse().getContentAsString();

    JacksonJsonParser jsonParser = new JacksonJsonParser();
    return jsonParser.parseMap(resultString).get("access_token").toString();
  }

}
