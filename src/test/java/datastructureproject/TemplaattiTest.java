package datastructureproject;

import org.junit.Test;
import static org.junit.Assert.*;

import datastructureproject.luokat.ShakkiTemplaatti;

public class TemplaattiTest {
    @Test
    public void templaattiXYTest(){
        ShakkiTemplaatti templaatti = new ShakkiTemplaatti();

        int y = 0;
        int x = 0;
        assertEquals('r', templaatti.getTemplaatti()[y][x]);
        assertEquals('r', templaatti.getTemplaatti()[y][x+7]);
        assertEquals('r', templaatti.getTemplaatti()[y+7][x+7]);
        assertEquals('r', templaatti.getTemplaatti()[y+7][x+7]);

        assertEquals('n', templaatti.getTemplaatti()[y][x+1]);
        assertEquals('0', templaatti.getTemplaatti()[y+4][x]);

        assertEquals('k', templaatti.getTemplaatti()[y][4]);
        assertEquals('k', templaatti.getTemplaatti()[y+7][4]);
    }
}
