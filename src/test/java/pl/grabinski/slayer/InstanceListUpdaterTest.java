package pl.grabinski.slayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InstanceListUpdaterTest {

    private static final String ID = "1";
    private static final String NAME = "RainbowDash";
    private static final String IMAGE_NAME = "Cirros";
    private static final String NOTES = "some very important notes";
    private static final OffsetDateTime CREATED = OffsetDateTime.now();
    private static final String STATUS = "ACTIVE";

    @Mock
    private InstanceRetriever instanceRetriever;
    @Mock
    private InstanceRepository instanceRepository;
    @Mock
    private Flavor flavor;

    private Instance someOpenStackOriginatedInstance = new Instance();
    private Instance repositoryInstance = new Instance();

    @Captor
    private ArgumentCaptor<Instance> savedInstanceCaptor;

    private InstanceListUpdater instanceListUpdater;


    @Before
    public void setUp() {
        someOpenStackOriginatedInstance.setId(ID);
        someOpenStackOriginatedInstance.setName(NAME);
        someOpenStackOriginatedInstance.setImageName(IMAGE_NAME);
        someOpenStackOriginatedInstance.setFlavor(flavor);
        someOpenStackOriginatedInstance.setCreated(CREATED);
        someOpenStackOriginatedInstance.setStatus(STATUS);

        repositoryInstance.setId(ID);
        repositoryInstance.setNotes(NOTES);

        instanceListUpdater = new InstanceListUpdater(instanceRetriever, instanceRepository);
    }

    @Test
    public void whenThereIsNoInstanceInRepositoryShouldSaveNewOne() throws Exception {
        when(instanceRepository.findOne(ID)).thenReturn(Optional.empty());

        instanceListUpdater.update(someOpenStackOriginatedInstance);

        verify(instanceRepository).save(savedInstanceCaptor.capture());
        Instance savedInstance = savedInstanceCaptor.getValue();
        verifyOpenStackOriginatedAttributesUpdated(savedInstance);
    }

    private void verifyOpenStackOriginatedAttributesUpdated(Instance savedInstance) {
        assertEquals(ID, savedInstance.getId());
        assertEquals(NAME, savedInstance.getName());
        assertEquals(IMAGE_NAME, savedInstance.getImageName());
        assertEquals(flavor, savedInstance.getFlavor());
        assertEquals(CREATED, savedInstance.getCreated());
        assertEquals(STATUS, savedInstance.getStatus());
    }


    @Test
    public void whenUpdatingRepositoryInstanceShouldNotModifyNotes() throws Exception {
        when(instanceRepository.findOne(ID)).thenReturn(Optional.of(repositoryInstance));

        instanceListUpdater.update(someOpenStackOriginatedInstance);

        verify(instanceRepository).save(savedInstanceCaptor.capture());
        Instance savedInstance = savedInstanceCaptor.getValue();
        verifyOpenStackOriginatedAttributesUpdated(savedInstance);
        assertEquals(NOTES, savedInstance.getNotes());
    }

}