package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class PrimIDShader implements Shader {
    private static final Color[] BORDERS = { Color.RED, Color.GREEN, Color.
                                                                       BLUE,
    Color.
      YELLOW,
    Color.
      CYAN,
    Color.
      MAGENTA };
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal(
                                                                       );
                                                   float f = n == null ? 1.0F
                                                     : Math.
                                                     abs(
                                                       state.
                                                         getRay(
                                                           ).
                                                         dot(
                                                           n));
                                                   return BORDERS[state.getPrimitiveID(
                                                                          ) %
                                                                    BORDERS.
                                                                      length].
                                                     copy(
                                                       ).
                                                     mul(
                                                       f); }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public PrimIDShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfOzvnP3FsxyGJSWMncRxax3ALragKRmntwyZO" +
                                                    "L/jqM5F6CMzc3tzdxnu7y+6cfXHqQqKiRFSKUDEQKLVUFEqhIUFVI1pVSPnS" +
                                                    "AqJUAlWt+qGk7ZeippGaD6WotKXvzeze7u2dTZEQJ+3c7Mx7M+/Ne+/33uzZ" +
                                                    "K2SdY5Nhy9SPFHSTx1mFxw/rN8f5EYs58QPJm1PUdlguoVPHmYGxWXXgpa73" +
                                                    "Pnik2B0lsQzZRA3D5JRrpuFMM8fU51kuSbr80XGdlRxOupOH6TxVylzTlaTm" +
                                                    "8JEkWR9g5WQw6YmggAgKiKAIEZRRnwqYNjCjXEogBzW4cz/5JokkScxSUTxO" +
                                                    "dtUuYlGbltxlUkIDWKEV3w+BUoK5YpOdVd2lznUKPzasLD9xb/ePm0hXhnRp" +
                                                    "RhrFUUEIDptkSEeJlbLMdkZzOZbLkI0GY7k0szWqa4tC7gzpcbSCQXnZZtVD" +
                                                    "wsGyxWyxp39yHSrqZpdVbtpV9fIa03Pe27q8Tgug6xZfV6nhBI6Dgu0aCGbn" +
                                                    "qco8luY5zchxsiPMUdVx8KtAAKwtJcaLZnWrZoPCAOmRttOpUVDS3NaMApCu" +
                                                    "M8uwCyfbVl0Uz9qi6hwtsFlOesN0KTkFVG3iIJCFk81hMrESWGlbyEoB+1y5" +
                                                    "87ZTR439RlTInGOqjvK3AlN/iGma5ZnNDJVJxo69ycfplldORgkB4s0hYknz" +
                                                    "8jeufuX6/ouvSZrPNKCZyh5mKp9Vz2Q739qeGLqlCcVotUxHQ+PXaC7cP+XO" +
                                                    "jFQsiLwt1RVxMu5NXpz+5dcffIFdjpL2SRJTTb1cAj/aqJolS9OZfQczmE05" +
                                                    "y02SNmbkEmJ+krRAP6kZTI5O5fMO45OkWRdDMVO8wxHlYQk8ohboa0be9PoW" +
                                                    "5UXRr1iEkBZ4yDA87UT+xD8nc0rRLDGFqtTQDFMB32XUVosKU81Zm1mmMp6Y" +
                                                    "UrJwysUSteccxSkbed1cmFXLDjdLimOrimkXvGFFNW2mOEWaY7aSsrXS5O1p" +
                                                    "8RJHp7M+3e0qqH33QiQChtkehgUdImq/qQPtrLpcHhu/em72jWg1TNxzAyCD" +
                                                    "3eLubnHcLS53iwd3I5GI2OQa3FVaHuw2BwgA2NgxlL7nwH0nB5rA5ayFZjh0" +
                                                    "JB0AhV1RxlUz4cPEpABDFXy195m7T8Tff+7L0leV1TG9ITe5eHrh2KEHboyS" +
                                                    "aC04o2ow1I7sKYTUKnQOhoOy0bpdJ9597/zjS6YfnjVo76JGPSdG/UDYCLap" +
                                                    "shzgqL/83p30wuwrS4NR0gxQAvDJKbg7IFN/eI+a6B/xkBR1WQcK5027RHWc" +
                                                    "8uCvnRdtc8EfEd7RiU2PdBQ0YEhAAcITP7v45IWnhm+JBvG6K5AB04zL6N/o" +
                                                    "23/GZgzG/3A69ehjV07cLYwPFLsbbTCIbQKwAKwBJ/bQa/f//tI7Z34T9R2G" +
                                                    "Q1IsZ3VNrcAa1/m7AFLogFZo1sG7jJKZ0/IazeoM/e7fXXtuuvC3U93SUDqM" +
                                                    "eHa+/qMX8MevHSMPvnHvP/vFMhEVM5WvuU8mD2CTv/KobdMjKEfl2Nt9T75K" +
                                                    "vwdACuDlaItM4BERmhFx9HFhkSHR3hCauxGbnVbdXEWM9FYDamj1+JjAhBuI" +
                                                    "q39N6dnjf35faFQXGQ3yTIg/o5x9elti32XB77socu+o1CMNFCc+7+dfKP0j" +
                                                    "OhD7RZS0ZEi36lY+h6heRm/JQLZ3vHIIqqOa+drMLdPUSDUEt4fDI7BtODh8" +
                                                    "hIM+UmO/XcaDoNkIZ3otnvIYPB1uvliPDc5usrC9phIhovMlwbJLtIPYfFbY" +
                                                    "pImTFsvW5iGtgfc6osjiIIdmUL0Cc2NT07ePT6fBcntWt5xwIpm5V36w+9cP" +
                                                    "rOz+E5x6hrRqDug3ahcalBIBnr+fvXT57Q195wSYNGepIzUN12D1JVZN5SQO" +
                                                    "pqN6MON4DgfgedM9mDdlIs19spnNOeJwVlImS1BVpajBdC+Dfir7yOjazMnW" +
                                                    "YP7TkAgxw61mMR4jbgTi+xewSXiOMdnYMaLY/Rw2+8APYjozCry4dgBjroU6" +
                                                    "at4t9JSlnktzT7/7okyM4WgNEbOTyw9/GD+1HA2UzrvrqtcgjyyfhdU3SKt/" +
                                                    "CL8IPP/FB62NA9LqPQm3httZLeIsC1F611piiS0m/nJ+6ec/XDoh1eiprRzH" +
                                                    "4WL04m//86v46T++3qAsaQKXxZcxq1K1RFQer2c5icUIFlBdmwZDWPfmZKGi" +
                                                    "mfHqzQYmK3U2tUlfTZlyUESFj2YPP/+jl/lbw7dKDfaubsAw46vH/7ptZl/x" +
                                                    "vo9RnOwInWd4yecPnn39juvU70RJUxUU665ItUwjtVDYbjO40xkzNYDYL11a" +
                                                    "nHVjf44If66skcLYGnMFbCgAo4o2kiaFc9/ROEWPlywukuriT7f+5LbnVt4R" +
                                                    "NUJFwHK3JRbM1uTFRlEsqlhZuKKj9q12FRNOeub48kpu6tmboq68M5y0cdO6" +
                                                    "QWfzTA9s1ST691RRchNxc0ini5Kd4fQhZPYlHlrNj/vrZBeOwQCy8SQ8si1B" +
                                                    "srT8H01Nim3KaxjgKDZQX8bKVg6iV9AcxOZr0qRpyFVZ09QZNeqLkJDS+JDt" +
                                                    "8PS4Svf830pHag3W19BgcFfHrxVMLPOtNZQ6ic0xTtYXGJ8GPvR2HJr4SBVE" +
                                                    "lt8HT6+rQu/HtRu+PiQ2E6Sn1hDzEWy+zQFmVcrBnqmiyV0QCJmged7Ucg2K" +
                                                    "QE46ghcxDJ3eug9A8qOFem6lq3Xryl2/k9WA92GhDW73+bKuB2uiQD9m2Syv" +
                                                    "CWnbZIUkw+x0o6CSV0Msd0RHiPuEpP8uJ91helAL/4JkK2CzABn4ntsLEn0f" +
                                                    "8B+IsPuM5XlMtw/3sjaskEB44u0j+FZzFUG4Fh/XPGgty89rs+r5lQN3Hr36" +
                                                    "xWcFTgNE0cVFXKUVKiR5warC865VV/PWiu0f+qDzpbY9HpLUXL2CsmH/8P8A" +
                                                    "aoZXscoUAAA=");
}
