package com.simpleprog.proteintracker.tests;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
//import org.junit.Ignore;
import org.junit.Rule;

import com.simpleprog.proteintracker.HistoryItem;
import com.simpleprog.proteintracker.InvalidGoalException;
import com.simpleprog.proteintracker.Notifier;
import com.simpleprog.proteintracker.NotifierStub;
import com.simpleprog.proteintracker.TrackingService;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;

import static org.hamcrest.CoreMatchers.*;




public class TrackingServiceTests {
	private TrackingService sut;
	
	@BeforeClass
	public static void beforeClass() {
		//System.out.println("Before class");
	}
	
	@AfterClass
	public static void afterClass() {
		//System.out.println("After class");
	}
	
	@Before
	public void beforeTest() {
		sut = new TrackingService(new NotifierStub());
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void NewTrackingServiceTotalIsZero() {
		assertEquals("Tracking service total was not 0",
				0, sut.getTotal());
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void WhenAddingProteinTotalIncreasesByThatAmount() {
		sut.addProtein(10);
		assertEquals("Protein amount was not correct",
				10, sut.getTotal());
		assertThat(sut.getTotal(), is(10));
		assertThat(sut.getTotal(), allOf(is(10), instanceOf(Integer.class)));
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void WhenRemovingProteinTotalRemainsZero() {
		sut.removeProtein(5);
		assertEquals(0, sut.getTotal());
	}
	
    @Test
    public void WhenGoalIsMetHistoryIsUpdated() throws InvalidGoalException {
        sut.setGoal(5);
        sut.addProtein(6);
        HistoryItem result = sut.getHistory().get(1);
        assertEquals("sent:goal met", result.getOperation());
    }
    
    @Test
    public void WhenGoalIsMetHistoryIsUpdatedWithMocks() throws InvalidGoalException {
        Mockery context = new Mockery();
        final Notifier mockNotifier = context.mock(Notifier.class);
        sut = new TrackingService(mockNotifier);
        
        context.checking(new Expectations() {{
            oneOf(mockNotifier).send("goal met");
            will(returnValue(true));
        }});
        
        sut.setGoal(5);
        sut.addProtein(6);
        HistoryItem result = sut.getHistory().get(1);
        assertEquals("sent:goal met", result.getOperation());

        context.assertIsSatisfied();
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void WhenGoalIsSetToLessThanZeroExceptionIsThrown2() throws InvalidGoalException {
        thrown.expect(InvalidGoalException.class);
        thrown.expectMessage("Goal was less than zero!");
        sut.setGoal(-5);
    }


	@Test(expected = InvalidGoalException.class)
	@Category(GoodTestsCategory.class)
	public void WhenGoalIsSetToLessThanZeroExceptionIsThrown() throws InvalidGoalException {
		sut.setGoal(-5);
	}
	
	@Test(timeout = 200)
	//@Ignore
	@Category({GoodTestsCategory.class, BadTestsCategory.class})
	public void BadTest() {
		for (int i = 0; i < 10000000; ++i) {
			sut.addProtein(1);
		}
	}

}
