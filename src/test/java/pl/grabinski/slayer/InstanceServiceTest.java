package pl.grabinski.slayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InstanceServiceTest {

    @Mock
    private InstanceRepository instanceRepository;

    @Mock
    private Instance instance;
    @Captor
    private ArgumentCaptor<Sort> sortArgumentCaptor;


    @Test
    public void findAll() throws Exception {
        when(instanceRepository.findAll(sortArgumentCaptor.capture())).thenReturn(Collections.singletonList(instance));
        InstanceService instanceService = new InstanceService(instanceRepository);
        assertEquals(Collections.singletonList(instance), instanceService.findAll());
        assertEquals("created", sortArgumentCaptor.getValue().iterator().next().getProperty());
    }

}