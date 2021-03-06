package edu.kit.ifv.mobitopp.simulation.external;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;
import org.matsim.api.core.v01.population.Activity;

public class ToLocationTest {

  private ExternalTrip trip;
  private ExternalTripTestData testData;

  @Before
  public void initialise() {
    testData = ExternalTripTestData.create();
    trip = new ToLocation(testData.tripId, testData.from, testData.toLink, testData.startTime);
  }

  @Test
  public void createDestination() {
    Activity destination = trip.doCreateDestination(testData.suffix, testData.activityCreator);

    assertThat(destination, is(sameInstance(testData.linkActivity)));

    verify(testData.activityCreator).activityForLink(testData.suffix, testData.toLink);
    verifyNoMoreInteractions(testData.activityCreator);
  }

  @Test
  public void createOrigin() {
    Activity destination = trip.doCreateSource(testData.suffix, testData.activityCreator);

    assertThat(destination, is(sameInstance(testData.zoneActivity)));

    verify(testData.activityCreator).activityForZone(testData.suffix, testData.from);
    verifyNoMoreInteractions(testData.activityCreator);
  }

}
