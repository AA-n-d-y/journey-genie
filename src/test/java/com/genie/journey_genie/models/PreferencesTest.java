package com.genie.journey_genie.models;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class PreferencesTest {
    @Test
    void preferencesTest() {
        Preferences preferences = new Preferences(3,2,true,"Canada",5,"aquariums,museums");

        assertEquals(3, preferences.getDuration());
        assertEquals(2, preferences.getActivitiesPerDay());
        assertEquals(true, preferences.isTolls());
        assertEquals("Canada", preferences.getLocation());
        assertEquals(5, preferences.getRange(), 0.001);
        assertEquals("aquariums,museums", preferences.getInterests());

        preferences.setDuration(5);
        assertEquals(5, preferences.getDuration());
        preferences.setActivitiesPerDay(3);
        assertEquals(3, preferences.getActivitiesPerDay());
        preferences.setTolls(false);
        assertEquals(false, preferences.isTolls());
        preferences.setLocation("Japan");
        assertEquals("Japan", preferences.getLocation());
        preferences.setRange(6);
        assertEquals(6, preferences.getRange(), 0.001);
        preferences.setInterests("hiking,beaches");
        assertEquals("hiking,beaches", preferences.getInterests());
    }
}
