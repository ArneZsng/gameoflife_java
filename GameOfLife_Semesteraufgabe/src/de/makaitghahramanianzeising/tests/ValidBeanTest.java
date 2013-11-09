package de.makaitghahramanianzeising.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.makaitghahramanianzeising.exceptions.GOLException;
import de.makaitghahramanianzeising.utils.ValidBean;

public class ValidBeanTest {

    @Test
    public void shouldBeValid() {
        //when
        ValidBean validBean = new ValidBean(true, null);
        //then
        assertTrue(validBean.isValid());
    }

    @Test
    public void shouldThrowError() {
        //when
        String message = "Dies ist eine Fehlermeldung.";
        ValidBean validBean = new ValidBean(false, new GOLException(message));
        //then
        try {
            throw validBean.getExceptionOnInvalid();
        } catch (Exception e) {
            assertEquals(e.getMessage(), message);
        }
    }

}
