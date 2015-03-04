package org.sunflow.core;
import org.sunflow.image.Color;
public interface Shader extends RenderObject {
    public Color getRadiance(ShadingState state);
    public void scatterPhoton(ShadingState state, Color power);
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1170614044000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Xa2wUVRS+++i7ZLcFWh590AIaKOxgBI2UKBVaKF1g3UKB" +
                               "Apbbmbu7Q2dnhpm7\n7bYQhJgISnwQNdFEkR8kPEQxUYMmqBBAUf+IiTEhET" +
                               "UkaqL88A9i9Ifn3ruzj+mLuMnO3rn3PO85\n5ztnz95GRbaF6mQ7RIdNYodW" +
                               "dUewZRNllYZtexNs9clXi8oiJ7t0w4s8YeRVFYoCYdmWFEyxpCpS\n5+rWtI" +
                               "VaTEMbjmsGDZE0De3WlmXkrQsvGyVwy7Hz1QdP+Bu9qCiMAljXDYqpaujtGk" +
                               "naFAXDu/Eg\nllJU1aSwatPWMJpC9FRylaHbFOvU3oP2I18YFZsyk0lRU9hR" +
                               "LoFyycQWTkpcvRThakHCVItQrOpE\nacuqA85FhZxgdoYvOpoahJSywx5wh1" +
                               "sAXs/Jei28HeWq6TvV89C+46d9KNCLAqrezYTJ4AkFfb2o\nMkmS/cSy2xSF" +
                               "KL2oSidE6SaWijV1hGvtRdW2GtcxTVnEjhLb0AYZYbWdMonFdTqbYVQpM5+s" +
                               "lEwN\nK3tHMZVoivNWFNNwHNyuybkt3O1g++BguQqGWTEsE4fFP6DqEPFGN0" +
                               "fWx3ldQACsJUlCE0ZWlV/H\nsIGqRSw1rMelbmqpehxIi4wUaKFo1rhC2V2b" +
                               "WB7AcdJH0Qw3XUQcAVUZvwjGQtF0NxmXBFGa5YpS\nXnxaau4cPvXGpyt5bv" +
                               "sVImvM/nJganAxRUmMWESXiWC8mwq90rktVedFCIinu4gFTdv885vDv33W\n" +
                               "KGhmj0GzsX83kWmfvOFoY3TvGgP5mBmlpmGrLPgFnvNyiGROWtMmVG1NViI7" +
                               "DDmHF6Ofbztwhvzu\nReWdqFg2tFQS8qhKNpKmqhFrDdGJhSlROlEZ0ZVV/L" +
                               "wTlcA6DCkvdjfGYjahnciv8a1ig7/DFcVA\nBLuiMliresxw1iamCb5Omwih" +
                               "EvgiD3zrkfiUsgdFC0OSndJjmjEk2ZYsGVY8+y4bFpG6E1ghVojl\njElRh5" +
                               "QwkkTCMtZV3ZDiKlSpbCxWyCD7vWdJaWZX9ZDHw4DOXbAa5PpaQwPaPvnkra" +
                               "/2tXc9e1gk\nA0vgjEcU1YKCUEZBiCkICQXI4+FypzFFIgxwiQNQjgBclQu6" +
                               "d67bdbjZB/E3h/zsCtK8PmY5L8Do\nMohX4t2ni5d8f6HiKjfEKdpAHix2Ey" +
                               "pSoCqnd5NFCOz/8Frk5VdvH9rOlQqtPopKcT9AA5YpRWXZ\nGqcAoal+TZXz" +
                               "rfLwdS1F9aNcjkJiEEvkLLO9fry64phwaOuflc/gKztF9lcX5mo74Pmvw5fJ" +
                               "/Sue\n/3mM6y6jhrlYI4NEy7PMy1RC4DPa2mVjPYecTt4WZCi4506/fZ5eb1" +
                               "kuVC4cvyW5GRcuPz7SuPzc\nES/yjt0qmBXQrMqZhAjrL9kW0Oi6BLfoqsHZ" +
                               "T/gS6jUvR0WGMGOgaSFTa/51gFKwJ2Xp7GLZTiUo\nbXansWXIRIEekdN7Ym" +
                               "bAtwX1HPUiP8AktAbuE6Bug1t5AbK1OgnHVJWEUUXMsJJYY0cOtJfThGUM\n" +
                               "5XZ4fQX5eiqEqYKl9hL4VmVqn/+y0+kme9aIeuT09fw5R6Spl62b2WMey677" +
                               "cqkNGKVBzrFAzNus\nJw1Fjam4XyOsyP4NzH/gwz9eCIos0mDHicyiyQXk9m" +
                               "c+jg58/eRfDVyMR2Y9MlduOTJRdVNzktss\nCw8zO9IHv61//Qv8JkA4wKat" +
                               "jhCOhJ7JiorhCLRFNhgQfjMrOO3D/NnKri4DFux9JXs8SFFFnNAo\n8LFQO6" +
                               "ILIEpNQn9kbsMoADcxTiWMMeX0yfvOxJtTe778mN9EBc4fl/IhaD02W0Xk2W" +
                               "Mpi1itGwTX\nYjsBdEsvbtgR1C7+AxJ7QaIMNtsbLYCSdAGAZaiLSm5culyz" +
                               "67oPeTtQuWZgpQOzkQYaEyQesROA\n1mnzsZU8t4JDrLME+QWhQmxlL4sLs/" +
                               "JR+NZlsrLu/2RlYWS8GVi6p9hOFiauPzJB7HvYo4uiKbaM\nKSB4JGFQiIqF" +
                               "ZuQP75aahCFgkNfyrWeaP7m2+a1DAg4XTDCh53P1yU/9+NOA78VL/YLPPQi5" +
                               "iI82\nnPjl/VvRaaL+xLQ4d9TAls8jJkbuU8BkedM0kQZOfaWl6ez+6E1uEe" +
                               "NbQJF/0FDFtLmaPcIiOGsm\nzYs0dD3Ru1klzxg1wIuhUw7f2LvjTvi7vzl8" +
                               "ZgfDCpjOYilNy0PnfKQuNi0SU7mFFaI2TP4DnTbo\nTg7wgP1w2xRBloDCzi" +
                               "OjqCSzyicaoMgHRGypmU5SBXkVsSk75LTnfJ+Zp3MLws//IzkdKyX+JfXJ\n" +
                               "W9/ZPid9ZNNLvA0Wwb+rkRE+DsN0L9pAtus1jSvNkfUNeu9cz4V3H3FCxmFi" +
                               "Wl6VFRTqanE6QQyh\n046N5+1Jk3IEHvmo9oMVJ4/d9PLJ5z+qknzf2g4AAA" +
                               "==");
}
