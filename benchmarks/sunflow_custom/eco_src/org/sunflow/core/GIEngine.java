package org.sunflow.core;
import org.sunflow.image.Color;
public interface GIEngine {
    public Color getGlobalRadiance(ShadingState state);
    public boolean init(Scene scene);
    public Color getIrradiance(ShadingState state, Color diffuseReflectance);
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1170609328000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVXW2wUVRg+u9t7S3qhtNxKaQG5FHbECAmUKAULLCxQt1Cg" +
                               "gOV05ux22tk5w8zZ\ndlsIQkwEJV6ImmiiyAMJF1FM1KAJKgRQ1BcxMSYkoo" +
                               "ZETZQHXxCjD/7nnJ29TC8gbjJnZ878l/Pf\nvv+fM7dQvmOjqaoTZIMWcYIr" +
                               "29uw7RBtpYEdZxNsdalX8ovbTqwzqR/5wsivawyVh1VH0TDDiq4p\noceakz" +
                               "ZqsqgxGDMoC5IkC/Yai1Ly1oYXDRO45ei5qgPH8+r9KD+MyrFpUoaZTs1Wg8" +
                               "QdhirCvbgf\nKwmmG0pYd1hzGI0jZiK+kpoOwyZzdqN9KBBGBZbKZTLUEHaV" +
                               "K6BcsbCN44pQr7QJtSBhvE0Y1k2i\ntaTVAef8XE44doovMpwahBTxlx1gjj" +
                               "gBWD09bbW0dpipVuBkx+K9x04FUHknKtfNdi5MBUsY6OtE\nZXES7ya206Jp" +
                               "ROtElSYhWjuxdWzoQ0JrJ6py9JiJWcImToQ41OjnhFVOwiK20OluhlGZym2y" +
                               "Eyqj\ndtpHUZ0YmvuUHzVwDMyuyZgtzV3F98HAEh0OZkexSlyWvD7dhIjXez" +
                               "nSNs5cBwTAWhgnrIemVeWZ\nGDZQlYylgc2Y0s5s3YwBaT5NgBaGJo8qlPva" +
                               "wmofjpEuhiZ66drkK6AqFo7gLAxN8JIJSRClyZ4o\nZcWnqeb2oZOvf7Jc5H" +
                               "aeRlSDn78EmKZ5mCIkSmxiqkQy3kkEXw5tS0z1IwTEEzzEkqZl1rnN4V8/\n" +
                               "rZc0U0ag2djdS1TWpW44Uh/Zs5qiAD9GkUUdnQc/x3JRDm2pN81JC6q2Ji2R" +
                               "vwy6Ly9EPtu2/zT5\nzY9KQqhApUYiDnlUqdK4pRvEXk1MYmNGtBAqJqa2Ur" +
                               "wPoUK4D0PKy92N0ahDWAjlGWKrgIpncFEU\nRHAXFcO9bkape29h1iPukxZC" +
                               "qBAu5INrLpK/Er5AvQUVJ2FGDTqgOLaqUDuWflapTZTVoVYzBhqD\nPGssht" +
                               "YoPTROFKxiUzepEtOhTlW6QCP9/P8/yErys1UN+Hwc7LxFa0C+r6GGRuwu9c" +
                               "TNL/e2rnvm\nkEwInsQpqxiaBCqCKRVBriLoqkA+n5BczVXJYIAr+6AoAb7K" +
                               "5rbvXLvrUGMAssAayOOOSIoqmew+\nAKPnSKIe7zxV8OB350uviKO4pVueBY" +
                               "7thMlEqMzo3WQTAvvfv9r20iu3Dm4XSqXWAENFuBsAAquM\noeJ0pTMA0kS3" +
                               "oavDTlU3Wt2Imj+49Y+yp/HlnTK7q3JzsRXw+pfBS2T2sud+GsGVxYxaCwzS" +
                               "T4ws\nnQGuEsKa0taq0vUCUkIC9lUoqGdPvXWOXWtaKlXOG73leBnnLT02VL" +
                               "/07GE/8o/cCvgpoBmVcAlt\nvH+kIb7e4wSv6Mr+KY8HevSrfoF6HEFGQMtc" +
                               "puZsd4BSOE/CNrlj+U4ZKG30pqhNVaJBD8joPT6p\nPLAFdRzxozyAQYB+YR" +
                               "Og6jSv8hzkanZTiasqDKPSKLXj2OCvXOguYT02HcjsiNqpEPfjIUylPD0W\n" +
                               "w1Wdqm3xz99OsPhaI2tN0NeJdbpMQD+/b+TLTJ5dD2SSFjDIABzkgZi52YxT" +
                               "TY/quNsgvHz+KZ+1\n8IPfn6+QWWTAjhuZ+XcXkNmftALt/+qJP6cJMT6V98" +
                               "BMIWXIZD2Nz0husW08yM+RPPBN3Wuf4zcA\nogEWHX2ICKTzCdN8ws5ahuqG" +
                               "YUR7D9ag7fHGT4RnlgraRWJdwl2XKjj+/AhfFjJUGSNstUG7sREB\nbh5wV0" +
                               "FttgI9Dl2QGw8NH/wxSj2MMMt0qXtPxxoTu7/4SPijFGcPRdkQsx5bzTL+fH" +
                               "mIx63WC3Jr\nsNMDdA9f2LCjwrjwN0jsBIkqnNnZaAOoJnMAKkWdX3j94qWa" +
                               "XdcCyL8KlRgUa6swH1yg/UD6EacH\n8DhpPbpcZFjFQBFfhZtQLkrxh6bc3J" +
                               "wJ19RUbk69n9zMjY8nwjXDI6xCRxWCN4wR2ghfQox3TZ2B\nGydmj9y2HofW" +
                               "3S8q9ObTjR9f3fzmQQlyc8eYq7O5utQnf/ixL/DCxW7J5x1fPMRHph3/+b2b" +
                               "kWpZ\nVXLGmzFszMrmkXOeMKXc4nnQMJYGQX25qeHMvsgNcSLON5uhwm5KDY" +
                               "Il4K7gy1rp79b7CnUYrjmp\nUM/5/6H2S4J7K+a7VaTQr42REb18gfF2HBR7" +
                               "CGDmHgs97bld9+q5JHR+d1Th8DZx2FeLnLTV8PU9\nO26Hv/1L9JT0NFwKI2" +
                               "k0YRhZLSu7fRVYNonqwqJSCRWW+ONfcl4HQvrzP3E6W5L1M1SaRQYpkrrL\n" +
                               "JhpkKABE/HbIcj1UIUCFf1oE5RydO8BwS2fkVI/4MHTbeEJ+GnapW9/ePj15" +
                               "eNOLYjbIh0/KoSHx\nDQCfNLI3pkeBhlGlubK+Ru+e7Tj/zhI34wVqVmdlYk" +
                               "4yr5Bvx4gijB8jN7nWuMVEWxr6sPb9ZSeO\n3vCLQe9fVWFcGs8PAAA=");
}
