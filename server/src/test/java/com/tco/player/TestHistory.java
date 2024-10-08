package com.tco.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHistory {
    @Test
    @DisplayName("cjstumpf: test init")
    public void historytestinit() { History inv = new History();
    }


    @Test
    @DisplayName("cjstumpf: test clean")
    public void historytestclean() { History inv = new History();
    inv.clean();
    assertEquals(0,inv.returnList().size());
    }

    @Test
    @DisplayName("cjstumpf: test add")
    public void historytestadd() { History inv = new History();
        int beforeAdd = inv.returnList().size();
        inv.addtoList(5);
        assertEquals(beforeAdd+1, inv.returnList().size());
    }

    @Test
    @DisplayName("cjstumpf: test remove")
    public void historytestremove() { History inv = new History();
        inv.addtoList(5);
        int beforeAdd = inv.returnList().size();
        inv.removefromList(5);
        assertEquals(beforeAdd-1, inv.returnList().size());
    }

}
