package org.sunflow;
public class EcoMode {
    private static EcoMode sharedMode = null;
    public static EcoMode sharedMode() { if (sharedMode == null) { sharedMode = new EcoMode(); }
                                         return sharedMode; }
    private int currentMode;
    public int getMode() { return this.currentMode; }
    public void setMode(int mode) { this.currentMode = mode; }
    private EcoMode() { super();
                        this.currentMode = 0; }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1415303427000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYfWwUxxUf353v8Nno/AHmIwRjYyBgcttEpWpxJCCuCRfO" + "4PiLxIQ64925u4W9\n3c3u7Hnt0IS0VaCJkpR+SK3UEFQhQb4jpRWtlKZE+W" + "gaWolUaiohhSZCSiq1qVRVSqnaP/pmZu/2\ndu/OQa2lnZudee/Ne/Pe+723" + "fv5T1GxbaI1sp+m8Sez00PgotmyiDGnYtidgaUZ+q7ll9Oxe3Yig\npiyKqA" + "pFqaxsSwqmWFIVKfPVQddCA6ahzec1g6aJS9OHtW2evDuz22oEHjh1vvPhM7" + "GeCGrOohTW\ndYNiqhr6sEaKNkXt2cO4hCWHqpqUVW06mEVLie4Uhwzdplin" + "9v3oQRTNorgpM5kU9WbLh0twuGRi\nCxclfrw0yo8FCV0WoVjVibKrchxwbg" + "1ygtoe31gtNQhZwjanwByuAVi9rmK1sLbGVDN6bupLR08/\nE0WpaZRS9XEm" + "TAZLKJw3jdqKpDhLLHuXohBlGnXohCjjxFKxpi7wU6dRp63mdUwdi9hjxDa0" + "EiPs\ntB2TWPzM8mIWtcnMJsuRqWFV7iinEk0pvzXnNJwHs7t9s4W5u9k6GJ" + "hUQTErh2VSZokdUXXweE+Y\no2Jj/14gANZEkdCCUTkqpmNYQJ3ClxrW89I4" + "tVQ9D6TNhgOnULS6oVB21yaWj+A8maFoZZhuVGwB\nVQu/CMZC0fIwGZcEXl" + "od8lKVfwa6Pztx7sev7eSxHVOIrDH9k8C0NsQ0RnLEIrpMBOM1J/39zD3O\n" + "mghCQLw8RCxodm04P5n98696BM0NdWj2zx4mMp2R953sGXvgDgNFmRpLTMNW" + "mfMDlvN0GPV2Bl0T\nsra7IpFtpsubF8bevufYs+QvEZTMoLhsaE4R4qhDNo" +
                                                   "qmqhHrDqITC1OiZFAL0ZUhvp9BCZhnIeTF\n6v5cziY0g2IaX4ob/B2uKAci" +
                                                   "2BW1wFzVc0Z5bmJa4HPXRAgl4EFt8ESR+OO/FG1MS7aj5zRjTrIt\nWTKsfO" +
                                                   "V9WDZGDIWkWbyYFN0uFYwikbCMdVU3pLwKGSobNyukxH6vS4rL9Omca2piAB" +
                                                   "dOVA1ifI+h\nKcSakc9efffo8N5vnxBBwALXs4SiLhCe9oSnPeGoqYnLXMYO" +
                                                   "EVcPF3cEUhDAqm3z+KE77zvRBwa7\n5lwMrGakfaCzdzIIGfLzNMMhTYZgWf" +
                                                   "mTg8fT187uEMEiNYbTutytl164ePofbVsiKFIf65hFgLZJ\nJmaUAWQFw/rD" +
                                                   "2VFP/t8eHXnl/Ysf3OTnCUX9Nelby8nSry9895YhEwUAzRd/ZlUqegBNnYyg" +
                                                   "GOQ0\n4BjXHyBibfiMQBoOliGN2ZLIotacYRWxxrbKOJSkBcuY81d4ULTzeR" +
                                                   "c4J8nisgOeFV6g8l+2u9xk\nY7cIIubtkBUcMq99M/6FP77a+ha/ljK6pqrq" +
                                                   "1zihIlc7/GCZsAiB9Q9+OPq9H3x6/CCPFC9UKEqY\nllqC7HSBZ6PPA1mqAV" +
                                                   "IwT/ZP6kVDUXMqntUIC7n/pDbc8rO/PtEufKPBStm1Wz9fgL++6nZ07OLX\n" +
                                                   "/rmWi2mSWZXw7fDJhDldvuRdloXnmR7uw7+/8Ue/xk8BiAFw2OoC4ViAuGmI" +
                                                   "X6TEL34LH9OhvVvY\n0AeytzaI/To1eUY++my+z7n/N7/gWrfi6uJe7YcRbA" +
                                                   "4K17NhPbvdFeH03YPtAtB98cK+e9u1C/8G\nidMgUYZaaO+3ACjcgBc96ubE" +
                                                   "5dff6L7vvSiK7EZJzcDKbswTALVA5BG7ABjjmjt28uBqn1vCRm4y\n4pew2r" +
                                                   "sAt+otAsptbpz/u1lF91NnZnbruey7+5/iF9Aw8+sUtJCchdcmT137Hb3C5f" +
                                                   "gpyLh73VoA\nhS7I5/3y+6WO+MtPFyMoMY3aZa9Pm8KawwJ9GtoKu9y8QS8X" +
                                                   "2A+2CKIeDlYgZk04/auODSe/D9ww\nZ9Rs3hbKd5bqaACemJfvsXC+NyE+2c" +
                                                   "FZ+vm4SWRnhM03UxS3eRPnUpS0C9giCisK9tdZ/qJVVY30\nPkPntVaVGfy4" +
                                                   "5YhjhqYtkmP5xLDQnf9o0+V1v20fuigyr0DRhqor8SiljF4yZO7RPVhXoI6L" +
                                                   "RFxT\n98ADFjahL7r04ceHnhz45G1WU0wBZGy8lQ07Rchtaxia24MguRqepd" +
                                                   "6lLW1waSNs2EhRq+xYANKU\nX4yFVlZ/XVhqEbqUEsfvq4/0/fKdyaePi5q3" +
                                                   "SMwHuGbkh/704ZHok6/PCr5wYIeIT6498/ErV8eW\nCXgU7ez6mo6ymke0tD" +
                                                   "xyUiaDit7FTuDUbw70Pv/g2BVPo85gYzYMHy+fzL9BNt32+Ed1eowoNN0h\n" +
                                                   "5+xbxDlubWRC3YibzqwGQRlAFVD9xka9L1f7+N1/b3sEv3ko4mHwFAXYMsyb" +
                                                   "NVIiWpWoKJMU6GFG\neLfvQ8Cjzzx3nr43sF1cwJbGrgwzbtl+eqFn+0uP/Q" +
                                                   "+dS0/ItrDojtINd0UL6jsR/kEiEKXmQybI\nNBjEkSTo41j6RABN1lUSg3uk" +
                                                   "H564lxjx+t1DfSg5xIYhd5GSqC2yp7MhH8QgtjTMx4wfS4XPS/RK\ntLAXuW" +
                                                   "JcC1vshiflGZeqaxwbDi6i52L2LbDBgaYnTzhWsNcJX/PS/6N5DzxdnuZd16" +
                                                   "15k8gnrgin\n+sYi6n+LDQ+B+rZQnxONmkKdSYpiJUNVfHOOXa85UFkS3rcG" +
                                                   "g/iVNf9kEB/GcvbyA/d+lv3Dv3jX\nXPl4bYUvyJyjadXlsGoeN6GiqFz/Vl" +
                                                   "EcTf7zBMB21ccOs0rMuGqPC6KTgFZAxKbfNfnGCora/bIm\n6ncQhJgJ6wN4" +
                                                   "wP9BU85ZR/yLZka++4WD69zHJr7DgaBZ1vACD49kFiVEW1/J+96G0sqyLqGX" +
                                                   "X5p6\n9cWvlHGNd33LqrIxEDm3it1FvANYU7+VHi6alDe/Cz9f8dPbzp66wk" +
                                                   "ut+V8TPp0DVxMAAA==");
}
