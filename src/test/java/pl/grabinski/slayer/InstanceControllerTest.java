package pl.grabinski.slayer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        List<Instance> instances = Collections.singletonList(new Instance());
        when(instanceService.findAll()).thenReturn(instances);
        mockMvc.perform(get("/instances"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("instance/list"))
                .andExpect(model().attribute("instances", instances));
    }

    @Test
    public void updateNotes() throws Exception {
        mockMvc.perform(post("/instances/123").param("notes", "some notes"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/instances"));
        verify(instanceService).updateNotes("123", "some notes");
    }

}