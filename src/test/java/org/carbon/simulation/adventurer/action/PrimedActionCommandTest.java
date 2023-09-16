package org.carbon.simulation.adventurer.action;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.RegionMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class PrimedActionCommandTest {
    @Mock
    private Action action;

    @Mock
    private Adventurer adventurer;

    @Mock
    private RegionMap map;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_Execute_The_Action_Properly(){
        PrimedActionCommand command = new PrimedActionCommand(action, adventurer, map);

        command.execute();

        verify(action).execute(adventurer, map);
    }
}
