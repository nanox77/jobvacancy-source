package com.jobvacancy.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobOfferTest {
	
	private static final String DEFAULT_TITLE = "SAMPLE_TEXT";    
    private static final String DEFAULT_LOCATION = "SAMPLE_TEXT";    
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private JobOffer jobOffer = new JobOffer();
    	
	
    @Before
    public void initTest() {
        jobOffer = new JobOffer();
        jobOffer.setTitle(DEFAULT_TITLE);
        jobOffer.setLocation(DEFAULT_LOCATION);
        jobOffer.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
	public void getNumberOfPostulantsMostReturnZeroTest(){						
		
		Assert.assertEquals(0,jobOffer.getNumberOfPostulants());
	}    
    
	@Test
	public void getNumberOfPostulantsMostReturnOneTest(){				
		Postulant postulant = new Postulant();
		jobOffer.addPostulant(postulant);
		
		Assert.assertEquals(1,jobOffer.getNumberOfPostulants());
	}
	
	
}
