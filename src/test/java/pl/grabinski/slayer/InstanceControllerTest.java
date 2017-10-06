package pl.grabinski.slayer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InstanceController.class)
public class InstanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstanceService instanceService;

    @Test
    public void list() throws Exception {
        List<Instance> instances = Collections.singletonList(
                new Instance("1", "Abc", OffsetDateTime.now(), "ACTIVE"));
        when(instanceService.findAll()).thenReturn(instances);
        mockMvc.perform(get("/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("instance/list"))
                .andExpect(model().attribute("instances", instances));
    }

}