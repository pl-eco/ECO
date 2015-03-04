package org.sunflow.core;
public interface ImageSampler {
    public boolean prepare(Options options, Scene scene, int w, int h);
    public void render(Display display);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUZDXAU1fndXf4TuJBIiCiRn6AD0bvSKc4ATiWJQYIHSUmk" +
                                "NW09Nrvvcgt7u8vuu+SI4KgzHRhnRMaigqOZcQaqtCCOlVrb2sHaVqi1LY61" +
                                "pa1oO52R2tLCTH+mRaXf997d7t7m7kgCZua+7L33ve//7+0dOkvKbYu0mYa2" +
                                "dUgzWIRmWGSTtjTCtprUjqyJLe2VLJsqnZpk2/2wFpfnPx/+94XdyfogqRgg" +
                                "jZKuG0xiqqHb66ltaMNUiZGwu9ql0ZTNSH1skzQsRdNM1aIx1WYrYqTWc5SR" +
                                "1lhOhCiIEAURolyEaLuLBYemUT2d6sQTks7sLeReEoiRClNG8RiZl0/ElCwp" +
                                "lSXTyzUAClX4fQMoxQ9nLDLX0V3oPE7hR9uiex6/u/6FEAkPkLCq96E4MgjB" +
                                "gMkAqUvR1CC17HZFocoAmaFTqvRRS5U0dZTLPUAabHVIl1jaoo6RcDFtUovz" +
                                "dC1XJ6NuVlpmhuWol1CppuS+lSc0aQh0bXJ1FRquwnVQsEYFwayEJNPckbLN" +
                                "qq4wcp3/hKNj6x2AAEcrU5QlDYdVmS7BAmkQvtMkfSjaxyxVHwLUciMNXBiZ" +
                                "XZQo2tqU5M3SEI0z0uzH6xVbgFXNDYFHGJnpR+OUwEuzfV7y+Ofsult23aOv" +
                                "1oNcZoXKGspfBYdafIfW0wS1qC5TcbBucewxqemVnUFCAHmmD1ngvLTt/Mob" +
                                "W44dFzjXFMDpGdxEZRaX9w9OP3lt56JlIRSjyjRsFZ2fpzkP/97szoqMCZnX" +
                                "5FDEzUhu89j6n9513zfpX4OkpptUyIaWTkEczZCNlKlq1Lqd6tSSGFW6STXV" +
                                "lU6+300q4Tmm6lSs9iQSNmXdpEzjSxUG/w4mSgAJNFElPKt6wsg9mxJL8ueM" +
                                "SQiphA8JwOdqIv6qEDDyhWjSSNGoJEu6qhtRiF0qWXIySmUjakspUwOv2Wk9" +
                                "oRkjUduSo4Y15HyXDYtGu1Pg9z6OaUUwtMxPg2gGNakfCQTAyNf6U1yD7Fht" +
                                "aAq14vKedEfX+efibwSdkM/agJE5wCaSZRNBNhEvGxIIcOpXITvhPjD+Zkhj" +
                                "KHB1i/q+umbjzvkhiBtzpAxNl+F51Zz7Agd9YvEMXvXysX1Hn2hbFvQme9hT" +
                                "PvsoE6Ezw+Xbb1EK6+/u7f36o2d3fJkzBYwFhRi0IuyEQILqCFXma8e3nHrv" +
                                "9P63g46gIQYVNT2oqTIjVdIglCNJZoxUO3VlnCJziiUnLyz7H9gzpvQcWCJS" +
                                "qCE/4Lugnh9+5+OfR/a+f6KAB6qZYd6k0WGqeXjWIkuIjCy3LtlYy+tWN28L" +
                                "MmTtgwe/9RI72bZcsFxcvLv5D77+wIez+z+f3BgkwfwOhdxhqQZP9mJfcfrH" +
                                "dT7l/SQPrj104vbr5UeCJJQtTwVKcf6hFV4zAFOLQu/Q0aC4UgNM5/sj2jJk" +
                                "qkCDcfkunisdjb+yvTVIyqDGQl9hEtQBKNktfuZ5ZXFFLuqQVTkYIWFYKUnD" +
                                "rVxfqGFJyxhxV3iqTefPM8A9jRgWs+ETzhYO/h93G02EV4nU5PjXcNiCYB73" +
                                "bRAf5yNYgJF1vRvjUOQ0KLTojNY79ZShqAlVGtQoZttH4YVLjv5tV72IIA1W" +
                                "ct658dIE3PWrO8h9b9z9nxZOJiBjk3XzzkUT6dfoUm63LGkrypG5/605+16X" +
                                "noIeAHXXVkcpL6VlXLUyrutM6AjjykqPycXKITSNQ+iToeID00UlBjVLTUHv" +
                                "GM42t+j2hvc2P3nmsEgBfyf0IdOdex68GNm1J+gZFxaM69jeM2Jk4J6cJjx/" +
                                "Ef4C8PkEP+hxXBAto6Ez27fmOo3LNNG980qJxVms+uDI9u8/u30HqoFsbmAk" +
                                "BIUIHzv4wnLOfSmHy5Bdtizh924ESxipNC0K5U6E660IOkUN64K9QcPQqKSb" +
                                "4yjwhTYnrGtx8Qb4NGfDunmqYZ0vcIAjBIoHx22qbWrSVk6+v4S2X0LQA8Ub" +
                                "chk6WyFly4YNVSmtKfdaYTc2uXOgGHoifO4GV5bQPORqDqLBFAze9ZvAqwUt" +
                                "sSeKUhuCeCZnsHqehyhVREhVSg4GxU3VJT4hypymgiCBIAntbogy3rzskl2j" +
                                "Lw1d0TOBPqSOvfmzf4XvF7mWn6P8EpI96j936rehz9ay1od5tykblGxe36ug" +
                                "AtuIycjc4hcaTkskYO0lPTfT9Rxn7zguZ8Swa0SOgMvpvAQtbIS43J2K9x09" +
                                "teNmXjrCwyrMr1Tpz96x8iuoOx2tyLt3FTRTXD5z5KHj8z7c0MgH6pxFvJPQ" +
                                "WskcNwmtluwkrJdX/u7V15o2ngyR4CpSoxmSskrCOxVMxtC8qJ2E4S9j3rqS" +
                                "J3LdCI629TwLLbKwiMpZnXjLiMvbnvzkzb9sP30iRCqgIWJXh/oCDZyRSLGb" +
                                "rJdAaz883QanoN1OF6fhXuWYHkKgwVl1ejsjNxWjzeu/bwTAuyAUEWp1GGmd" +
                                "h/lnfHNF2jS9uzyY6qYeTPdCu52A8Rzds3WUNPC0me6GIA6F3k0YBBs7Y+19" +
                                "ffH+u3q74hva13e3d8S6eJCasBnox0c1U8J7X1Q1pVOyFJF4By5WL1g568Ry" +
                                "nnjjjTRFwzRzw0gjLIr9ztCp7lZIU0g4bnRuLSKv57VHXN799rlpG8798DzP" +
                                "Mf9UOirGr2xRtMgs/3UkmxKfO7buK/XasQtAZACIyDK17R4LGgU/vy2bC/VO" +
                                "LpAMKViEdQQWgocRPOKtyRPrA6L4lqjzj5fY2+fnp16q44o6/xiCvQiegDqf" +
                                "BJt0GgrPhA5+dJenq+wuaAwY9QsPk10pk/Hxb/S7s1685Zmx0/wuhYSe4vQR" +
                                "jCF4eoK2uvS0IKIJ4TdK2OrZCdqKk5vPMQ4geAbBQWjXdEtaEjevOyZmJK4l" +
                                "gkP8EILDV0hzr2LfLrF3dPJKv4DgRQTfgdhghnjLVWDA8GxM3BQvI/gegh9M" +
                                "xhSTSZgfldj78QTt4fJLuVnzKoLXEPwEpzeDqYmt+G1gkjY4zg8hOPFp2eCX" +
                                "JfZOXo4NfoHgVwjeYqRa2KBd06Zihl87ZnjnSprBe4XwT/yaoQ9x2n8ofe9o" +
                                "caO8G1/0WGkTBrmujEz53ZST+OPlmPH3CN5H8GcQa0RS2VQs+IFjwTOTsWCJ" +
                                "uhJ0Ed5FIK6Vfy9ed//EEc5Nug+lONpZBP9AcN7VZ8pG+OcVMoI3Wf53KdU/" +
                                "mqLq/0VwAcHHl696QKxlGKnzvpTF60DzuF90xK8Q8nNj4apZY3f+hr8Sc34p" +
                                "qI6RqkRa0zyTsXdKrjAtmhC9tlqMWvy+GyiDzuC/q0Ng4z/UIxASaJWM1HrQ" +
                                "GKnMPnmRahgJARI+1sKwSPKmRNM/My4oenNamxa/c8XlI2Nr1t1z/uYDfMIt" +
                                "hyl0dBSpwMWiUrzQ40TxDdm8otRytCpWL7ow/fnqhbl3MHzWbPBEWrMnep7+" +
                                "P0nGa2ZTHAAA");
}
