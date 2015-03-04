package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public final class GIEngineFactory {
    public static final GIEngine create(Options options) { String type = options.
                                                             getString(
                                                               "gi.engine",
                                                               null);
                                                           if (type ==
                                                                 null ||
                                                                 type.
                                                                 equals(
                                                                   "null") ||
                                                                 type.
                                                                 equals(
                                                                   "none"))
                                                               return null;
                                                           else
                                                               if (type.
                                                                     equals(
                                                                       "ambocc"))
                                                                   return new AmbientOcclusionGIEngine(
                                                                     options);
                                                               else
                                                                   if (type.
                                                                         equals(
                                                                           "fake"))
                                                                       return new FakeGIEngine(
                                                                         options);
                                                                   else
                                                                       if (type.
                                                                             equals(
                                                                               "igi"))
                                                                           return new InstantGI(
                                                                             options);
                                                                       else
                                                                           if (type.
                                                                                 equals(
                                                                                   "irr-cache"))
                                                                               return new IrradianceCacheGIEngine(
                                                                                 options);
                                                                           else
                                                                               if (type.
                                                                                     equals(
                                                                                       "path"))
                                                                                   return new PathTracingGIEngine(
                                                                                     options);
                                                                               else {
                                                                                   UI.
                                                                                     printWarning(
                                                                                       Module.
                                                                                         LIGHT,
                                                                                       "Unrecognized GI engine type \"%s\" - ignoring",
                                                                                       type);
                                                                                   return null;
                                                                               }
    }
    public GIEngineFactory() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "BZ9tujz1UML438ChsYhwDQAA");
}
