package org.sunflow.core;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public interface Tesselatable extends RenderObject {
    public PrimitiveList tesselate();
    public BoundingBox getWorldBounds(Matrix4 o2w);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVWX2wURRifu/69Untt+Vf+FWgLCRRuQxQNlhBpU2jxgLMt" +
                                "GGrkmO7OXRdmd5bZufYoapDESHwgRkDBxD5BDIpIjAR9IOFJIPiCMRofRJ9V" +
                                "HnjRBzTxm9nbvb1tq4mJTTo3+833d77v931z5SGqcTnqdhg9lqdMpEhRpA7T" +
                                "zSlxzCFuald6cwZzlxh9FLvuCNCyese15O+P3x5vjqPaUTQf2zYTWJjMdoeI" +
                                "y+gEMdIoWab2U2K5AjWnD+MJrBWESbW06YqeNJoXEhWoK+27oIELGrigKRe0" +
                                "7WUuEHqC2AWrT0pgW7hH0Wsolka1ji7dE2h1pRIHc2yV1GRUBKChXn7vh6CU" +
                                "cJGjVUHsXswzAj7XrZ1972DzZ1UoOYqSpj0s3dHBCQFGRlGjRawxwt3thkGM" +
                                "UdRiE2IME25iak4pv0dRq2vmbSwKnASXJIkFh3Bls3xzjbqMjRd0wXgQXs4k" +
                                "1PC/anIU5yHWReVYvQh3SDoE2GCCYzyHdeKLVB8xbUOglVGJIMau54EBROss" +
                                "IsZZYKraxkBArV7uKLbz2rDgpp0H1hpWACsCLZ1TqbxrB+tHcJ5kBWqL8mW8" +
                                "I+BKqIuQIgItjLIpTZClpZEshfLzcM/W08ftATuufDaITqX/9SDUHhEaIjnC" +
                                "ia0TT7BxffpdvOjmqThCwLwwwuzx3Hjl0XMb2m/d8XiWzcKzd+ww0UVWvzjW" +
                                "dH9537otVdKNeoe5pkx+ReSq/DOlk56iA8hbFGiUhyn/8NbQVwdOfER+jaOG" +
                                "QVSrM1qwoI5adGY5JiV8J7EJx4IYgyhBbKNPnQ+iOtinTZt41L25nEvEIKqm" +
                                "ilTL1DdcUQ5UyCuqg71p55i/d7AYV/uigxCqg38Ug/9O5P3Nk4tAL2jjzCIa" +
                                "1rFt2kyD2iWY6+Ma0ZnmYsuhkDW3YOcom9RcrmuM54NvnXGijRDXJRQLPEZJ" +
                                "SpaW838oLcpImidjMbjk5VGIU0DHAKMG4Vn9bKG3/9HV7L14UPKlOxBoBZhJ" +
                                "lcykpJlU2AyKxZT2BdKclz64/CMAY2hwjeuGX9516FRHFdSNM1ktr66ocNXm" +
                                "f4BgxC2F4B1f3rpw/f3uLfEw2JOh9jlMhFc6LWW7I5wQoP94PnPm3MM3X1JG" +
                                "gaNzNgNdcu2DQoLuCF3mjTtHf/jpwcVv44GjVQI6amGMmrpA9XgM2hHWhUCJ" +
                                "oK+EA4mp/cLZ7moIapBwDx4y3BVzQVi1n4snz04bey9t8oDWWgmLfuj6n3z3" +
                                "19ep8z/fnSVPCcGcjZRMEBryLC5NQv2UrPXrbLfqboNqeOiA7bcuf3xD3O9+" +
                                "1jO5fu4ZGBW8ffKXpSPbxg/FUbxyjknrQGqQkhk5fYIpszISfFTl5d1X7u5c" +
                                "q78TR1WlJjZLw64U6glfAxjlBCaMLS9UUhrAaEe07jnTiQFjqGx3/Sp8PXvz" +
                                "1a44qoZODNNHYOgW0Njbo8YrmmePX5vSVA1cQo5xC1N55E+PBjHO2WSZogDZ" +
                                "pPYtkJ4lEgUd8N9aai/qV57Od+S6wAOw4l+m1na5rPZyK7cdcumUlbW2jARo" +
                                "hRTqTSaja59tMcPMmRKsEpN/Jtdsuv7b6WavgihQ/Oxs+HcFZfqSXnTi3sE/" +
                                "2pWamC5HcRmdZTYPpPPLmrdzjo9JP4qvf7Piwm38AUwK6M6uOUVUw0WlziCd" +
                                "2qoifkatPZGzbXJ5UlZ9qRkRH4PtMzCY4aYFA2VCxe/MUKcIG4OcLJbENaW8" +
                                "+Pn5Tzmp9D5W2Srawm5aMHRSuzG8K4pPKfUD/xB6Wi59AjXliXiRcWr0soJt" +
                                "uL7i5TMUq3N4sfSy4szoiwI1hhu6zFfbjNeg94LRr04n6xdP7/teASV4ZSRg" +
                                "1OcKlIZwGMZkrcNJzlSuJ7z6d9TPMLyIo7kSqFr+KC+HPLb9As0LsQlUV9qF" +
                                "mQ4IVAVMcjvqOEVUMWWc6MzprOhy6oXsd6SC90bO6p9O79pz/NHTl1R7q4G3" +
                                "9dSUelHBA9GDedDVVs+pzddVO7DucdO1xJp4KYNNcmkN1VHIt5Wzo7DfcoTC" +
                                "zdQXiz/f+uH0AzWq/gbHhnNaugwAAA==");
}
