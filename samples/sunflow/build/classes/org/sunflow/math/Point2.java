package org.sunflow.math;
public final class Point2 {
    public float x;
    public float y;
    public Point2() { super(); }
    public Point2(float x, float y) { super();
                                      this.x = x;
                                      this.y = y; }
    public Point2(Point2 p) { super();
                              x = p.x;
                              y = p.y; }
    public final Point2 set(float x, float y) { this.x = x;
                                                this.y = y;
                                                return this; }
    public final Point2 set(Point2 p) { x = p.x;
                                        y = p.y;
                                        return this; }
    public final String toString() { return String.format("(%.2f, %.2f)",
                                                          x,
                                                          y); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYW2xURRiePb0XaEvLpRRoSynEAu4RjSZSgsJaoLjSSguJ" +
                                                    "JbJMz852Dz03z5mlS7FeiAaChhgtiEb7YDAIcouRoDEmvCgQfMEYjQ+C8UUi" +
                                                    "8sCDaLz/M3N2z9mz2woJbnJmZ2f+6/z//81/9th1VOLYaLFlajsGNJOGSZqG" +
                                                    "t2n3h+kOizjhddH7u7HtkHhEw47TC2sxpeVU9c0/XknWSKi0D9VhwzAppqpp" +
                                                    "OBuIY2rbSTyKqr3VDo3oDkU10W14O5ZTVNXkqOrQ9iia5GOlqDWaMUEGE2Qw" +
                                                    "QeYmyCs9KmCaQoyUHmEc2KDOU+gZFIqiUkth5lE0L1eIhW2su2K6uQcgoZz9" +
                                                    "3gROcea0jZqzvguf8xzev1gefX1LzQdFqLoPVatGDzNHASMoKOlDk3Wi9xPb" +
                                                    "WRmPk3gfmmoQEu8htoo1dZjb3YdqHXXAwDRlk+whscWURWyu0zu5yQrzzU4p" +
                                                    "1LSz7iVUosUzv0oSGh4AX2d4vgoPV7N1cLBSBcPsBFZIhqV4UDXiFDUFObI+" +
                                                    "tj4KBMBaphOaNLOqig0MC6hWxE7DxoDcQ23VGADSEjMFWihqGFcoO2sLK4N4" +
                                                    "gMQoqg/SdYstoKrgB8FYKJoeJOOSIEoNgSj54nN9/fJ9O421hsRtjhNFY/aX" +
                                                    "A1NjgGkDSRCbGAoRjJMXRQ/gGZ/ukRAC4ukBYkFz5ukbDy9pPHte0MwuQNPV" +
                                                    "v40oNKYc6q+6NCfS9mARM6PcMh2VBT/Hc57+3e5Oe9qCypuRlcg2w5nNsxs+" +
                                                    "f+K5o+SahCo7Ualiaikd8miqYuqWqhF7DTGIjSmJd6IKYsQjfL8TlcE8qhpE" +
                                                    "rHYlEg6hnahY40ulJv8NR5QAEeyIymCuGgkzM7cwTfJ52kIITYEH1cJThMSH" +
                                                    "f1O0RU6aOpGxgg3VMGXIXYJtJSkTxYzZxDLljkiX3A+nnNSxPejITspIaOZQ" +
                                                    "TEk51NRlx1Zk0x7ILMs6KIUzgaS9N8zyzPrfNaSZjzVDoRAc/5xg8WtQN2tN" +
                                                    "LU7smDKaWtVx40TsopQtBvd0KJoJCsKugjBTEBYKUCjE5U5jikRIISCDUNoA" +
                                                    "epPbep5ct3VPCxxk2hoqZkcKpC3glqu9QzEjXv13cpRTIAnr39m8O/zb4YdE" +
                                                    "Esrjg3VBbnT24NDzm569R0JSLuoyb2CpkrF3M6zMYmJrsNoKya3effXmyQMj" +
                                                    "pld3OTDuwkE+JyvnluC526ZC4gCQnvhFzfh07NORVgkVA0YALlIMeQyQ0xjU" +
                                                    "kVPW7RmIZL6UgMMJ09axxrYyuFZJk7Y55K3whKji86kQlEksz9mk1E18/s12" +
                                                    "6yw2ThMJxKIc8IJD8OqPz75x+s3FD0p+tK723X89hIran+olSa9NCKx/d7D7" +
                                                    "tf3Xd2/mGQIU8wspaGVjBJAAQgbH+uL5p769cvnQV1I2q0IUrsRUv6YqaZCx" +
                                                    "0NMCOKEBVrHYt240dDOuJlTcrxGWnH9WL1h6+ud9NSKaGqxkkmHJfwvw1met" +
                                                    "Qs9d3PJrIxcTUtg95XnukYkDqPMkr7RtvIPZkX7+y7lvnMNvA4wCdDnqMOFo" +
                                                    "hLhniB+9zEO1iI/hwN5SNjRbeXt8oSE/xhVujCsKxpgNrQFtEpcogfltE3RN" +
                                                    "tqoDkG93bxp5pPbK4FtXj4sCDl5LAWKyZ3TvP+F9o5Lv7p6fd336ecT9zU2e" +
                                                    "Ilz8Bz4heP5mD3ONLQj8ro24l0hz9haxLJYo8yYyi6tY/ePJkU/eG9kt3KjN" +
                                                    "vbo6oDM7/vVfX4QPfn+hAGJCJZiY59QybugDEwRxFRvuu/UgVrlBrLrlIIZE" +
                                                    "ofB9TrVmAnM62fBIvjnCnvpbyYfVrIXzAfrvXVr/rh9+4+eUB8kFUiTA3ycf" +
                                                    "e6shsuIa5/ewkXE3pfNvNWh3Pd57j+q/SC2ln0morA/VKG4vvQlrKYZAfdA/" +
                                                    "OpkGG/rtnP3cXlA0Pu1Z7J8TzFGf2iAqe7kBc0bN5pWFgHgGPMVufIuD8Q0h" +
                                                    "Pnmch5i6SL6MR1WEK3r7wnozwnb4hIlQt/BxARvuElFn0zaW3KqBNX8+IFZR" +
                                                    "c8drWnk1Hdo1Ohbvenep5KbZCooqqGndrZHtRPOJYk3C3Jwm4THepnsh3Xvk" +
                                                    "/TP00uJloi4XjZ+GQcZzu35q6F2R3HobrUFTwKegyCOPHbuwZqHyqoSKspmR" +
                                                    "9+aRy9Semw+VNoFXJaM3Jysas4GsZ6c7G546N5B1ha/nCQLGhifShXE9G3QB" +
                                                    "U8kJcGEbG+CKK4K22pdzeYgVy7d9lmv7rDtjex6c2ROYzW3Vb9vsZniaXLOb" +
                                                    "7ozZfquenmDvGTYMUXh3N8ULKKeaTlENbyAYGoXFRgGQhmZI9Oas46jPe9kX" +
                                                    "L6jKibHq8pljG7/h3Wb2JbIC3uQSKU3zo5VvXmrZJKFyEysEdln860WwLPiC" +
                                                    "QFEx++L2vSDI9lA0yUdGUZk78xO9BIECIjZ92Srgt0DhNMrBHiuIRPNzMIH/" +
                                                    "MZKp35T4aySmnBxbt37njQfe5WBQomh4eJhJKY+iMtFDZzFg3rjSMrJK17b9" +
                                                    "UXWqYkEG26rYUOtLE59tTYX7yw7dorwjHP5o5ofLD49d5g3uv+WFJMCxEgAA");
}
