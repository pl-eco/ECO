package org.sunflow.core.accel;
import org.sunflow.core.AccelerationStructure;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
public class NullAccelerator implements AccelerationStructure {
    private PrimitiveList primitives;
    private int n;
    public NullAccelerator() { super();
                               primitives = null;
                               n = 0; }
    public void build(PrimitiveList primitives) { this.primitives = primitives;
                                                  n = primitives.getNumPrimitives(
                                                                   );
    }
    public void intersect(Ray r, IntersectionState state) { for (int i =
                                                                   0;
                                                                 i <
                                                                   n;
                                                                 i++)
                                                                primitives.
                                                                  intersectPrimitive(
                                                                    r,
                                                                    i,
                                                                    state);
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170005348000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Xb2wURRSf2/69o9A/QMEKBUpLpOCtGDGpRbDUAoWDNhRI" +
                                                    "KEqZ7s21S/d2l9259lqoAomB8IEYLQgG+8FAEORfjASNIekXBYJfMEbjB8H4" +
                                                    "RSLygQ8iERXfzOze3u1dUb94yczNzrw38968937vzZm7qMC20ALT0AZ7NIOG" +
                                                    "SZKGt2uLw3TQJHZ4dWRxO7ZsEm3WsG1vgLkupeZC6f2Hb/aWSaiwE03Gum5Q" +
                                                    "TFVDt9cT29D6STSCSr3ZFo3EbYrKIttxP5YTVNXkiGrTxgiakMZKUW3EFUEG" +
                                                    "EWQQQeYiyE0eFTBNJHoi3sw4sE7tHeg1FIigQlNh4lE0J3MTE1s47mzTzjWA" +
                                                    "HYrZ9yZQijMnLTQ7pbvQOUvhQwvkkXe2ln2Uh0o7UamqdzBxFBCCwiGdqCRO" +
                                                    "4t3EspuiURLtROU6IdEOYqlYU4e43J2owlZ7dEwTFkldEptMmMTiZ3o3V6Iw" +
                                                    "3ayEQg0rpV5MJVrU/SqIabgHdK30dBUarmDzoGBIBcGsGFaIy5Lfp+pRimb5" +
                                                    "OVI61q4BAmAtihPaa6SOytcxTKAKYTsN6z1yB7VUvQdIC4wEnEJR1bibsrs2" +
                                                    "sdKHe0gXRdP9dO1iCaiC/CIYC0VT/WR8J7BSlc9Kafa5u27JwZ36Kl3iMkeJ" +
                                                    "ojH5i4Gp2se0nsSIRXSFCMaS+shhXHl5v4QQEE/1EQuaS7vuvbSweuyqoHky" +
                                                    "B01b93ai0C7lePekGzOa5zfkMTGKTcNWmfEzNOfu3+6sNCZNiLzK1I5sMewu" +
                                                    "jq3/YvPu0+SOhEKtqFAxtEQc/KhcMeKmqhFrJdGJhSmJtqIg0aPNfL0VFcE4" +
                                                    "oupEzLbFYjahrShf41OFBv+GK4rBFuyKimCs6jHDHZuY9vJx0kQIFUFDL0Ir" +
                                                    "RuLH/yki8kYb3F3GCtZV3ZDBeQm2lF6ZKEZXN9xubxxbfbasJGxqxGU7occ0" +
                                                    "Y0C2LUU2rJ7Ut2JYBPZQiCavS2haExsxnQwrzNzN/L8OSjKNywYCATDGDD8U" +
                                                    "aBBFqwwtSqwuZSSxvOXeua7rUio0nLuiaB6cF3bOC7Pzwvy8sO88FAjwY6aw" +
                                                    "c4W9wVp9EPeAiCXzO15dvW1/TR44mjmQD1fNSGtAV0eYFsVo9sChlUOgAh46" +
                                                    "/f0t+8IPTi4THiqPj+Q5udHYkYE9m15/RkJSJiQz5WAqxNjbGZCmALPWH4q5" +
                                                    "9i3dd/v++cPDhheUGRjvYEU2J4v1Gr8ZLEMhUUBPb/v62fhi1+XhWgnlA4AA" +
                                                    "aFIMTg54VO0/IyPmG138ZLoUgMIxw4pjjS25oBeivZYx4M1w/5jEx+VglAks" +
                                                    "CKqglThRwf/Z6mST9VOEPzEr+7Tg+Lzi07GjF99d0CClQ3lpWnLsIFQAQ7nn" +
                                                    "JBssQmD++yPtbx+6u28L9xCgmJvrgFrWNwNMYO5yb1zd8d2tm8e/ljyvopAv" +
                                                    "E92aqiRhj3neKQAiGgAZs33tRj1uRNWYirs1wpzzj9K6RRd/OVgmrKnBjOsM" +
                                                    "C/95A2/+ieVo9/Wtv1XzbQIKS2Ke5h6ZuIDJ3s5NloUHmRzJPV/NPHoFvwcY" +
                                                    "C7hmq0OEQxXimiF+9TI3VT3vw761RaybbWatJfnMdP4lwdHzxw+iFSwXpwXf" +
                                                    "721a994fH3CNssInRwry8XfKZ45VNS+9w/k9P2bcs5LZgAR1i8f77On4r1JN" +
                                                    "4ecSKupEZYpTFG3CWoJ5SycUArZbKUHhlLGemdRFBmtMxekMfwylHeuPIA8I" +
                                                    "Ycyo2TjkCxoeI3XQgk7QBP1BE0B80MBZanhfx7qnXJ8tMi21H7OKC4VgGIcs" +
                                                    "2U9sTjgVoj4LgNtdmojjrGXC7M9lClUBLeQIFRpHqCbWNVIU0B/vGqkjReUg" +
                                                    "D1fc6jt2+6zAZb8f+IjJ/pEDj8IHR6S0em1uVsmUziNqNn7RE4VOj+AXgPYX" +
                                                    "a0wXNiFydkWzUzjMTlUOpsnif87jxOJHrPjp/PBnHwzvE2pUZJYrLVCNn/3m" +
                                                    "zy/DR364liMv5kEpKu6ey1mbFmYB13TZuTOVMSGOOnh6AOhnws4crwbkgh7f" +
                                                    "OzIabTuxSHKCfQ1FQWqYT2ukn2i+AJ+ZkVbX8qrXC6wDpz68RG8seEGoXD++" +
                                                    "xf2MV/b+XLVhae+2/5BMZ/l08m95au2ZayvnKW9JKC8Vn1mFfCZTY2ZUhiwC" +
                                                    "F6hvyIjN6syEJkMrd8KgPGdC8+znQWtAWJJ9LuNUrzwGe7eybjNFBd0J1XnN" +
                                                    "vMy6VQJ8V1OU32+o0Wxw5hMbM+VtgFbpyFv5r+WVHPs7njcly/PW40F3sSZr" +
                                                    "sZU9q2yRodgTkPCDeh6jcpx1EMhB1WVlE9ty5B+oAXy1IsuA07NepuI1pZwb" +
                                                    "LS2eNrrxW179pF48QXh2xGCXdEROGxeaFompXLCgwGcRlVA0VeauX8FY/J/L" +
                                                    "u0NQJ+At76cG07G/dDLQaEIaGeC3M0on2gkIAURsuMt0L76Mp32Wl8IiLyVR" +
                                                    "WvCyyif9K6MMYvHJ3/xuLCXEq79LOT+6et3Oe8+f4IFZoGh4aIi/EeHJKyrA" +
                                                    "VDzOGXc3d6/CVfMfTroQrHNxZhLrKpyyzyfbrNzVUUvcpLyeGfpk2sdLTo7e" +
                                                    "5OXZ30GtyxCMEQAA");
}
