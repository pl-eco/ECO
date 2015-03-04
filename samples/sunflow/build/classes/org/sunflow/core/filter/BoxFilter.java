package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class BoxFilter implements Filter {
    private float s;
    public BoxFilter(float size) { super();
                                   s = size; }
    public float getSize() { return s; }
    public float get(float x, float y) { return 1.0F; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXX2wURRif27bXPxR6Lf8qQoFSCG3xViRosASFSwvFgza0" +
                                                    "YChKme7NtUv3dpfdufYoVoHEQHggRguCwT4YCIL8i5GgMSS8KBB8wRiND4Lx" +
                                                    "RSLywINIRMVvZm537/auiA9esnuzM9/f+b7vN9+cuoOKbAs1moa2vVczaJik" +
                                                    "aHirtjhMt5vEDq+OLm7Hlk1iEQ3bdifMdSu15yruPXirLyShYBeaiHXdoJiq" +
                                                    "hm6vI7ahDZBYFFV4s80aSdgUhaJb8QCWk1TV5Khq06YoGpfBSlFd1DFBBhNk" +
                                                    "MEHmJsjLPSpgGk/0ZCLCOLBO7W3odRSIoqCpMPMomp0txMQWTqTFtHMPQEIJ" +
                                                    "+94ATnHmlIVmub4Ln3McPtAoj7y7OfRxAaroQhWq3sHMUcAICkq6UHmCJHqI" +
                                                    "ZS+PxUisC1XqhMQ6iKViTR3idnehKlvt1TFNWsTdJDaZNInFdXo7V64w36yk" +
                                                    "Qg3LdS+uEi3mfBXFNdwLvk7xfBUetrB5cLBMBcOsOFaIw1LYr+oximb6OVwf" +
                                                    "614CAmAtThDaZ7iqCnUME6hKxE7Deq/cQS1V7wXSIiMJWiiaNqZQttcmVvpx" +
                                                    "L+mmqNpP1y6WgKqUbwRjoWiyn4xLgihN80UpIz531i7dv0NfpUvc5hhRNGZ/" +
                                                    "CTDV+JjWkTixiK4QwVjeED2Ip1zcKyEExJN9xILmwmt3X1xQc+mKoHkyD01b" +
                                                    "z1ai0G7laM+E69Mj9UsKmBklpmGrLPhZnvP0b0+vNKVMqLwprkS2GHYWL637" +
                                                    "cuPOk+S2hMpaUVAxtGQC8qhSMRKmqhFrJdGJhSmJtaJSoscifL0VFcM4qupE" +
                                                    "zLbF4zahrahQ41NBg3/DFsVBBNuiYhiretxwxiamfXycMhFCxfCg+fAUIfHj" +
                                                    "/xStl/uMBJGxgnVVN2TIXYItpU8miiHbOGFqEDU7qcc1Y1C2LUU2rF73WzEs" +
                                                    "IoN6SB15hZFq4aMwSy/z/xKcYh6FBgMB2Ozp/lLXoEpWGVqMWN3KSHJF890z" +
                                                    "3dckN/XTewG4AqrCaVVhpiosVIVdVSgQ4BomMZUilBCIfihpALvy+o5XV2/Z" +
                                                    "W1sAOWQOFsIuMtJa8CttR7NiRLy6b+XopkDyVX+waU/4/vEXRPLJY4N0Xm50" +
                                                    "6dDgrg1vPC0hKRttmV8wVcbY2xlGulhY56+yfHIr9ty6d/bgsOHVWxZ8p2Eg" +
                                                    "l5OVca0/ApahkBgAoye+YRY+331xuE5ChYANgIcUQ/4C1NT4dWSVc5MDjcyX" +
                                                    "InA4blgJrLElB8/KaJ9lDHozPDUm8HElBGUcy+9J8JSkE57/s9WJJntPEqnE" +
                                                    "ouzzgkNvy2eXDp9/r3GJlInSFRnnXgehouYrvSTptAiB+R8Otb9z4M6eTTxD" +
                                                    "gGJOPgV17B0BBICQwba+eWXb9zdvHP1G8rKKwlGY7NFUJQUy5nlaAB80wCgW" +
                                                    "+7r1esKIqXEV92iEJeefFXMXnv91f0hEU4MZJxkW/LsAb/6JFWjntc2/13Ax" +
                                                    "AYWdT57nHpnYgIme5OWWhbczO1K7vp5x+DJ+H+ATIMtWhwhHoYBbL/WP6FEs" +
                                                    "NQGwOZDGdXm46mb/kVunRdn4DwEfMdk7su9heP+IlHFSzsk5rDJ5xGnJk2G8" +
                                                    "SJ6H8AvA8zd7WNKwCYGWVZE0ZM9yMds0WXhmP8osrqLl57PDn384vEe4UZV9" +
                                                    "UDRDH3T627++Ch/68WoexIL8MzDlNsrcxgb+DjOj+I4ivtbEXrPMnLUUn6l+" +
                                                    "nL1vYc1JBmT90ab17P7pPrcpB3TyhMPH3yWfOjItsuw25/eqn3HPTOUiODRy" +
                                                    "Hu8zJxO/SbXBLyRU3IVCSrpL3IC1JKuxLuiMbKd1hE4yaz27yxFHepOLbtP9" +
                                                    "+ZCh1o87XhxgzKjZuMwHNeUO1ATTUBP0Q00A8UGEs9Ty91z2mu9UerFpqQOY" +
                                                    "taAQIbayiKOTyTXVZcWPjSdTNDXnFBNnF8vGGWO1VzwTj+4eGY21HVsopdPm" +
                                                    "OYpKqWE+pZEBomWokpikrGNtDW8ovRDtO/HRBXq98XmR0w1jp5Wf8fLuX6Z1" +
                                                    "Luvb8h8Os5k+n/wiT6w5dXXlPOVtCRW4kc7pkbOZmrLjW2YRaOr1zqwo17hR" +
                                                    "Zg+qdsLt/OccKF7A8hfpy49Y28henZANvYR2AGbyRMitZz7Rnm0YS79Q2rDQ" +
                                                    "YxsmiUC7CbeIk3Y/wkTMXq9QVAAmjmEe5HCp20qxA6I6504m7hHKmdGKkqmj" +
                                                    "67/jzYHb65dCwx1Palpm6WWMg6ZF4io3plQUoqiSeL6aEJ0dZbcuNuC2EkGv" +
                                                    "wj3WT09RIfvLJIM0GpdBBsFJjzKJDNgQIGJD03QqNMTPRQZBYQFBKZRRXaw1" +
                                                    "yPzK6hNYAfH7rpPsSXHj7VbOjq5eu+Pus8d45RTBTXloiN+P4LonWiS3YGaP" +
                                                    "Kc2RFVxV/2DCudK5DhBMYK+qdF/ks21m/vahOWFSfuAPfTr1k6XHR2/w/uUf" +
                                                    "LugRKYgQAAA=");
}
