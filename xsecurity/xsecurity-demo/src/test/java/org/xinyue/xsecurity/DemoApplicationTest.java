package org.xinyue.xsecurity;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {
    private Log log = LogFactory.getLog(DemoApplicationTest.class);

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void queryUserTest() throws Exception {
        String result = mockMvc.perform(get("/user")
                .param("name", "xinyue")
                .param("page", "3")
                .param("sort", "age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        log.info(result);
    }

    @Test
    public void echo() throws Exception {
        String result = mockMvc.perform(get("/hello")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.info(result);
    }

    @Test
    public void getUserByIdTest() throws Exception {
        String result = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Xinyue"))
                .andReturn().getResponse().getContentAsString();

        log.info(result);
    }


    @Test
    public void createUserTest() throws Exception {
        String content = "{\"name\":\"Xinyue\",\"password\":null,\"date\":" + new Date().getTime() + "}";
        String result = mockMvc.perform(post("/create/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void updateUserTest() throws Exception {
//        String content = "{\"name\":\"Xinyue\",\"password\":\"123456x\",\"date\":" + new Date().getTime() + "}";
        String content = "{\"name\":\"Xinyue\",\"password\":null,\"date\":" + new Date().getTime() + "}";
        String result = mockMvc.perform(put("/update/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void deleteUserTest() throws Exception {
        String result = mockMvc.perform(delete("/delete/user/-1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is5xxServerError())
                .andReturn().getResponse().getContentAsString();

        log.info(result);
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(fileUpload("/file")
                .file(new MockMultipartFile("file", "demo.txt", "multipart/form-data", "Upload File".getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
