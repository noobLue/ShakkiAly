package datastructureproject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.model.Side;
import datastructureproject.luokat.apulaiset.ShakkiApu;

public class ShakkiApuTest {
    @Test
    public void mustanVastustajaValkoinen(){
        assertEquals(Side.WHITE, ShakkiApu.vastustaja(Side.BLACK));
    }

    @Test
    public void valkoisenVastustajaMusta(){
        assertEquals(Side.BLACK, ShakkiApu.vastustaja(Side.WHITE));
    }
}
