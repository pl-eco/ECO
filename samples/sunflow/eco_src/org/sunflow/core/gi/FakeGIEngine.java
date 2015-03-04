package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class FakeGIEngine implements GIEngine {
    private Vector3 up;
    private Color sky;
    private Color ground;
    public FakeGIEngine(Options options) { super();
                                           up = options.getVector("gi.fake.up",
                                                                  new Vector3(
                                                                    0,
                                                                    1,
                                                                    0)).normalize();
                                           sky = options.getColor("gi.fake.sky",
                                                                  Color.
                                                                    WHITE).
                                                   copy();
                                           ground = options.getColor("gi.fake.ground",
                                                                     Color.
                                                                       BLACK).
                                                      copy();
                                           sky.mul((float) Math.PI);
                                           ground.mul((float) Math.
                                                                PI);
    }
    public Color getIrradiance(ShadingState state, Color diffuseReflectance) {
        float cosTheta =
          Vector3.
          dot(
            up,
            state.
              getNormal());
        float sin2 =
          1 -
          cosTheta *
          cosTheta;
        float sine =
          sin2 >
          0
          ? (float)
              Math.
              sqrt(
                sin2) *
          0.5F
          : 0;
        if (cosTheta >
              0)
            return Color.
              blend(
                sky,
                ground,
                sine);
        else
            return Color.
              blend(
                ground,
                sky,
                sine);
    }
    public Color getGlobalRadiance(ShadingState state) { return Color.
                                                                  BLACK;
    }
    public boolean init(Scene scene) { return true; }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1166304750000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYfWwcxRUf3/kj/kD+SuwkxHHsGFLi5LZVAm3jSGBcO5hs" +
                                                   "sHtODDikZrw7d954\nb2fZnT2fTURBVEka1I+orQQSJBGNlEChINEqrURpEN" +
                                                   "BSokpQqVRCIm0Vqa3UUqmqRFO1f/TNzO7t\n7d6dQ+hJu7e3896b9/l7b+6F" +
                                                   "j1Cd66ANmptiSzZxUyNTk9hxiT5iYtfdD69mtbfqGifP7bVoAtWo\nKGHoDL" +
                                                   "WqmqvomGHF0JXxLw0VHDRoU3Mpa1KWIgWWOmze6su7W721TOC9py50PHa2tj" +
                                                   "eB6lTUii2L\nMswMao2aJOcy1KYexnmseMwwFdVw2ZCKbiCWlxuhlsuwxdyH" +
                                                   "0CMoqaJ6W+MyGepTg80V2FyxsYNz\nithemRTbgoROhzBsWEQfLm4HnNuinK" +
                                                   "C2z5cupwYhq/jiNJgjNACrNxWtltaWmWonz0/fduTMc0nU\nOoNaDWuKC9PA" +
                                                   "Egb7zaCWHMnNEccd1nWiz6B2ixB9ijgGNo1lsesM6nCNrIWZ5xA3TVxq5jlh" +
                                                   "h+vZ\nxBF7Bi9V1KJxmxxPY9Qp+ihjEFMPftVlTJwFs7tCs6W5Y/w9GNhkgG" +
                                                   "JOBmskYKldMCyIeG+co2jj\nwF4gANaGHGHztLhVrYXhBeqQsTSxlVWmmGNY" +
                                                   "WSCtox7swtD6qkK5r22sLeAsmWVobZxuUi4BVaNw\nBGdhaE2cTEiCKK2PRa" +
                                                   "kkPoNdHx8///Rrd4jcrtWJZnL9m4BpY4wpTTLEIZZGJONVL/Xd8fu9DQmE\n" +
                                                   "gHhNjFjSDN904YD6l5/3SpobK9BMzB0mGpvV7jnZm354D0VJrsYqm7oGD37E" +
                                                   "clEOk/7KUMGGqu0q\nSuSLqWDxYvoX9z/6PPlrAjWNo3qNml4O8qhdoznbMI" +
                                                   "mzh1jEwYzo46iRWPqIWB9HDfCsQsrLtxOZ\njEvYOKo1xat6Kn6DizIggruo" +
                                                   "EZ4NK0ODZxuzefFcsBFCDXCh7XC1IfkR3wztSCmuZ2VMuqi4jqZQ\nJ1v8rV" +
                                                   "GHKFlDGcMLZM/4qJWFjVM8eWyGJpR5miMK1rBlWBSooFw1ul0nef59/SILXN" +
                                                   "OOxZoaDn3x\nEjYh+++ipk6cWe3clXeOjO79+nGZHjylfRsZ2gQ7pfydUnyn" +
                                                   "VNZIle6EamrEBqv5jjJC4N8FqFTA\ntJZbpg7d/eDx/iSkhr1YC87hpP1gja" +
                                                   "/GqEZHwnIeF8inQU6tffbgsdTVc7fLnFKqo25F7uZ3X7x0\n5p8tWxMoURkS" +
                                                   "uXkAyk1czCTH0SLUDcSLqJL8v5/Y98r7lz78TFhODA2UVXk5J6/S/nggHKoR" +
                                                   "HXAv\nFH92XWvyXjR9MoFqofQB7oT+gCQb43tEqnUoQD5uS4OKmjPUyWGTLw" +
                                                   "Vw1cTmHboYvhEZ0iaeOyE4\nzTx9u+Fa4+ez+Oara2x+75IZxaMds0Ig69XH" +
                                                   "6z/7u1eb3xJuCUC4taTNTREmS7o9TJb9DiHw/sMn\nJ7/zvY+OHRSZ4qcKg9" +
                                                   "7nzZmGVgCWm0MWqGUT8IQHcuCAlaO6kTHwnEl4xv239abP/fhv32yToTHh\n" +
                                                   "TRDZbdcWEL5fdyd69NJX/rVRiKnReC8JzQjJpDWdoeRhx8FLXI/CY7/peeqX" +
                                                   "+BmAOoAX11gmAjFq\n/CLgSnUD8JVV14Qt1BKOVgTZVnFP8UgIZiTWdvBbP2" +
                                                   "y+rUptVGjts9qR57P93kO/+qkwqxmXzgil\ncdqH7SGZGvy2mbu/O17ed2F3" +
                                                   "Huh2XrzngTbz4n9A4gxI1KCluhMOoEohEmWfuq7hg9ff6HrwvSRK\njKEmk2" +
                                                   "J9DIsCQY2QmcSdB0Aq2LffIcF0cVUAqQUknLDed0Ch5FcSlLulOj6M8cEgLK" +
                                                   "3ZuW3n1Xcm\nnhEOqIoMFfpiTM7yawdOXf01uyzkhCXKufsK5WgLw1TI+4X3" +
                                                   "8+31L5/OJVDDDGrT/HFvGpseL4QZ\nmE7cYAaEkTCyHp00ZFsdKkLQhjg8lG" +
                                                   "wbB4cQ5eGZU/PnlhgetAR40O7jQXscD2qQeBgWLAPivqVY\nvQ22Y+QxHwFR" +
                                                   "wrMr5nwO+mlqmvAc2CGxhd938tudMsqfr5oNu6N6dsHV4evZUUXPvfw2wiBp" +
                                                   "FpYC\nfbpL9TFyMHBxfKBOTB31OtVZB1enr05nFXXSvjr1WYd6ln6dGk2toJ" +
                                                   "EI5M0lZVKEnHVlkBN0cl7m\nPdVmSDH/HrvvHy1H8ZuHZFfuiM5lo3B2+fPS" +
                                                   "G2TL7m/8scIg0ciovd0keWLGircnMg3sE+N1WCwn\nnvvBBfbe4C655dbqhR" +
                                                   "5n3LrrzHLvrpee+BQzQG/MCXHR7fkbv5ycN95OiBOArL2yk0OUaShacU2g\n" +
                                                   "j+dY+yN1tynah1W4evwE6qnYh8MYhz0iIfyaCKLdUxbtqXmsw/mEn9DItRJO" +
                                                   "bDO/Qhey+A1Q6YYs\nTNHQ+nSDG3stsWEKZ65VVAHiix846qDb4NriO2jLJ3" +
                                                   "ZQrAOv7CAhprCCA47wm8dQOzhgj0nnsJm+\nbifk/x8nDMA16Dth8NM6oavc" +
                                                   "CRocnwT/0RWsP8FvjzN+RDIYlPLa0v9XHCMH57S8GE2vHO3/2dsH\nTh+TVb" +
                                                   "xCu45wzWpf/f0fFpLfen1O8sV7coz45Mazf3rlSnq1BB95oN9cdqYu5ZGHem" +
                                                   "FKq83hr2+l\nHQT1m4N9LzySviw04nyHoM3NUWoSbIUR/donjSh0xpbSwxSf" +
                                                   "JteW/dki/yDQ1A8efuBj9bf/FseC\n4iG+GU7SGc80S/t5yXO97ZCMIVRtlt" +
                                                   "3dFl9PMdRZ4WgHnTprCBWflHRPM9QWp4OA869SstMMNZeQ\ngVP8p1KiZ6Hx" +
                                                   "AhF//H5xHGgTQyKfZ1JynilEPMQ9sjmSL+J/rwCZPfnP16x234sHNxWe2P9t" +
                                                   "Afd1\nmomXl7mYJhU1yGNQEd37qkoLZL2LXn5p+tUffjGIsZiCVxfC9hspyJ" +
                                                   "1ydYWIQ0epfPYYzdlMnBaW\nf9L9o93nTl1OiNPP/wCJvkj0rhQAAA==");
}
