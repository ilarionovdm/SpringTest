package ru.dilarionov.test.tests.mvc;

import ru.dilarionov.test.config.TestUserDbConfig;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.dilarionov.springtest.config.HistoryDbConfig;
import ru.dilarionov.springtest.config.WebSecurityConfig;
import ru.dilarionov.springtest.controller.RegistrationController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {RegistrationController.class})
@ContextConfiguration(classes = {WebSecurityConfig.class, TestUserDbConfig.class, HistoryDbConfig.class})
@ComponentScan(
        basePackages = {
                "ru.dilarionov.springtest.controller"
        })
public class TestRegistration {

    @Autowired
    private MockMvc mvc;

    @Test
    @Description("Тест навигации по страницам без аутентификации")
    public void test01() throws Exception {
        mvc.perform(get("/registration").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
        mvc.perform(get("/testing").accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection());
    }
}
