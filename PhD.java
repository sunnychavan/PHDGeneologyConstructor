package a1;

/** NetId:Tcc86, Sc2448 Time spent: 1:30 P.M. - hh hours, mm minutes. <br>
 * What I thought about this assignment: <br>
 * <br>
 * An instance maintains info about the PhD of a person. */

public class PhD {

    /** Name of the person with a PhD, length > 0. */
    private String name;
    /** Month PhD was awarded, in range 1..12 with 1 being January, etc. */
    private int month_awarded;
    /** year PhD was awarded, Must be > 1000 */
    private int year_awarded;
    /** First advisor of this person, Null if unknown */
    private PhD advisor1;
    /** Second advisor of this person, Null if unknown */
    private PhD advisor2;
    /** number of PhD advisees of this person, must be >= 0 */
    private int advisees;

    /** Constructor: an instance for a person with name n, PhD month m, PhD year y. <br>
     * Its advisors are unknown, and it has no advisees. <br>
     * Precondition: n has at least 1 char, m is in 1..12, and y > 1000. */
    public PhD(String n, int m, int y) {
        assert n.length() > 0;
        assert 0 < m && m <= 12;
        assert y > 1000;
        name= n;
        month_awarded= m;
        year_awarded= y;

    }

    /** Return the name of this person */
    public String name() {
        return name;
    }

    /** Return the date on which this person got the PhD. In the form "month/year", with no blanks,
     * e.g. "6/2007" */
    public String date() {
        return month_awarded + "/" + year_awarded;
    }

    /** Return the first advisor of this PhD (null if unknown). */
    public PhD advisor1() {
        return advisor1;

    }

    /** Return the second advisor of this PhD (null if unknown). */
    public PhD advisor2() {
        return advisor2;
    }

    /** Return the number of PhD advisees of this person. */
    public int numAdvisees() {
        return advisees;
    }

    /** Add p as the first advisor of this person. <br>
     * Precondition: the first advisor is unknown and p is not null. */
    public void addAdvisor1(PhD p) {
        assert advisor1 == null;
        assert p != null;
        advisor1= p;
        p.advisees+= 1;

    }

    /** Add p as the second advisor of this person. <br>
     * Precondition: The first advisor (of this person) is known, the second advisor is unknown,<br>
     * p is not null, and p is different from the first advisor. */
    public void addAdvisor2(PhD p) {
        assert advisor1 != null;
        assert advisor2 == null;
        assert p != null;
        assert p != advisor1;
        advisor2= p;
        p.advisees+= 1;
    }

    /** Constructor: a PhD with name n, PhD month m, PhD year y, first advisor adv1, and second
     * advisor adv2. <br>
     * Precondition: n has at least 1 char, m is in 1..12, y > 1000, adv1 and adv2 are not null, and
     * adv1 and adv2 are different. */
    public PhD(String n, int m, int y, PhD adv1, PhD adv2) {
        assert n.length() > 0;
        assert 0 < m && m <= 12;
        assert y > 1000;
        assert adv1 != null;
        assert adv2 != null;
        assert adv1 != adv2;
        name= n;
        month_awarded= m;
        year_awarded= y;
        addAdvisor1(adv1);
        addAdvisor2(adv2);

    }

    /** Return value of "this PhD has at least one advisee" <br>
     * i.e. true if this PhD has at least one advisee and false otherwise */
    public boolean hasAdvisee() {
        return advisees > 0;
    }

    /** Return value of "p is not null and this person got the PhD after p." <br>
     * Precondition: p is not null. */
    public boolean gotAfter(PhD p) {
        //assert p != null;
        return p != null && (year_awarded > p.year_awarded ||
            month_awarded > p.month_awarded && year_awarded == p.year_awarded);
    }

    /** Return value of "this person and p are intellectual siblings." <br>
     * Precondition: p is not null, p is not this person */
    public boolean areSiblings(PhD p) {
        assert p != null;
        return this != p && advisor1 != null && advisor2 != null &&
            (advisor1 == p.advisor1 || advisor1 == p.advisor2 ||
                advisor2 == p.advisor1 ||
                advisor2 == p.advisor2);
    }
}
