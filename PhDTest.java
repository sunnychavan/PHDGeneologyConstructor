package a1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PhDTest {

    @Test
    void testConstructor1() {
        PhD test1= new PhD("Jay Sangwan", 3, 2045);
        assertEquals("Jay Sangwan", test1.name());
        assertEquals("3/2045", test1.date());
        assertEquals(null, test1.advisor1());
        assertEquals(null, test1.advisor2());
        assertEquals(0, test1.numAdvisees());

        // Test length assert statement
        assertThrows(AssertionError.class, () -> new PhD("", 2, 3456));
        // Test month assert statement
        assertThrows(AssertionError.class, () -> new PhD("re", 0, 3456));
        assertThrows(AssertionError.class, () -> new PhD("re", 13, 3456));
        // Test year assert statement
        assertThrows(AssertionError.class, () -> new PhD("re", 1, 90));

    }

    @Test
    void testaddAdvisors() {
        PhD Student= new PhD("Sunny Chavan", 8, 3000);
        PhD OldDude= new PhD("Connor McCarthy", 5, 2025);
        PhD Immortal= new PhD("Tyler Carreja", 12, 1001);
        // test instance of addadvisor1
        Student.addAdvisor1(OldDude);
        assertEquals(OldDude, Student.advisor1());
        assertEquals(1, OldDude.numAdvisees());
        // test instance of addadvisor2
        Student.addAdvisor2(Immortal);
        assertEquals(Immortal, Student.advisor2());
        assertEquals(1, Immortal.numAdvisees());

        // Test addadvisor1 , advisor1 == null assert statement
        assertThrows(AssertionError.class,
            () -> { PhD r= new PhD("re", 2, 3456, OldDude, Student); r.addAdvisor1(Immortal); });
        // Test p != null assert statement
        assertThrows(AssertionError.class,
            () -> { PhD r= new PhD("re", 2, 3456); r.addAdvisor1(null); });

        // Test addadvisor2, advisor1 != null assert statement
        assertThrows(AssertionError.class,
            () -> { PhD r= new PhD("re", 2, 3456); r.addAdvisor2(Immortal); });
        // Test advisor2 == null assert statement
        assertThrows(AssertionError.class,
            () -> { PhD r= new PhD("re", 2, 3456, OldDude, Student); r.addAdvisor2(Immortal); });
        // Test p != null assert statement
        assertThrows(AssertionError.class,
            () -> { PhD r= new PhD("re", 2, 3456); r.addAdvisor2(null); });
        // Test advisor1 != p
        assertThrows(AssertionError.class,
            () -> {
                PhD r= new PhD("re", 2, 3456);
                r.addAdvisor1(OldDude);
                r.addAdvisor2(OldDude);
            });

    }

    @Test
    void testConstructor2() {
        PhD OldDude= new PhD("Connor McCarthy", 5, 2025);
        PhD Immortal= new PhD("Tyler Carreja", 12, 1001);
        PhD Student= new PhD("Sunny Chavan", 8, 3000, OldDude, Immortal);
        assertEquals("Sunny Chavan", Student.name());
        assertEquals("8/3000", Student.date());
        assertEquals(OldDude, Student.advisor1());
        assertEquals(1, OldDude.numAdvisees());
        assertEquals(Immortal, Student.advisor2());
        assertEquals(1, Immortal.numAdvisees());

        // Test length assert statement
        assertThrows(AssertionError.class, () -> new PhD("", 2, 3456, OldDude, Immortal));
        // Test month assert statement
        assertThrows(AssertionError.class, () -> new PhD("re", 0, 3456, OldDude, Immortal));
        assertThrows(AssertionError.class, () -> new PhD("re", 13, 3456, OldDude, Immortal));
        // Test year assert statement
        assertThrows(AssertionError.class, () -> new PhD("re", 1, 90, OldDude, Immortal));
        // Test adv1,adv2 != null
        assertThrows(AssertionError.class, () -> new PhD("re", 1, 90, null, Immortal));
        assertThrows(AssertionError.class, () -> new PhD("re", 1, 90, OldDude, null));
        // Test adv1 != adv2
        assertThrows(AssertionError.class, () -> new PhD("re", 1, 90, OldDude, OldDude));

    }

    @Test
    void testBooleans() {
        PhD OldDude= new PhD("Connor McCarthy", 8, 2025);
        PhD Immortal= new PhD("Tyler Carreja", 12, 1001);
        PhD Newton= new PhD("Issac Newton", 3, 1001);
        PhD Blueman= new PhD("Kyle", 3, 1001);
        PhD Student= new PhD("Sunny Chavan", 8, 3000, OldDude, Immortal);
        PhD Student2= new PhD("Jay Klein", 9, 100000, Immortal, Newton);
        PhD Student3= new PhD("Bill", 9, 20000000);

        // test instance of hasadvisee with PhD that has no advisees
        assertEquals(false, Student.hasAdvisee());
        // test instance of hasadvisee with PhD of 1 advisee
        assertEquals(true, OldDude.hasAdvisee());
        // test instance of hasadvisee with PhD of multiple advisees
        assertEquals(true, Immortal.hasAdvisee());

        // test instance of gotAfter with same year and q’s month after r’s
        assertEquals(true, Immortal.gotAfter(Newton));
        // test instance of gotAfter with same year and q’s month before r’s
        assertEquals(false, Newton.gotAfter(Immortal));
        // test instance of gotAfter with same year and same month
        assertEquals(false, Newton.gotAfter(Blueman));
        // test instance of gotAfter with q's year before r's and q’s month after r’s
        assertEquals(false, Immortal.gotAfter(Student2));
        // test instance of gotAfter with q's year before r's and q’s month before r’s
        assertEquals(false, Newton.gotAfter(Student2));
        // test instance of gotAfter with q's year before r's and same month
        assertEquals(false, OldDude.gotAfter(Student));
        // test instance of gotAfter with q's year after r's and q’s month after r’s
        assertEquals(true, Student2.gotAfter(Student));
        // test instance of gotAfter with q's year after r's and q’s month before r’s
        assertEquals(true, Student.gotAfter(Immortal));
        // test instance of gotAfter with q's year after r's and same month
        assertEquals(true, Student3.gotAfter(Student2));

        PhD null1= new PhD("MC", 9, 1075);
        PhD null2= new PhD("Carlos", 10, 2020);
        PhD samefirst= new PhD("Drake", 6, 2040, OldDude, Newton);
        PhD samefirst1= new PhD("PopSmoke", 7, 2060, OldDude, Immortal);
        PhD samesecond= new PhD("Lil", 4, 3000, Newton, Immortal);
        PhD samesecond1= new PhD("Lilly", 4, 4000, OldDude, Immortal);
        PhD nocommon= new PhD("boop", 2, 5000, Student, Student2);

        // test instance of areSiblings where neither A nor B has an advisor
        assertEquals(false, null1.areSiblings(null2));
        // test instance of areSiblings where A and B are the same object and have the same non-null
        // first advisor
        assertEquals(false, samefirst.areSiblings(samefirst));
        // test instance of areSiblings where A and B are different objects and have the same
        // non-null first advisor
        assertEquals(true, samefirst1.areSiblings(samefirst));
        // test instance of areSiblings where A and B are different objects and have the same
        // non-null second advisor
        assertEquals(true, samesecond1.areSiblings(samesecond));
        // test instance of areSiblings where A and B are different objects and the first advisor of
        // one is the second advisor of the other
        assertEquals(true, samefirst.areSiblings(samesecond));
        // test instance of areSiblings where A and B are different objects and the second advisor
        // of one is the first advisor of the other
        assertEquals(true, samesecond.areSiblings(samefirst));
        // test instance of areSiblings where A and B are different objects and have no common
        // advisors
        assertEquals(false, samefirst1.areSiblings(nocommon));
        // test instance of areSiblings where A and B are different objects and have the same
        // advisors
        assertEquals(true, samefirst1.areSiblings(samesecond1));

        // test p != null assert statement for gotAfter()
        //assertThrows(AssertionError.class, () -> OldDude.gotAfter(null));
        
        // test p != null assert statement for areSiblings()
        assertThrows(AssertionError.class, () -> OldDude.areSiblings(null));

    }
}
