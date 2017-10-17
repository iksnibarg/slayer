package pl.grabinski.slayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InstanceServiceTest {

    private static final String ID = "123";
    private static final String NOTES = "some notes";

    @Mock
    private InstanceRepository instanceRepository;
    @Mock
    private Instance instance;
    @Captor
    private ArgumentCaptor<Sort> sortArgumentCaptor;

    private InstanceService instanceService;

    @Before
    public void setUp() {
        instanceService = new InstanceService(instanceRepository);
    }

    @Test
    public void findAll() throws Exception {
        when(instanceRepository.findAll(sortArgumentCaptor.capture())).thenReturn(Collections.singletonList(instance));
        assertEquals(Collections.singletonList(instance), instanceService.findAll());
        assertEquals("created", sortArgumentCaptor.getValue().iterator().next().getProperty());
    }

    @Test
    public void shouldBeAbleToUpdateNotes() {
        when(instanceRepository.findOne(ID)).thenReturn(Optional.of(instance));
        instanceService.updateNotes(ID, NOTES);

        verify(instance).setNotes(NOTES);
        verifyNoMoreInteractions(instance);
        verify(instanceRepository).save(instance);
    }

    @Test
    public void settingNotesToNotExistingInstanceShouldBeIgnored() {
        when(instanceRepository.findOne(ID)).thenReturn(Optional.empty());
        instanceService.updateNotes(ID, NOTES);

        verify(instanceRepository).findOne(ID);
        verifyNoMoreInteractions(instanceRepository);
    }

}