package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class IDShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal();
                                                   float f = n == null ? 1.0F
                                                     : Math.
                                                     abs(
                                                       state.
                                                         getRay().
                                                         dot(
                                                           n));
                                                   return new Color(state.
                                                                      getInstance().
                                                                      hashCode()).
                                                     mul(
                                                       f);
    }
    public void scatterPhoton(ShadingState state, Color power) {
        
    }
    public IDShader() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1160854852000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVXe2wURRif3vVBH+ZKoQVRWlpQ3rcQxURKlFqLHBxy9HhZ" +
                                                   "1Drdnbtburez7s62\nRyX4ioIaH0RNNFEkhgRFERM0aIKK8S3/qImamIgaEj" +
                                                   "VRTIyJYvQPv5m5vdvbuxbFS252dvZ7zPf6\nzTcvnkE1jo0uVp0o22ERJ9qb" +
                                                   "TGDbIVqvgR1nIywNqu/V1CcOrjVpCFXFUUjXGIrEVUfRMMOKrimx\na7tzNl" +
                                                   "poUWNH2qAsSnIsut1Ylpe3Jr6sTOCWfcda7jxQ3RFCNXEUwaZJGWY6NfsMkn" +
                                                   "UYao5vxyNY\ncZluKHHdYd1xdAEx3WwvNR2GTebcinahcBzVWiqXyVBn3FOu" +
                                                   "gHLFwjbOKkK9khBqQcIUmzCsm0Tr\nKagDzkWlnLDtPF9/OTUImcQ/bgZzxA" +
                                                   "7A6lkFq6W1ZaZa4ec2X7Fz//NhFBlAEd1McmEqWMJA3wBq\nypLsELGdHk0j" +
                                                   "2gCabBKiJYmtY0MfE1oHUIujp03MXJs4/cShxggnbHFci9hCp7cYR00qt8l2" +
                                                   "VUbt\ngo9SOjE0760mZeA0mN1WNFuau4qvg4ENOmzMTmGVeCzVw7oJEe8Ich" +
                                                   "RsnLMWCIC1LktYhhZUVZsY\nFlCLjKWBzbSSZLZupoG0hrqghaEZ4wrlvraw" +
                                                   "OozTZJCh6UG6hPwEVPXCEZyFodYgmZAEUZoRiJIv\nPgvbft/z3FNvrhS5Xa" +
                                                   "0R1eD7bwCm9gBTP0kRm5gqkYxn3ehjsRvci0MIAXFrgFjS9FxybFP8x7c6\n" +
                                                   "JM1FFWjWD20nKhtUr9/b0X/bdRSF+TYmWdTRefBLLBflkMh/6c5ZULVtBYn8" +
                                                   "Y9T7eKL//RvuOER+\nCqGGGKpVqeFmIY8mqzRr6QaxryMmsTEjWgzVE1PrFd" +
                                                   "9jqA7mcUh5ubo+lXIIi6FqQyzVUvEOLkqB\nCO6iepjrZop6cwuzjJjnLIRQ" +
                                                   "HfzRXPg3IPkTT4YuiyqOa6YMOqo4tqpQO114V6lNFCeDNWIDpCTF\nJMqTx2" +
                                                   "JovZKhWaJgFZu6SZW0DuWq0sUaGeHP/y4yx3faMlpVxaEvWMIGZP9qagDtoH" +
                                                   "rw9Mc7+9be\nt0emB0/pvI0MzQJN0bymKNcUlZqiniZUVSUUTOUaZYTAv8NQ" +
                                                   "qYBpTfOTN625ZU9XGFLDGq0G53DS\nLrAmv40+lfYWyzkmkE+FnJr+7Lbd0b" +
                                                   "MHr5Y5pYyPuhW5Gz85fHL/b00LQihUGRK5eQDKDVxMguNo\nAermBIuokvxf" +
                                                   "7l939IuTX88rlhNDc8qqvJyTV2lXMBA2VYkGuFcUf+DCSHgL2rw3hKqh9AHu" +
                                                   "xP4B\nSdqDOkqqtdtDPm5LXRw1pqidxQb/5MFVA8vYdLS4IjKkmQ9TZbLwQA" +
                                                   "Y2KEDz7N21S7483viesNjD\n14jvBEsSJqt1cjEPNtqEwPrXTyQeffzM7m0i" +
                                                   "CfJZwOBYc4cMXc0By6VFFihTA6CCx2jOJjNLNT2l\n4yGD8GT6O3LJ0ld/fq" +
                                                   "hZet2AFS9oi84toLh+4TXojpM3/9EuxFSp/JgomlEkk9ZMKUrusW28g+8j\n" +
                                                   "d+dnM5/8AD8NKAbI4ehjRIABEpYh4ceocO98MS4OfFvChy6QvWicrK5wKA+q" +
                                                   "Ow+lu9xbP3pd7LoR\n+093fxjWYatbBlXongJK56H8UAJS/Gurxcc2HoJpwe" +
                                                   "pdjZ0MCLv8xPU3Nhsn/gK1A6BWhRPTWW9D\n2edKIp2nrqn76u132m75NIxC" +
                                                   "q1CDQbG2Cov8R/WQeMTJAN7krKtXim00j07io/ALErudkfdSruRN\nvMwW46" +
                                                   "X57OHzeX6qKjGfxtC0MriSKMVtnDne+SjO9t1bf226F797k0ScltIzpw/6sh" +
                                                   "92vEPmrnjw\nuwogWc+otdggI8Tw7SnMVZYg3TrROhTr/P7nXzjGPl24XKpc" +
                                                   "MD7IBRkXLN8/1rH8yAPngW8dAScE\nRU8euWhDOKN/GBLdjYS2sq6olKnb7w" +
                                                   "5QCvtxbZM7lq80iXScVUjHRh7Ubm/iPf3pKICID8sDVRQS\nfg15sW4vi7Uw" +
                                                   "lUDTxcvUI2vzkyXlsycRE2rWTlCnG/iwGoDKteAOQCCa0/3XB1vPQhsyIpD3" +
                                                   "9L1d\nb3y46ZndMpDzJ7gj+LkG1du/+XY4/PDbQ5Iv2IoFiPe2H/j+6On+qT" +
                                                   "L/ZL86u6xl9PPInlUYE7F4\nBXROpEFQv7uw88Vd/afEjjjfSobqhig1CJZJ" +
                                                   "tZQPMVl6y85ZyOLl2tLQL4F/JB/6yL8OfVVpmc+s\nWObQdvOLBxFi8AShFY" +
                                                   "l5M0ONacL6gY9ncUUE0bPQf/MzJX/VEOYP/h/zr4J/a9781vPN/InNP5cl\n" +
                                                   "Qg2dwD0uH6DuL3BUzKCaEhnK8qCStKRVWxmqHqG6VvRK9t96JcfQJK955Efs" +
                                                   "9LLLpbwQqfGvbrvx\n9/jnf4o2qHBpaYSbQ8o1DB/i+NGn1rJJShdmNMqD0B" +
                                                   "KP2yudDbKVhRKXE7HNXZL+LrigB+nBZv7w\nk90DSeQjg2rJz/xEexgKAxGf" +
                                                   "3md50WkWpye/MUbl9aj01OOemV0CJOK+76G2K2/8g+rWw9tm5R7Y\n+Ig4Cm" +
                                                   "pUA4+Niasd3FRl+1dA/s5xpXmyPkEvH9l8/KUrveIvaQzLEnup/DpB1OG0qd" +
                                                   "yY9WUtJlqp\nsdemvbLi4L5TIdEa/gNsMmF0phEAAA==");
}
