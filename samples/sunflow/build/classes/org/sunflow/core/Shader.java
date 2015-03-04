package org.sunflow.core;
import org.sunflow.image.Color;
public interface Shader extends RenderObject {
    public Color getRadiance(ShadingState state);
    public void scatterPhoton(ShadingState state, Color power);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK1XXWwURRyf236X0i8+BVroBySlcBti0GCRAE2BlgOaFkyo" +
                                "gTLdnbtburuzzM61R7EGSQzEB2K0KBjtE0RRvmIkaAwJTwLBF4zR+CD4plF5" +
                                "4EUfUPE/s7d7e9sWEmOTnZud/X9//P7TCw9QictQu0PNwymT8jjJ8vhBc22c" +
                                "H3aIG+9JrO3FzCV6p4lddzecDWrNV2r+ePRmulZBpQNoDrZtyjE3qO32EZea" +
                                "I0RPoJr8aZdJLJej2sRBPILVDDdMNWG4vCOBZoVYOWpN+CaoYIIKJqjSBHVT" +
                                "ngqYZhM7Y3UKDmxz9xB6FcUSqNTRhHkcNRUKcTDDVk5Mr/QAJJSL95fAKcmc" +
                                "ZWhZ4Lvn8xSHT7WrE+/ur/20CNUMoBrD7hfmaGAEByUDqMoi1hBh7iZdJ/oA" +
                                "qrMJ0fsJM7BpjEm7B1C9a6RszDOMBEEShxmHMKkzH7kqTfjGMhqnLHAvaRBT" +
                                "999KkiZOga/z8756Hm4R5+BgpQGGsSTWiM9SPGzYOkdLoxyBj63bgQBYyyzC" +
                                "0zRQVWxjOED1Xu5MbKfUfs4MOwWkJTQDWjhaNKNQEWsHa8M4RQY5Whil6/U+" +
                                "AVWFDIRg4WhelExKgiwtimQplJ8HO9efPGJvsxVps040U9hfDkyNEaY+kiSM" +
                                "2BrxGKtWJt7B86+fUBAC4nkRYo/m2isPN65qvHHLo1k8Dc2uoYNE44Pa2aHq" +
                                "u0s629YVCTPKHeoaIvkFnsvy78196cg60HnzA4niY9z/eKPvq71HPya/Kaiy" +
                                "G5Vq1MxYUEd1GrUcwyRsK7EJw5zo3aiC2Hqn/N6NymCfMGzine5KJl3Cu1Gx" +
                                "KY9KqXyHECVBhAhRGewNO0n9vYN5Wu6zDkKoDB4Ug6cBeX/lYuFou5qmFlGx" +
                                "hm3DpirULsFMS6tEo6qLLceErLkZO2nSUdVlmkpZKnjXKCNqfxrrhMVFUTn/" +
                                "r7issL52NBaDwC6JtrUJHbGNmkA7qE1kNnc9vDR4RwnKPOc3RwtAQTynIC4U" +
                                "xD0FKBaTcucKRV6yINTD0LQAZ1Vt/ft6DpxoLoIqcUaLRaCysosW+i/AGDFI" +
                                "9uuWL26cufpe+zol3No1IbDsJ9wrlLq83t2MEDj/8XTv26ceHH9ZKgWKlukU" +
                                "tIq1E8oGsBAw5fVbh364f+/st0pgaBEH/MwMmYbGUTkeAvDBGueoIkCRsCMx" +
                                "uZ/HUcOUKPVBxRHmNYNwt2GmhpVgc/bYxKS+69war63qC5ugCzD+4nd/fx0/" +
                                "/dPtaTJUwamz2iQjxAxZpgiVUDM5bV0a3SGxrFuOCg06+Y3zn1zjd9tf8FSu" +
                                "nHniRRlvHvt10e4N6QMKUgqnltAOR5WCs1fMmmCmLI04HxV5fseF21tXaG8p" +
                                "qCgHWdPAcyFTRzgMoJQRmCe2CKg4qQSlzdGKZ1QjOgydvN6Vy/DVwevjrQoq" +
                                "BtyFWcMxYAPAeGNUeQFUdvi1KVSVQBCSlFnYFJ/8WVHJ04yO5k9kK1bLfR2k" +
                                "Rzxoib/xf8XXOY5Y53qtK+kXy7VRLE1ebsW2WSwtorJW5DsBgM+EehPJaN1j" +
                                "W1Q3kgYeMonoyb9qlq+5+vvJWq+CTDjxs7Pq6QLy589sRkfv7P+zUYqJaWLw" +
                                "5rszT+Y16Zy85E2M4cPCjuxr3zScuYk/gLkAWOwaY0TCa+xpDSVgB2atuG0Q" +
                                "GZ31kvZ5uXaI8OWwRbxvFMuzHM1KEd4HfCLdvugCRDMsGLrCbcqcKZLkweog" +
                                "abPE4YZc4vwE/qekFRqu5AleFMtmKafnCe4lxNLF0WxXwxxwqTdNOaSMobYn" +
                                "XFyZYcEsHckNe3W8/v7w+79c9No/ejOIEJMTE288jp+cUELXp5YpN5gwj3eF" +
                                "ksbO9gL4GP5i8PwjHuGJOPBGaH1nbo4vCwa544jSbnqSWVLFlp8vj3/50fhx" +
                                "JReZNo6KR6ihT01mFoDdG12iMhdOueV6NzPt0mRN+YLJPd9LSAhuTxVwhUlm" +
                                "TDOEOGH0KXUYSRrSgAqv0x35MwA3/Wghg4HiR9q31yPbB4UaIuOoLLcLEx3g" +
                                "qAiIxBZDdFDBPHWi07WloBDkzd/H3ox39x/ULk/27Dzy8LlzEshL4H+GsTF5" +
                                "U4SLrwdoAX43zSjNl1W6re1R9ZWK5X4eqsVSH2qIkG1Lp8ebLsvhEiHGPl/w" +
                                "2foPJ+/JofwvTFz3gJINAAA=");
}
