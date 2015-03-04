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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
