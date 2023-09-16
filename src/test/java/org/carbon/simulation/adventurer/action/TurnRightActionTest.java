package org.carbon.simulation.adventurer.action;

import org.carbon.simulation.adventurer.Adventurer;
import org.carbon.simulation.map.RegionMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class TurnRightActionTest {
    @Mock
    private Adventurer adventurer;

    @Mock
    private RegionMap map;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_Delegate_To_Adventurer(){
        TurnRightAction turnRightAction = new TurnRightAction();

        turnRightAction.execute(adventurer, map);

        verify(adventurer).turnRight();
    }
}
