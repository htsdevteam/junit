package com.simpleprog.proteintracker.tests;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.junit.Assume;

import com.simpleprog.proteintracker.NotifierStub;
import com.simpleprog.proteintracker.TrackingService;

@RunWith(Theories.class)
public class TrackingServiceTheories {
    @DataPoints
    public static int[] data() {
        return new int[] {
                1, 5, 10, 15, 20, 50, -4
        };
    }
    
    @Theory
    public void positiveValuesShouldAlwaysHavePositiveTotals(int value) {
        TrackingService sut = new TrackingService(new NotifierStub()); 
        sut.addProtein(value);
        Assume.assumeTrue(value > 0);
        
        assertTrue(sut.getTotal() > 0);
    }
}
