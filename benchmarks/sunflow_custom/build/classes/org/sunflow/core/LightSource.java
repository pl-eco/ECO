package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public interface LightSource extends RenderObject {
    public int getNumSamples();
    public void getSamples(ShadingState state);
    public void getPhoton(double randX1, double randY1, double randX2, double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power);
    public float getPower();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0XW2xURXR2+y6FPniVV+kLklLYG0IQsYQAtYXWhTZdIKFG" +
                                "lum9s7uX3nvnMne2LywBEgPxgxgtCkb7BVGUV4wEjSHhSyD4gzEaPwT/NCof" +
                                "/OgHKp6Zu4+7t9tiDHGTmZ175pw573NmLj5ERQ5DrTY1RuMG5SEywkMHjfUh" +
                                "PmoTJ9QdXt+LmUO0dgM7zm6ARdXGq5W/P34jURVExf1oLrYsyjHXqeX0EYca" +
                                "Q0QLo8ostMMgpsNRVfggHsJKkuuGEtYd3hZGszykHDWH0yIoIIICIihSBGVr" +
                                "FguIZhMrabYLCmxx5xA6ggJhVGyrQjyOGnIPsTHDZuqYXqkBnFAqvveCUpJ4" +
                                "hKH6jO6uzlMUPt2qTLyzv+qTAlTZjyp1KyLEUUEIDkz6UYVJzAHCnK2aRrR+" +
                                "VG0RokUI07Ghj0m5+1GNo8ctzJOMZIwkgEmbMMkza7kKVejGkiqnLKNeTCeG" +
                                "lv4qihk4DrouyOrqatgp4KBguQ6CsRhWSZqkcFC3NI6W+ykyOja/BAhAWmIS" +
                                "nqAZVoUWBgCqcX1nYCuuRDjTrTigFtEkcOFo8bSHClvbWB3EcRLlqNaP1+tu" +
                                "AVaZNIQg4Wi+H02eBF5a7POSxz8Pd206ddjaYQWlzBpRDSF/KRDV+Yj6SIww" +
                                "YqnEJaxYFX4bL7hxMogQIM/3Ibs41199tGV13c3bLs6SPDg9AweJyqPquYE5" +
                                "95a2t2wsEGKU2tTRhfNzNJfh35vaaRuxIfMWZE4Um6H05s2+L/cd/Yj8GkTl" +
                                "XahYpUbShDiqVqlp6wZh24lFGOZE60JlxNLa5X4XKoF1WLeIC+2JxRzCu1Ch" +
                                "IUHFVH6DiWJwhDBRCax1K0bTaxvzhFyP2AihEhgoAON55P5miYkjVUlQkyhY" +
                                "xZZuUQVil2CmJhSi0igjNlU62nuUAbBywsRs0FGcpBUz6HBUTTqcmorDVIWy" +
                                "eBqsqJQRKAnxBI/QJFNJSASb/f+wGRHaVg0HAuCIpf4yYEAG7aCGRlhUnUhu" +
                                "63h0OXo3mEmLlJ04WgpcQikuIcEl5OGCAgF5+DzBzfUw+GcQMh1qYEVL5JXu" +
                                "AycbCyC07OFCYd0RmXq16Q8g9Eklk7zz85tnr73bujHorQeVngobIdyNruos" +
                                "392MEID/cKb3rdMPT7wsmQJGUz4GzWJuh1iDAgqF6LXbh75/cP/cN8GMoAUc" +
                                "im5ywNBVjkrxAFQsrHKOyjKlx6tIQK7nc7Rsiqn6IEwJczNIqLtsuiyXFerc" +
                                "8YlJref8WjcXa3IzpwMaw6Vv//oqdObHO3ncVMapvcYgQ8TwSFYoWEJApbh1" +
                                "qHSnLIBdsr+okP6vX/j4Or/X+oLLctX0bdJPeOv4L4t3b04cCKJgbqsT3AFU" +
                                "Lih7RYPKNKLlPuX9R17YefHO9pXqm0FUkKpzeWp6LlGb1wzAlBFoQpYwqICU" +
                                "A9NGf9gzqhINOlWW76p6fC16Y7w5iAqhWEOD4hgKCtT+Oj/znPralo5NwaoI" +
                                "jBCjzMSG2Eo3mHKeYHQ4C5H5OEeuq8E9lSILlsKoSVUg+S9259pinufmr8Rf" +
                                "Iuc6MTVI3wbFslFMTSKyVmYzAaqlAfEmnNG8xzKppsd0PGAQkZN/Vq5Ye+23" +
                                "U1VuBBkASXtn9dMPyMIXbUNH7+7/o04eE1BFt85mZxbNTdK52ZO3MoZHhRwj" +
                                "x75edvYWfh+aCRRwRx8jsiajVGUQQm2SGm+Qc5tvb7OY1nE0O074rqQZwaZt" +
                                "EAd4tcxw0WO6Cb1nKNUclfGaB4Pv/XzJjXx/J/Uhk5MTrz8JnZoIeq4bTVM6" +
                                "vpfGvXJIYWe7Dn8CvwCMv8UQmgiA23Jq2lN9rz7T+GxbeLVhJrEki86frox/" +
                                "8eH4iWDKMi0cFUCVsqeYTQLWZGJPNrsQjPpU7NX/19jL9VLgaSUxksAaXLHE" +
                                "JZNIHj0z+Dkipm6OysHPaScL0BYxvejy7uSocIjq2r9TeRSGklJZeTYql0iE" +
                                "knyCFWsUuogsRtHslDbOQq9xTLicwPUIfLcuvV87ZX8vERfndXkP0E24borc" +
                                "pUwqsn8Gu+pi2gddA+zam6CcWgKw56kmFAMtgrEhZcINz8aEXtkOzbAngdAW" +
                                "SoXcdJiwfEaHqkxxngwY4WiW5+4iSlPtlLeRe59XL09Wli6c3POd7AmZO3cZ" +
                                "XHxjScPwtBxv+ym2GYnpUtAyt9Tb8u8wvA/9eQBBK/6kkGMu2hGQz4PGIaTc" +
                                "lRfpGOQ3IInlcagRKOdCZfuvV0055VC+F9PNN+m+GKPqlcnuXYcfPXdedvIi" +
                                "eGmOjcn3BTyX3I6WaeAN056WPqt4R8vjOVfLVqSr0Rwx1XiCwiPb8vwNp8O0" +
                                "uWwRY58t/HTTB5P35a3sH7910GnIDwAA");
}
